/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Post;

import DAO.PostDAO;
import DTO.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "GetUserHasMostPost", urlPatterns = {"/GetUserHasMostPost"})
public class GetUserHasMostPost extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String strStart = request.getParameter("start");
        String strEnd = request.getParameter("end");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp start = null;
        Timestamp end = null;
        try {
            Date tmp = sdf.parse(strStart);
            start = new Timestamp(tmp.getTime());
            tmp = sdf.parse(strEnd);
            end = new Timestamp(tmp.getTime());
            if (start.getTime() > end.getTime()) {
                throw new NumberFormatException("BIGGER");
            }
            PostDAO dao = new PostDAO();
            List<User> list = dao.getTopTenUserHasMostPost(start, end);
            request.setAttribute("RS", list);
            request.getRequestDispatcher("personal/homePage.jsp").forward(request, response);
        } catch (ParseException ex) {
            ex.printStackTrace();
            response.sendRedirect("Home?ERROR=Wrong time format");
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            response.sendRedirect("Home?ERROR=Something went wrong!");
        }
    }
}
