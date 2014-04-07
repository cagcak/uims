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
public class JsonInformationDiploma {

    public JsonInformationDiploma() {
    }
    
    private Connection connection;
    private Statement statement;

    public void GenerateDiplomaInfoAsJson(String idNumber) throws SQLException
    {
        File folder = new File("src\\main\\json\\", idNumber);
        folder.mkdir();

        if (!folder.exists()) {
            try {
                folder.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(JsonInformationDiploma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String query = "SELECT INFORMATION_DIPLOMA.firstname, "
                + "INFORMATION_DIPLOMA.lastname, "
                + "INFORMATION_DIPLOMA.middlename, "
                + "INFORMATION_DIPLOMA.fullname_ru, "
                + "INFORMATION_DIPLOMA.current_address, "
                + "INFORMATION_DIPLOMA.passport_no, "
                + "INFORMATION_DIPLOMA.birthday, "
                + "INFORMATION_DIPLOMA.phone_number, "
                + "INFORMATION_DIPLOMA.thesis_project_en, "
                + "INFORMATION_DIPLOMA.thesis_project_ru, "
                + "INFORMATION_DIPLOMA.thesis_project_kg, "
                + "INFORMATION_DIPLOMA.year_of_school_graduation "
                + "FROM INFORMATION_DIPLOMA "
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
            
            System.out.println("\n\tJsonArray form \n" + data.getAsJsonArray());
            System.out.println("\n\tJsonObject form \n" + jsonResponse.getAsJsonObject());

            FileOutputStream output = null;
            File file;
            String content = data.toString();

            try {

                String folder_location = folder.toString() + "\\";
                String filename = "InformationDiploma";
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
                Logger.getLogger(JsonInformationDiploma.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e) {
                    Logger.getLogger(JsonInformationDiploma.class.getName()).log(Level.SEVERE, null, e);
                }
            }

        } finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
    }
    
}
