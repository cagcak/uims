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
import iaau.uims.jdbc.model.myinformation.AccountingStatusInfo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Çağrı Çakır
 */
public class AccountingStatusDAO {
    
    private Connection connection;
    private Statement statement;

    public AccountingStatusDAO() {
    }
    
    public AccountingStatusInfo getAccountingSatatusInfoByIDnumber(String idNumber) throws SQLException
    {
        String query = "SELECT USERS.idnumber, "
                + "ACCOUNTING_STATUS_INFO.registration, "
                + "ACCOUNTING_STATUS_INFO.midterm, ACCOUNTING_STATUS_INFO.`final` "
                + "FROM USERS "
                + "INNER JOIN ACCOUNTING_STATUS_INFO "
                + "ON USERS.iduser=ACCOUNTING_STATUS_INFO.`USERS_iduser` "
                + "WHERE USERS.idnumber="+idNumber;
        
        ResultSet rs = null;
        AccountingStatusInfo a_status = null;
        
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            rs = statement.executeQuery(query);
            
            if(rs.next())
            {
                a_status = new AccountingStatusInfo();
                a_status.setRegistration(rs.getString("registration"));
                a_status.setMidterm(rs.getString("midterm"));
                a_status.setFinal1(rs.getString("final"));
            }
        }finally{
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
        return a_status;
    }
}
