/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */

package iaau.uims.json.generate.myinformation;

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
public class JsonGeneralInfo {

    public JsonGeneralInfo() {
    }
    
    private Connection connection;
    private Statement statement;

    public void GenerateGeneralInfoAsJson(String idNumber) throws SQLException
    {
        String query = "SELECT USERS.idnumber, GENERAL_INFO.faculty, GENERAL_INFO.department, GENERAL_INFO.group_name, GENERAL_INFO.supervisor, GENERAL_INFO.education, GENERAL_INFO.registration "
                + "FROM USERS "
                + "INNER JOIN GENERAL_INFO "
                + "ON USERS.iduser=GENERAL_INFO.`USERS_iduser` "
                + "WHERE USERS.idnumber=" + idNumber;
        
        ResultSet rs = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            rs = statement.executeQuery(query);

            JsonObject jsonResponse = new JsonObject();
            JsonArray data = new JsonArray();

            if (rs.next())
            {
                JsonArray row = new JsonArray();
                row.add(new JsonPrimitive(rs.getString("faculty")));
                row.add(new JsonPrimitive(rs.getString("department")));
                row.add(new JsonPrimitive(rs.getString("group_name")));
                row.add(new JsonPrimitive(rs.getString("supervisor")));
                row.add(new JsonPrimitive(rs.getString("education")));
                row.add(new JsonPrimitive(rs.getString("registration")));
                data.add(row);
            }
            
            jsonResponse.add("jsonGeneralInfo", data);
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
