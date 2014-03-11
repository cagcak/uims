/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jdbc.model.myinformation;

/**
 *
 * @author Çağrı Çakır
 */
public class GeneralInfo {
    
    private int id_general_info;
    private String faculty;
    private String department;
    private String group_name;
    private String supervisor;
    private String education;
    private String registration;
    private int USERS_iduser;

    public int getId_general_info() {
        return id_general_info;
    }

    public void setId_general_info(int id_general_info) {
        this.id_general_info = id_general_info;
    }

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

    public int getUSERS_iduser() {
        return USERS_iduser;
    }

    public void setUSERS_iduser(int USERS_iduser) {
        this.USERS_iduser = USERS_iduser;
    }

    @Override
    public String toString() {
        return "GeneralInfo{" + "id_general_info=" + id_general_info + ", faculty=" + faculty + ", department=" + department + ", group_name=" + group_name + ", supervisor=" + supervisor + ", registration=" + registration + ", USERS_iduser=" + USERS_iduser + '}';
    }
    
    
}
