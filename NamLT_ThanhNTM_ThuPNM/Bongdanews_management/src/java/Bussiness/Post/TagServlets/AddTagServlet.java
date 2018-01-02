/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Post.TagServlets;

import DAO.TagDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
@WebServlet(name = "AddTagServlet", urlPatterns = {"/AddTag"})
public class AddTagServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String jsonresult = "fail";
        String postIdString = request.getParameter("postId");
        //get tags from request
        String tags = request.getParameter("tags");
        String[] tagArray = tags.split(",");
        try {
            //get tags array list
            List<String> tagList = Arrays.asList(tagArray);
            //remove all tag accent
            for (int i = 0; i < tagList.size(); i++) {
                String tagWithNoAccent = TagDAO.removeAccent(tagList.get(i)).trim().toLowerCase();
                tagList.set(i, tagWithNoAccent);
            }
            TagDAO dao = new TagDAO();
            //add new tag to database
            boolean rs = dao.createNewTag(tagList);
            if (rs) {
                //final return list
                List<String> successList = new ArrayList<>();
                //get post id
                int postId = Integer.parseInt(postIdString);
                //map tag with post
                for (String string : tagList) {
                    boolean status = dao.mapTagWithPost(postId, string);
                    if (status) {
                        successList.add(string);
                    }
                }
                //return added tag
                jsonresult = new Gson().toJson(successList);
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
