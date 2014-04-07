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
import iaau.uims.jdbc.model.Transcript;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Çağrı Çakır
 */
public class TranscriptDAO {
    
    private Connection connection;
    private Statement statement;

    public TranscriptDAO() {
    }
    
    public Transcript getTranscriptByIDnumber(String idNumber) throws SQLException
    {
        String query = "SELECT TRASCRIPT.subject_code, "
                            + "TRASCRIPT.subject_name, "
                            + "TRASCRIPT.semester, "
                            + "TRASCRIPT.`year`, "
                            + "TRASCRIPT.credits, "
                            + "TRASCRIPT.credits, "
                            + "TRASCRIPT.average "
                    + "FROM TRASCRIPT "
                    + "WHERE USERS_idnumber = "+idNumber;
        
        ResultSet rs = null;
        Transcript transcript = null;
        
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            rs = statement.executeQuery(query);

            if (rs.next())
            {
                transcript = new Transcript();
                transcript.setSubject_code(rs.getString("subject_code"));
                transcript.setSubject_name(rs.getString("subject_name"));
                transcript.setSemester(rs.getString("semester"));
                transcript.setYear(rs.getString("year"));
                transcript.setCredits(rs.getString("credits"));
                transcript.setAverage(rs.getString("average"));
            }
        }finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
        return transcript;
    }
}
