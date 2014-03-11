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
public class ApplicationsFormsPK implements Serializable {
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "idapplications_forms")
    private int idapplicationsForms;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERS_iduser")
    private int uSERSiduser;

    public ApplicationsFormsPK() {
    }

    public ApplicationsFormsPK(int idapplicationsForms, int uSERSiduser) {
        this.idapplicationsForms = idapplicationsForms;
        this.uSERSiduser = uSERSiduser;
    }

    public int getIdapplicationsForms() {
        return idapplicationsForms;
    }

    public void setIdapplicationsForms(int idapplicationsForms) {
        this.idapplicationsForms = idapplicationsForms;
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
        hash += (int) idapplicationsForms;
        hash += (int) uSERSiduser;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationsFormsPK)) {
            return false;
        }
        ApplicationsFormsPK other = (ApplicationsFormsPK) object;
        if (this.idapplicationsForms != other.idapplicationsForms) {
            return false;
        }
        if (this.uSERSiduser != other.uSERSiduser) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iaau.uims.ApplicationsFormsPK[ idapplicationsForms=" + idapplicationsForms + ", uSERSiduser=" + uSERSiduser + " ]";
    }
    
}
