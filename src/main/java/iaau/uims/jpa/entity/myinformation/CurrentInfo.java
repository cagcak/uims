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
@Table(name = "current_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CurrentInfo.findAll", query = "SELECT c FROM CurrentInfo c"),
    @NamedQuery(name = "CurrentInfo.findByIdCurrent", query = "SELECT c FROM CurrentInfo c WHERE c.currentInfoPK.idCurrent = :idCurrent"),
    @NamedQuery(name = "CurrentInfo.findByFullname", query = "SELECT c FROM CurrentInfo c WHERE c.fullname = :fullname"),
    @NamedQuery(name = "CurrentInfo.findByCurrentYear", query = "SELECT c FROM CurrentInfo c WHERE c.currentYear = :currentYear"),
    @NamedQuery(name = "CurrentInfo.findByCurrentSemester", query = "SELECT c FROM CurrentInfo c WHERE c.currentSemester = :currentSemester"),
    @NamedQuery(name = "CurrentInfo.findByCurrentMonth", query = "SELECT c FROM CurrentInfo c WHERE c.currentMonth = :currentMonth"),
    @NamedQuery(name = "CurrentInfo.findByCurrentExam", query = "SELECT c FROM CurrentInfo c WHERE c.currentExam = :currentExam"),
    @NamedQuery(name = "CurrentInfo.findByUSERSiduser", query = "SELECT c FROM CurrentInfo c WHERE c.currentInfoPK.uSERSiduser = :uSERSiduser")})
public class CurrentInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected CurrentInfoPK currentInfoPK;
    
    @Size(max = 45)
    @Column(name = "fullname")
    private String fullname;
    
    @Size(max = 45)
    @Column(name = "current_year")
    private String currentYear;
    
    @Size(max = 45)
    @Column(name = "current_semester")
    private String currentSemester;
    
    @Size(max = 45)
    @Column(name = "current_month")
    private String currentMonth;
    
    @Size(max = 45)
    @Column(name = "current_exam")
    private String currentExam;
    
    @JoinColumn(name = "USERS_iduser", referencedColumnName = "iduser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public CurrentInfo() {
    }

    public CurrentInfo(CurrentInfoPK currentInfoPK) {
        this.currentInfoPK = currentInfoPK;
    }

    public CurrentInfo(int idCurrent, int uSERSiduser) {
        this.currentInfoPK = new CurrentInfoPK(idCurrent, uSERSiduser);
    }

    public CurrentInfoPK getCurrentInfoPK() {
        return currentInfoPK;
    }

    public void setCurrentInfoPK(CurrentInfoPK currentInfoPK) {
        this.currentInfoPK = currentInfoPK;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(String currentYear) {
        this.currentYear = currentYear;
    }

    public String getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(String currentSemester) {
        this.currentSemester = currentSemester;
    }

    public String getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(String currentMonth) {
        this.currentMonth = currentMonth;
    }

    public String getCurrentExam() {
        return currentExam;
    }

    public void setCurrentExam(String currentExam) {
        this.currentExam = currentExam;
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
        hash += (currentInfoPK != null ? currentInfoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CurrentInfo)) {
            return false;
        }
        CurrentInfo other = (CurrentInfo) object;
        if ((this.currentInfoPK == null && other.currentInfoPK != null) || (this.currentInfoPK != null && !this.currentInfoPK.equals(other.currentInfoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iaau.uims.CurrentInfo[ currentInfoPK=" + currentInfoPK + " ]";
    }
    
}
