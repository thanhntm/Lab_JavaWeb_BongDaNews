/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Redirect.UserManagement;

import DAO.UserDAO;
import DTO.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ThuPMNSE62369
 */
@WebServlet(name = "UpdateBasicInfoPage", urlPatterns = {"/UpdateBasicInfo"})
public class UpdateBasicInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String userId = request.getParameter("userId");
            UserDAO dao = new UserDAO();
            User dto = dao.viewInfoAccount(Integer.parseInt(userId));
            request.setAttribute("UPDATEBASIC", dto);
            request.setAttribute("USERID", userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("admin/updateBasicInfo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
        request.getRequestDispatcher("admin/updateBasicInfo.jsp").forward(request, response);
    }

}
