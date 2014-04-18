/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */

package iaau.uims.servlet;
/*
 * This servlet class takes an HTTP get request from the android application by
 * JSON inside a JSONArray, parses, maps and converts to a string variable to 
 * check the ID number sent inside the database, if there is a match then sends 
 * back to the android application as a new session with its true value.
 * Then the other servlet classes handle with sending informations for a dedicated
 * user.
*/
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import iaau.uims.jdbc.dao.UsersDAO;
import iaau.uims.jdbc.model.Users;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Çağrı Çakır
 */
public class GetAndroidRequest extends HttpServlet {

    public GetAndroidRequest() {
        super();
    }

    private static final long serialVersionUID = 1L;
    
    // This will store all received users at the same session
    List<Users> users = new ArrayList<Users>();
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {   
        doPost(request, response);
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

        user_credentials = br.readLine();

        ObjectMapper mapper = new ObjectMapper();

        Users user = mapper.readValue(user_credentials, Users.class);

        response.setContentType("application/json");

        users.add(user);
        
        int listSize = users.size() - 1;
                    
        String user_idnumber = users.get(listSize).getIdnumber();
        String user_password = users.get(listSize).getPassword();
        
        HttpSession session = request.getSession(true);
        session.setAttribute("userid", user_idnumber);
        session.setAttribute("userpassword", user_password);

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("list: " + users);
        System.out.println("list size: " + users.size());
        System.out.println("Current <user_idnumber>: " + user_idnumber);
        System.out.println("Current <user_password>: " + user_password);

        Boolean result;
        result = check(user_idnumber, user_password).get("server_result").getAsBoolean();
        System.out.println("98: server_result: "+result);
        
        if (result == true) {
            mapper.writeValueAsString("accepted");
            System.out.println("102: accepted");
            System.out.println("-------------------------------------------------------------------------------");
            mapper.writeValue(response.getOutputStream(), result);
            
        } else if (result == false) {
            mapper.writeValueAsString("rejected");
            System.out.println("133: rejected");
            System.out.println("-------------------------------------------------------------------------------");
            mapper.writeValue(response.getOutputStream(), result);
        }
    }

    private static JsonObject check(String idNumber, String Password) {

        try {

            UsersDAO user_query = new UsersDAO();
            Users user = user_query.getUser(idNumber, Password);

            JsonObject resp = new JsonObject();
            Gson resp_in_gson = new GsonBuilder().create();
            
            if (user != null) {
                resp.addProperty("server_result", Boolean.TRUE);
                resp_in_gson.toJson(resp);
                System.out.println(resp);
                return resp;
            } else if (user == null) {
                resp.addProperty("server_result", Boolean.FALSE);
                resp_in_gson.toJson(resp);
                System.out.println(resp);
                return resp;
            } else {
                System.out.println("No such User with ID number : " + idNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("noting");
        return null;
    }

   
    

    @Override
    public String getServletInfo() {
        return "The servlet, GetAndroidRequest, takes \"ID number\" and \"password\" user credentials "
                + "by JSON and parses, converts, assigns as a string value to add the list"
                + "called \"users\" with a proper JSON form. ";
    }

}
