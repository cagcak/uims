/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 */

package iaau.uims.jdbc.dao;

import iaau.uims.jdbc.factory.ConnectionFactory;
import iaau.uims.jdbc.factory.ConnectionUtility;
import iaau.uims.jdbc.model.user.Perms;
import iaau.uims.jdbc.model.user.Users;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Çağrı Çakır
 */
public class PermsDAO {
    private Connection connection;
    private Statement statement;
    
    public Perms getUserPermissionByIDnumber(String idNumber) throws SQLException
    {
        String query = "SELECT ROLES.idnumber, ROLES.user_role, PERMS.permission "
                        + "FROM PERMS "
                        + "INNER JOIN ROLES "
                        + "ON ROLES.`PERMS_idPERMS`=PERMS.`idPERMS` "
                        + "WHERE ROLES.idnumber=" + idNumber;
        
        ResultSet rs = null;
        Perms permission = null;
        
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            
            rs = statement.executeQuery(query);

            if (rs.next())
            {
                permission = new Perms();

                permission.setUserRole(rs.getString("user_role"));
                permission.setPermission(rs.getString("permission"));
            }
        }finally{
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
        
        return permission;
        
    }
}
