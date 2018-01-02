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
 * @author Thanh Nguyen
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            UserDAO dao = new UserDAO();
            User user = dao.checkLogin(username, password);
            if (user != null) {
                if (user.getStatus() == User.STATUS_BANNED) {
                    String fail = "Account is banned!";
                    response.sendRedirect("Login?ERRORLOGIN=" + fail);
                }
                HttpSession session = request.getSession();
                session.setAttribute("INFO", user);
                response.sendRedirect("Home");
            } else {
                String fail = "Invalid username or password. Please input again!";
                response.sendRedirect("Login?ERRORLOGIN=" + fail);
            }
        } catch (Exception e) {
            log("Error at LoginController " + e.getMessage());
        }
    }

}
