/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */

package iaau.uims.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import iaau.uims.jdbc.model.user.Users;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Çağrı Çakır
 */
public class GetAndroidRequest extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    // This will store all received users at the same session
    List<Users> users = new LinkedList<Users>();
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        //  initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();

        //  Set response type to JSON
        response.setContentType("application/json");

        //  Send List<Users> as JSON to client
        mapper.writeValue(response.getOutputStream(), users);
    }


//  doPost(): receives JSON data, parse it, map it and send back as JSON
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        // receiving JSON data from request
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String user_credentials = "";
        if (br != null) 
        {
            user_credentials = br.readLine();
        }
        
        // initiating jackson mapper
        ObjectMapper mapper = new ObjectMapper();
        
        
        // Converting received JSON to Users
        Users user = mapper.readValue(user_credentials, Users.class);
        
        
        // Setting response type to JSON
        response.setContentType("application/json");
        
        
        // Adding user to List<Users>
        if (users.size() > 20) {
            users.remove(0);
        }

        users.add(user);
        
        // Sending List<Users> as JSON to client
        mapper.writeValue(response.getOutputStream(), users);
        
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
