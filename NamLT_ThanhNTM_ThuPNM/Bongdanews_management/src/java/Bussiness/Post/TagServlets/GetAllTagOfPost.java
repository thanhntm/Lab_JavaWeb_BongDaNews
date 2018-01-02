/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Post.TagServlets;

import DAO.TagDAO;
import DTO.Tag;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "GetAllTagOfPost", urlPatterns = {"/GetAllTagOfPost"})
public class GetAllTagOfPost extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jsonresult = "fail";
        String postIdString = request.getParameter("postId");
        try {
            int postId = Integer.parseInt(postIdString);
            TagDAO dao = new TagDAO();
            List<Tag> rs = dao.getAllTagOfPost(postId);
            if (rs != null) {
                jsonresult = new Gson().toJson(rs);
                response.setContentType("application/json");
            } else {
                response.setContentType("text/html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().write(jsonresult);
    }
}
