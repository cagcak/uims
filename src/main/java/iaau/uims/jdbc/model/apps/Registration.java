/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jdbc.model.apps;

/**
 *
 * @author Çağrı Çakır
 */
public class Registration {
    
    private int idRegistration;
    private String subject_code;
    private String subject_name;
    private String semester;
    private String year;
    private String hours;
    private String credits;
    private String registration_status;
    private int USERS_iduser;

    public int getIdRegistration() {
        return idRegistration;
    }

    public void setIdRegistration(int idRegistration) {
        this.idRegistration = idRegistration;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getRegistration_status() {
        return registration_status;
    }

    public void setRegistration_status(String registration_status) {
        this.registration_status = registration_status;
    }

    public int getUSERS_iduser() {
        return USERS_iduser;
    }

    public void setUSERS_iduser(int USERS_iduser) {
        this.USERS_iduser = USERS_iduser;
    }

    @Override
    public String toString() {
        return "Registration{" + "idRegistration=" + idRegistration + ", subject_code=" + subject_code + ", subject_name=" + subject_name + ", semester=" + semester + ", year=" + year + ", hours=" + hours + ", credits=" + credits + ", registration_status=" + registration_status + ", USERS_iduser=" + USERS_iduser + '}';
    }
    
    
}
