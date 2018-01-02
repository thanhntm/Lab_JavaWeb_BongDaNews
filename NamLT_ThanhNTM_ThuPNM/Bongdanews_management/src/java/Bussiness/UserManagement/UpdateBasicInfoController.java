/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.UserManagement;

import DAO.UserDAO;
import DTO.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ThuPMNSE62369
 */
@WebServlet(name = "UpdateBasicInfoController", urlPatterns = {"/UpdateBasicInfoController"})
public class UpdateBasicInfoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            String userId = request.getParameter("userId");
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String birthdayStr = request.getParameter("birthday");
            String[] date = birthdayStr.split("-");
            int yearBirthday = Integer.parseInt(date[0]);
            int monthBirthday = Integer.parseInt(date[1]);
            int dayBirthday = Integer.parseInt(date[2]);
            Timestamp birthday = new Timestamp(yearBirthday - 1900, monthBirthday - 1, dayBirthday, 0, 0, 0, 0);
            
            String gender = request.getParameter("gender");
            String address = request.getParameter("address");
            String phoneNo = request.getParameter("phoneNumber");
            String roleStr = request.getParameter("role");
            int role;          
            if (roleStr.trim().equals("1")) {
                role = 1;
            } else {
                role = 2;
            }
            Timestamp now = new Timestamp(System.currentTimeMillis());
            String tmpNow = now + "";
            String[] split = tmpNow.split(" ");
            String[] tmpSplit = split[0].split("-");
            int year = Integer.parseInt(tmpSplit[0]);
            int month = Integer.parseInt(tmpSplit[1]);
            int day = Integer.parseInt(tmpSplit[2]);
            now = new Timestamp(year - 1900, month - 1, day, 0, 0, 0, 0);
            boolean check = false;
            if (birthday.after(now) || birthday.equals(now)) {
                check = true;
            }
            User dto = new User();
            UserDAO dao = new UserDAO();
            dto.setId(Integer.parseInt(userId.trim()));
            dto.setMail(email);
            dto.setName(name);
            dto.setBirthday(birthday);
            dto.setSex(gender);
            dto.setAddress(address);
            dto.setPhone(phoneNo);
            dto.setRole(role);  
            if (!check) {
                if (dao.updateBasicInfo(dto)) {
                    request.setAttribute("UPDATEBASIC", dto);
                    request.setAttribute("RESULT", "Update Successfully!!!");
                    request.getRequestDispatcher("UpdateBasicInfo").forward(request, response);
                } else {
                    request.setAttribute("UPDATEBASIC", dto);
                    request.setAttribute("RESULT", "Update Failed!!!");
                    request.getRequestDispatcher("UpdateBasicInfo").forward(request, response);
                }
            } else {
                request.setAttribute("UPDATEBASIC", dto);
                request.setAttribute("errorDob", "Birthday cannot equal or less than present");
                request.getRequestDispatcher("UpdateBasicInfo").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
