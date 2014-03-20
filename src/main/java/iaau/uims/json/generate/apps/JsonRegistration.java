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
public class JsonRegistration {

    public JsonRegistration() {
    }
    
    private Connection connection;
    private Statement statement;

    public void GenerateRegistrationAsJson(String idNumber) throws SQLException
    {
        String query = "SELECT USERS.idnumber, REGISTRATION.subject_code, "
                + "REGISTRATION.subject_name, REGISTRATION.semester, "
                + "REGISTRATION.`year`, REGISTRATION.hours, REGISTRATION.credits, "
                + "REGISTRATION.registration_status "
                + "FROM USERS "
                + "INNER JOIN REGISTRATION "
                + "ON USERS.iduser=REGISTRATION.`USERS_iduser` "
                + "WHERE USERS.idnumber=" + idNumber;

        ResultSet rs = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            rs = statement.executeQuery(query);

            JsonObject jsonResponse = new JsonObject();
            JsonArray data = new JsonArray();

            while (rs.next()) {
                JsonArray row = new JsonArray();
                row.add(new JsonPrimitive(rs.getString("subject_code")));
                row.add(new JsonPrimitive(rs.getString("subject_name")));
                row.add(new JsonPrimitive(rs.getString("semester")));
                row.add(new JsonPrimitive(rs.getString("year")));
                row.add(new JsonPrimitive(rs.getString("hours")));
                row.add(new JsonPrimitive(rs.getString("credits")));
                row.add(new JsonPrimitive(rs.getString("registration_status")));
                data.add(row);
            }

            jsonResponse.add("jsonRegistration", data);
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
