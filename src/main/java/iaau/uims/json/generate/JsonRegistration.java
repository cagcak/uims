/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */

package iaau.uims.json.generate;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import iaau.uims.jdbc.factory.ConnectionFactory;
import iaau.uims.jdbc.factory.ConnectionUtility;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Çağrı Çakır
 */
public class JsonRegistration  {

    public JsonRegistration() {
    }
    
    private Connection connection;
    private Statement statement;
    private JsonObject jsonResponse;
    private JsonObject jsonSubjectDetailResponse;
    
    
    public JsonObject GenerateRegistrationAsJson(String idNumber) throws SQLException
    {
        String query = "SELECT REGISTRATION.subject_code, "
                + "REGISTRATION.subject_name, "
                + "REGISTRATION.semester, "
                + "REGISTRATION.`year`, "
                + "REGISTRATION.hours, "
                + "REGISTRATION.credits, "
                + "REGISTRATION.registration_status "
                + "FROM REGISTRATION "
                + "WHERE USERS_idnumber = " + idNumber;

        ResultSet rs = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            rs = statement.executeQuery(query);

            jsonResponse = new JsonObject();
            JsonObject data = new JsonObject();

            int node = 1;
            while (rs.next()) {
                JsonObject subject = new JsonObject();

                subject.addProperty("subject_code", (rs.getString("subject_code")));
                subject.addProperty("subject_name", (rs.getString("subject_name")));
                subject.addProperty("semester", (rs.getString("semester")));
                subject.addProperty("year", (rs.getString("year")));
                subject.addProperty("hours", (rs.getString("hours")));
                subject.addProperty("credits", (rs.getString("credits")));
                subject.addProperty("registration_status", (rs.getString("registration_status")));
                
                data.add(String.valueOf(node), subject);

                node++;
            }

            jsonResponse.add("Registration", data);
            
            // Pretty formatting json data
            Gson gson = new GsonBuilder().
                    setPrettyPrinting().
                    serializeNulls().
                    setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).
                    create();
            gson.toJson(jsonResponse);
            
            // Write to output the generated json data
//            if (jsonResponse.isJsonArray()) {
//                System.out.println(gson.toJson(jsonResponse));
//            } else if (jsonResponse.isJsonObject()) {
//                System.out.println(gson.toJson(jsonResponse));
//            }

        } finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
    return jsonResponse;
    }
    
    public JsonObject GenerateRegistrationDetailAsJson(String idnumber, String requestedSubject) throws SQLException
    {
        String query = "SELECT REGISTRATION.subject_code, "
                            + "REGISTRATION.subject_name, "
                            + "REGISTRATION.semester, "
                            + "REGISTRATION.`year`, "
                            + "REGISTRATION.hours, "
                            + "REGISTRATION.credits, "
                            + "REGISTRATION.registration_status "
                         + "FROM REGISTRATION "
                         + "WHERE REGISTRATION.`USERS_idnumber` = " + idnumber
                         + " AND REGISTRATION.subject_name = " + "'" + requestedSubject + "'";
        
        ResultSet rs = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            rs = statement.executeQuery(query);
            
            jsonSubjectDetailResponse = new JsonObject();
            JsonObject data = new JsonObject();
            
            while (rs.next())
            {
                JsonObject subject = new JsonObject();
                
                subject.addProperty("subject_code", (rs.getString("subject_code")));
                subject.addProperty("subject_name", (rs.getString("subject_name")));
                subject.addProperty("semester", (rs.getString("semester")));
                subject.addProperty("year", (rs.getString("year")));
                subject.addProperty("hours", (rs.getString("hours")));
                subject.addProperty("credits", (rs.getString("credits")));
                subject.addProperty("registration_status", (rs.getString("registration_status")));
                
                data.add("RequestSubject", subject);
            }
            
            jsonSubjectDetailResponse.add("RegistrationDetail", data);
            
            // Pretty formatting json data
            Gson gson = new GsonBuilder().
                    setPrettyPrinting().
                    serializeNulls().
                    setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).
                    create();
            gson.toJson(jsonSubjectDetailResponse);

//             Write to output the generated json data
            if (jsonSubjectDetailResponse.isJsonArray()) {
                System.out.println(gson.toJson(jsonSubjectDetailResponse));
            } else if (jsonSubjectDetailResponse.isJsonObject()) {
                System.out.println(gson.toJson(jsonSubjectDetailResponse));
            }

        } finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
        
        return jsonSubjectDetailResponse;
    }
}
