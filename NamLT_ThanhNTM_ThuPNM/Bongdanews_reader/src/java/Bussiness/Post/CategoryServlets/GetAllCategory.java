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
@WebServlet(name = "GetAllCategory", urlPatterns = {"/GetAllCategory"})
public class GetAllCategory extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jsonresult = "fail";
        try {
            CategoryDAO dao = new CategoryDAO();
            List<Category> list = dao.getAllCategory();
            if (list != null) {
                jsonresult = new Gson().toJson(list);
            }else{
                throw new Exception("Fail to get category list");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html");
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonresult);
    }

}
