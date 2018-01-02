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
     * @param category A category object includes name, description
     * @return the auto-generated primary key of new category
     */
    public int createNewCategory(String name, String description) {
        String sql = "INSERT INTO Category(categoryName,[description],[status]) VALUES(?,?,?)";
        int result = -1;
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, name);
            pstm.setString(2, description);
            pstm.setInt(3, Category.STATUS_HIDDEN);
            pstm.executeUpdate();
            resultSet = pstm.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                //create category fail
                throw new SQLException("Creating category failed, no ID obtained.");
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
     * @param category A category object includes id, name, description
     * @return A boolean variable to specify whether update action is
     * successfully
     */
    public boolean updateCategory(Category category) {
        String sql = "UPDATE Category SET categoryName=?,[description]=? WHERE id=?";
        int result = 0;
        try {
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, category.getCategoryName());
            pstm.setString(2, category.getDescription());
            pstm.setInt(3, category.getId());
            result = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result > 0;
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

    private boolean hideCategories(List<Category> list) {
        boolean result = true;
        try {
            connection = getConnection();
            String sql = "UPDATE Category SET [status]=? WHERE id=?";
            pstm = connection.prepareStatement(sql);
            for (Category category : list) {
                pstm.setInt(1, Category.STATUS_HIDDEN);
                pstm.setInt(2, category.getId());
                pstm.addBatch();
            }
            pstm.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
        for (Category category : list) {
            if (category.getChildList() != null) {
                result = hideCategories(category.getChildList());
                if (!result) {
                    return result;
                }
            }
        }
        return true;
    }

    private boolean setShowCategoriesStatus(List<Category> list) {
        boolean result = true;
        try {
            connection = getConnection();
            String sql = "UPDATE Category SET [status]=? WHERE id=?";
            pstm = connection.prepareStatement(sql);
            for (int i = 0; i < list.size(); i++) {
                pstm.setInt(1, i + 1);
                pstm.setInt(2, list.get(i).getId());
                pstm.addBatch();
            }
            pstm.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
        for (Category category : list) {
            if (category.getChildList() != null) {
                result = setShowCategoriesStatus(category.getChildList());
                if (!result) {
                    return result;
                }
            }
        }
        return true;
    }

    private boolean setMotherCategory(List<Category> list, int motherId) {
        boolean result = true;
        try {
            connection = getConnection();
            String sql = "UPDATE Category SET ownedBy=? WHERE id=?";
            pstm = connection.prepareStatement(sql);
            for (Category category : list) {
                pstm.setInt(1, motherId);
                pstm.setInt(2, category.getId());
                pstm.addBatch();
            }
            pstm.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
        for (Category category : list) {
            if (category.getChildList() != null) {
                result = setMotherCategory(category.getChildList(), category.getId());
                if (!result) {
                    return result;
                }
            }
        }
        return true;
    }

    private boolean beMotherCategory(List<Category> list) {
        boolean result = true;
        try {
            connection = getConnection();
            String sql = "UPDATE Category SET ownedBy=NULL WHERE id=?";
            pstm = connection.prepareStatement(sql);
            for (Category category : list) {
                pstm.setInt(1, category.getId());
                pstm.addBatch();
            }
            pstm.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
        return true;
    }

    public boolean updateCategoryOrder(List<Category> menuBarList, List<Category> hiddenList) {
        //set all first category in 2 list to be mother
        if ((beMotherCategory(menuBarList) && beMotherCategory(hiddenList)) == false) {
            return false;
        }
        //set mother of children in 2 list
        for (Category category : menuBarList) {
            if (category.getChildList() != null) {
                if (!setMotherCategory(category.getChildList(), category.getId())) {
                    return false;
                }
            }
        }
        for (Category category : hiddenList) {
            if (category.getChildList() != null) {
                if (!setMotherCategory(category.getChildList(), category.getId())) {
                    return false;
                }
            }
        }
        //set status of all category in hidden list As 0
        if (!hideCategories(hiddenList)) {
            return false;
        }
        //set the order to show of Menu Bar list
        if (!setShowCategoriesStatus(menuBarList)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return A list of category with 1 element include id, name, description,
     * status, if of the owner post
     */
    public List<Category> getAllCategory() {
        String sql = "SELECT a.id,a.ownedBy, b.categoryName AS 'motherName',a.categoryName,a.postId,a.[description],a.[status] "
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

    public boolean setCategory(int postId, int categoryId) {
        try {
            String sql = "UPDATE Post SET categoryId=? WHERE id=?";
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, categoryId);
            pstm.setInt(2, postId);
            int rs = pstm.executeUpdate();
            return rs > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }
}
