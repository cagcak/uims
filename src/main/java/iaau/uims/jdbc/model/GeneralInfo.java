/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jdbc.model;

/**
 *
 * @author Çağrı Çakır
 */
public class GeneralInfo {
    
    
    private String faculty;
    private String department;
    private String group_name;
    private String supervisor;
    private String education;
    private String registration;
    private String USERS_idnumber;

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getUSERS_idnumber() {
        return USERS_idnumber;
    }

    public void setUSERS_idnumber(String USERS_idnumber) {
        this.USERS_idnumber = USERS_idnumber;
    }

    @Override
    public String toString() {
        return "GeneralInfo{" + "faculty=" + faculty + ", department=" + department + ", group_name=" + group_name + ", supervisor=" + supervisor + ", education=" + education + ", registration=" + registration + ", USERS_idnumber=" + USERS_idnumber + '}';
    }

    
    
}
