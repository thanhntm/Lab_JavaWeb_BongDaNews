/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.MediaSource;
import DTO.Post;
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
public class MediaSourceDAO extends DBConnection {

    public MediaSourceDAO() {
    }

    /**
     *
     * @param postId
     * @param type
     * @return the auto-generated primary key of the new MediaSource
     */
    public int createNewMediaSource(String postId, String type) {
        String sql = "INSERT INTO MediaSource(postId,[type],uploadedTime,[status]) VALUES(?,?,?,?)";
        String changePathSql = "UPDATE MediaSource SET [path]=? WHERE id=?";
        try {
            connection = getConnection();
            //this statement will return a generated key
            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            pstm.setInt(1, Integer.parseInt(postId));
            pstm.setString(2, type);
            pstm.setTimestamp(3, currentTime);
            pstm.setInt(4, MediaSource.STATUS_ACTIVED);
            int result = pstm.executeUpdate();
            if (result == 0) {
                throw new SQLException("Creating source failed, no rows affected.");
            }

            //return generated key
            ResultSet generatedKeys = pstm.getGeneratedKeys();
            if (generatedKeys.next()) {
                //create media successfully
                //set the status of all post variable as false        
                int key = (int) generatedKeys.getLong(1);
                pstm = connection.prepareStatement(changePathSql);
                String path = "";
                //generate absolute path in server
                type = (type != null && type != "") ? '.' + type : "";
                postId = (postId != null && postId != "") ? postId + '/' : "";
                path = postId + key + type;
                pstm.setString(1, path);
                pstm.setInt(2, key);
                result = pstm.executeUpdate();
                if (result == 0) {
                    throw new SQLException("Update path fail.");
                }
                return key;
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

    public boolean disableMediaSource(int id) {
        String sql = "UPDATE MediaSource SET [status]=? WHERE id=?";
        int result = 0;
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, MediaSource.STATUS_DISABLE);
            pstm.setInt(2, id);
            result = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result == 0;
    }

    public List<MediaSource> getMediaSourceByPost(int postId) {
        String sql = "SELECT id,[type],[path],[status] FROM MediaSource WHERE [status]=? AND postId=?";
        List<MediaSource> result = null;
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, MediaSource.STATUS_ACTIVED);
            pstm.setInt(2, postId);
            resultSet = pstm.executeQuery();
            result = new ArrayList<>();
            MediaSource tmp = null;
            while (resultSet.next()) {
                tmp = new MediaSource();
                tmp.setId(resultSet.getInt("id"));
                tmp.setType(resultSet.getString("type"));
                tmp.setPath(resultSet.getString("path"));
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

    public List<MediaSource> getMediaSource(int id) {
        String sql = "SELECT id,[type],[path],[status] FROM MediaSource WHERE [status]=? AND id=?";
        List<MediaSource> result = null;
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, MediaSource.STATUS_ACTIVED);
            pstm.setInt(2, id);
            resultSet = pstm.executeQuery();
            result = new ArrayList<>();
            MediaSource tmp = null;
            while (resultSet.next()) {
                tmp = new MediaSource();
                tmp.setId(resultSet.getInt("id"));
                tmp.setType(resultSet.getString("type"));
                tmp.setPath(resultSet.getString("path"));
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
}
