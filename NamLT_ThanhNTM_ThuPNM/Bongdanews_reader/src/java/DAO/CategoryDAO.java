/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Category;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CategoryDAO extends DBConnection {

    public CategoryDAO() {
    }


    /**
     *
     * @param id The id of category need to be find
     * @return A list of category with 1 element include id, name, description,
     * status, if of the owner post
     */
    
    
    public List<Category> getCategoryById(int id) {
        String sql = "SELECT a.id,a.ownedBy, b.id AS 'motherName',a.categoryName,a.postId,a.[description],a.[status] "
                + "FROM(SELECT id,ownedBy,categoryName,postId,[description],[status] FROM Category WHERE id=?) a "
                + "LEFT JOIN Category b ON a.ownedBy = b.id";
        List<Category> result = null;
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            resultSet = pstm.executeQuery();
            Category tmp = null;
            result = new ArrayList<>();
            while (resultSet.next()) {
                tmp = new Category();
                tmp.setId(resultSet.getInt("id"));
                tmp.setCategoryName(resultSet.getString("categoryName"));
                tmp.setDescription(resultSet.getString("description"));
                Category motherCategory = new Category();
                motherCategory.setId(resultSet.getInt("ownedBy"));
                motherCategory.setCategoryName(resultSet.getString("motherName"));
                tmp.setMotherCategory(motherCategory);
                tmp.setPostID(resultSet.getInt("postId"));
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

    public List<Category> getCategoryListInMenuBar() {
        String sql = "SELECT a.id,a.ownedBy, b.id AS 'motherName',a.categoryName,a.postId,a.[description],a.[status] "
                + "FROM(SELECT id,ownedBy,categoryName,postId,[description],[status] FROM Category WHERE ownedBy IS NULL AND [status]>?) a "
                + "LEFT JOIN Category b ON a.ownedBy = b.id ORDER BY [status] ASC";
        List<Category> result = null;
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, Category.STATUS_HIDDEN);
            resultSet = pstm.executeQuery();
            Category tmp = null;
            result = new ArrayList<>();
            while (resultSet.next()) {
                tmp = new Category();
                tmp.setId(resultSet.getInt("id"));
                tmp.setCategoryName(resultSet.getString("categoryName"));
                tmp.setDescription(resultSet.getString("description"));
                Category motherCategory = new Category();
                motherCategory.setId(resultSet.getInt("ownedBy"));
                motherCategory.setCategoryName(resultSet.getString("motherName"));
                tmp.setMotherCategory(motherCategory);
                tmp.setPostID(resultSet.getInt("postId"));
                tmp.setStatus(resultSet.getInt("status"));
                result.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        if (result != null) {
            for (Category category : result) {
                category.getChildList();
            }
        }
        return result;
    }

    public List<Category> getCategoryListNotInMenuBar() {
        String sql = "SELECT a.id,a.ownedBy, b.id AS 'motherName',a.categoryName,a.postId,a.[description],a.[status] "
                + "FROM(SELECT id,ownedBy,categoryName,postId,[description],[status] FROM Category WHERE ownedBy IS NULL AND [status]=?) a "
                + "LEFT JOIN Category b ON a.ownedBy = b.id";
        List<Category> result = null;
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, Category.STATUS_HIDDEN);
            resultSet = pstm.executeQuery();
            Category tmp = null;
            result = new ArrayList<>();
            while (resultSet.next()) {
                tmp = new Category();
                tmp.setId(resultSet.getInt("id"));
                tmp.setCategoryName(resultSet.getString("categoryName"));
                tmp.setDescription(resultSet.getString("description"));
                Category motherCategory = new Category();
                motherCategory.setId(resultSet.getInt("ownedBy"));
                motherCategory.setCategoryName(resultSet.getString("motherName"));
                tmp.setMotherCategory(motherCategory);
                tmp.setPostID(resultSet.getInt("postId"));
                tmp.setStatus(resultSet.getInt("status"));
                result.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        if (result != null) {
            for (Category category : result) {
                category.getChildList();
            }
        }
        return result;
    }

    public List<Category> getChildCategoryList(int motherId) {
        String sql = "SELECT a.id,a.ownedBy, b.id AS 'motherName',a.categoryName,a.postId,a.[description],a.[status] "
                + "FROM(SELECT id,ownedBy,categoryName,postId,[description],[status] FROM Category WHERE ownedBy=?) a "
                + "LEFT JOIN Category b ON a.ownedBy = b.id ORDER BY [status] ASC";
        List<Category> result = null;
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, motherId);
            resultSet = pstm.executeQuery();
            Category tmp = null;
            result = new ArrayList<>();
            while (resultSet.next()) {
                tmp = new Category();
                tmp.setId(resultSet.getInt("id"));
                tmp.setCategoryName(resultSet.getString("categoryName"));
                tmp.setDescription(resultSet.getString("description"));
                Category motherCategory = new Category();
                motherCategory.setId(resultSet.getInt("ownedBy"));
                motherCategory.setCategoryName(resultSet.getString("motherName"));
                tmp.setMotherCategory(motherCategory);
                tmp.setPostID(resultSet.getInt("postId"));
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

    /**
     *
     * @return A list of category with 1 element include id, name, description,
     * status, if of the owner post
     */
    public List<Category> getAllCategory() {
        String sql = "SELECT a.id,a.ownedBy, b.id AS 'motherName',a.categoryName,a.postId,a.[description],a.[status] "
                + "FROM Category a LEFT JOIN Category b ON a.ownedBy = b.id";
        List<Category> result = null;
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            resultSet = pstm.executeQuery();
            Category tmp = null;
            result = new ArrayList<>();
            while (resultSet.next()) {
                tmp = new Category();
                tmp.setId(resultSet.getInt("id"));
                tmp.setCategoryName(resultSet.getString("categoryName"));
                tmp.setDescription(resultSet.getString("description"));
                Category motherCategory = new Category();
                motherCategory.setId(resultSet.getInt("ownedBy"));
                motherCategory.setCategoryName(resultSet.getString("motherName"));
                tmp.setMotherCategory(motherCategory);
                tmp.setPostID(resultSet.getInt("postId"));
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

    
    public List<Category> getOwnedCategory() {
        List<Category> list = new ArrayList<>();
        try {
            String sql = "SELECT id, categoryName FROM Category WHERE ownedBy is Null and status > 0";
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                Category tmp = new Category();
                tmp.setId(resultSet.getInt("id"));
                tmp.setCategoryName(resultSet.getString("categoryName"));
                list.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public String getCategoryName(int id) {
        String result = "";
        try {
            String sql = "SELECT categoryName "
                    + "FROM Category "
                    + "WHERE id = ?";
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("categoryName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }
}
