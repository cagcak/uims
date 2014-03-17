/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */

package iaau.uims.json.generate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import iaau.uims.jdbc.factory.ConnectionFactory;
import iaau.uims.jdbc.factory.ConnectionUtility;
import iaau.uims.jdbc.model.user.Users;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Çağrı Çakır
 */
public class GenerateJSONfromDB {
    
  
    public static void main (String[]args) throws SQLException
    {
        
    Connection connection = null;
    Statement statement = null;
    
//      String query = RETRIEVE_USER_QUERY_BY_IDNUMBER;
        String query = "SELECT * FROM USERS";
        ResultSet rs = null;
        

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

//            statement = connection.prepareStatement(query);
//            statement.setString(1, IDnumber);
            rs = statement.executeQuery(query);

            JsonObject jsonResponse = new JsonObject();
            JsonArray data = new JsonArray();
            
            if (rs.next()) {
                JsonArray row = new JsonArray();
                row.add(new JsonPrimitive(rs.getString("iduser")));
                row.add(new JsonPrimitive(rs.getString("idnumber")));
                row.add(new JsonPrimitive(rs.getString("password")));
                data.add(row);
            }
            jsonResponse.add("aaData", data);
        System.out.println(data);

        } finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
    }
    
    }
