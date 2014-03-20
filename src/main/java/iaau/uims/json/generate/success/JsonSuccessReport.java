/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */

package iaau.uims.json.generate.success;

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
public class JsonSuccessReport {

    public JsonSuccessReport() {
    }
    
    private Connection connection;
    private Statement statement;

    public void GenerateSuccessReportAsJson(String idNumber) throws SQLException
    {
        String query = "SELECT USERS.idnumber, SUCCESS_REPORT.subject_name, "
                + "SUCCESS_REPORT.hours, SUCCESS_REPORT.midterm, "
                + "SUCCESS_REPORT.`final`, SUCCESS_REPORT.average, "
                + "SUCCESS_REPORT.attandance, SUCCESS_REPORT.semester, "
                + "SUCCESS_REPORT.academic_year "
                + "FROM USERS "
                + "INNER JOIN SUCCESS_REPORT "
                + "ON USERS.iduser=SUCCESS_REPORT.`USERS_iduser` "
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
                row.add(new JsonPrimitive(rs.getString("subject_name")));
                row.add(new JsonPrimitive(rs.getString("hours")));
                row.add(new JsonPrimitive(rs.getString("midterm")));
                row.add(new JsonPrimitive(rs.getString("final")));
                row.add(new JsonPrimitive(rs.getString("average")));
                row.add(new JsonPrimitive(rs.getString("attandance")));
                row.add(new JsonPrimitive(rs.getString("semester")));
                row.add(new JsonPrimitive(rs.getString("academic_year")));
                data.add(row);
            }

            jsonResponse.add("jsonSuccessReport", data);
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
