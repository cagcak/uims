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
public class InformationDiplomaPK implements Serializable {
    
    @Basic(optional = false)
    @Column(name = "idinformation_diploma")
    private int idinformationDiploma;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERS_iduser")
    private int uSERSiduser;

    public InformationDiplomaPK() {
    }

    public InformationDiplomaPK(int idinformationDiploma, int uSERSiduser) {
        this.idinformationDiploma = idinformationDiploma;
        this.uSERSiduser = uSERSiduser;
    }

    public int getIdinformationDiploma() {
        return idinformationDiploma;
    }

    public void setIdinformationDiploma(int idinformationDiploma) {
        this.idinformationDiploma = idinformationDiploma;
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
        hash += (int) idinformationDiploma;
        hash += (int) uSERSiduser;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformationDiplomaPK)) {
            return false;
        }
        InformationDiplomaPK other = (InformationDiplomaPK) object;
        if (this.idinformationDiploma != other.idinformationDiploma) {
            return false;
        }
        if (this.uSERSiduser != other.uSERSiduser) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iaau.uims.InformationDiplomaPK[ idinformationDiploma=" + idinformationDiploma + ", uSERSiduser=" + uSERSiduser + " ]";
    }
    
}
