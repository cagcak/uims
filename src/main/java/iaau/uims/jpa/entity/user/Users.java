/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.entity.user;
///////////////////////////////////////////////////
import iaau.uims.jpa.entity.apps.ApplicationsForms;
import iaau.uims.jpa.entity.apps.InformationDiploma;
import iaau.uims.jpa.entity.apps.Registration;
import iaau.uims.jpa.entity.success.SuccessReport;
import iaau.uims.jpa.entity.transcript.Trascript;
import iaau.uims.jpa.entity.myinformation.AccountingStatusInfo;
import iaau.uims.jpa.entity.myinformation.CurrentInfo;
import iaau.uims.jpa.entity.myinformation.GeneralInfo;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
///////////////////////////////////////////////////
/**
 * @author Çağrı Çakır
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByIduser", query = "SELECT u FROM Users u WHERE u.iduser = :iduser"),
    @NamedQuery(name = "Users.findByIdnumber", query = "SELECT u FROM Users u WHERE u.idnumber = :idnumber"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iduser")
    private Integer iduser;
    
    @Size(max = 11)
    @Column(name = "idnumber")
    private String idnumber;
    
    @Size(max = 45)
    @Column(name = "password")
    private String password;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<ApplicationsForms> applicationsFormsCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Trascript> trascriptCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<GeneralInfo> generalInfoCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<InformationDiploma> informationDiplomaCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Roles> rolesCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<AccountingStatusInfo> accountingStatusInfoCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<CurrentInfo> currentInfoCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Registration> registrationCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<SuccessReport> successReportCollection;

    public Users() {
    }

    public Users(Integer iduser) {
        this.iduser = iduser;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<ApplicationsForms> getApplicationsFormsCollection() {
        return applicationsFormsCollection;
    }

    public void setApplicationsFormsCollection(Collection<ApplicationsForms> applicationsFormsCollection) {
        this.applicationsFormsCollection = applicationsFormsCollection;
    }

    @XmlTransient
    public Collection<Trascript> getTrascriptCollection() {
        return trascriptCollection;
    }

    public void setTrascriptCollection(Collection<Trascript> trascriptCollection) {
        this.trascriptCollection = trascriptCollection;
    }

    @XmlTransient
    public Collection<GeneralInfo> getGeneralInfoCollection() {
        return generalInfoCollection;
    }

    public void setGeneralInfoCollection(Collection<GeneralInfo> generalInfoCollection) {
        this.generalInfoCollection = generalInfoCollection;
    }

    @XmlTransient
    public Collection<InformationDiploma> getInformationDiplomaCollection() {
        return informationDiplomaCollection;
    }

    public void setInformationDiplomaCollection(Collection<InformationDiploma> informationDiplomaCollection) {
        this.informationDiplomaCollection = informationDiplomaCollection;
    }

    @XmlTransient
    public Collection<Roles> getRolesCollection() {
        return rolesCollection;
    }

    public void setRolesCollection(Collection<Roles> rolesCollection) {
        this.rolesCollection = rolesCollection;
    }

    @XmlTransient
    public Collection<AccountingStatusInfo> getAccountingStatusInfoCollection() {
        return accountingStatusInfoCollection;
    }

    public void setAccountingStatusInfoCollection(Collection<AccountingStatusInfo> accountingStatusInfoCollection) {
        this.accountingStatusInfoCollection = accountingStatusInfoCollection;
    }

    @XmlTransient
    public Collection<CurrentInfo> getCurrentInfoCollection() {
        return currentInfoCollection;
    }

    public void setCurrentInfoCollection(Collection<CurrentInfo> currentInfoCollection) {
        this.currentInfoCollection = currentInfoCollection;
    }

    @XmlTransient
    public Collection<Registration> getRegistrationCollection() {
        return registrationCollection;
    }

    public void setRegistrationCollection(Collection<Registration> registrationCollection) {
        this.registrationCollection = registrationCollection;
    }

    @XmlTransient
    public Collection<SuccessReport> getSuccessReportCollection() {
        return successReportCollection;
    }

    public void setSuccessReportCollection(Collection<SuccessReport> successReportCollection) {
        this.successReportCollection = successReportCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iduser != null ? iduser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.iduser == null && other.iduser != null) || (this.iduser != null && !this.iduser.equals(other.iduser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iaau.uims.Users[ iduser=" + iduser + " ]";
    }
    
}
