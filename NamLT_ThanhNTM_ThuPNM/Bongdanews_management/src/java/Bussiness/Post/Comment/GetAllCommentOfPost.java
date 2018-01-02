/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Post.Comment;

import DAO.CommentDAO;
import DTO.Comment;
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
@WebServlet(name = "GetAllCommentOfPost", urlPatterns = {"/GetAllCommentOfPost"})
public class GetAllCommentOfPost extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jsonresult = "fail";
        String postId = request.getParameter("postId");
        try {
            CommentDAO dao = new CommentDAO();
            List<Comment> rs = dao.getCommentOfPost(Integer.parseInt(postId));
            if (rs != null) {
                jsonresult = new Gson().toJson(rs);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().write(jsonresult);
    }

}
