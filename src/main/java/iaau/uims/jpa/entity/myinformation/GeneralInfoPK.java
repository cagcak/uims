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
public class GeneralInfoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_general_info")
    private int idGeneralInfo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERS_iduser")
    private int uSERSiduser;

    public GeneralInfoPK() {
    }

    public GeneralInfoPK(int idGeneralInfo, int uSERSiduser) {
        this.idGeneralInfo = idGeneralInfo;
        this.uSERSiduser = uSERSiduser;
    }

    public int getIdGeneralInfo() {
        return idGeneralInfo;
    }

    public void setIdGeneralInfo(int idGeneralInfo) {
        this.idGeneralInfo = idGeneralInfo;
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
        hash += (int) idGeneralInfo;
        hash += (int) uSERSiduser;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeneralInfoPK)) {
            return false;
        }
        GeneralInfoPK other = (GeneralInfoPK) object;
        if (this.idGeneralInfo != other.idGeneralInfo) {
            return false;
        }
        if (this.uSERSiduser != other.uSERSiduser) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iaau.uims.GeneralInfoPK[ idGeneralInfo=" + idGeneralInfo + ", uSERSiduser=" + uSERSiduser + " ]";
    }
    
}
