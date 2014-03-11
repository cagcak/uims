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
public class AccountingStatusInfoPK implements Serializable {
    
    @Basic(optional = false)
    @Column(name = "id_accounting")
    private int idAccounting;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERS_iduser")
    private int uSERSiduser;

    public AccountingStatusInfoPK() {
    }

    public AccountingStatusInfoPK(int idAccounting, int uSERSiduser) {
        this.idAccounting = idAccounting;
        this.uSERSiduser = uSERSiduser;
    }

    public int getIdAccounting() {
        return idAccounting;
    }

    public void setIdAccounting(int idAccounting) {
        this.idAccounting = idAccounting;
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
        hash += (int) idAccounting;
        hash += (int) uSERSiduser;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountingStatusInfoPK)) {
            return false;
        }
        AccountingStatusInfoPK other = (AccountingStatusInfoPK) object;
        if (this.idAccounting != other.idAccounting) {
            return false;
        }
        if (this.uSERSiduser != other.uSERSiduser) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iaau.uims.AccountingStatusInfoPK[ idAccounting=" + idAccounting + ", uSERSiduser=" + uSERSiduser + " ]";
    }
    
}
