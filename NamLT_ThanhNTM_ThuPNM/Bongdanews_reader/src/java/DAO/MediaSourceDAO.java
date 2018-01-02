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
