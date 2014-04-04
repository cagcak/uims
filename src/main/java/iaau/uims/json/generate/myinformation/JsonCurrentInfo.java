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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Çağrı Çakır
 */
public class JsonCurrentInfo {

    public JsonCurrentInfo() {
    }
    
    private Connection connection;
    private Statement statement;

    public void GenerateCurrentInfoAsJson(String idNumber) throws SQLException
    {
        
        File folder = new File("src\\main\\json\\", idNumber);
        folder.mkdir();

        if (!folder.exists()) {
            try {
                folder.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(JsonGeneralInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String query = "SELECT CURRENT_INFO.fullname, "
                + "CURRENT_INFO.current_year, "
                + "CURRENT_INFO.current_semester, "
                + "CURRENT_INFO.current_month, "
                + "CURRENT_INFO.current_exam "
                + "FROM CURRENT_INFO "
                + "WHERE USERS_idnumber = " + idNumber;

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
                row.add(new JsonPrimitive(rs.getString("fullname")));
                row.add(new JsonPrimitive(rs.getString("current_year")));
                row.add(new JsonPrimitive(rs.getString("current_semester")));
                row.add(new JsonPrimitive(rs.getString("current_month")));
                row.add(new JsonPrimitive(rs.getString("current_exam")));
                data.add(row);
            }
            
            jsonResponse.add("jsonCurrentInfo", data);
            System.out.println("JSONArray form: " + data);
            
            FileOutputStream output = null;
            File file;
            String content = data.toString();

            try {

                String folder_location = folder.toString() + "\\";
                String filename = "CurrentInfo";
                file = new File(folder_location + filename.toString() + ".json");
                output = new FileOutputStream(file);

                if (!file.exists()) {
                    file.createNewFile();
                }

                byte[] content_in_bytes = content.getBytes();

                output.write(content_in_bytes);
                output.flush();
                output.close();

            } catch (IOException ex) {
                Logger.getLogger(JsonGeneralInfo.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e) {
                    Logger.getLogger(JsonGeneralInfo.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            
        } finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
    }
    
}
