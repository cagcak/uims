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
public class PostTranscriptServlet extends HttpServlet {

    private static final int BUFSIZE = 4096;
    private static final long serialVersionUID = 1L;
    private String Transcript_path;

    public PostTranscriptServlet() {
        super();
    }

    public void init() {
        Transcript_path = getServletContext().getRealPath("") + File.separator + "Transcript.json";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        File Transcript_file = new File(Transcript_path);
        int length = 0;
        ServletOutputStream transcript_outStream = response.getOutputStream();
        ServletContext Transcript_context = getServletConfig().getServletContext();
        String Transcript_mimetype = Transcript_context.getMimeType(Transcript_path);

        // sets response content type
        if (Transcript_mimetype == null) {
            Transcript_mimetype = "application/octet-stream";
        }

        response.setContentType(Transcript_mimetype);
        response.setContentLength((int) Transcript_file.length());
        String Transcript_fileName = (new File(Transcript_path)).getName();

        // sets HTTP header
        response.setHeader("Content-Disposition", "attachment; filename=\"" + Transcript_fileName + "\"");

        byte[] byteBuffer = new byte[BUFSIZE];
        DataInputStream in = new DataInputStream(new FileInputStream(Transcript_file));

        // reads the file's bytes and writes them to the response stream
        while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
            transcript_outStream.write(byteBuffer, 0, length);
        }

        in.close();
        transcript_outStream.close();
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
