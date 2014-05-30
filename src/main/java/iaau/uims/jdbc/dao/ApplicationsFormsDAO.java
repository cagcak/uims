/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */

package iaau.uims.jdbc.dao;

import iaau.uims.jdbc.factory.ConnectionFactory;
import iaau.uims.jdbc.factory.ConnectionUtility;
import iaau.uims.jdbc.model.ApplicationsForms;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.nio.charset.StandardCharsets.*;
import org.mozilla.universalchardet.UniversalDetector;

/**
 *
 * @author Çağrı Çakır
 */
public class ApplicationsFormsDAO {
    
    private Connection connection;
    private Statement statement;

    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    
    public ApplicationsFormsDAO() {
    }
    
    public ApplicationsForms getAppsFormsByIDnumber(String idNumber) throws SQLException, UnsupportedEncodingException
    {
        String query = "SELECT APPLICATIONS_FORMS.reference_type, "
                            + "APPLICATIONS_FORMS.language, "
                            + "APPLICATIONS_FORMS.fullname, "
                            + "APPLICATIONS_FORMS.USERS_idnumber "
                            + "FROM APPLICATIONS_FORMS "
                            + "WHERE USERS_idnumber = "+idNumber;
        
        ResultSet rs = null;
        ApplicationsForms appsforms = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            rs = statement.executeQuery(query);

            
            
            if (rs.next()) {
                appsforms = new ApplicationsForms();
                appsforms.setReference(rs.getString("reference_type"));
                appsforms.setLanguage(rs.getString("language"));
                appsforms.setFullname(rs.getString("fullname"));
//                appsforms.setFullname(Charset.forName("UTF-8").encode(rs.getString("fullname")).toString());
//                String russianChars = rs.getString("fullname");
                /*byte ptext[] = russianChars.getBytes(ISO_8859_5);
                String value = new String(ptext, UTF_8);
                byte[] bytes = russianChars.getBytes(ISO_8859_1);
                UniversalDetector encDetector = new UniversalDetector(null);
                encDetector.handleData(bytes, 0, bytes.length);
                encDetector.dataEnd();
                String encoding = encDetector.getDetectedCharset();
                if (encoding != null) {
                    russianChars = new String(bytes, encoding);
                }
                
                appsforms.setIdnumber(russianChars);*/

                appsforms.setIdnumber(rs.getString("USERS_idnumber"));
                
                
            }
        } finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
        return appsforms;
    }
}
