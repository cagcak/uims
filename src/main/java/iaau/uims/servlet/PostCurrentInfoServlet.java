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
public class PostCurrentInfoServlet extends HttpServlet {

    private static final int BUFSIZE = 4096;
    private static final long serialVersionUID = 1L;
    private String CurrentInfo_path;

    public PostCurrentInfoServlet() {
        super();
    }

    public void init() {
        CurrentInfo_path = getServletContext().getRealPath("") + File.separator + "CurrentInfo.json";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        File CurrentInfo_file = new File(CurrentInfo_path);
        int length = 0;
        ServletOutputStream CurrentInfo_outStream = response.getOutputStream();
        ServletContext CurrentInfo_context = getServletConfig().getServletContext();
        String CurrentInfo_mimetype = CurrentInfo_context.getMimeType(CurrentInfo_path);

        // sets response content type
        if (CurrentInfo_mimetype == null) {
            CurrentInfo_mimetype = "application/octet-stream";
        }
        response.setContentType(CurrentInfo_mimetype);
        response.setContentLength((int) CurrentInfo_file.length());
        String CurrentInfo_fileName = (new File(CurrentInfo_path)).getName();

        // sets HTTP header
        response.setHeader("Content-Disposition", "attachment; filename=\"" + CurrentInfo_fileName + "\"");

        byte[] byteBuffer = new byte[BUFSIZE];
        DataInputStream in = new DataInputStream(new FileInputStream(CurrentInfo_file));

        // reads the file's bytes and writes them to the response stream
        while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
            CurrentInfo_outStream.write(byteBuffer, 0, length);
        }

        in.close();
        CurrentInfo_outStream.close();
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
