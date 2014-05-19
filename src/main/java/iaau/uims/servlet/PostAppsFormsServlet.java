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
public class PostAppsFormsServlet extends HttpServlet {

    private static final int BUFSIZE = 4096;
    private static final long serialVersionUID = 1L;
    private String AppsForms_path;
    private String idNumber = "08010101865";

    public PostAppsFormsServlet() {
        super();
    }

    public void init() {
        AppsForms_path = getServletContext().getRealPath("")
                + File.separator
//                + "src"
//                + File.separator
//                + "main"
//                + File.separator
//                + "webapp"
//                + File.separator
                + "json"
                + File.separator
                + idNumber
                + File.separator + "ApplicationsForms.json";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        File AppsForms_file = new File(AppsForms_path);
        int length = 0;
        ServletOutputStream appsForms_outStream = response.getOutputStream();
        ServletContext AppsForms_context = getServletConfig().getServletContext();
        String AppsForms_mimetype = AppsForms_context.getMimeType(AppsForms_path);

        // sets response content type
        if (AppsForms_mimetype == null) {
            AppsForms_mimetype = "application/octet-stream";
        }

        response.setContentType(AppsForms_mimetype);
        response.setContentLength((int) AppsForms_file.length());
        String AppsForms_fileName = (new File(AppsForms_path)).getName();

        // sets HTTP header
        response.setHeader("Content-Disposition", "attachment; filename=\"" + AppsForms_fileName + "\"");

        byte[] byteBuffer = new byte[BUFSIZE];
        DataInputStream in = new DataInputStream(new FileInputStream(AppsForms_file));

        // reads the file's bytes and writes them to the response stream
        while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
            appsForms_outStream.write(byteBuffer, 0, length);
        }

        in.close();
        appsForms_outStream.close();
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
