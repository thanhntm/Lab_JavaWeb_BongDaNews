/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Media;

import DAO.MediaSourceDAO;
import DTO.MediaSource;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
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
@WebServlet("/files/*")
public class FileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String systemDataFolder = getServletContext().getInitParameter("SystemDataFolderDirectory");
        String mediaId = request.getPathInfo().substring(1);
        File file = null;
        MediaSourceDAO dao = new MediaSourceDAO();
        try {
            List<MediaSource> list = dao.getMediaSource(Integer.parseInt(mediaId));
            MediaSource media = list.get(0);
            file = new File(systemDataFolder + media.getPath().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Type", getServletContext().getMimeType(file.getCanonicalPath()));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
        Files.copy(file.toPath(), response.getOutputStream());
    }
}
