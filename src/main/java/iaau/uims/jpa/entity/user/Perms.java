/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.entity.user;

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

/**
 *
 * @author Çağrı Çakır
 */
@Entity
@Table(name = "perms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perms.findAll", query = "SELECT p FROM Perms p"),
    @NamedQuery(name = "Perms.findByIdPERMS", query = "SELECT p FROM Perms p WHERE p.idPERMS = :idPERMS"),
    @NamedQuery(name = "Perms.findByUserRole", query = "SELECT p FROM Perms p WHERE p.userRole = :userRole"),
    @NamedQuery(name = "Perms.findByPermission", query = "SELECT p FROM Perms p WHERE p.permission = :permission")})
public class Perms implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPERMS")
    private Integer idPERMS;
    
    @Size(max = 5)
    @Column(name = "user_role")
    private String userRole;
    
    @Size(max = 5)
    @Column(name = "permission")
    private String permission;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perms")
    private Collection<Roles> rolesCollection;

    public Perms() {
    }

    public Perms(Integer idPERMS) {
        this.idPERMS = idPERMS;
    }

    public Integer getIdPERMS() {
        return idPERMS;
    }

    public void setIdPERMS(Integer idPERMS) {
        this.idPERMS = idPERMS;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @XmlTransient
    public Collection<Roles> getRolesCollection() {
        return rolesCollection;
    }

    public void setRolesCollection(Collection<Roles> rolesCollection) {
        this.rolesCollection = rolesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPERMS != null ? idPERMS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perms)) {
            return false;
        }
        Perms other = (Perms) object;
        if ((this.idPERMS == null && other.idPERMS != null) || (this.idPERMS != null && !this.idPERMS.equals(other.idPERMS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iaau.uims.Perms[ idPERMS=" + idPERMS + " ]";
    }
    
}
