/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.entity.user;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Çağrı Çakır
 */
@Embeddable
public class RolesPK implements Serializable {
    
    @Basic(optional = false)
    @Column(name = "idROLES")
    private int idROLES;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERS_iduser")
    private int uSERSiduser;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERMS_idPERMS")
    private int pERMSidPERMS;

    public RolesPK() {
    }

    public RolesPK(int idROLES, int uSERSiduser, int pERMSidPERMS) {
        this.idROLES = idROLES;
        this.uSERSiduser = uSERSiduser;
        this.pERMSidPERMS = pERMSidPERMS;
    }

    public int getIdROLES() {
        return idROLES;
    }

    public void setIdROLES(int idROLES) {
        this.idROLES = idROLES;
    }

    public int getUSERSiduser() {
        return uSERSiduser;
    }

    public void setUSERSiduser(int uSERSiduser) {
        this.uSERSiduser = uSERSiduser;
    }

    public int getPERMSidPERMS() {
        return pERMSidPERMS;
    }

    public void setPERMSidPERMS(int pERMSidPERMS) {
        this.pERMSidPERMS = pERMSidPERMS;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idROLES;
        hash += (int) uSERSiduser;
        hash += (int) pERMSidPERMS;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesPK)) {
            return false;
        }
        RolesPK other = (RolesPK) object;
        if (this.idROLES != other.idROLES) {
            return false;
        }
        if (this.uSERSiduser != other.uSERSiduser) {
            return false;
        }
        if (this.pERMSidPERMS != other.pERMSidPERMS) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iaau.uims.RolesPK[ idROLES=" + idROLES + ", uSERSiduser=" + uSERSiduser + ", pERMSidPERMS=" + pERMSidPERMS + " ]";
    }
    
}
