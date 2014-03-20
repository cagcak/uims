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
public class JsonAccountingStatusInfo {

    public JsonAccountingStatusInfo() {
    }
    
    private Connection connection;
    private Statement statement;

    public void GenerateAccountingStatusInfoAsJson(String idNumber) throws SQLException
    {
        String query = "SELECT USERS.idnumber, "
                + "ACCOUNTING_STATUS_INFO.registration, "
                + "ACCOUNTING_STATUS_INFO.midterm, ACCOUNTING_STATUS_INFO.`final` "
                + "FROM USERS "
                + "INNER JOIN ACCOUNTING_STATUS_INFO "
                + "ON USERS.iduser=ACCOUNTING_STATUS_INFO.`USERS_iduser` "
                + "WHERE USERS.idnumber=" + idNumber;

        ResultSet rs = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            rs = statement.executeQuery(query);

            JsonObject jsonResponse = new JsonObject();
            JsonArray data = new JsonArray();

            while (rs.next())
            {
                JsonArray row = new JsonArray();
                row.add(new JsonPrimitive(rs.getString("registration")));
                row.add(new JsonPrimitive(rs.getString("midterm")));
                row.add(new JsonPrimitive(rs.getString("final")));
                data.add(row);
            }
            
            jsonResponse.add("jsonAccountingStatusInfo", data);
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
