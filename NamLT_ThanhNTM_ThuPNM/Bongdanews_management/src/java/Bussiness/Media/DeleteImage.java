/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Media;

import DAO.MediaSourceDAO;
import DTO.MediaSource;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
@WebServlet("/delete_image")
public class DeleteImage extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteImage() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String folderLocation = getServletContext().getInitParameter("SystemDataFolderDirectory");
        String result = "fail";
        try {
            //get media's id
            String id = request.getParameter("name");
            MediaSourceDAO dao = new MediaSourceDAO();
            //get Media Object
            List<MediaSource> obj = dao.getMediaSource(Integer.parseInt(id));
            if (obj != null && obj.size() != 0) {
                MediaSource tmp = obj.get(0);
                //get path of media object
                Path fullPath = Paths.get(folderLocation + tmp.getPath().trim());
                //disable media object in DB
                dao.disableMediaSource(Integer.parseInt(id));
                //delete media in server directory
                Files.delete(fullPath);
                result = "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        String jsonResponseData = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponseData);
    }

}
