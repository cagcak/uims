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
public class JsonAccountingStatusInfo  {

    public JsonAccountingStatusInfo() {
    }
    
    private Connection connection;
    private Statement statement;
    private JsonObject jsonResponse;
    
    
    public JsonObject GenerateAccountingStatusInfoAsJson(String idNumber) throws SQLException
    {
        String query = "SELECT ACCOUNTING_STATUS_INFO.registration, "
                + "ACCOUNTING_STATUS_INFO.midterm, "
                + "ACCOUNTING_STATUS_INFO.`final` "
                + "FROM ACCOUNTING_STATUS_INFO "
                + "WHERE USERS_idnumber = " + idNumber;

        ResultSet rs = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            rs = statement.executeQuery(query);

            jsonResponse = new JsonObject();
            JsonArray data = new JsonArray();

            while (rs.next())
            {
                JsonObject info = new JsonObject();

                info.addProperty("registration", (rs.getString("registration")));
                info.addProperty("midterm", (rs.getString("midterm")));
                info.addProperty("final", (rs.getString("final")));

                data.add(info);
            }
            
            jsonResponse.add("AccountingStatusInfo_Response", data);
            
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
