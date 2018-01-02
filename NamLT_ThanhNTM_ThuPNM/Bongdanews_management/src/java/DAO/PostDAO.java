/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Category;
import DTO.Post;
import DTO.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class PostDAO extends DBConnection {

    /**
     * A static variable to control changes of post table
     */
    private static final Object LOCK_POST_MODIFYING = new Object() {
    };
    /**
     * A static variable to control changes of allPost variable
     */
    private static final Object LOCK_GET_ALL_POST = new Object() {
    };
    /**
     * A static variable to control changes of allPost_isLastUpdated variable
     */
    private static final Object LOCK_ALL_POST_IS_LAST_UPDATED = new Object() {
    };
    /**
     * A boolean variable specifies that whether allPost variable is newest
     */
    private static boolean allPost_isLastUpdated;
    /**
     * A variable holds all post data to reduce retrieving post data from
     * database
     */
    private static List<Post> allPost;

    static {
        allPost_isLastUpdated = false;
        allPost = null;
    }

    public PostDAO() {
    }

    public static boolean isAllPost_isLastUpdated() {
        synchronized (LOCK_ALL_POST_IS_LAST_UPDATED) {
            return allPost_isLastUpdated;
        }
    }

    private void setAllPost_isLastUpdated(boolean allPost_isLastUpdated) {
        synchronized (LOCK_ALL_POST_IS_LAST_UPDATED) {
            PostDAO.allPost_isLastUpdated = allPost_isLastUpdated;
        }
    }

    public List<Post> getAllPost() {
        synchronized (LOCK_GET_ALL_POST) {
            if (!isAllPost_isLastUpdated()) {
                //if all post variable is not newest
                //retrieve data of all post from database
                allPost = getAllPostsInDB();
            }
            return allPost;
        }
    }

    private void setAllPost(List<Post> allPost) {
        synchronized (LOCK_GET_ALL_POST) {
            //set the status of all post variable as true
            setAllPost_isLastUpdated(true);
            PostDAO.allPost = allPost;
        }
    }

    /**
     *
     * @param authorId id of the author of the new post
     * @return auto-generated primary key of new post
     */
    public int createNewPost(int authorId) {
        synchronized (LOCK_POST_MODIFYING) {
            String sql = "INSERT INTO Post(title,createdDay,viewNumber,authorId,status) VALUES(?,?,?,?,?)";
            try {
                connection = getConnection();
                //this statement will return a generated key
                pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                pstm.setString(1, "New post");
                pstm.setTimestamp(2, currentTime);
                pstm.setInt(3, 0);
                pstm.setInt(4, authorId);
                pstm.setInt(5, Post.STATUS_DRAFT);
                int result = pstm.executeUpdate();
                if (result == 0) {
                    throw new SQLException("Creating post failed, no rows affected.");
                }

                //return generated key
                ResultSet generatedKeys = pstm.getGeneratedKeys();
                if (generatedKeys.next()) {
                    //create post successfully
                    //set the status of all post variable as false        
                    setAllPost_isLastUpdated(false);
                    return generatedKeys.getInt(1);
                } else {
                    //create post fail
                    throw new SQLException("Creating post failed, no ID obtained.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
            return -1;
        }
    }

    /**
     *
     * @param post must contain title, post's content, opening paragraph and id
     * @return a boolean variable to specify whether update action is
     * successfully
     */
    public boolean updatePost(Post post) {
        synchronized (LOCK_POST_MODIFYING) {
            String sql = "UPDATE Post SET title=?, postContent=?, openingParagraph=? WHERE id=?";
            try {
                connection = getConnection();
                //this statement will return a generated key
                pstm = connection.prepareStatement(sql);
                pstm.setString(1, post.getTitle());
                pstm.setString(2, post.getPostContent());
                pstm.setString(3, post.getOpeningParagraph());
                pstm.setInt(4, post.getId());
                int result = pstm.executeUpdate();
                if (result == 0) {
                    throw new SQLException("Update post failed, no rows affected.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                closeConnection();
            }
            //update post successfully
            //set the status of all post variable as false        
            setAllPost_isLastUpdated(false);
            return true;
        }
    }

    /**
     *
     * @return a list of all post in DB which include id, title, postContent,
     * openingParagraph, authorId, status, viewNumber, categoryId
     */
    public List<Post> getAllPostsInDB() {
        String sql = "SELECT id,title,postContent,symbolicImage,openingParagraph,authorId,[status],viewNumber,categoryId FROM Post";
        List<Post> result = null;
        try {
            connection = getConnection();
            //this statement will return a generated key
            pstm = connection.prepareStatement(sql);
            resultSet = pstm.executeQuery();
            result = new ArrayList<>();
            Post tmp = null;
            User author = null;
            Category category = null;
            while (resultSet.next()) {
                tmp = new Post();
                tmp.setId(resultSet.getInt("id"));
                tmp.setTitle(resultSet.getString("title"));
                tmp.setPostContent(resultSet.getString("postContent"));
                tmp.setSymbolicImage(resultSet.getInt("symbolicImage"));
                tmp.setOpeningParagraph(resultSet.getString("openingParagraph"));
                author = new User();
                author.setId(resultSet.getInt("authorId"));
                tmp.setAuthor(author);
                category = new Category();
                category.setId(resultSet.getInt("categoryId"));
                tmp.setCategory(category);
                tmp.setViewNumber(resultSet.getInt("viewNumber"));
                tmp.setStatus(resultSet.getInt("status"));
                result.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<Post> getAllPostsInfo() {
        String sql = "SELECT Post.id,Post.title,Post.openingParagraph,Post.symbolicImage,Post.createdDay,Post.authorId,[User].name AS 'authorName',Post.[status],Post.viewNumber,Post.categoryId,Category.categoryName "
                + "FROM Post "
                + "LEFT JOIN [User] ON Post.authorId = [User].id "
                + "LEFT JOIN Category ON Post.categoryId = Category.id";
        List<Post> result = null;
        try {
            connection = getConnection();
            //this statement will return a generated key
            pstm = connection.prepareStatement(sql);
            resultSet = pstm.executeQuery();
            result = new ArrayList<>();
            Post tmp = null;
            User author = null;
            Category category = null;
            while (resultSet.next()) {
                tmp = new Post();
                tmp.setId(resultSet.getInt("id"));
                tmp.setTitle(resultSet.getString("title"));
                tmp.setOpeningParagraph(resultSet.getString("openingParagraph"));
                tmp.setSymbolicImage(resultSet.getInt("symbolicImage"));
                tmp.setCreatedDay(resultSet.getTimestamp("createdDay"));
                author = new User();
                author.setId(resultSet.getInt("authorId"));
                author.setName(resultSet.getString("authorName"));
                tmp.setAuthor(author);
                category = new Category();
                category.setId(resultSet.getInt("categoryId"));
                category.setCategoryName(resultSet.getString("categoryName"));
                tmp.setCategory(category);
                tmp.setViewNumber(resultSet.getInt("viewNumber"));
                tmp.setStatus(resultSet.getInt("status"));
                result.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<Post> getMyPostsInfo(int authorId) {
        String sql = "SELECT a.*, [User].name AS 'authorName', Category.categoryName "
                + "FROM (SELECT id,title,openingParagraph,authorId,[status],viewNumber,categoryId,createdDay FROM Post WHERE authorId=?) a "
                + "LEFT JOIN [User] ON a.authorId = [User].id "
                + "LEFT JOIN Category ON a.categoryId = Category.id";
        List<Post> result = null;
        try {
            connection = getConnection();
            //this statement will return a generated key
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, authorId);
            resultSet = pstm.executeQuery();
            result = new ArrayList<>();
            Post tmp = null;
            User author = null;
            Category category = null;
            while (resultSet.next()) {
                tmp = new Post();
                tmp.setId(resultSet.getInt("id"));
                tmp.setTitle(resultSet.getString("title"));
                tmp.setOpeningParagraph(resultSet.getString("openingParagraph"));
                author = new User();
                author.setId(resultSet.getInt("authorId"));
                author.setName(resultSet.getString("authorName"));
                tmp.setAuthor(author);
                category = new Category();
                category.setId(resultSet.getInt("categoryId"));
                category.setCategoryName(resultSet.getString("categoryName"));
                tmp.setCategory(category);
                tmp.setViewNumber(resultSet.getInt("viewNumber"));
                tmp.setCreatedDay(resultSet.getTimestamp("createdDay"));
                tmp.setStatus(resultSet.getInt("status"));
                result.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    /**
     *
     * @param id the id of the Post to return
     * @return a list of Post with 1 element include id, title, postContent,
     * openingParagraph, authorId, status, viewNumber, categoryId, createdDay
     */
    public List<Post> getById(int id) {
        String sql = "SELECT id,title,postContent,symbolicImage,openingParagraph, authorId,[status],viewNumber,categoryId,createdDay FROM Post WHERE id=?";
        List<Post> result = null;
        try {
            connection = getConnection();
            //this statement will return a generated key
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            resultSet = pstm.executeQuery();
            result = new ArrayList<>();
            Post tmp = null;
            User author = null;
            Category category = null;
            while (resultSet.next()) {
                tmp = new Post();
                tmp.setId(resultSet.getInt("id"));
                tmp.setTitle(resultSet.getString("title"));
                tmp.setCreatedDay(resultSet.getTimestamp("createdDay"));
                tmp.setOpeningParagraph(resultSet.getString("openingParagraph"));
                tmp.setPostContent(resultSet.getString("postContent"));
                tmp.setSymbolicImage(resultSet.getInt("symbolicImage"));
                author = new User();
                author.setId(resultSet.getInt("authorId"));
                tmp.setAuthor(author);
                category = new Category();
                category.setId(resultSet.getInt("categoryId"));
                tmp.setCategory(category);
                tmp.setViewNumber(resultSet.getInt("viewNumber"));
                tmp.setStatus(resultSet.getInt("status"));
                result.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean changeSymbolImage(int postId, int imageId) {
        try {
            String sql = "UPDATE Post SET symbolicImage=? WHERE id=?";
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, imageId);
            pstm.setInt(2, postId);
            return pstm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean setStatus(int id, int status) {
        try {
            String SQL = "UPDATE Post SET [status]=? WHERE id=?";
            connection = getConnection();
            pstm = connection.prepareStatement(SQL);
            pstm.setInt(1, status);
            pstm.setInt(2, id);
            int rs = pstm.executeUpdate();
            return rs > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public List<User> getTopTenUserHasMostPost(Timestamp started, Timestamp end) {
        List<User> result = null;
        try {
            String SQL = "SELECT TOP 10 [User].*,a.quantity FROM "
                    + "(SELECT authorId,COUNT(id) AS 'quantity' FROM Post "
                    + "WHERE createdDay > ? AND createdDay < ? GROUP BY authorId) a "
                    + "LEFT JOIN [User] ON [User].id = a.authorId "
                    + "ORDER BY a.quantity DESC";
            connection = getConnection();
            pstm = connection.prepareStatement(SQL);
            pstm.setTimestamp(1, started);
            pstm.setTimestamp(2, end);
            resultSet = pstm.executeQuery();
            result = new ArrayList<>();
            User tmp = null;
            while (resultSet.next()) {                
                tmp = new User();
                tmp.setId(resultSet.getInt("id"));
                tmp.setMail(resultSet.getString("mail"));
                tmp.setSex(resultSet.getString("sex"));
                tmp.setName(resultSet.getString("name"));
                tmp.setBirthday(resultSet.getTimestamp("birthday"));
                tmp.setStatus(resultSet.getInt("status"));
                tmp.setPostQuantity(resultSet.getInt("quantity"));
                result.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return result;
    }
}
