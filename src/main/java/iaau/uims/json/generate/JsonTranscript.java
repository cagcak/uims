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
public class JsonTranscript {

    public JsonTranscript() {
    }
    
    private Connection connection;
    private Statement statement;

    public void GenerateTranscriptAsJson(String idNumber) throws SQLException
    {
        
        File folder = new File("src\\main\\json\\", idNumber);
        folder.mkdir();

        if (!folder.exists()) {
            try {
                folder.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(JsonTranscript.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String query = "SELECT TRASCRIPT.subject_code, "
                + "TRASCRIPT.subject_name, "
                + "TRASCRIPT.semester, "
                + "TRASCRIPT.`year`, "
                + "TRASCRIPT.credits, "
                + "TRASCRIPT.credits, "
                + "TRASCRIPT.average "
                + "FROM TRASCRIPT "
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
                row.add(new JsonPrimitive(rs.getString("subject_code")));
                row.add(new JsonPrimitive(rs.getString("subject_name")));
                row.add(new JsonPrimitive(rs.getString("semester")));
                row.add(new JsonPrimitive(rs.getString("year")));
                row.add(new JsonPrimitive(rs.getString("credits")));
                row.add(new JsonPrimitive(rs.getString("average")));
                data.add(row);
            }

            jsonResponse.add("jsonTranscript", data);
            
            System.out.println("\n\tJsonArray form \n" + data.getAsJsonArray());
            System.out.println("\n\tJsonObject form \n" + jsonResponse.getAsJsonObject());

            FileOutputStream output = null;
            File file;
            String content = data.toString();

            try {

                String folder_location = folder.toString() + "\\";
                String filename = "Transcript";
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
                Logger.getLogger(JsonTranscript.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e) {
                    Logger.getLogger(JsonTranscript.class.getName()).log(Level.SEVERE, null, e);
                }
            }

        } finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
    }
    
}
