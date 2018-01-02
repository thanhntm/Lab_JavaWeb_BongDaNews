/*
 * To change this license header, choose License HeaderesultSetin Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.User;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thanh Nguyen
 */
public class UserDAO extends DBConnection {

    public User getProfile(int id) {
        User user = new User();
        try {
            String sql = "SELECT mail, name, birthday, sex, address, phone, identityCard, description, role FROM [User] WHERE id = ? ";
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                user.setMail(resultSet.getString("mail"));
                user.setName(resultSet.getString("name"));
                user.setBirthday(resultSet.getTimestamp("birthday"));
                user.setSex(resultSet.getString("sex"));
                user.setAddress(resultSet.getString("address"));
                user.setPhone(resultSet.getString("phone"));
                user.setIdentityCard(resultSet.getString("identityCard"));
                user.setDescription(resultSet.getString("description"));
                user.setRole(resultSet.getInt("role"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    public User checkLogin(String username, String password) {
        User user = new User();
        try {
            String sql = "SELECT id, name, role, mail,[status] FROM [User] WHERE mail = ? and password = ?";
            connection = getConnection();
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, username);
            pstm.setString(2, password);
            resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setRole(resultSet.getInt("role"));
                user.setMail(resultSet.getString("mail"));
                user.setStatus(resultSet.getInt("status"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean setRole(int id, int role) {
        try {
            connection = getConnection();
            String sql = "UPDATE [User] SET role = ? WHERE id = ?";
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, role);
            pstm.setInt(2, id);
            return pstm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean banOrActiveAccount(int id, int status) {
        try {
            connection = getConnection();
            String sql = "UPDATE [User] SET status = ? WHERE id = ?";
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, status);
            pstm.setInt(2, id);
            return pstm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean updateProfile(User user) {
        try {
            connection = getConnection();
            String sql = "UPDATE [User] SET mail = ?, name = ?, birthday = ?, sex = ?, address = ?, phone = ?, description = ? WHERE id = ?";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, user.getMail());
            pstm.setString(2, user.getName());
            pstm.setTimestamp(3, user.getBirthday());
            pstm.setString(4, user.getSex());
            pstm.setString(5, user.getAddress());
            pstm.setString(6, user.getPhone());
            pstm.setString(7, user.getDescription());
            pstm.setInt(8, user.getId());
            return pstm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean updateBasicInfo(User user) {
        try {
            connection = getConnection();
            String sql = "UPDATE [User] SET mail = ?, name = ?, birthday = ?, sex = ?, address = ?, phone = ?, role = ? WHERE id = ?";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, user.getMail());
            pstm.setString(2, user.getName());
            pstm.setTimestamp(3, user.getBirthday());
            pstm.setString(4, user.getSex());
            pstm.setString(5, user.getAddress());
            pstm.setString(6, user.getPhone());
            pstm.setInt(7, user.getRole());
            pstm.setInt(8, user.getId());
            return pstm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }
    
    public boolean changePassword(int id, String newPassword) {
        try {
            connection = getConnection();
            String sql = "UPDATE [User] SET password = ? WHERE id = ?";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, newPassword);
            pstm.setInt(2, id);
            return pstm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public User viewInfoAccount(int id) {
        User user = new User();
        try {
            connection = getConnection();
            String sql = "SELECT password, mail, name, birthday, sex, address, phone, description, role FROM [User] WHERE id = ?";
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                user.setPassword(resultSet.getString("password"));
                user.setMail(resultSet.getString("mail"));
                user.setName(resultSet.getString("name"));
                user.setBirthday(resultSet.getTimestamp("birthday"));
                user.setSex(resultSet.getString("sex"));
                user.setAddress(resultSet.getString("address"));
                user.setPhone(resultSet.getString("phone").trim());
                user.setRole(resultSet.getInt("role"));
                user.setDescription(resultSet.getString("description"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    public List<User> getInfoByAccountList(List<Integer> list) {
        List<User> result = null;
        try {
            connection = getConnection();
            String sql = "SELECT id,mail,name FROM [User] WHERE [role] !=? AND (";
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    sql += "id=? ";
                } else {
                    sql += "OR id=? ";
                }
            }
            sql += ")";
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, User.ROLE_ADMIN);
            for (int i = 0; i < list.size(); i++) {
                pstm.setInt(i + 2, list.get(i));
            }
            resultSet = pstm.executeQuery();
            result = new ArrayList<>();
            User user = null;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setMail(resultSet.getString("mail"));
                user.setName(resultSet.getString("name"));
                result.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public int addNewUser(User user) {
        try {
            connection = getConnection();
            String sql = "INSERT INTO [USER](mail, name, birthday, sex, address, phone, description, role, status, password, identityCard) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            //this statement will return a generated key
            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, user.getMail());
            pstm.setString(2, user.getName());
            Timestamp dob = new Timestamp(user.getBirthday().getTime());
            pstm.setTimestamp(3, dob);
            pstm.setString(4, user.getSex());
            pstm.setString(5, user.getAddress());
            pstm.setString(6, user.getPhone());
            pstm.setString(7, user.getDescription());
            pstm.setInt(8, user.getRole());
            pstm.setInt(9, User.STATUS_ACTIVE);
            pstm.setString(10, user.getPhone());
            pstm.setString(11, user.getIdentityCard());
            int result = pstm.executeUpdate();
            if (result == 0) {
                throw new Exception("Create account failed!");
            }
            resultSet = pstm.getGeneratedKeys();
            if (resultSet.next()) {
                return (int) resultSet.getLong(1);
            } else {
                throw new Exception("Creating account failed, no ID obtained.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return -1;
    }

    public List<User> getAllUser() {
        List<User> result = null;
        try {
            connection = getConnection();
            String sql = "SELECT id, name, birthday, mail, sex, [role], [status] FROM [User] where [role] != ?";
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, User.ROLE_ADMIN);
            resultSet = pstm.executeQuery();
            result = new ArrayList<>();
            while (resultSet.next()) {
                User dto = new User();
                dto.setId(resultSet.getInt("id"));
                dto.setName(resultSet.getString("name"));
                dto.setBirthday(resultSet.getTimestamp("birthday"));
                dto.setMail(resultSet.getString("mail"));
                dto.setSex(resultSet.getString("sex"));
                dto.setRole(resultSet.getInt("role"));
                dto.setStatus(resultSet.getInt("status"));
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }
}
