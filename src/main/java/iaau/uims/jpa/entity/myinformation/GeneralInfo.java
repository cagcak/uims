/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.entity.myinformation;

import iaau.uims.jpa.entity.user.Users;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Çağrı Çakır
 */
@Entity
@Table(name = "general_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneralInfo.findAll", query = "SELECT g FROM GeneralInfo g"),
    @NamedQuery(name = "GeneralInfo.findByIdGeneralInfo", query = "SELECT g FROM GeneralInfo g WHERE g.generalInfoPK.idGeneralInfo = :idGeneralInfo"),
    @NamedQuery(name = "GeneralInfo.findByFaculty", query = "SELECT g FROM GeneralInfo g WHERE g.faculty = :faculty"),
    @NamedQuery(name = "GeneralInfo.findByDepartment", query = "SELECT g FROM GeneralInfo g WHERE g.department = :department"),
    @NamedQuery(name = "GeneralInfo.findByGroupName", query = "SELECT g FROM GeneralInfo g WHERE g.groupName = :groupName"),
    @NamedQuery(name = "GeneralInfo.findBySupervisor", query = "SELECT g FROM GeneralInfo g WHERE g.supervisor = :supervisor"),
    @NamedQuery(name = "GeneralInfo.findByEducation", query = "SELECT g FROM GeneralInfo g WHERE g.education = :education"),
    @NamedQuery(name = "GeneralInfo.findByRegistration", query = "SELECT g FROM GeneralInfo g WHERE g.registration = :registration"),
    @NamedQuery(name = "GeneralInfo.findByUSERSiduser", query = "SELECT g FROM GeneralInfo g WHERE g.generalInfoPK.uSERSiduser = :uSERSiduser")})
public class GeneralInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected GeneralInfoPK generalInfoPK;
    
    @Size(max = 45)
    @Column(name = "faculty")
    private String faculty;
    
    @Size(max = 45)
    @Column(name = "department")
    private String department;
    
    @Size(max = 45)
    @Column(name = "group_name")
    private String groupName;
    
    @Size(max = 45)
    @Column(name = "supervisor")
    private String supervisor;
    
    @Size(max = 45)
    @Column(name = "education")
    private String education;
    
    @Size(max = 45)
    @Column(name = "registration")
    private String registration;
    
    @JoinColumn(name = "USERS_iduser", referencedColumnName = "iduser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public GeneralInfo() {
    }

    public GeneralInfo(GeneralInfoPK generalInfoPK) {
        this.generalInfoPK = generalInfoPK;
    }

    public GeneralInfo(int idGeneralInfo, int uSERSiduser) {
        this.generalInfoPK = new GeneralInfoPK(idGeneralInfo, uSERSiduser);
    }

    public GeneralInfoPK getGeneralInfoPK() {
        return generalInfoPK;
    }

    public void setGeneralInfoPK(GeneralInfoPK generalInfoPK) {
        this.generalInfoPK = generalInfoPK;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (generalInfoPK != null ? generalInfoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeneralInfo)) {
            return false;
        }
        GeneralInfo other = (GeneralInfo) object;
        if ((this.generalInfoPK == null && other.generalInfoPK != null) || (this.generalInfoPK != null && !this.generalInfoPK.equals(other.generalInfoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iaau.uims.GeneralInfo[ generalInfoPK=" + generalInfoPK + " ]";
    }
    
}
