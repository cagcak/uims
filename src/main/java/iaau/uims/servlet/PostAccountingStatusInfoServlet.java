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
public class PostAccountingStatusInfoServlet extends HttpServlet {

    private static final int BUFSIZE = 4096;
    private static final long serialVersionUID = 1L;
    private String AccountingStatusInfo_path;

    public PostAccountingStatusInfoServlet() {
        super();
    }

    public void init() {
        AccountingStatusInfo_path = getServletContext().getRealPath("") + File.separator + "AccountingStatusInfo.json";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        File AccountingStatusInfo_file = new File(AccountingStatusInfo_path);
        int length = 0;
        ServletOutputStream AccountingStatusInfo_outStream = response.getOutputStream();
        ServletContext AccountingStatusInfo_context = getServletConfig().getServletContext();
        String AccountingStatusInfo_mimetype = AccountingStatusInfo_context.getMimeType(AccountingStatusInfo_path);

        // sets response content type
        if (AccountingStatusInfo_mimetype == null) {
            AccountingStatusInfo_mimetype = "application/octet-stream";
        }

        response.setContentType(AccountingStatusInfo_mimetype);
        response.setContentLength((int) AccountingStatusInfo_file.length());
        String AccountingStatusInfo_fileName = (new File(AccountingStatusInfo_path)).getName();

        // sets HTTP header
        response.setHeader("Content-Disposition", "attachment; filename=\"" + AccountingStatusInfo_fileName + "\"");

        byte[] byteBuffer = new byte[BUFSIZE];
        DataInputStream in = new DataInputStream(new FileInputStream(AccountingStatusInfo_file));

        // reads the file's bytes and writes them to the response stream
        while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
            AccountingStatusInfo_outStream.write(byteBuffer, 0, length);
        }

        in.close();
        AccountingStatusInfo_outStream.close();
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
