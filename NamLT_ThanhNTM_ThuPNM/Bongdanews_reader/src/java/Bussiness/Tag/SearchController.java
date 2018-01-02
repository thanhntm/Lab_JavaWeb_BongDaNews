/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Tag;

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
 * @author Thanh Nguyen
 */
@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int pageNumber = 0;
            if (request.getParameter("p") != null) {
                pageNumber = (Integer.parseInt(request.getParameter("p")) - 1) * 10;
            }
            String search = request.getParameter("seachtxt");
            TagDAO tDao = new TagDAO();
            List<Tag> list = tDao.searchTag(search);
            System.out.println("Do lon cua tagName " + list.size());
            if (list.size() > 0) {
                List<Post> listPost = new ArrayList<>();
                listPost = tDao.getPostBySpecialTag(list, pageNumber);
                System.out.println("So luong bai post " + listPost.size());
                if (listPost.size() > 0) {
                    int total = listPost.size();
                    int numberOfPage = 0;
                    if (total % 10 == 0) {
                        numberOfPage = total / 10;
                    } else {
                        numberOfPage = total / 10 + 1;
                    }
                    System.out.println(numberOfPage);
                    request.setAttribute("TOTAL", numberOfPage);
                    request.setAttribute("LISTPOST", listPost);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at SearchController " + e.getMessage());
        } finally {
            request.getRequestDispatcher("tag.jsp").forward(request, response);
        }
    }
}
