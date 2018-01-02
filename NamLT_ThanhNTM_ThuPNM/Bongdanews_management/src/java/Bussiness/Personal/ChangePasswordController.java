/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Personal;

import DAO.UserDAO;
import DTO.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ThuPMNSE62369
 */
@WebServlet(name = "ChangePasswordController", urlPatterns = {"/ChangePasswordController"})
public class ChangePasswordController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        try {
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String oldPassword = request.getParameter("oldPassword");
            String oldPass = request.getParameter("oldPass");
            String newPass = request.getParameter("newPassword");
            String confirm = request.getParameter("confirmPassword");
            User dto = new User();
            UserDAO dao = new UserDAO();
            User id = (User) session.getAttribute("INFO");
            boolean check = false;
            if (!confirm.equals(newPass)) {
                request.setAttribute("errorConfirm", "Confirm password must be the same as new password");
                check = true;
            }
            if (!oldPassword.equals(oldPass)) {
                request.setAttribute("errorPass", "Old password is wrong");
                check = true;
            } 
            dto.setMail(email);
            dto.setName(name);
            dto.setPassword(newPass);
            if (!check) {
                if (dao.changePassword(id.getId(), newPass)) {
                    request.setAttribute("UPDATEPASS", dto);
                    request.setAttribute("RESULT", "Update Successfully!!!");
                    request.getRequestDispatcher("ChangePassword").forward(request, response);
                } else {
                    request.setAttribute("UPDATEPASS", dto);
                    request.setAttribute("RESULT", "Update Failed!");
                    request.getRequestDispatcher("ChangePassword").forward(request, response);
                }
            } else {
                request.setAttribute("UPDATEPASS", dto);
                request.getRequestDispatcher("ChangePassword").forward(request, response);
            }
        } catch (Exception e) {
            log("Error at ChangePasswordController " + e.getMessage());
        }
    }

}
