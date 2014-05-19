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
import iaau.uims.servlet.UIMSServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Çağrı Çakır
 */
public class JsonApplicationsForms  {

    public JsonApplicationsForms() {
    }
    
    private Connection connection;
    private Statement statement;

    private static String PATH;
    private static String LOCAL_PATH = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\UIMS\\src\\main\\webapp\\json\\";
    
    public void GenerateAppsFormsAsJson(String idNumber) throws SQLException
    {
        File folder = new File(LOCAL_PATH, idNumber);
        folder.mkdir();

        if (!folder.exists()) {
            try {
                folder.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(JsonApplicationsForms.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String query = "SELECT APPLICATIONS_FORMS.reference_type, "
                + "APPLICATIONS_FORMS.language "
                + "FROM APPLICATIONS_FORMS "
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
                row.add(new JsonPrimitive(rs.getString("reference_type")));
                row.add(new JsonPrimitive(rs.getString("language")));
                data.add(row);
            }

            jsonResponse.add("jsonAppsForms", data);
            
            System.out.println("\n\tApplications and Forms\n" + data.getAsJsonArray());
//            System.out.println("\n\tJsonObject form \n" + jsonResponse.getAsJsonObject());

            FileOutputStream output = null;
            File file;
            String content = data.toString();

            try {

                String folder_location = folder.toString() + "\\";
                String filename = "ApplicationsForms";
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
                Logger.getLogger(JsonApplicationsForms.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e) {
                    Logger.getLogger(JsonApplicationsForms.class.getName()).log(Level.SEVERE, null, e);
                }
            }

        } finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
    }
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        UIMSServletContext.setContextPath(sce.getServletContext().getContextPath());
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//    }
}
