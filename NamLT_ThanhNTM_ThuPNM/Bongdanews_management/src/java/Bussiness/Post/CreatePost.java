/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Post;

import DAO.PostDAO;
import DTO.User;
import java.io.File;
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
 * @author ADMIN
 */
@WebServlet(name = "CreatePost", urlPatterns = {"/CreatePost"})
public class CreatePost extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String systemDataFolder = getServletContext().getInitParameter("SystemDataFolderDirectory");
        HttpSession session = request.getSession();
        User author = (User) session.getAttribute("INFO");
        String result = "fail";
        if (author != null) {
            int authorId = author.getId();
            PostDAO dao = new PostDAO();
            int generatedKey = dao.createNewPost(authorId);
            if (generatedKey != -1) {
                result = generatedKey + "";
                new File(systemDataFolder + generatedKey).mkdir();
            }
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result);
    }

}
