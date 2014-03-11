/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.entity.apps;

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
@Table(name = "registration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registration.findAll", query = "SELECT r FROM Registration r"),
    @NamedQuery(name = "Registration.findByIdRegistration", query = "SELECT r FROM Registration r WHERE r.registrationPK.idRegistration = :idRegistration"),
    @NamedQuery(name = "Registration.findBySubjectCode", query = "SELECT r FROM Registration r WHERE r.subjectCode = :subjectCode"),
    @NamedQuery(name = "Registration.findBySubjectName", query = "SELECT r FROM Registration r WHERE r.subjectName = :subjectName"),
    @NamedQuery(name = "Registration.findBySemester", query = "SELECT r FROM Registration r WHERE r.semester = :semester"),
    @NamedQuery(name = "Registration.findByYear", query = "SELECT r FROM Registration r WHERE r.year = :year"),
    @NamedQuery(name = "Registration.findByHours", query = "SELECT r FROM Registration r WHERE r.hours = :hours"),
    @NamedQuery(name = "Registration.findByCredits", query = "SELECT r FROM Registration r WHERE r.credits = :credits"),
    @NamedQuery(name = "Registration.findByRegistrationStatus", query = "SELECT r FROM Registration r WHERE r.registrationStatus = :registrationStatus"),
    @NamedQuery(name = "Registration.findByUSERSiduser", query = "SELECT r FROM Registration r WHERE r.registrationPK.uSERSiduser = :uSERSiduser")})
public class Registration implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected RegistrationPK registrationPK;
    
    @Size(max = 10)
    @Column(name = "subject_code")
    private String subjectCode;
    
    @Size(max = 45)
    @Column(name = "subject_name")
    private String subjectName;
    
    @Size(max = 7)
    @Column(name = "semester")
    private String semester;
    
    @Size(max = 10)
    @Column(name = "year")
    private String year;
    
    @Column(name = "hours")
    private Short hours;
    
    @Column(name = "credits")
    private Short credits;
    
    @Column(name = "registration_status")
    private Integer registrationStatus;
    
    @JoinColumn(name = "USERS_iduser", referencedColumnName = "iduser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Registration() {
    }

    public Registration(RegistrationPK registrationPK) {
        this.registrationPK = registrationPK;
    }

    public Registration(int idRegistration, int uSERSiduser) {
        this.registrationPK = new RegistrationPK(idRegistration, uSERSiduser);
    }

    public RegistrationPK getRegistrationPK() {
        return registrationPK;
    }

    public void setRegistrationPK(RegistrationPK registrationPK) {
        this.registrationPK = registrationPK;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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

    public Short getHours() {
        return hours;
    }

    public void setHours(Short hours) {
        this.hours = hours;
    }

    public Short getCredits() {
        return credits;
    }

    public void setCredits(Short credits) {
        this.credits = credits;
    }

    public Integer getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(Integer registrationStatus) {
        this.registrationStatus = registrationStatus;
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
        hash += (registrationPK != null ? registrationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registration)) {
            return false;
        }
        Registration other = (Registration) object;
        if ((this.registrationPK == null && other.registrationPK != null) || (this.registrationPK != null && !this.registrationPK.equals(other.registrationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iaau.uims.Registration[ registrationPK=" + registrationPK + " ]";
    }
    
}
