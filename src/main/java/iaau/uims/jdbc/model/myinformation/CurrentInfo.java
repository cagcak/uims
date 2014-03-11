/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jdbc.model.myinformation;

/**
 *
 * @author Çağrı Çakır
 */
public class CurrentInfo {
    
    private int id_current;
    private String fullname;
    private String current_year;
    private String current_semester;
    private String current_month;
    private String current_exam;
    private int USERS_iduser;

    public int getId_current() {
        return id_current;
    }

    public void setId_current(int id_current) {
        this.id_current = id_current;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCurrent_year() {
        return current_year;
    }

    public void setCurrent_year(String current_year) {
        this.current_year = current_year;
    }

    public String getCurrent_semester() {
        return current_semester;
    }

    public void setCurrent_semester(String current_semester) {
        this.current_semester = current_semester;
    }

    public String getCurrent_month() {
        return current_month;
    }

    public void setCurrent_month(String current_month) {
        this.current_month = current_month;
    }

    public String getCurrent_exam() {
        return current_exam;
    }

    public void setCurrent_exam(String current_exam) {
        this.current_exam = current_exam;
    }

    public int getUSERS_iduser() {
        return USERS_iduser;
    }

    public void setUSERS_iduser(int USERS_iduser) {
        this.USERS_iduser = USERS_iduser;
    }

    @Override
    public String toString() {
        return "CurrentInfo{" + "id_current=" + id_current + ", fullname=" + fullname + ", current_year=" + current_year + ", current_semester=" + current_semester + ", current_month=" + current_month + ", current_exam=" + current_exam + ", USERS_iduser=" + USERS_iduser + '}';
    }
    
    
    
}
