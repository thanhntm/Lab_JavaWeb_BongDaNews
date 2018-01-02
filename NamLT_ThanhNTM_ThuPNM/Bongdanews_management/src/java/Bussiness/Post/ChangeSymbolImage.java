/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Post;

import DAO.PostDAO;
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
@WebServlet(name = "ChangeSymbolImage", urlPatterns = {"/ChangeSymbolImage"})
public class ChangeSymbolImage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String result = "fail";
        String postId = request.getParameter("postId");
        String imageId = request.getParameter("imageId");
        try {
            PostDAO dao = new PostDAO();
            boolean rs = dao.changeSymbolImage(Integer.parseInt(postId), Integer.parseInt(imageId));
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
