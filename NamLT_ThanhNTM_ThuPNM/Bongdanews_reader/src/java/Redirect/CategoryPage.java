/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Redirect;

import DAO.CategoryDAO;
import DAO.PostDAO;
import DTO.Post;
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
@WebServlet(name = "CategoryPage", urlPatterns = {"/Category"})
public class CategoryPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int categoryId = Integer.parseInt(request.getParameter("p"));
            int pageNumer = 0;
            if (request.getParameter("n") != null) {
                pageNumer = (Integer.parseInt(request.getParameter("n")) - 1) * 9;
            }
            PostDAO pDao = new PostDAO();
            CategoryDAO cDao = new CategoryDAO();
            int total = pDao.getTotalPostInCategory(categoryId);
            int numberOfPage = 0;
            if (total % 9 == 0) {
                numberOfPage = total / 9;
            } else {
                numberOfPage = total / 9 + 1;
            }
            List<Post> list = new ArrayList<>();
            list = pDao.getTopNine(categoryId, pageNumer);
            List<Post> latest = new ArrayList<>();
            List<Post> popular = new ArrayList<>();
            latest = pDao.getLastTenPost();
            popular = pDao.getTenPopularPost();
            String tagName = cDao.getCategoryName(categoryId);
            request.setAttribute("LATEST", latest);
            request.setAttribute("POPULAR", popular);
            request.setAttribute("TOTAL", numberOfPage);
            request.setAttribute("TAGNAME", tagName);
            request.setAttribute("ID", categoryId);
            request.setAttribute("LISTPOST", list);
        } catch (Exception e) {
            log("ERROR at CategoryPage " + e.getMessage());
        } finally {
            request.getRequestDispatcher("category.jsp").forward(request, response);
        }
    }
}
