/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Redirect;

import DAO.PostDAO;
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
 * @author ADMIN
 */
@WebServlet(name = "TagPage", urlPatterns = {"/Tag"})
public class TagPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //**************Get post by tag name*****************
            String search = request.getParameter("searchtxt");
            PostDAO pDao = new PostDAO();
            TagDAO tDao = new TagDAO();
            if (search == null) {
                System.out.println("asdgfilsakdhgilasdfhglhdsfaghsdflkj");

                String tagName = request.getParameter("name");
                int total = tDao.getTotalPostInTagName(tagName);
                int pageNumber = 0;
                if (request.getParameter("p") != null) {
                    pageNumber = (Integer.parseInt(request.getParameter("p")) - 1) * 10;
                }
//            System.out.println("aaaaaaaaâ " + pageNumber);
                List<Post> postByTag = new ArrayList<>();
                postByTag = tDao.getAllPostByTag(tagName, pageNumber);
                if (postByTag != null) {

                    int numberOfPage = 0;
                    if (total % 10 == 0) {
                        numberOfPage = total / 10;
                    } else {
                        numberOfPage = total / 10 + 1;
                    }
                    request.setAttribute("TOTAL", numberOfPage);
                    request.setAttribute("TAG", tagName);
                    request.setAttribute("LISTPOST", postByTag);
                } else {
                    request.setAttribute("NOTFOUND", "No Element Found!");
                }

            } else {
                //*******************get post by search button******************
                int pageNumber = 0;
                if (request.getParameter("p") != null) {
                    pageNumber = (Integer.parseInt(request.getParameter("p")) - 1) * 10;
                }
                List<Tag> list = tDao.searchTag(search);
                System.out.println("Do lon cua tagName " + list.size());
                if (list.size() > 0) {
                    List<Post> listPost = new ArrayList<>();
                    listPost = tDao.getPostBySpecialTag(list, pageNumber);
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("aaaaaaaaa " + list.get(i).getTagName());
                    }

                    System.out.println("So luong bai post " + listPost.size());
                    if (listPost.size() > 0) {
                        int total = tDao.getNumberOfPostInSearch(list);
                        System.out.println("TOTAL = " + total);
                        int numberOfPage = 0;
                        if (total % 10 == 0) {
                            numberOfPage = total / 10;
                        } else {
                            numberOfPage = total / 10 + 1;
                        }
                        System.out.println(numberOfPage);
                        request.setAttribute("TAGNAME", list);
                        request.setAttribute("SEARCH", search);
                        request.setAttribute("TOTAL", numberOfPage);
                        request.setAttribute("LISTPOST", listPost);
                    }
                } else {
                    request.setAttribute("SEARCH", search);
                    request.setAttribute("NOTFOUND", "No element found for keyword '" + search + "'. Please input another key to search !");
                }
            }
            //*************Popular & Latest***************
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
            request.getRequestDispatcher("tag.jsp").forward(request, response);
        }
    }
}
