/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Timestamp;

/**
 *
 * @author Thanh Nguyen
 */
public class MediaSource {

    public static final int STATUS_DISABLE = 0;
    public static final int STATUS_ACTIVED = 1;
    private String type, path;
    int id, postID, status;
    Timestamp uploadedTime;

    public MediaSource() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getUploadedTime() {
        return uploadedTime;
    }

    public void setUploadedTime(Timestamp uploadedTime) {
        this.uploadedTime = uploadedTime;
    }

}
