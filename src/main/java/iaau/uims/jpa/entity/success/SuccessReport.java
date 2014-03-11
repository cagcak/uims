/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.entity.success;

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
@Table(name = "success_report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SuccessReport.findAll", query = "SELECT s FROM SuccessReport s"),
    @NamedQuery(name = "SuccessReport.findByIdSuccess", query = "SELECT s FROM SuccessReport s WHERE s.successReportPK.idSuccess = :idSuccess"),
    @NamedQuery(name = "SuccessReport.findBySubjectName", query = "SELECT s FROM SuccessReport s WHERE s.subjectName = :subjectName"),
    @NamedQuery(name = "SuccessReport.findByHours", query = "SELECT s FROM SuccessReport s WHERE s.hours = :hours"),
    @NamedQuery(name = "SuccessReport.findByMidterm", query = "SELECT s FROM SuccessReport s WHERE s.midterm = :midterm"),
    @NamedQuery(name = "SuccessReport.findByFinal1", query = "SELECT s FROM SuccessReport s WHERE s.final1 = :final1"),
    @NamedQuery(name = "SuccessReport.findByAverage", query = "SELECT s FROM SuccessReport s WHERE s.average = :average"),
    @NamedQuery(name = "SuccessReport.findByAttandance", query = "SELECT s FROM SuccessReport s WHERE s.attandance = :attandance"),
    @NamedQuery(name = "SuccessReport.findBySemester", query = "SELECT s FROM SuccessReport s WHERE s.semester = :semester"),
    @NamedQuery(name = "SuccessReport.findByAcademicYear", query = "SELECT s FROM SuccessReport s WHERE s.academicYear = :academicYear"),
    @NamedQuery(name = "SuccessReport.findByUSERSiduser", query = "SELECT s FROM SuccessReport s WHERE s.successReportPK.uSERSiduser = :uSERSiduser")})
public class SuccessReport implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected SuccessReportPK successReportPK;
    
    @Size(max = 45)
    @Column(name = "subject_name")
    private String subjectName;
    
    @Column(name = "hours")
    private Short hours;
    
    @Column(name = "midterm")
    private Short midterm;
    
    @Size(max = 3)
    @Column(name = "final")
    private String final1;
    
    @Column(name = "average")
    private Short average;
    
    @Column(name = "attandance")
    private Short attandance;
    
    @Size(max = 10)
    @Column(name = "semester")
    private String semester;
    
    @Size(max = 10)
    @Column(name = "academic_year")
    private String academicYear;
    
    @JoinColumn(name = "USERS_iduser", referencedColumnName = "iduser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public SuccessReport() {
    }

    public SuccessReport(SuccessReportPK successReportPK) {
        this.successReportPK = successReportPK;
    }

    public SuccessReport(int idSuccess, int uSERSiduser) {
        this.successReportPK = new SuccessReportPK(idSuccess, uSERSiduser);
    }

    public SuccessReportPK getSuccessReportPK() {
        return successReportPK;
    }

    public void setSuccessReportPK(SuccessReportPK successReportPK) {
        this.successReportPK = successReportPK;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Short getHours() {
        return hours;
    }

    public void setHours(Short hours) {
        this.hours = hours;
    }

    public Short getMidterm() {
        return midterm;
    }

    public void setMidterm(Short midterm) {
        this.midterm = midterm;
    }

    public String getFinal1() {
        return final1;
    }

    public void setFinal1(String final1) {
        this.final1 = final1;
    }

    public Short getAverage() {
        return average;
    }

    public void setAverage(Short average) {
        this.average = average;
    }

    public Short getAttandance() {
        return attandance;
    }

    public void setAttandance(Short attandance) {
        this.attandance = attandance;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
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
        hash += (successReportPK != null ? successReportPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SuccessReport)) {
            return false;
        }
        SuccessReport other = (SuccessReport) object;
        if ((this.successReportPK == null && other.successReportPK != null) || (this.successReportPK != null && !this.successReportPK.equals(other.successReportPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iaau.uims.SuccessReport[ successReportPK=" + successReportPK + " ]";
    }
    
}
