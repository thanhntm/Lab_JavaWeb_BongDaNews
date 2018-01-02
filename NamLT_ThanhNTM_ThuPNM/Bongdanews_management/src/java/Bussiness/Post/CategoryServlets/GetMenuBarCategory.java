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
@WebServlet(name = "GetMenuBarCategory", urlPatterns = {"/GetMenuBarCategory"})
public class GetMenuBarCategory extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jsonresult = "fail";
        HashMap result;
        List<Category> menuBarList;
        List<Category> hiddenList;
        try {
            CategoryDAO dao = new CategoryDAO();
            menuBarList = dao.getCategoryListInMenuBar();
            hiddenList = dao.getCategoryListNotInMenuBar();
            if (menuBarList != null && hiddenList != null) {
                result = new HashMap();
                result.put("barList", menuBarList);
                result.put("hiddenList", hiddenList);
                jsonresult = new Gson().toJson(result);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().write(jsonresult);

    }
}
