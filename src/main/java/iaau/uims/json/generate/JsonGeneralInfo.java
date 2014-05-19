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
import com.google.gson.JsonArray;
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
public class JsonGeneralInfo  {

    public JsonGeneralInfo() {
    }
    
    private Connection connection;
    private Statement statement;
    private JsonObject jsonResponse;
    
    
    public JsonObject GenerateGeneralInfoAsJson(String idNumber) throws SQLException
    {
        String query = "SELECT GENERAL_INFO.faculty, "
                            + "GENERAL_INFO.department, "
                            + "GENERAL_INFO.group_name, "
                            + "GENERAL_INFO.supervisor, "
                            + "GENERAL_INFO.education, "
                            + "GENERAL_INFO.registration "
                    + "FROM GENERAL_INFO "
                    + "WHERE USERS_idnumber = "+idNumber;
        
        ResultSet rs = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            rs = statement.executeQuery(query);

            jsonResponse = new JsonObject();
            JsonArray data = new JsonArray();

            while (rs.next()) {
                JsonObject info = new JsonObject();

                info.addProperty("faculty", (rs.getString("faculty")));
                info.addProperty("department", (rs.getString("department")));
                info.addProperty("group_name", (rs.getString("group_name")));
                info.addProperty("supervisor", (rs.getString("supervisor")));
                info.addProperty("education", (rs.getString("education")));
                info.addProperty("registration", (rs.getString("registration")));
                
                data.add(info);
            }

            jsonResponse.add("GeneralInfo_Response", data);

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
    
}
