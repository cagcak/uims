/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.entity.transcript;

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
@Table(name = "trascript")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trascript.findAll", query = "SELECT t FROM Trascript t"),
    @NamedQuery(name = "Trascript.findByIdTranscript", query = "SELECT t FROM Trascript t WHERE t.trascriptPK.idTranscript = :idTranscript"),
    @NamedQuery(name = "Trascript.findBySubjectCode", query = "SELECT t FROM Trascript t WHERE t.subjectCode = :subjectCode"),
    @NamedQuery(name = "Trascript.findBySubjectName", query = "SELECT t FROM Trascript t WHERE t.subjectName = :subjectName"),
    @NamedQuery(name = "Trascript.findBySemester", query = "SELECT t FROM Trascript t WHERE t.semester = :semester"),
    @NamedQuery(name = "Trascript.findByYear", query = "SELECT t FROM Trascript t WHERE t.year = :year"),
    @NamedQuery(name = "Trascript.findByCredits", query = "SELECT t FROM Trascript t WHERE t.credits = :credits"),
    @NamedQuery(name = "Trascript.findByAverage", query = "SELECT t FROM Trascript t WHERE t.average = :average"),
    @NamedQuery(name = "Trascript.findByUSERSiduser", query = "SELECT t FROM Trascript t WHERE t.trascriptPK.uSERSiduser = :uSERSiduser")})
public class Trascript implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected TrascriptPK trascriptPK;
    
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
    
    @Column(name = "credits")
    private Short credits;
    
    @Size(max = 3)
    @Column(name = "average")
    private String average;
    
    @JoinColumn(name = "USERS_iduser", referencedColumnName = "iduser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Trascript() {
    }

    public Trascript(TrascriptPK trascriptPK) {
        this.trascriptPK = trascriptPK;
    }

    public Trascript(int idTranscript, int uSERSiduser) {
        this.trascriptPK = new TrascriptPK(idTranscript, uSERSiduser);
    }

    public TrascriptPK getTrascriptPK() {
        return trascriptPK;
    }

    public void setTrascriptPK(TrascriptPK trascriptPK) {
        this.trascriptPK = trascriptPK;
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

    public Short getCredits() {
        return credits;
    }

    public void setCredits(Short credits) {
        this.credits = credits;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
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
        hash += (trascriptPK != null ? trascriptPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trascript)) {
            return false;
        }
        Trascript other = (Trascript) object;
        if ((this.trascriptPK == null && other.trascriptPK != null) || (this.trascriptPK != null && !this.trascriptPK.equals(other.trascriptPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iaau.uims.Trascript[ trascriptPK=" + trascriptPK + " ]";
    }
    
}
