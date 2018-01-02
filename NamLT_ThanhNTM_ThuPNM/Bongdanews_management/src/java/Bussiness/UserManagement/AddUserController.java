/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.UserManagement;

import DAO.UserDAO;
import DTO.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thanh Nguyen
 */
@WebServlet(name = "AddUserController", urlPatterns = {"/AddUserController"})
public class AddUserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            /* TODO output your page here. You may use following sample code. */
            String mail = request.getParameter("email");
            String name = request.getParameter("name");
            String birthdayInString = request.getParameter("birthdate");
            String date[] = birthdayInString.split("-");
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);
            Timestamp birthday = new Timestamp(year - 1900, month - 1, day, 0, 0, 0, 0);
            String gender = request.getParameter("gender");
            if (gender.equals("male")) {
                gender = "male";
            } else {
                gender = "female";
            }
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String idcard = request.getParameter("idcard");
            String description = request.getParameter("description");
            String roleInString = request.getParameter("role");
            int role;
            if (roleInString.equals("1")) {
                role = 1;
            } else {
                role = 2;
            }
            UserDAO dao = new UserDAO();
            User user = new User();
            user.setMail(mail);
            user.setName(name);
            user.setBirthday(birthday);
            user.setSex(gender);
            user.setAddress(address);
            user.setPhone(phone);
            user.setIdentityCard(idcard);
            user.setDescription(description);
            user.setRole(role);
            int result = dao.addNewUser(user);
            System.out.println(result);
            if (result > 0) {
                request.setAttribute("RESULT", "Add new user successful!");
                request.getRequestDispatcher("AddNewUser").forward(request, response);
            } else {
                request.setAttribute("RESULT", "Add new user failed!");
                request.getRequestDispatcher("AddNewUser");
            }
        } catch (Exception e) {
            log("Error at AddUserController " + e.getMessage());
        }
    }
}
