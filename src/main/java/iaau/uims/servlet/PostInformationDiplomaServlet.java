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
public class PostInformationDiplomaServlet extends HttpServlet {

    private static final int BUFSIZE = 4096;
    private static final long serialVersionUID = 1L;
    private String InformationDiploma_path;
    private String idNumber = "08010101865";

    public PostInformationDiplomaServlet() {
        super();
    }

    public void init() {
        InformationDiploma_path = getServletContext().getRealPath("")
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
                + File.separator + "InformationDiploma.json";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        File InformationDiploma_file = new File(InformationDiploma_path);
        int length = 0;
        ServletOutputStream informationDiploma_outStream = response.getOutputStream();
        ServletContext InformationDiploma_context = getServletConfig().getServletContext();
        String InformationDiploma_mimetype = InformationDiploma_context.getMimeType(InformationDiploma_path);

        // sets response content type
        if (InformationDiploma_mimetype == null) {
            InformationDiploma_mimetype = "application/octet-stream";
        }

        response.setContentType(InformationDiploma_mimetype);
        response.setContentLength((int) InformationDiploma_file.length());
        String InformationDiploma_fileName = (new File(InformationDiploma_path)).getName();

        // sets HTTP header
        response.setHeader("Content-Disposition", "attachment; filename=\"" + InformationDiploma_fileName + "\"");

        byte[] byteBuffer = new byte[BUFSIZE];
        DataInputStream in = new DataInputStream(new FileInputStream(InformationDiploma_file));

        // reads the file's bytes and writes them to the response stream
        while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
            informationDiploma_outStream.write(byteBuffer, 0, length);
        }

        in.close();
        informationDiploma_outStream.close();
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
