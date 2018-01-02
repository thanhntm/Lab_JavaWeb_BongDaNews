/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Post;

import DAO.PostDAO;
import DTO.Post;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SavePost", urlPatterns = {"/SavePost/*"})
public class SavePost extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String postId = request.getPathInfo().substring(1);
        String title = URLDecoder.decode(request.getParameter("title"), "UTF-8");
        String content = URLDecoder.decode(request.getParameter("content"), "UTF-8");
        String firstParagraph = URLDecoder.decode(request.getParameter("firstParagraph"), "UTF-8");
        System.out.println(content);
        String result = "success";
        try {
            if (title != null && title.trim() != "") {
                PostDAO dao = new PostDAO();
                Post post = new Post();
                post.setId(Integer.parseInt(postId));
                post.setTitle(title);
                post.setPostContent(content);
                post.setOpeningParagraph(firstParagraph);
                dao.updatePost(post);
            }
        } catch (Exception e) {
            result = e.getMessage();
        }
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result);

    }

}
