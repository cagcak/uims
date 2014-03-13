/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------- * 
 *  https://github.com/cagricakir/uims.git   * 
 */
package iaau.uims.jdbc.dao;

import iaau.uims.jdbc.factory.ConnectionFactory;
import iaau.uims.jdbc.factory.ConnectionUtility;
import iaau.uims.jdbc.model.user.Roles;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Çağrı Çakır
 */
public class RolesDAO {
    
    private Connection connection;
    private Statement statement;

    public RolesDAO() {
    }
    
    public Roles getUserRoleByIDnumber(String idNumber) throws SQLException
    {
        String query = "SELECT USERS.idnumber, ROLES.user_role "
                        + "FROM ROLES "
                        + "INNER JOIN USERS "
                        + "ON ROLES.idnumber=USERS.idnumber "
                        + "WHERE ROLES.idnumber="+idNumber;
        ResultSet rs = null;
        Roles role = null;
        
//        DatabaseMetaData meta = connection.getMetaData();
//        ResultSet rsForeignKeys = meta.getImportedKeys(connection.getCatalog(), null, "roles");
//
//        while (rsForeignKeys.next()) {
//            String fkTableFirstName = rsForeignKeys.getString("users");
//            String fkTableSecondName = rsForeignKeys.getString("perms");
//
//            String fkFirstTableColumnName = rsForeignKeys.getString("USERS_iduser");
//            String fkSecondTableColumnName = rsForeignKeys.getString("PERMS_idPERMS");
//
//            System.out.printf(fkTableFirstName, fkTableSecondName, fkFirstTableColumnName, fkSecondTableColumnName);
//        }
        
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            
            rs = statement.executeQuery(query);
            
            if(rs.next())
            {
                role = new Roles();
//                role.setUSERS_idusers(rs.getInt("USERS_idusers"));
                role.setIdnumber(rs.getString("idnumber"));
                role.setUserRole(rs.getString("user_role"));
            }
        }finally{
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
        return role;
    }
}
