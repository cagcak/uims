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
public class JsonInformationDiploma {

    public JsonInformationDiploma() {
    }
    
    private Connection connection;
    private Statement statement;

    public void GenerateDiplomaInfoAsJson(String idNumber) throws SQLException
    {
        String query = "SELECT USERS.idnumber, INFORMATION_DIPLOMA.firstname, "
                + "INFORMATION_DIPLOMA.lastname, INFORMATION_DIPLOMA.middlename, "
                + "INFORMATION_DIPLOMA.fullname_ru, INFORMATION_DIPLOMA.current_address, "
                + "INFORMATION_DIPLOMA.passport_no, INFORMATION_DIPLOMA.birthday, "
                + "INFORMATION_DIPLOMA.phone_number, INFORMATION_DIPLOMA.thesis_project_en, "
                + "INFORMATION_DIPLOMA.thesis_project_ru, INFORMATION_DIPLOMA.thesis_project_kg, "
                + "INFORMATION_DIPLOMA.year_of_school_graduation "
                + "FROM USERS "
                + "INNER JOIN INFORMATION_DIPLOMA "
                + "ON USERS.iduser=INFORMATION_DIPLOMA.`USERS_iduser` "
                + "WHERE USERS.idnumber=" + idNumber;

        ResultSet rs = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            rs = statement.executeQuery(query);

            JsonObject jsonResponse = new JsonObject();
            JsonArray data = new JsonArray();

            if (rs.next()) {
                JsonArray row = new JsonArray();
                row.add(new JsonPrimitive(rs.getString("firstname")));
                row.add(new JsonPrimitive(rs.getString("lastname")));
                row.add(new JsonPrimitive(rs.getString("middlename")));
                row.add(new JsonPrimitive(rs.getString("fullname_ru")));
                row.add(new JsonPrimitive(rs.getString("current_address")));
                row.add(new JsonPrimitive(rs.getString("passport_no")));
                row.add(new JsonPrimitive(rs.getString("birthday")));
                row.add(new JsonPrimitive(rs.getString("phone_number")));
                row.add(new JsonPrimitive(rs.getString("thesis_project_en")));
                row.add(new JsonPrimitive(rs.getString("thesis_project_ru")));
                row.add(new JsonPrimitive(rs.getString("thesis_project_kg")));
                row.add(new JsonPrimitive(rs.getString("year_of_school_graduation")));
                data.add(row);
            }

            jsonResponse.add("jsonInfoDiploma", data);
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
