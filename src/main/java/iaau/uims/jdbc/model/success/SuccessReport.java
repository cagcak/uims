/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jdbc.model.success;

/**
 *
 * @author Çağrı Çakır
 */
public class SuccessReport {
    
    private int id_success;
    private String subject_name;
    private String houres;
    private String midterm;
    private String final1;
    private String average;
    private String attandance;
    private String academic_year;
    private int USERS_iduser;

    public int getId_success() {
        return id_success;
    }

    public void setId_success(int id_success) {
        this.id_success = id_success;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getHoures() {
        return houres;
    }

    public void setHoures(String houres) {
        this.houres = houres;
    }

    public String getMidterm() {
        return midterm;
    }

    public void setMidterm(String midterm) {
        this.midterm = midterm;
    }

    public String getFinal1() {
        return final1;
    }

    public void setFinal1(String final1) {
        this.final1 = final1;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getAttandance() {
        return attandance;
    }

    public void setAttandance(String attandance) {
        this.attandance = attandance;
    }

    public String getAcademic_year() {
        return academic_year;
    }

    public void setAcademic_year(String academic_year) {
        this.academic_year = academic_year;
    }

    public int getUSERS_iduser() {
        return USERS_iduser;
    }

    public void setUSERS_iduser(int USERS_iduser) {
        this.USERS_iduser = USERS_iduser;
    }

    @Override
    public String toString() {
        return "SuccessReport{" + "id_success=" + id_success + ", subject_name=" + subject_name + ", houres=" + houres + ", midterm=" + midterm + ", final1=" + final1 + ", average=" + average + ", attandance=" + attandance + ", academic_year=" + academic_year + ", USERS_iduser=" + USERS_iduser + '}';
    }
    
    
}
