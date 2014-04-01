/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */

package iaau.uims.json.generate.user;

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
public class JsonUserRolePermission {

    public JsonUserRolePermission() {
    }
    
    private Connection connection;
    private Statement statement;

    public void GenerateUserRolePermAsJson(String idNumber) throws SQLException
    {
        String query = "SELECT PERMS.`permission` "
                     + "FROM PERMS "
                     + "WHERE ROLES_USERS_idnumber = " + idNumber;

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
                row.add(new JsonPrimitive(rs.getString("permission")));
                data.add(row);
            }
            
            jsonResponse.add("jsonUserRolePermission", data);
            System.out.println("JSONArray form: " + data);
//          Generated JSON String of Output must be like
//          [["user","userp"]] as [["user_role","permission"]]
            String a = data.toString();
            System.out.println("String form: " + a);
        } finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
    }
    
}
