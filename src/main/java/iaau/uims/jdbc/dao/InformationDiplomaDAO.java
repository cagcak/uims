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
import iaau.uims.jdbc.model.apps.InformationDiploma;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Çağrı Çakır
 */
public class InformationDiplomaDAO {
    
    private Connection connection;
    private Statement statement;

    public InformationDiplomaDAO() {
    }
    
    public InformationDiploma getInfoDiplomaByIDnumber(String idNumber) throws SQLException
    {
        String query = "SELECT USERS.idnumber, INFORMATION_DIPLOMA.firstname, "
                + "INFORMATION_DIPLOMA.lastname, INFORMATION_DIPLOMA.middlename, "
                + "INFORMATION_DIPLOMA.fullname_ru, INFORMATION_DIPLOMA.current_address, "
                + "INFORMATION_DIPLOMA.passport_no, INFORMATION_DIPLOMA.birthday, "
                + "INFORMATION_DIPLOMA.phone_number, INFORMATION_DIPLOMA.thesis_project_en, "
                + "INFORMATION_DIPLOMA.thesis_project_ru, INFORMATION_DIPLOMA.thesis_project_kg, "
                + "INFORMATION_DIPLOMA.year_of_school_graduation "
                + "FROM USERS "
                + "INNER JOIN INFORMATION_DIPLOMA "
                + "ON USERS.iduser=INFORMATION_DIPLOMA.`USERS_iduser` "
                + "WHERE USERS.idnumber="+idNumber;
        
        ResultSet rs = null;
        InformationDiploma infodiplom = null;
        
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            rs = statement.executeQuery(query);

            if (rs.next())
            {
                infodiplom = new InformationDiploma();
                infodiplom.setFirstname(rs.getString("firstname"));
                infodiplom.setLastname(rs.getString("lastname"));
                infodiplom.setMiddlename(rs.getString("middlename"));
                infodiplom.setFullname_ru(rs.getString("fullname_ru"));
                infodiplom.setCurrent_address(rs.getString("current_address"));
                infodiplom.setPassport_no(rs.getString("passport_no"));
                infodiplom.setBirthday(rs.getString("birthday"));
                infodiplom.setPhone_number(rs.getString("phone_number"));
                infodiplom.setThesis_project_en(rs.getString("thesis_project_en"));
                infodiplom.setThesis_project_ru(rs.getString("thesis_project_ru"));
                infodiplom.setThesis_project_kg(rs.getString("thesis_project_kg"));
                infodiplom.setYear_of_school_graduation(rs.getString("year_of_school_graduation"));
            }
        }finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
        return infodiplom;
    }
}
