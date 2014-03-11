/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.entity.user;

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
@Table(name = "roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r"),
    @NamedQuery(name = "Roles.findByIdROLES", query = "SELECT r FROM Roles r WHERE r.rolesPK.idROLES = :idROLES"),
    @NamedQuery(name = "Roles.findByIdnumber", query = "SELECT r FROM Roles r WHERE r.idnumber = :idnumber"),
    @NamedQuery(name = "Roles.findByUserRole", query = "SELECT r FROM Roles r WHERE r.userRole = :userRole"),
    @NamedQuery(name = "Roles.findByUSERSiduser", query = "SELECT r FROM Roles r WHERE r.rolesPK.uSERSiduser = :uSERSiduser"),
    @NamedQuery(name = "Roles.findByPERMSidPERMS", query = "SELECT r FROM Roles r WHERE r.rolesPK.pERMSidPERMS = :pERMSidPERMS")})
public class Roles implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected RolesPK rolesPK;
    
    @Size(max = 11)
    @Column(name = "idnumber")
    private String idnumber;
    
    @Size(max = 5)
    @Column(name = "user_role")
    private String userRole;
    
    @JoinColumn(name = "PERMS_idPERMS", referencedColumnName = "idPERMS", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Perms perms;
    
    @JoinColumn(name = "USERS_iduser", referencedColumnName = "iduser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Roles() {
    }

    public Roles(RolesPK rolesPK) {
        this.rolesPK = rolesPK;
    }

    public Roles(int idROLES, int uSERSiduser, int pERMSidPERMS) {
        this.rolesPK = new RolesPK(idROLES, uSERSiduser, pERMSidPERMS);
    }

    public RolesPK getRolesPK() {
        return rolesPK;
    }

    public void setRolesPK(RolesPK rolesPK) {
        this.rolesPK = rolesPK;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Perms getPerms() {
        return perms;
    }

    public void setPerms(Perms perms) {
        this.perms = perms;
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
        hash += (rolesPK != null ? rolesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.rolesPK == null && other.rolesPK != null) || (this.rolesPK != null && !this.rolesPK.equals(other.rolesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iaau.uims.Roles[ rolesPK=" + rolesPK + " ]";
    }
    
}
