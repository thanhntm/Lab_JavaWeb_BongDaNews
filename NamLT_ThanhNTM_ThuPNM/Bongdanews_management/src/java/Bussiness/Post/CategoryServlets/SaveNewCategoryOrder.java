/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Post.CategoryServlets;

import DAO.CategoryDAO;
import DTO.Category;
import DTO.CategoryList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;
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
@WebServlet(name = "SaveNewCategoryOrder", urlPatterns = {"/SaveNewCategoryOrder"})
public class SaveNewCategoryOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jsonresult = "fail";
        //get json string from request
        String barListString = URLDecoder.decode(request.getParameter("barList"), "UTF-8");
        String hiddenListString = URLDecoder.decode(request.getParameter("hiddenList"), "UTF-8");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            //start to parse json string to json object
            List<Object> tmpList1 = gson.fromJson(barListString, List.class);
            List<Object> tmpList2 = gson.fromJson(hiddenListString, List.class);

            List<Category> barList = new ArrayList<>();
            List<Category> hiddenList = new ArrayList<>();
            for (Object obj : tmpList1) {
                Category tmp = gson.fromJson(obj.toString(), Category.class);
                barList.add(tmp);
            }
            for (Object obj : tmpList2) {
                Category tmp = gson.fromJson(obj.toString(), Category.class);
                hiddenList.add(tmp);
            }
            //finish parse json string to json object
            CategoryDAO dao = new CategoryDAO();
            boolean result = dao.updateCategoryOrder(barList, hiddenList);
            if (result) {
                jsonresult = "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("text/html");
        response.getWriter().write(jsonresult);
    }
}
