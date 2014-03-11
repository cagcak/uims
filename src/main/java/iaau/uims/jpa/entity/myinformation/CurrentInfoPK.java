/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.entity.myinformation;

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
public class CurrentInfoPK implements Serializable {
    
    @Basic(optional = false)
    @Column(name = "id_current")
    private int idCurrent;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERS_iduser")
    private int uSERSiduser;

    public CurrentInfoPK() {
    }

    public CurrentInfoPK(int idCurrent, int uSERSiduser) {
        this.idCurrent = idCurrent;
        this.uSERSiduser = uSERSiduser;
    }

    public int getIdCurrent() {
        return idCurrent;
    }

    public void setIdCurrent(int idCurrent) {
        this.idCurrent = idCurrent;
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
        hash += (int) idCurrent;
        hash += (int) uSERSiduser;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CurrentInfoPK)) {
            return false;
        }
        CurrentInfoPK other = (CurrentInfoPK) object;
        if (this.idCurrent != other.idCurrent) {
            return false;
        }
        if (this.uSERSiduser != other.uSERSiduser) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iaau.uims.CurrentInfoPK[ idCurrent=" + idCurrent + ", uSERSiduser=" + uSERSiduser + " ]";
    }
    
}
