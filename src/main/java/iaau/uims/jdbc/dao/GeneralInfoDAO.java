/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------------- * 
 *     https://github.com/cagricakir/uims.git      *
 */

package iaau.uims.jdbc.dao;

import iaau.uims.jdbc.factory.ConnectionFactory;
import iaau.uims.jdbc.factory.ConnectionUtility;
import iaau.uims.jdbc.model.myinformation.GeneralInfo;
import iaau.uims.jdbc.model.user.Roles;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Çağrı Çakır
 */
public class GeneralInfoDAO {
    
    private Connection connection;
    private Statement statement;

    public GeneralInfoDAO() {
    }
    
    public GeneralInfo getGeneralInformationByIDnumber(String idNumber) throws SQLException
    {
        String query = "SELECT USERS.idnumber, GENERAL_INFO.faculty, GENERAL_INFO.department, GENERAL_INFO.group_name, GENERAL_INFO.supervisor, GENERAL_INFO.education, GENERAL_INFO.registration "
                        + "FROM USERS "
                        + "INNER JOIN GENERAL_INFO "
                        + "ON USERS.iduser=GENERAL_INFO.`USERS_iduser` "
                        + "WHERE USERS.idnumber="+idNumber;
        ResultSet rs = null;
        GeneralInfo generalInfo = null;
        
        try
        {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            
            rs = statement.executeQuery(query);
            
            if(rs.next())
            {
                generalInfo = new GeneralInfo();
                generalInfo.setFaculty(rs.getString("faculty"));
                generalInfo.setDepartment(rs.getString("department"));
                generalInfo.setGroup_name(rs.getString("group_name"));
                generalInfo.setSupervisor(rs.getString("supervisor"));
                generalInfo.setEducation(rs.getString("education"));
                generalInfo.setRegistration(rs.getString("registration"));
            }
        }finally{
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
        
        return generalInfo;
        
    }
}
