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
import iaau.uims.jdbc.model.apps.Registration;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Çağrı Çakır
 */
public class RegistrationDAO {
    
    private Connection connection;
    private Statement statement;

    public RegistrationDAO() {
    }
    
    public Registration getRegiatrationByIDnumber(String idNumber) throws SQLException
    {
        String query = "SELECT REGISTRATION.subject_code, "
                            + "REGISTRATION.subject_name, "
                            + "REGISTRATION.semester, "
                            + "REGISTRATION.`year`, "
                            + "REGISTRATION.hours, "
                            + "REGISTRATION.credits, "
                            + "REGISTRATION.registration_status "
                        + "FROM REGISTRATION "
                        + "WHERE USERS_idnumber = "+idNumber;
        
        ResultSet rs = null;
        Registration reg = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            rs = statement.executeQuery(query);

            if (rs.next())
            {
                reg = new Registration();
                reg.setSubject_code(rs.getString("subject_code"));
                reg.setSubject_name(rs.getString("subject_name"));
                reg.setSemester(rs.getString("semester"));
                reg.setYear(rs.getString("year"));
                reg.setHours(rs.getString("hours"));
                reg.setCredits(rs.getString("credits"));
                reg.setRegistration_status(rs.getString("registration_status"));
            }
    }finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
        return reg;
    }
}
