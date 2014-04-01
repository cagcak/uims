/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */

package iaau.uims.json.generate.apps;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
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
public class JsonApplicationsForms {

    public JsonApplicationsForms() {
    }
    
    private Connection connection;
    private Statement statement;

    public void GenerateAppsFormsAsJson(String idNumber) throws SQLException
    {
        String query = "SELECT APPLICATIONS_FORMS.reference_type, "
                + "APPLICATIONS_FORMS.language "
                + "FROM APPLICATIONS_FORMS "
                + "WHERE USERS_idnumber = " + idNumber;

        ResultSet rs = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            rs = statement.executeQuery(query);

            JsonObject jsonResponse = new JsonObject();
            JsonArray data = new JsonArray();

            while (rs.next()) {
                JsonArray row = new JsonArray();
                row.add(new JsonPrimitive(rs.getString("reference_type")));
                row.add(new JsonPrimitive(rs.getString("language")));
                data.add(row);
            }

            jsonResponse.add("jsonAppsForms", data);
            System.out.println("JSONArray form: " + data);

            String a = data.toString();
            System.out.println("String form: " + a);

        } finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
    }
    
}
