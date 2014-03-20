/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */

package iaau.uims.json.generate.transcript;

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
public class JsonTranscript {

    public JsonTranscript() {
    }
    
    private Connection connection;
    private Statement statement;

    public void GenerateTranscriptAsJson(String idNumber) throws SQLException
    {
        String query = "SELECT USERS.idnumber, TRASCRIPT.subject_code, "
                + "TRASCRIPT.subject_name, TRASCRIPT.semester, TRASCRIPT.`year`, "
                + "TRASCRIPT.credits, TRASCRIPT.average "
                + "FROM USERS "
                + "INNER JOIN TRASCRIPT "
                + "ON USERS.iduser=TRASCRIPT.`USERS_iduser` "
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
                row.add(new JsonPrimitive(rs.getString("credits")));
                row.add(new JsonPrimitive(rs.getString("average")));
                data.add(row);
            }

            jsonResponse.add("jsonTranscript", data);
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
