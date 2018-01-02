/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.CommentDAO;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Thanh Nguyen
 */
public class Comment {

    public static final int STATUS_DISABLED = 0;
    public static final int STATUS_ACTIVED = 1;
    private String name, comment;
    private int id, postID, responeTo, status;
    private Timestamp postedDay;
    private List<Comment> responseList;

    public Comment() {
    }

    public List<Comment> getResponseList() {
        CommentDAO dao = new CommentDAO();
        responseList = dao.getResponseComment(id);
        return responseList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getResponeTo() {
        return responeTo;
    }

    public void setResponeTo(int responeTo) {
        this.responeTo = responeTo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getPostedDay() {
        return postedDay;
    }

    public void setPostedDay(Timestamp postedDay) {
        this.postedDay = postedDay;
    }

}
