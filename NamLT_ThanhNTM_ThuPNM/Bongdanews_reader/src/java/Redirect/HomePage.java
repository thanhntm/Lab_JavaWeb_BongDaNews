/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Redirect;

import DAO.CategoryDAO;
import DAO.PostDAO;
import DTO.Category;
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
@WebServlet(name = "HomePage", urlPatterns = {"/Home"})
public class HomePage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //*************Load ban tin*******************
            
            CategoryDAO cDao = new CategoryDAO();
            PostDAO pDao = new PostDAO();
            List<Category> listMotherID = cDao.getOwnedCategory();
            List<List> TOTAL = new ArrayList();
            
            for (Category category : listMotherID) {
                System.out.println("qqqqqqqqqqqqqq " + category.getId());
                List list = new ArrayList();
                List<Post> listPost = new ArrayList<>();
                listPost = pDao.getTopFive(category.getId());
                list.add(category);
                list.add(listPost);
//                System.out.println("eeeeeeeeeeeeee " + listPost.get(0).getTitle());
                TOTAL.add(list);
            }
            System.out.println("1111111111111111111111111");
            for (List l : TOTAL) {
                Category c = new Category();
                c = (Category) l.get(0);
                List<Post> p = new ArrayList<>();
//                p = (List<Post>) l.get(1);
                System.out.println(c.getId() + " aaaaaaaaaaaaaaaaaaaaaaaa");
                for (Post post : (List<Post>) l.get(1)) {
                    System.out.println("cccccccccccccccccc " + post.getTitle() + "     " + post.getSymbolicImage());
                }
            }
            
            request.setAttribute("LISTPOST", TOTAL);
            
            
            //**************Popular & Latest***************
            List<Post> latest = new ArrayList<>();
            List<Post> popular = new ArrayList<>();
            latest = pDao.getLastTenPost();
            popular = pDao.getTenPopularPost();
            request.setAttribute("LATEST", latest);
            request.setAttribute("POPULAR", popular);
        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at HomePage.java " + e.getMessage());
        } finally {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
    }
}
