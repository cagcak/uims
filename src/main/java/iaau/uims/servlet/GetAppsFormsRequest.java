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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import iaau.uims.jdbc.dao.ApplicationsFormsDAO;
import iaau.uims.jdbc.factory.ConnectionFactory;
import iaau.uims.jdbc.factory.ConnectionUtility;
import iaau.uims.jdbc.model.ApplicationsForms;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Çağrı Çakır
 */
public class GetAppsFormsRequest extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    private Connection connection;
    private Statement statement;
    List<ApplicationsForms> reqList = new ArrayList<ApplicationsForms>();

    public GetAppsFormsRequest() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        doPost(request, response);
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), reqList);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        // Taking request parameter
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String params = "";
        params = br.readLine();
        
        // Signing taken parameter to equivalent objects
        ApplicationsForms members;
        members = mapper.readValue(params, ApplicationsForms.class);
        
        // Setting Response type
        response.setContentType("application/json");

        // Adding signed parameter objects into list
        reqList.add(members);
        int listSize = reqList.size() - 1;
        
        // Informing admin about added signed parameters from list
        String user_idnumber = reqList.get(listSize).getIdnumber();
        String reference = reqList.get(listSize).getReference();
        String language = reqList.get(listSize).getLanguage();
        String fullname = reqList.get(listSize).getFullname();
        
        // Printing POST request coming from client
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Requested parameters in list: " + reqList);
        System.out.println("list size: " + reqList.size());
        
        try {
            setRequestForm(user_idnumber, reference, language, fullname);
            
            Boolean result;
            result = check(user_idnumber).get("server_response").getAsBoolean();
            System.out.println("server_result: " + result);
            
            if (result == true) {
                mapper.writeValueAsString("request saved");
                System.out.println("User request form is accepted");
                System.out.println("-------------------------------------------------------------------------------");
                mapper.writeValue(response.getOutputStream(), result);
            } else if (result == false) {
                mapper.writeValueAsString("request rejected");
                System.out.println("User request form is rejected");
                System.out.println("-------------------------------------------------------------------------------");
                mapper.writeValue(response.getOutputStream(), result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetDiplomaInformationRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException io) {
            Logger.getLogger(GetDiplomaInformationRequest.class.getName()).log(Level.SEVERE, null, io);
        }
    }

    private static JsonObject check(String idNumber) throws SQLException, UnsupportedEncodingException
    {
        ApplicationsFormsDAO dao = new ApplicationsFormsDAO();
        ApplicationsForms model = dao.getAppsFormsByIDnumber(idNumber);
        
        JsonObject response = new JsonObject();
        Gson gsonResponse = new GsonBuilder().create();
        
        if (model != null) {
            response.addProperty("server_response", Boolean.TRUE);
            gsonResponse.toJson(response);
            System.out.println(response);
            return response;
        } else if (model == null) {
            response.addProperty("server_response", Boolean.FALSE);
            gsonResponse.toJson(response);
            System.out.println(response);
            return response;
        } else {
            System.out.println("No such User with ID number : " + idNumber);
        
        System.out.println("nothing checked");
        return null;
        }
    }
    
    public ApplicationsForms setRequestForm(String user_idnumber_param, String reference_param, String language_param, String fullname_param) throws SQLException
    {
        String query = "INSERT INTO "
                + "dbuims.applications_forms (reference_type, `language`, fullname, `USERS_idnumber`) "
                + "	VALUES (" 
                + "'" + reference_param + "'" + "," +
                 "'" + language_param + "'" + "," +
                 "'" + fullname_param + "'" + "," +
                 "'" + user_idnumber_param + "'" + ")";
        
        ResultSet rs = null;
        ApplicationsForms field = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            statement.executeUpdate(query);
            } finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
        return field;
    }
    
}
