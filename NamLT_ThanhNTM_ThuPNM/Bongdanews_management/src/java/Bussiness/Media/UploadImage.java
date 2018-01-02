/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness.Media;

import DAO.MediaSourceDAO;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.UUID;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ImageUploadServlet", urlPatterns = {
    "/upload_image/*"
})
@MultipartConfig
public class UploadImage extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadImage() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jsonResponseData = "fail";
        String systemDataFolder = getServletContext().getInitParameter("SystemDataFolderDirectory");
        // The route on which the file is saved.
        File uploads = new File(systemDataFolder);
        String multipartContentType = "multipart/form-data";
        // Get file Part based on field name, image extension, post's id.

        String postId = request.getPathInfo().substring(1);
        Map< Object, Object> responseData = null;

        // Create path components to save the file.
        final PrintWriter writer = response.getWriter();
        int generatedKey = -1;
        String linkName = null;
        String name = null;

        try {
            String fieldname = "file";
            Part filePart = request.getPart(fieldname);
            String type = filePart.getContentType();
            type = type.substring(type.lastIndexOf("/") + 1);
            // Check content type.
            if (request.getContentType() == null
                    || request.getContentType().toLowerCase().indexOf(multipartContentType) == -1) {
                throw new Exception("Invalid contentType. It must be " + multipartContentType);
            }

            // Insert new image to database.
            MediaSourceDAO dao = new MediaSourceDAO();
            generatedKey = dao.createNewMediaSource(postId, type);
            if (generatedKey == -1) {
                System.out.println("Create media source fail");
                throw new Exception("CREATE MEDIA SOURCE FAIL!");
            }
            type = (type != null && type != "") ? '.' + type : type;
            postId = (postId != null && postId != "") ? postId + '/' : "";
            name = postId + generatedKey + type;

            // Get absolute server path.
            String absoluteServerPath = uploads + name;

            // Create link.
            String path = request.getHeader("referer");
            linkName = "files/" + generatedKey;

            // Validate image.
            String mimeType = filePart.getContentType();
            String[] allowedExts = new String[]{"gif", "jpeg", "jpg", "png", "svg", "blob"};
            String[] allowedMimeTypes = new String[]{"image/gif", "image/jpeg", "image/pjpeg", "image/x-png", "image/png", "image/svg+xml"};

            if (!ArrayUtils.contains(allowedExts, FilenameUtils.getExtension(absoluteServerPath))
                    || !ArrayUtils.contains(allowedMimeTypes, mimeType.toLowerCase())) {
                // Delete the uploaded image if it dose not meet the validation.
                File file = new File(uploads + name);
                if (file.exists()) {
                    file.delete();
                }
                throw new Exception("WRONG VALIDATION");
            }

            // Save the file on server.
            File file = new File(uploads, name);
            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, file.toPath());
            } catch (Exception e) {
                writer.println("<br/> ERROR: " + e);
            }
            // Build response data.
            responseData = new HashMap< Object, Object>();
            responseData.put("link", linkName);

            // Send response data.
            jsonResponseData = new Gson().toJson(responseData);
            response.setContentType("application/json");
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().equals("WRONG VALIDATION")) {
                MediaSourceDAO dao = new MediaSourceDAO();
                dao.disableMediaSource(generatedKey);
            }
            writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("<br/> ERROR: " + e.getMessage());
            responseData = new HashMap< Object, Object>();
            responseData.put("error", e.toString());
            jsonResponseData = new Gson().toJson(responseData);
            response.setContentType("application/json");

        }
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponseData);
    }
}
