/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Post {

    public static final int STATUS_DISABLE = 0;
    public static final int STATUS_REJECTED = 1;
    public static final int STATUS_DRAFT = 2;
    public static final int STATUS_UNPUBLISHED = 3;
    public static final int STATUS_PUBLISHED = 4;
    private int id, symbolicImage, viewNumber, status;
    private String title, postContent, openingParagraph;
    private Timestamp createdDay;
    User author;
    Category category;

    public Post() {
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSymbolicImage() {
        return symbolicImage;
    }

    public void setSymbolicImage(int symbolicImage) {
        this.symbolicImage = symbolicImage;
    }

    public int getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(int viewNumber) {
        this.viewNumber = viewNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getOpeningParagraph() {
        return openingParagraph;
    }

    public void setOpeningParagraph(String openingParagraph) {
        this.openingParagraph = openingParagraph;
    }

    public Timestamp getCreatedDay() {
        return createdDay;
    }

    public void setCreatedDay(Timestamp createdDay) {
        this.createdDay = createdDay;
    }


}
