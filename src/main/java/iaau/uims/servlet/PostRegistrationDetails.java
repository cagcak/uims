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
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import iaau.uims.json.generate.JsonRegistration;
import iaau.uims.json.model.ModelRegistrationDetail;
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

/**
 *
 * @author Çağrı Çakır
 */
public class PostRegistrationDetails extends HttpServlet 
{
    private static JsonRegistration jsonGenerationMethodinstance;
    private static final long serialVersionUID = 1L;
    
    List<ModelRegistrationDetail> reqList = new ArrayList<ModelRegistrationDetail>();

    public PostRegistrationDetails() {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
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
        
        ModelRegistrationDetail members;
        members = mapper.readValue(params, ModelRegistrationDetail.class);
        
        // Setting Response type
        response.setContentType("application/json");

        // Adding signed parameter objects into list
        reqList.add(members);
        int listSize = reqList.size() - 1;

        // Informing admin about added signed parameters from list
        String idnumber = reqList.get(listSize).getIdnumber();
        String requestedSubject = reqList.get(listSize).getRequestedSubject();
        
        // Printing POST request coming from client
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Requested parameters in list: " + reqList);
        System.out.println("list size: " + reqList.size());
        System.out.println("|ID number: " + idnumber + " | Requested Subject Name: " + requestedSubject + "|");
        
        // Transmition data from generation
        JsonObject generatedJson;
        jsonGenerationMethodinstance = new JsonRegistration();
        
        try{
            
            generatedJson = jsonGenerationMethodinstance.GenerateRegistrationDetailAsJson(idnumber, requestedSubject).getAsJsonObject();
            
            // Pretty formatting json data
            Gson gson = new GsonBuilder().
                    setPrettyPrinting().
                    serializeNulls().
                    setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).
                    create();
            gson.toJson(generatedJson);
            
            System.out.println("Generated Requested Registration subject details JSON representation");
            System.out.println("-------------------------------------------------------------------------------------------------");
            System.out.println(generatedJson);
            System.out.println("-------------------------------------------------------------------------------------------------");

            mapper.writeValueAsString("Requested Subject details JSON data is generated");
            response.getWriter().write(gson.toJson(generatedJson).toString());            
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
