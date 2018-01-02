/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Post;
import DTO.Tag;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author ADMIN
 */
public class TagDAO extends DBConnection {

    public TagDAO() {
    }

    /**
     *
     * @param tag An Tag object includes tagName and description
     * @return An boolean variable to specify insert action is successfully
     */
    public boolean createNewTag(Tag tag) {
        String sql = "INSERT INTO Tag(tagName,[description]) VALUES(?,?)";
        int result = 0;
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, tag.getTagName());
            pstm.setString(2, tag.getDescription());
            result = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result > 0;
    }

    /**
     *
     * @param tag An Tag object includes tagName and description
     * @return An boolean variable to specify update action is successfully
     */
    public boolean updateTag(Tag tag) {
        String sql = "UPDATE Tag SET [description]=? WHERE tagName=?";
        int result = 0;
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, tag.getDescription());
            pstm.setString(2, tag.getTagName());
            result = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result > 0;
    }

    public List<Tag> getTagByPostId(int id) {
        List<Tag> list = new ArrayList<>();
        try {
            String sql = "SELECT tagName FROM PostHasTag WHERE postId = ?";
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                Tag tmp = new Tag();
                tmp.setTagName(resultSet.getString("tagName"));
                list.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<Post> getAllPostByTag(String tagName, int numerOfPage) {
        List<Post> list = new ArrayList<>();
        try {
            String sql = "SELECT p.title, p.id, p.createdDay, p.openingParagraph, p.symbolicImage "
                    + "FROM Post p, (SELECT p.postId, p.tagName "
                    + "FROM PostHasTag p "
                    + "WHERE p.tagName = ?) t "
                    + "WHERE p.id = t.postId and p.status = ? "
                    + "ORDER BY p.createdDay DESC "
                    + "OFFSET ? ROWS FETCH NEXT 10 ROW ONLY";
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, tagName);
            pstm.setInt(2, Post.STATUS_PUBLISHED);
            pstm.setInt(3, numerOfPage);
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                Post tmp = new Post();
                tmp.setId(resultSet.getInt("id"));
                tmp.setTitle(resultSet.getString("title"));
                tmp.setCreatedDay(resultSet.getTimestamp("createdDay"));
                tmp.setOpeningParagraph(resultSet.getString("openingParagraph"));
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

    public int getTotalPostInTagName(String tagName) {
        int total = 0;
        try {
            String sql = "SELECT COUNT(postId) TOTAL FROM PostHasTag WHERE tagName = ?";
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, tagName);
            resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                total = resultSet.getInt("TOTAL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return total;
    }

    private List<Tag> getAllTag() {
        List<Tag> list = new ArrayList<>();
        try {
            String sql = "SELECT tagName FROM Tag";
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                Tag tmp = new Tag();
                tmp.setTagName(resultSet.getString("tagName"));
                list.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    public int getNumberOfPostInSearch(List<Tag> tagName) {
        int result = 0;
        try {
            String sql1 = "SELECT COUNT(postId) TOTAL "
                    + "FROM PostHasTag "
                    + "WHERE tagName = ? ";
            String sql2 = "";
            for (int i = 0; i < tagName.size() - 1; i++) {
                sql2 = sql2 + " or tagName = ? ";
            }
            String sql = sql1 + sql2;
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, tagName.get(0).getTagName());
            for (int i = 1; i < tagName.size(); i++) {
                pstm.setString(i + 1, tagName.get(i).getTagName());
            }
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

    public List<Post> getPostBySpecialTag(List<Tag> tagName, int numberOfPage) {
        List<Post> list = new ArrayList<>();
        try {
            String sql1 = "SELECT DISTINCT p.title, p.id, p.createdDay, p.openingParagraph, p.symbolicImage "
                    + "FROM Post p, (SELECT p.postId, p.tagName "
                    + "FROM PostHasTag p "
                    + "WHERE p.tagName = ? ";
            String sql2 = "";
            for (int i = 0; i < tagName.size() - 1; i++) {
                System.out.println("noi chuoi sql2");
                sql2 = sql2 + " or p.tagName = ? ";
            }
            String sql3 = " ) t WHERE p.id = t.postId and p.status = ? "
                    + "ORDER BY p.createdDay DESC "
                    + "OFFSET ? ROWS FETCH NEXT 10 ROW ONLY";;
            String sql = sql1 + sql2 + sql3;
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, tagName.get(0).getTagName());
            for (int i = 1; i < tagName.size(); i++) {
                pstm.setString(i + 1, tagName.get(i).getTagName());
            }
            pstm.setInt(tagName.size() + 1, Post.STATUS_PUBLISHED);
            pstm.setInt(tagName.size() + 2, numberOfPage);
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                Post tmp = new Post();
                tmp.setId(resultSet.getInt("id"));
                tmp.setTitle(resultSet.getString("title"));
                tmp.setCreatedDay(resultSet.getTimestamp("createdDay"));
                tmp.setOpeningParagraph(resultSet.getString("openingParagraph"));
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

    public List<Tag> searchTag(String search) {
        List<Tag> list = getAllTag();
        List<Tag> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (checkSameSequence(search, list.get(i).getTagName()) >= 60) {
                Tag tmp = new Tag();
                tmp.setTagName(list.get(i).getTagName());
                result.add(tmp);
            }
        }
        return result;
    }

    private float checkSameSequence(String input, String existed) {
        input = removeAccent(input);
        float result = 0;
        char[] first = CatChuoi(input.toLowerCase().trim());
        char[] second = CatChuoi(existed.toLowerCase().trim());
        int len = (first.length < second.length ? first.length : second.length);
        float count = 0;
        for (int i = 0; i < len; i++) {
            if (first[i] == second[i]) {
                count++;
            }
        }
        int largeLen = (first.length > second.length ? first.length : second.length);
        result = (count / largeLen) * 100;
        return result;
    }

    private char[] CatChuoi(String s) {
        char[] x = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            x[i] = s.charAt(i);
        }
        return x;
    }

    public String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        s = pattern.matcher(temp).replaceAll("");
        s = s.replace("đ", "d");
        return s;
    }
}
