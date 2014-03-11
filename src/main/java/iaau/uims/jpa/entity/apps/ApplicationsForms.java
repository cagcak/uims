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
@Table(name = "applications_forms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApplicationsForms.findAll", query = "SELECT a FROM ApplicationsForms a"),
    @NamedQuery(name = "ApplicationsForms.findByIdapplicationsForms", query = "SELECT a FROM ApplicationsForms a WHERE a.applicationsFormsPK.idapplicationsForms = :idapplicationsForms"),
    @NamedQuery(name = "ApplicationsForms.findByReferenceType", query = "SELECT a FROM ApplicationsForms a WHERE a.referenceType = :referenceType"),
    @NamedQuery(name = "ApplicationsForms.findByLanguage", query = "SELECT a FROM ApplicationsForms a WHERE a.language = :language"),
    @NamedQuery(name = "ApplicationsForms.findByUSERSiduser", query = "SELECT a FROM ApplicationsForms a WHERE a.applicationsFormsPK.uSERSiduser = :uSERSiduser")})
public class ApplicationsForms implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected ApplicationsFormsPK applicationsFormsPK;
    
    @Size(max = 45)
    @Column(name = "reference_type")
    private String referenceType;
    
    @Size(max = 2)
    @Column(name = "language")
    private String language;
    
    @JoinColumn(name = "USERS_iduser", referencedColumnName = "iduser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public ApplicationsForms() {
    }

    public ApplicationsForms(ApplicationsFormsPK applicationsFormsPK) {
        this.applicationsFormsPK = applicationsFormsPK;
    }

    public ApplicationsForms(int idapplicationsForms, int uSERSiduser) {
        this.applicationsFormsPK = new ApplicationsFormsPK(idapplicationsForms, uSERSiduser);
    }

    public ApplicationsFormsPK getApplicationsFormsPK() {
        return applicationsFormsPK;
    }

    public void setApplicationsFormsPK(ApplicationsFormsPK applicationsFormsPK) {
        this.applicationsFormsPK = applicationsFormsPK;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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
        hash += (applicationsFormsPK != null ? applicationsFormsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationsForms)) {
            return false;
        }
        ApplicationsForms other = (ApplicationsForms) object;
        if ((this.applicationsFormsPK == null && other.applicationsFormsPK != null) || (this.applicationsFormsPK != null && !this.applicationsFormsPK.equals(other.applicationsFormsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iaau.uims.ApplicationsForms[ applicationsFormsPK=" + applicationsFormsPK + " ]";
    }
    
}
