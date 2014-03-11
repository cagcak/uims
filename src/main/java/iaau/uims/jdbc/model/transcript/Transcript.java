/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jdbc.model.transcript;

/**
 *
 * @author Çağrı Çakır
 */
public class Transcript {
    
    private int id_transcript;
    private String subject_code;
    private String subject_name;
    private String semester;
    private String year;
    private String credits;
    private String average;
    private int USERS_iduser;

    public int getId_transcript() {
        return id_transcript;
    }

    public void setId_transcript(int id_transcript) {
        this.id_transcript = id_transcript;
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

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public int getUSERS_iduser() {
        return USERS_iduser;
    }

    public void setUSERS_iduser(int USERS_iduser) {
        this.USERS_iduser = USERS_iduser;
    }

    @Override
    public String toString() {
        return "Transcript{" + "id_transcript=" + id_transcript + ", subject_code=" + subject_code + ", subject_name=" + subject_name + ", semester=" + semester + ", year=" + year + ", credits=" + credits + ", average=" + average + ", USERS_iduser=" + USERS_iduser + '}';
    }
    
    
    
}
