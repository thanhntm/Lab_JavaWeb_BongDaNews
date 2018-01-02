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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet("/load_images/*")
public class LoadImage extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadImage() {
        super();

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String postId = request.getPathInfo().substring(1);
        String folderLocation = getServletContext().getInitParameter("SystemDataFolderDirectory");
        ArrayList<Object> responseData = new ArrayList<>();
        try {
            MediaSourceDAO dao = new MediaSourceDAO();
            List<MediaSource> list = dao.getMediaSourceByPost(Integer.parseInt(postId));
            // Add images.
            for (MediaSource fileEntry : list) {
                Map<Object, Object> imageObj = new HashMap<Object, Object>();
                imageObj.put("url", "files/" + fileEntry.getId());
                imageObj.put("thumb", "files/" + fileEntry.getId());
                imageObj.put("name", fileEntry.getId());
                responseData.add(imageObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        String jsonResponseData = new Gson().toJson(responseData);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponseData);
    }

}
