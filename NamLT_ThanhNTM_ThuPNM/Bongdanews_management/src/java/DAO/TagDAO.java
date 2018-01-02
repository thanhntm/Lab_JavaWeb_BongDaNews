/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
    public boolean createNewTag(List<String> list) {
        String sql = "INSERT INTO Tag(tagName) VALUES(?)";
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            for (String tag : list) {
                pstm.setString(1, tag.trim());
                pstm.addBatch();
            }
            pstm.executeBatch();
        } catch (Exception e) {
            if (!e.getMessage().contains("PRIMARY KEY")) {
                e.printStackTrace();
            }
        } finally {
            closeConnection();
        }
        return true;
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

    public boolean mapTagWithPost(int postId, String tagName) {
        try {
            String SQL = "INSERT INTO PostHasTag(postId,tagName) VALUES(?,?)";
            connection = getConnection();
            pstm = connection.prepareStatement(SQL);
            pstm.setInt(1, postId);
            pstm.setString(2, tagName);
            int rs = pstm.executeUpdate();
            return rs > 0;
        } catch (Exception e) {
            if (!e.getMessage().contains("PRIMARY KEY")) {
                e.printStackTrace();
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean unmapTagWithPost(int postId, String tagName) {
        try {
            String SQL = "DELETE FROM PostHasTag WHERE postId=? AND tagName=?";
            connection = getConnection();
            pstm = connection.prepareStatement(SQL);
            pstm.setInt(1, postId);
            pstm.setString(2, tagName);
            int rs = pstm.executeUpdate();
            return rs > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public List<Tag> getAllTagOfPost(int postId) {
        List<Tag> rs = null;
        try {
            String SQL = "SELECT * FROM PostHasTag WHERE postId=?";
            connection = getConnection();
            pstm = connection.prepareStatement(SQL);
            pstm.setInt(1, postId);
            resultSet = pstm.executeQuery();
            rs = new ArrayList<>();
            Tag tag = null;
            while (resultSet.next()) {
                tag = new Tag();
                tag.setTagName(resultSet.getString("tagName").trim());
                rs.add(tag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return rs;
    }

    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        s = pattern.matcher(temp).replaceAll("");
        s = s.replace("đ", "d");
        return s;
    }
}
