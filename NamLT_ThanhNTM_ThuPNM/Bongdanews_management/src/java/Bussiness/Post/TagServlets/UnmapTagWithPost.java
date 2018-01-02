/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Post.TagServlets;

import DAO.TagDAO;
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
@WebServlet(name = "UnmapTagWithPost", urlPatterns = {"/UnmapTagWithPost"})
public class UnmapTagWithPost extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String result = "fail";
        String tagName = request.getParameter("tagName");
        String postIdString = request.getParameter("postId");
        try {
            int postId = Integer.parseInt(postIdString);
            TagDAO dao = new TagDAO();
            boolean rs = dao.unmapTagWithPost(postId, tagName);
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
