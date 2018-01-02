/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Redirect;

import DAO.PostDAO;
import DAO.TagDAO;
import DTO.Post;
import DTO.Tag;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "PostPage", urlPatterns = {"/Post"})
public class PostPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("p"));
            TagDAO tDao = new TagDAO();
            PostDAO pDao = new PostDAO();
            List<Tag> tageName = new ArrayList<>();
            List<Post> latest = new ArrayList<>();
            List<Post> popular = new ArrayList<>();
            tageName = tDao.getTagByPostId(id);
            latest = pDao.getLastTenPost();
            popular = pDao.getTenPopularPost();
            
            request.setAttribute("TAGNAME", tageName);
            request.setAttribute("LATEST", latest);
            request.setAttribute("POPULAR", popular);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("post.jsp").forward(request, response);
        }
    }
}
