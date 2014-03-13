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
import iaau.uims.jdbc.model.apps.ApplicationsForms;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Çağrı Çakır
 */
public class ApplicationsFormsDAO {
    
    private Connection connection;
    private Statement statement;

    public ApplicationsFormsDAO() {
    }
    
    public ApplicationsForms getAppsFormsByIDnumber(String idNumber) throws SQLException
    {
        String query = "SELECT USERS.idnumber, APPLICATIONS_FORMS.reference_type, "
                + "APPLICATIONS_FORMS.`language` "
                + "FROM USERS "
                + "INNER JOIN APPLICATIONS_FORMS "
                + "ON USERS.iduser=APPLICATIONS_FORMS.`USERS_iduser` "
                + "WHERE USERS.idnumber="+idNumber;
        
        ResultSet rs = null;
        ApplicationsForms appsforms = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            rs = statement.executeQuery(query);

            if (rs.next()) {
                appsforms = new ApplicationsForms();
                appsforms.setReference_type(rs.getString("reference_type"));
                appsforms.setLanguage(rs.getString("language"));
            }
        } finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
        return appsforms;
    }
}
