/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.CategoryDAO;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 *
 * @author Thanh Nguyen
 */
public class Category {

    public static final int STATUS_DISABLE = -1;
    public static final int STATUS_HIDDEN = 0;

    private String categoryName, description;
    private int id, postID, status;
    private Category motherCategory;
    @SerializedName("children")
    List<Category> childList;

    public Category() {
    }

    public List<Category> getChildList() {
        if (childList == null) {
            CategoryDAO dao = new CategoryDAO();
            childList = dao.getChildCategoryList(id);
        }
        for (Category category : childList) {
            category.getChildList();
        }
        return childList;
    }

    public void setChildList(List<Category> childList) {
        this.childList = childList;
    }

    public Category getMotherCategory() {
        return motherCategory;
    }

    public void setMotherCategory(Category motherCategory) {
        this.motherCategory = motherCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        String result = "";
        result += "id:" + id + ", name:" + categoryName + "\n";
        if (childList != null) {
            for (Category category : childList) {
                result += category.toString();
            }
        }
        return result;
    }

}
