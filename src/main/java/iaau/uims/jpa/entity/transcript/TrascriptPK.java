/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.entity.transcript;

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
public class TrascriptPK implements Serializable {
    
    @Basic(optional = false)
    @Column(name = "id_transcript")
    private int idTranscript;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERS_iduser")
    private int uSERSiduser;

    public TrascriptPK() {
    }

    public TrascriptPK(int idTranscript, int uSERSiduser) {
        this.idTranscript = idTranscript;
        this.uSERSiduser = uSERSiduser;
    }

    public int getIdTranscript() {
        return idTranscript;
    }

    public void setIdTranscript(int idTranscript) {
        this.idTranscript = idTranscript;
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
        hash += (int) idTranscript;
        hash += (int) uSERSiduser;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrascriptPK)) {
            return false;
        }
        TrascriptPK other = (TrascriptPK) object;
        if (this.idTranscript != other.idTranscript) {
            return false;
        }
        if (this.uSERSiduser != other.uSERSiduser) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iaau.uims.TrascriptPK[ idTranscript=" + idTranscript + ", uSERSiduser=" + uSERSiduser + " ]";
    }
    
}
