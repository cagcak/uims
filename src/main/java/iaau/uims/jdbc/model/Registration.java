/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jdbc.model;

/**
 *
 * @author Çağrı Çakır
 */
public class Registration {
    
    private int id_registration;
    private String subject_code;
    private String subject_name;
    private String semester;
    private String year;
    private String hours;
    private String credits;
    private String registration_status;
    private String USERS_idnumber;

    public int getId_registration() {
        return id_registration;
    }

    public void setId_registration(int id_registration) {
        this.id_registration = id_registration;
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

    public String getUSERS_idnumber() {
        return USERS_idnumber;
    }

    public void setUSERS_idnumber(String USERS_idnumber) {
        this.USERS_idnumber = USERS_idnumber;
    }

    @Override
    public String toString() {
        return "Registration{" + "id_registration=" + id_registration + ", subject_code=" + subject_code + ", subject_name=" + subject_name + ", semester=" + semester + ", year=" + year + ", hours=" + hours + ", credits=" + credits + ", registration_status=" + registration_status + ", USERS_idnumber=" + USERS_idnumber + '}';
    }

    
    
}
