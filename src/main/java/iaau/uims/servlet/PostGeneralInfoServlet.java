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
import java.io.PrintWriter;
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
public class PostGeneralInfoServlet extends HttpServlet 
{
    private static final int BUFSIZE = 4096;
    private static final long serialVersionUID = 1L;
    private String GeneralInfo_path;
    private String idNumber = "08010101865";

    public PostGeneralInfoServlet() {
        super();
    }
    
    public void init() 
    {
        GeneralInfo_path = getServletContext().getRealPath("")
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
                + File.separator + "GeneralInfo.json";
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        File GeneralInfo_file = new File(GeneralInfo_path);
        int length = 0;
        ServletOutputStream GeneralInfo_outStream = response.getOutputStream();
        ServletContext GeneralInfo_context = getServletConfig().getServletContext();
        String GeneralInfo_mimetype = GeneralInfo_context.getMimeType(GeneralInfo_path);

        // sets response content type
        if (GeneralInfo_mimetype == null) {
            GeneralInfo_mimetype = "application/octet-stream";
        }

        response.setContentType(GeneralInfo_mimetype);
        response.setContentLength((int) GeneralInfo_file.length());
        String GeneralInfo_fileName = (new File(GeneralInfo_path)).getName();

        // sets HTTP header
        response.setHeader("Content-Disposition", "attachment; filename=\"" + GeneralInfo_fileName + "\"");

        byte[] byteBuffer = new byte[BUFSIZE];
        DataInputStream in = new DataInputStream(new FileInputStream(GeneralInfo_file));

        // reads the file's bytes and writes them to the response stream
        while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
            GeneralInfo_outStream.write(byteBuffer, 0, length);
        }

        System.out.println(getServletContext().getRealPath("")
                + File.separator
                + "src"
                + File.separator
                + "main"
                + File.separator
                + "webapp"
                + File.separator
                + "json"
                + File.separator
                + idNumber
                + File.separator + "Transcript.json");
        in.close();
        GeneralInfo_outStream.close();
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
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
