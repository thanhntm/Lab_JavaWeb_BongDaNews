/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Post.CategoryServlets;

import DAO.CategoryDAO;
import DTO.Category;
import com.google.gson.Gson;
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
@WebServlet(name = "CreateCategory", urlPatterns = {"/CreateCategory"})
public class CreateCategory extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String jsonresult = "fail";
        try {
            if (name != null && name.trim() != "") {
                CategoryDAO dao = new CategoryDAO();
                int key = dao.createNewCategory(name, description);
                if (key != -1) {
                    Category newCategory = dao.getCategoryById(key).get(0);
                    jsonresult = new Gson().toJson(newCategory);
                }
                response.setContentType("application/json");
            }else{
                response.setContentType("text/html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonresult);

    }
}
