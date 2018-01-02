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

    /**
     *
     * @param id the id of the Post to return
     * @return a list of Post with 1 element include id, title, postContent,
     * openingParagraph, authorId, status, viewNumber, categoryId, createdDay
     */
    public List<Post> getById(int id) {
        String sql = "SELECT a.*, [User].name AS 'authorName', Category.categoryName "
                + "FROM (SELECT *  FROM Post WHERE id=? AND [status]=?) a "
                + "LEFT JOIN [User] ON a.authorId = [User].id "
                + "LEFT JOIN Category ON a.categoryId = Category.id ";
        List<Post> result = null;
        updateViewNumber(id);
        try {
            connection = getConnection();
            //this statement will return a generated key
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.setInt(2, Post.STATUS_PUBLISHED);
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
    
    private void updateViewNumber(int id) {
        String sql = "UPDATE Post SET viewNumber = viewNumber + 1 WHERE id = ?";
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public List<Post> getTopFive(int id) {
        List<Post> list = new ArrayList<>();
        try {
            String sql = "SELECT TOP 5 p.title, p.createdDay, p.openingParagraph, p.symbolicImage, p.id "
                    + "FROM Post p, (SELECT c.id "
                                + "FROM Category c "
                                + "WHERE c.ownedBy = ? or c.id = ? ) c "
                    + "WHERE p.categoryId = c.id and p.status = ? "
                    + "ORDER BY p.createdDay DESC";
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.setInt(2, id);
            pstm.setInt(3, Post.STATUS_PUBLISHED);
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                Post tmp = new Post();
                tmp.setTitle(resultSet.getString("title"));
                tmp.setCreatedDay(resultSet.getTimestamp("createdDay"));
                tmp.setOpeningParagraph(resultSet.getString("openingParagraph"));
                tmp.setSymbolicImage(resultSet.getInt("symbolicImage"));
                tmp.setId(resultSet.getInt("id"));
                list.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<Post> getTopNine(int categoryId, int pageNumber) {
        List<Post> list = new ArrayList<>();
        try {
            String sql = "SELECT p.title, p.createdDay, p.openingParagraph, p.symbolicImage, p.id "
                    + "FROM Post p "
                    + "WHERE p.categoryId = ? and p.status = 4"
                    + "ORDER BY p.createdDay DESC "
                    + "OFFSET ? ROWS FETCH NEXT 9 ROWS ONLY";
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, categoryId);
            pstm.setInt(2, pageNumber);
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                Post tmp = new Post();
                tmp.setTitle(resultSet.getString("title"));
                tmp.setCreatedDay(resultSet.getTimestamp("createdDay"));
                tmp.setOpeningParagraph(resultSet.getString("openingParagraph"));
                tmp.setSymbolicImage(resultSet.getInt("symbolicImage"));
                tmp.setId(resultSet.getInt("id"));
                list.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    public int getTotalPostInCategory(int categoryId) {
        int result = 0;
        try {
            String sql = "SELECT COUNT(p.id) TOTAL FROM Post p WHERE p.categoryId = ? and p.status = ? ";
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, categoryId);
            pstm.setInt(2, Post.STATUS_PUBLISHED);
            resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("TOTAL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public List<Post> getLastTenPost() {
        List<Post> list = new ArrayList<>();
        try {
            String sql = "SELECT TOP 10 p.id, p.title, p.symbolicImage FROM Post p "
                    + "WHERE p.status = ? "
                    + "ORDER BY p.createdDay DESC";
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, Post.STATUS_PUBLISHED);
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                Post tmp = new Post();
                tmp.setId(resultSet.getInt("id"));
                tmp.setTitle(resultSet.getString("title"));
                tmp.setSymbolicImage(resultSet.getInt("symbolicImage"));
                list.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public List<Post> getTenPopularPost() {
        List<Post> list = new ArrayList<>();
        try {
            String sql = "WITH p AS ( SELECT TOP 20 * "
                                + "FROM Post "
                                + "WHERE status = ? "
                                + "ORDER BY createdDay DESC ) "
                    + "SELECT TOP 10 p.id, p.title, p.symbolicImage FROM p "
                    + "ORDER BY p.viewNumber DESC";
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, Post.STATUS_PUBLISHED);
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                Post tmp = new Post();
                tmp.setId(resultSet.getInt("id"));
                tmp.setTitle(resultSet.getString("title"));
                tmp.setSymbolicImage(resultSet.getInt("symbolicImage"));
                list.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }
}
