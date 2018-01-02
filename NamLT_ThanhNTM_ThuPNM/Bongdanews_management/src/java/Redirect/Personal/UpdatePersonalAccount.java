/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Redirect.Personal;

import DAO.UserDAO;
import DTO.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UpdatePersonalAccountPage", urlPatterns = {"/UpdatePersonalAccount"})
public class UpdatePersonalAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            UserDAO dao = new UserDAO();
            User id = (User) session.getAttribute("INFO");
            User dto = dao.viewInfoAccount(id.getId());
            request.setAttribute("UPDATEPROFILE", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("personal/account/updateAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
        HttpSession session = request.getSession();
        try {
            UserDAO dao = new UserDAO();
            User id = (User) session.getAttribute("INFO");
            User dto = dao.viewInfoAccount(id.getId());
            request.setAttribute("UPDATEPROFILE", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("personal/account/updateAccount.jsp").forward(request, response);
    }

}
