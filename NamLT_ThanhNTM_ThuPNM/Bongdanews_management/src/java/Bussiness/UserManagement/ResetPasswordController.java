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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ThuPMNSE62369
 */
@WebServlet(name = "ResetPasswordController", urlPatterns = {"/ResetPasswordController"})
public class ResetPasswordController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            String userId = request.getParameter("userId");
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String newPass = request.getParameter("newPassword");
            String confirm = request.getParameter("confirmPassword");
            boolean check = false;
            if (!confirm.equals(newPass)) {
                request.setAttribute("errorConfirm", "Confirm password must be same as new password");
                check = true;
            }
            User dto = new User();
            UserDAO dao = new UserDAO();
            dto.setId(Integer.parseInt(userId.trim()));
            dto.setMail(email);
            dto.setName(name);
            dto.setPhone(newPass);           
            if (!check) {
                if (dao.changePassword(Integer.parseInt(userId.trim()), newPass)) {
                    request.setAttribute("RESETPASS", dto);
                    request.setAttribute("RESULT", "Reset Successfully!!!");
                    request.getRequestDispatcher("ResetPassword").forward(request, response);
                } else {
                    request.setAttribute("RESETPASS", dto);
                    request.setAttribute("RESULT", "Reset Failed!");
                    request.getRequestDispatcher("ResetPassword").forward(request, response);
                }
            } else {
                request.setAttribute("RESETPASS", dto);
                request.getRequestDispatcher("ResetPassword").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log("Error at ResetPasswordController " + e.getMessage());
        }
    }

}
