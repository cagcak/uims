/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.entity.apps;

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
public class RegistrationPK implements Serializable {
    
    @Basic(optional = false)
    @Column(name = "idRegistration")
    private int idRegistration;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERS_iduser")
    private int uSERSiduser;

    public RegistrationPK() {
    }

    public RegistrationPK(int idRegistration, int uSERSiduser) {
        this.idRegistration = idRegistration;
        this.uSERSiduser = uSERSiduser;
    }

    public int getIdRegistration() {
        return idRegistration;
    }

    public void setIdRegistration(int idRegistration) {
        this.idRegistration = idRegistration;
    }

    public int getUSERSiduser() {
        return uSERSiduser;
    }

    public void setUSERSiduser(int uSERSiduser) {
        this.uSERSiduser = uSERSiduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRegistration;
        hash += (int) uSERSiduser;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistrationPK)) {
            return false;
        }
        RegistrationPK other = (RegistrationPK) object;
        if (this.idRegistration != other.idRegistration) {
            return false;
        }
        if (this.uSERSiduser != other.uSERSiduser) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iaau.uims.RegistrationPK[ idRegistration=" + idRegistration + ", uSERSiduser=" + uSERSiduser + " ]";
    }
    
}
