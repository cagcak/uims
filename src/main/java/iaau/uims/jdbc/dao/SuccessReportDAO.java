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
import iaau.uims.jdbc.model.success.SuccessReport;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Çağrı Çakır
 */
public class SuccessReportDAO {
    
    private Connection connection;
    private Statement statement;

    public SuccessReportDAO() {
    }
    
    public SuccessReport getSuccessReportByIDnumber(String idNumber) throws SQLException
    {
        String query = "SELECT USERS.idnumber, SUCCESS_REPORT.subject_name, "
                + "SUCCESS_REPORT.hours, SUCCESS_REPORT.midterm, "
                + "SUCCESS_REPORT.`final`, SUCCESS_REPORT.average, "
                + "SUCCESS_REPORT.attandance, SUCCESS_REPORT.semester, "
                + "SUCCESS_REPORT.academic_year "
                + "FROM USERS "
                + "INNER JOIN SUCCESS_REPORT "
                + "ON USERS.iduser=SUCCESS_REPORT.`USERS_iduser` "
                + "WHERE USERS.idnumber="+idNumber;
        
        ResultSet rs = null;
        SuccessReport success_report = null;
        
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            rs = statement.executeQuery(query);
            
            if(rs.next())
            {
                success_report = new SuccessReport();
                success_report.setSubject_name(rs.getString("subject_name"));
                success_report.setHours(rs.getString("hours"));
                success_report.setMidterm(rs.getString("midterm"));
                success_report.setFinal1(rs.getString("final"));
                success_report.setAverage(rs.getString("average"));
                success_report.setAttandance(rs.getString("attandance"));
                success_report.setSemester(rs.getString("semester"));
                success_report.setAcademic_year(rs.getString("academic_year"));
            }
        }finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
        return success_report;
    }
}
