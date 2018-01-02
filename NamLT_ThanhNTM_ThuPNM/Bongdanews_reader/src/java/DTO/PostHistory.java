/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Timestamp;

/**
 *
 * @author ADMIN
 */
public class PostHistory {

    public static final String ACTION_EDIT_TAG = "EDIT TAG";
    public static final String ACTION_EDIT_CATEGORY = "EDIT CATEGORY";
    public static final String ACTION_EDIT_CONTENT = "EDIT CONTENT";
    public static final String ACTION_REJECT = "REJECT";
    public static final String ACTION_PUBLISH = "PUBLISH";
    public static final String ACTION_UNPUBLISH = "UNPUBLISH";
    public static final String ACTION_DISABLE_POST = "DISABLE POST";
    public static final String ACTION_DISABLE_COMMENT = "DISABLE COMMENT";
    private int postId, userId;
    private String action, description;
    private Timestamp time;

    public PostHistory() {
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

}
