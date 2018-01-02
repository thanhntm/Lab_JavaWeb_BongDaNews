/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Post.Comment;

import DAO.CommentDAO;
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
@WebServlet(name = "CommentActionServlet", urlPatterns = {"/commentAction/*"})
public class CommentActionServlet extends HttpServlet {

    public static final String ACTION_ACTIVE = "active";
    public static final String ACTION_DISABLE = "disable";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jsonresult = "fail";
        String action = request.getPathInfo().substring(1);
        String strCommentId = request.getParameter("commentId");
        try {
            if (strCommentId != null && strCommentId.trim() != "") {
                CommentDAO dao = new CommentDAO();
                boolean result = false;
                int commentId = Integer.parseInt(strCommentId);
                if (action.equals(ACTION_ACTIVE)) {
                    result = dao.activeComment(commentId);
                } else if (action.equals(ACTION_DISABLE)) {
                    result = dao.disableComment(commentId);
                }
                if (result) {
                    jsonresult = "success";
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        response.setContentType("text/html");
        response.getWriter().write(jsonresult);
    }
}
