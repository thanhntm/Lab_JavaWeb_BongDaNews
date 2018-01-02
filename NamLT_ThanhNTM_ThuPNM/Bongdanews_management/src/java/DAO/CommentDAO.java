/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Comment;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CommentDAO extends DBConnection {

    public CommentDAO() {
    }

    /**
     *
     * @param comment An Comment object includes id, name, comment, posted day,
     * and id of the comment to response
     * @return
     */
    public int createNewComment(Comment comment) {
        String sql = "INSERT INTO Comment(postId,responeTo,name,comment,postedDay,[status]) VALUES(?,?,?,?,?,?)";
        int result = -1;
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            pstm.setInt(1, comment.getPostID());
            if (comment.getResponeTo() != -1) {
                pstm.setInt(2, comment.getResponeTo());
            } else {
                pstm.setNull(2, Types.INTEGER);
            }
            pstm.setString(3, comment.getName());
            pstm.setString(4, comment.getComment());
            pstm.setTimestamp(5, timestamp);
            pstm.setInt(6, Comment.STATUS_ACTIVED);
            pstm.executeUpdate();
            resultSet = pstm.getGeneratedKeys();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            } else {
                //create comment fail
                throw new SQLException("Creating comment failed, no ID obtained.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<Comment> getCommentOfPost(int postId) {
        List<Comment> result = null;
        try {
            String SQL = "SELECT * FROM Comment WHERE postId=? AND responeTo IS NULL";
            connection = getConnection();
            pstm = connection.prepareStatement(SQL);
            pstm.setInt(1, postId);
            resultSet = pstm.executeQuery();
            result = new ArrayList<>();
            Comment tmp = null;
            while (resultSet.next()) {
                tmp = new Comment();
                tmp.setId(resultSet.getInt("id"));
                tmp.setName(resultSet.getString("name"));
                tmp.setComment(resultSet.getString("comment"));
                tmp.setPostedDay(resultSet.getTimestamp("postedDay"));
                tmp.setStatus(resultSet.getInt("status"));
                result.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        if (result != null) {
            for (Comment comment : result) {
                comment.getResponseList();
            }
        }
        return result;
    }

    public List<Comment> getResponseComment(int cmtId) {
        List<Comment> result = null;
        try {
            String SQL = "SELECT * FROM Comment WHERE responeTo=?";
            connection = getConnection();
            pstm = connection.prepareStatement(SQL);
            pstm.setInt(1, cmtId);
            resultSet = pstm.executeQuery();
            result = new ArrayList<>();
            Comment tmp = null;
            while (resultSet.next()) {
                tmp = new Comment();
                tmp.setId(resultSet.getInt("id"));
                tmp.setName(resultSet.getString("name"));
                tmp.setResponeTo(resultSet.getInt("responeTo"));
                tmp.setComment(resultSet.getString("comment"));
                tmp.setPostedDay(resultSet.getTimestamp("postedDay"));
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

    private boolean setCommentStatus(int commentId, int status) {
        String sql = "UPDATE Comment SET [status]=? WHERE id=?";
        int result = 0;
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, status);
            pstm.setInt(2, commentId);
            result = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result > 0;
    }

    public boolean disableComment(int commentId) {
        return setCommentStatus(commentId, Comment.STATUS_DISABLED);
    }

    public boolean activeComment(int commentId) {
        return setCommentStatus(commentId, Comment.STATUS_ACTIVED);
    }
}
