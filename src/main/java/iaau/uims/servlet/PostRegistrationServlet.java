/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */
package iaau.uims.servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Çağrı Çakır
 */
public class PostRegistrationServlet extends HttpServlet {

    private static final int BUFSIZE = 4096;
    private static final long serialVersionUID = 1L;
    private String Registration_path;

    public PostRegistrationServlet() {
        super();
    }

    public void init() {
        Registration_path = getServletContext().getRealPath("") + File.separator + "Registration.json";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        File Registration_file = new File(Registration_path);
        int length = 0;
        ServletOutputStream registration_outStream = response.getOutputStream();
        ServletContext Registration_context = getServletConfig().getServletContext();
        String Registration_mimetype = Registration_context.getMimeType(Registration_path);

        // sets response content type
        if (Registration_mimetype == null) {
            Registration_mimetype = "application/octet-stream";
        }

        response.setContentType(Registration_mimetype);
        response.setContentLength((int) Registration_file.length());
        String Registration_fileName = (new File(Registration_path)).getName();

        // sets HTTP header
        response.setHeader("Content-Disposition", "attachment; filename=\"" + Registration_fileName + "\"");

        byte[] byteBuffer = new byte[BUFSIZE];
        DataInputStream in = new DataInputStream(new FileInputStream(Registration_file));

        // reads the file's bytes and writes them to the response stream
        while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
            registration_outStream.write(byteBuffer, 0, length);
        }

        in.close();
        registration_outStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
