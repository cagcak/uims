/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 */

package iaau.uims.jdbc.dao;

import iaau.uims.jdbc.factory.ConnectionFactory;
import iaau.uims.jdbc.factory.ConnectionUtility;
import iaau.uims.jdbc.model.myinformation.CurrentInfo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Çağrı Çakır
 */
public class CurrentInfoDAO {
    
    private Connection connection;
    private Statement statement;

    public CurrentInfoDAO() {
    }
    
    public CurrentInfo getCurrentInfoByIDnumber(String idNumber) throws SQLException
    {
        String query = "SELECT " + "USERS.idnumber, "
                + "CURRENT_INFO.fullname, "
                + "CURRENT_INFO.current_year, "
                + "CURRENT_INFO.current_semester, "
                + "CURRENT_INFO.current_month, "
                + "CURRENT_INFO.current_exam "
                + "FROM USERS "
                + "INNER JOIN CURRENT_INFO "
                + "ON USERS.iduser=CURRENT_INFO.`USERS_iduser` "
                + "WHERE USERS.idnumber="+idNumber;
        
        ResultSet rs = null;
        CurrentInfo currentinfo = null;
        
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            rs = statement.executeQuery(query);
            
            if (rs.next())
            {
                currentinfo = new CurrentInfo();
                currentinfo.setFullname(rs.getString("fullname"));
                currentinfo.setCurrent_year(rs.getString("current_year"));
                currentinfo.setCurrent_semester(rs.getString("current_semester"));
                currentinfo.setCurrent_month(rs.getString("current_month"));
                currentinfo.setCurrent_exam(rs.getString("current_exam"));
            }
        }finally{
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
        return currentinfo;
    }
}
