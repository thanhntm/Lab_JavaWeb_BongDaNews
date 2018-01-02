/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Post;

import DAO.PostDAO;
import DTO.Post;
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
@WebServlet(name = "PostActionServlet", urlPatterns = {"/postAction/*"})
public class PostActionServlet extends HttpServlet {
    
    public static final String ACTION_PUBLISH = "publish";
    public static final String ACTION_UNPUBLISH = "unpublish";
    public static final String ACTION_ACTIVE = "active";
    public static final String ACTION_DISABLE = "disable";
    public static final String ACTION_REJECT = "reject";
    public static final String ACTION_SUBMIT = "submit";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jsonresult = "fail";
        String action = request.getPathInfo().substring(1);
        String postIdString = request.getParameter("postId");
        try {
            if (postIdString != null && postIdString.trim() != "") {
                PostDAO dao = new PostDAO();
                boolean result = false;
                int postId = Integer.parseInt(postIdString);
                if (action.equals(ACTION_SUBMIT)) {
                    result = dao.setStatus(postId, Post.STATUS_UNPUBLISHED);
                } else if (action.equals(ACTION_PUBLISH)) {
                    result = dao.setStatus(postId, Post.STATUS_PUBLISHED);
                } else if (action.equals(ACTION_REJECT)) {
                    result = dao.setStatus(postId, Post.STATUS_REJECTED);
                } else if (action.equals(ACTION_UNPUBLISH)) {
                    result = dao.setStatus(postId, Post.STATUS_DRAFT);
                } else if (action.equals(ACTION_DISABLE)) {
                    result = dao.setStatus(postId, Post.STATUS_DISABLE);
                } else if (action.equals(ACTION_ACTIVE)) {
                    result = dao.setStatus(postId, Post.STATUS_DRAFT);
                }
                if (result) {
                    jsonresult = "success";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("text/html");
        response.getWriter().write(jsonresult);
    }
    
}
