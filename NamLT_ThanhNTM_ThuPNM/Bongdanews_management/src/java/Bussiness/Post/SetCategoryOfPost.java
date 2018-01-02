/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Post;

import DAO.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SetCategoryOfPost", urlPatterns = {"/SetCategoryOfPost"})
public class SetCategoryOfPost extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String result = "fail";
        String postId = request.getParameter("postId");
        String categoryId = request.getParameter("categoryId");
        try {
            CategoryDAO dao = new CategoryDAO();
            boolean rs = dao.setCategory(Integer.parseInt(postId), Integer.parseInt(categoryId));
            if (rs) {
                result = "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("text/html");
        response.getWriter().write(result);
    }
}
