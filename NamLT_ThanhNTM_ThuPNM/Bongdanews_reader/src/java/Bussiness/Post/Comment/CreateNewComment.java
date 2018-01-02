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
import java.util.HashMap;
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
@WebServlet(name = "CreateNewComment", urlPatterns = {"/CreateNewComment"})
public class CreateNewComment extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jsonresult = "fail";
        request.setCharacterEncoding("UTF-8");
        String stringPostId = request.getParameter("postId");
        String stringResponse = request.getParameter("responseId");
        try {
            String comment = request.getParameter("comment").trim();
            String name = request.getParameter("name").trim();
            if (name.length() > 50 || name.length() < 3) {
                throw new Exception("Name length size has problem!");
            }
            if (comment.length() > 1000 || comment.length() < 10) {
                throw new Exception("Comment length size has problem!");
            }
            Comment cmt = new Comment();
            cmt.setComment(comment);
            cmt.setName(name);
            int postId = Integer.parseInt(stringPostId);
            cmt.setPostID(postId);
            if (stringResponse != null && stringResponse != "") {
                System.out.println("CMT:" + stringResponse);
                int responseId = Integer.parseInt(stringResponse);
                cmt.setResponeTo(responseId);
            }
            //add to db
            CommentDAO dao = new CommentDAO();
            int rs = dao.createNewComment(cmt);
            if (rs != -1) {
                HashMap<String, Integer> map = new HashMap<>();
                map.put("id", rs);
                jsonresult = new Gson().toJson(map);
                response.setContentType("application/json");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().write(jsonresult);
    }
}
