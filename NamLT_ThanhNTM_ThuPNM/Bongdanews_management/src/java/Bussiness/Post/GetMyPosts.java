/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Post;

import DAO.PostDAO;
import DTO.Post;
import DTO.User;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "GetMyPosts", urlPatterns = {"/GetMyPosts"})
public class GetMyPosts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jsonData = "fail";
        PostDAO dao = new PostDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("INFO");
        try {
            if (user != null) {
                List<Post> result = dao.getMyPostsInfo(user.getId());
                if (result != null) {
                    jsonData = new Gson().toJson(result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonData);
    }
}
