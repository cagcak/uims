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
public class DownloadFormEN extends HttpServlet 
{
    private static final int BUFSIZE = 4096;
    private static final long serialVersionUID = 1L;
    private String formsPATH;
    private String folderName = "forms";
    private String fileName = "Petition(EN).doc";

    public DownloadFormEN() {
    }
    
    /**
     *   Informing file and its location to serve
     */
    @Override
    public void init() 
    {
        formsPATH = getServletContext().getRealPath("") + 
                File.separator + folderName + File.separator + fileName;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        File file = new File(formsPATH);
        int length = 0;
        ServletOutputStream sos = response.getOutputStream();
        ServletContext sc = getServletConfig().getServletContext();
        String mimeType = sc.getMimeType(formsPATH);
        
        // setting response content type
        if( mimeType == null )
        {
            mimeType = "application/octet-stream";
        }
        
        response.setContentType(mimeType);
        response.setContentLength((int) file.length());
        String realFileName = (new File(formsPATH)).getName();
        
        // setting HTTP header
        response.setHeader("Content-Disposition", "attachment; filename=\"" + realFileName + "\"");
        
        byte[] byteBuffer = new byte[BUFSIZE];
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        
        // reading the file's bytes and writes them to the response stream
        while ((in != null) && ((length = in.read(byteBuffer)) != -1))
        {
            sos.write(byteBuffer, 0, length);
        }
        
        in.close();
        sos.close();
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        doGet(request, response);
    }

}
