/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 *********************************************
 * This class defines a method getUser(int idUser) which 
 * retrieves a particular row with the given iduser.  
 */

package iaau.uims.jdbc.dao;

import iaau.uims.jdbc.factory.ConnectionFactory;
import iaau.uims.jdbc.factory.ConnectionUtility;
import iaau.uims.jdbc.model.user.Roles;
import iaau.uims.jdbc.model.user.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Çağrı Çakır
 */
public class UsersDAO {
    
    private Connection connection;
    private Statement statement;
//    private PreparedStatement statement = null;
    
//    private static final String INSERT_USER_QUERY = "INSERT INTO USERS VALUES(?,?)";
//    private static final String RETRIEVE_USER_QUERY_BY_TABLEID = "SELECT * FROM USERS WHERE iduser = ?";
//    private static final String RETRIEVE_USER_QUERY_BY_IDNUMBER = "SELECT * FROM USERS WHERE idnumber = ?";
//    private static final String RETRIEVE_USERROLE_QUERY_BY_IDNUMBER = "SELECT USERS.idnumber, ROLES.user_role FROM ROLES INNER JOIN USERS ON ROLES.idnumber  = USERS.idnumber;";
//    private static final String RETRIEVE_ALL_USER_QUERY = "SELECT * FROM USERS";
//    private static final String UPDATE_USER_QUERY = "UPDATE USERS SET idnumber = ?, password = ? WHERE iduser = ?";
//    private static final String DELETE_USER_QUERY = "DELETE FROM USERS WHERE idnumber = ?";
//    private static final String DELETE_ALL_USERS_QUERY = "DELETE FROM USERS";
    
    public UsersDAO() {
    }
        
    
    /*This method reads data from database ResultSet and 
      stores it in "user" object and returns this object to the caller.*/
    public Users getUserByTableID(int idUser) throws SQLException
    {
//        String query = RETRIEVE_USER_QUERY_BY_TABLEID;
        String query = "SELECT * FROM USERS WHERE iduser = "+idUser;
        ResultSet rs = null;
        Users user = null;
        
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            
//            statement = connection.prepareStatement(query);
//            statement.setInt(1, idUser);
            
            rs = statement.executeQuery(query);
            
            if( rs.next() )
            {
                user = new Users();
                user.setIduser(rs.getInt("iduser"));
                user.setIdnumber(rs.getString("idnumber"));
                user.setPassword(rs.getString("password"));
            }
            
        }finally{
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
        
        return user;
    }
    
    public Users getUserByIDnumber(String IDnumber) throws SQLException
    {
//      String query = RETRIEVE_USER_QUERY_BY_IDNUMBER;
        String query = "SELECT * FROM USERS WHERE idnumber = " + IDnumber;
        ResultSet rs = null;
        Users user = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

//            statement = connection.prepareStatement(query);
//            statement.setString(1, IDnumber);
            rs = statement.executeQuery(query);

            if (rs.next()) {
                user = new Users();
                user.setIduser(rs.getInt("iduser"));
                user.setIdnumber(rs.getString("idnumber"));
                user.setPassword(rs.getString("password"));
            }

        } finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }

        return user;
    }
    
}