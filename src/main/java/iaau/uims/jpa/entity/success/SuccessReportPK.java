/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.entity.success;

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
public class SuccessReportPK implements Serializable {
    
    @Basic(optional = false)
    @Column(name = "id_success")
    private int idSuccess;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERS_iduser")
    private int uSERSiduser;

    public SuccessReportPK() {
    }

    public SuccessReportPK(int idSuccess, int uSERSiduser) {
        this.idSuccess = idSuccess;
        this.uSERSiduser = uSERSiduser;
    }

    public int getIdSuccess() {
        return idSuccess;
    }

    public void setIdSuccess(int idSuccess) {
        this.idSuccess = idSuccess;
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
        hash += (int) idSuccess;
        hash += (int) uSERSiduser;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SuccessReportPK)) {
            return false;
        }
        SuccessReportPK other = (SuccessReportPK) object;
        if (this.idSuccess != other.idSuccess) {
            return false;
        }
        if (this.uSERSiduser != other.uSERSiduser) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iaau.uims.SuccessReportPK[ idSuccess=" + idSuccess + ", uSERSiduser=" + uSERSiduser + " ]";
    }
    
}
