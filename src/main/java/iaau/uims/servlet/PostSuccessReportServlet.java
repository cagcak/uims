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
public class PostSuccessReportServlet extends HttpServlet {

    private static final int BUFSIZE = 4096;
    private static final long serialVersionUID = 1L;
    private String SuccessReport_path;
    private String idNumber = "08010101865";

    public PostSuccessReportServlet() {
        super();
    }

    public void init() {
        SuccessReport_path = getServletContext().getRealPath("")
                + File.separator + "json" + File.separator + idNumber + File.separator + "SuccessReport.json";

        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        File SuccessReport_file = new File(SuccessReport_path);
        int length = 0;
        ServletOutputStream SuccessReport_outStream = response.getOutputStream();
        ServletContext SuccessReport_context = getServletConfig().getServletContext();
        String SuccessReport_mimetype = SuccessReport_context.getMimeType(SuccessReport_path);

        // sets response content type
        if (SuccessReport_mimetype == null) {
            SuccessReport_mimetype = "application/octet-stream";
        }

        response.setContentType(SuccessReport_mimetype);
        response.setContentLength((int) SuccessReport_file.length());
        String SuccessReport_fileName = (new File(SuccessReport_path)).getName();

        // sets HTTP header
        response.setHeader("Content-Disposition", "attachment; filename=\"" + SuccessReport_fileName + "\"");

        byte[] byteBuffer = new byte[BUFSIZE];
        DataInputStream in = new DataInputStream(new FileInputStream(SuccessReport_file));

        // reads the file's bytes and writes them to the response stream
        while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
            SuccessReport_outStream.write(byteBuffer, 0, length);
        }

        in.close();
        SuccessReport_outStream.close();
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
