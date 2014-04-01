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
    
    
    private String fullname;
    private String current_year;
    private String current_semester;
    private String current_month;
    private String current_exam;
    private String USERS_idnumber;

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

    public String getUSERS_idnumber() {
        return USERS_idnumber;
    }

    public void setUSERS_idnumber(String USERS_idnumber) {
        this.USERS_idnumber = USERS_idnumber;
    }

    @Override
    public String toString() {
        return "CurrentInfo{" + "fullname=" + fullname + ", current_year=" + current_year + ", current_semester=" + current_semester + ", current_month=" + current_month + ", current_exam=" + current_exam + ", USERS_idnumber=" + USERS_idnumber + '}';
    }

    
    
    
    
}
