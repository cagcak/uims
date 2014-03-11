/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.entity.myinformation;

import iaau.uims.jpa.entity.user.Users;
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
@Table(name = "accounting_status_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountingStatusInfo.findAll", query = "SELECT a FROM AccountingStatusInfo a"),
    @NamedQuery(name = "AccountingStatusInfo.findByIdAccounting", query = "SELECT a FROM AccountingStatusInfo a WHERE a.accountingStatusInfoPK.idAccounting = :idAccounting"),
    @NamedQuery(name = "AccountingStatusInfo.findByRegistration", query = "SELECT a FROM AccountingStatusInfo a WHERE a.registration = :registration"),
    @NamedQuery(name = "AccountingStatusInfo.findByMidterm", query = "SELECT a FROM AccountingStatusInfo a WHERE a.midterm = :midterm"),
    @NamedQuery(name = "AccountingStatusInfo.findByFinal1", query = "SELECT a FROM AccountingStatusInfo a WHERE a.final1 = :final1"),
    @NamedQuery(name = "AccountingStatusInfo.findByUSERSiduser", query = "SELECT a FROM AccountingStatusInfo a WHERE a.accountingStatusInfoPK.uSERSiduser = :uSERSiduser")})
public class AccountingStatusInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected AccountingStatusInfoPK accountingStatusInfoPK;
    
    @Size(max = 45)
    @Column(name = "registration")
    private String registration;
    
    @Size(max = 10)
    @Column(name = "midterm")
    private String midterm;
    
    @Size(max = 10)
    @Column(name = "final")
    private String final1;
    
    @JoinColumn(name = "USERS_iduser", referencedColumnName = "iduser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public AccountingStatusInfo() {
    }

    public AccountingStatusInfo(AccountingStatusInfoPK accountingStatusInfoPK) {
        this.accountingStatusInfoPK = accountingStatusInfoPK;
    }

    public AccountingStatusInfo(int idAccounting, int uSERSiduser) {
        this.accountingStatusInfoPK = new AccountingStatusInfoPK(idAccounting, uSERSiduser);
    }

    public AccountingStatusInfoPK getAccountingStatusInfoPK() {
        return accountingStatusInfoPK;
    }

    public void setAccountingStatusInfoPK(AccountingStatusInfoPK accountingStatusInfoPK) {
        this.accountingStatusInfoPK = accountingStatusInfoPK;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getMidterm() {
        return midterm;
    }

    public void setMidterm(String midterm) {
        this.midterm = midterm;
    }

    public String getFinal1() {
        return final1;
    }

    public void setFinal1(String final1) {
        this.final1 = final1;
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
        hash += (accountingStatusInfoPK != null ? accountingStatusInfoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountingStatusInfo)) {
            return false;
        }
        AccountingStatusInfo other = (AccountingStatusInfo) object;
        if ((this.accountingStatusInfoPK == null && other.accountingStatusInfoPK != null) || (this.accountingStatusInfoPK != null && !this.accountingStatusInfoPK.equals(other.accountingStatusInfoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iaau.uims.AccountingStatusInfo[ accountingStatusInfoPK=" + accountingStatusInfoPK + " ]";
    }
    
}
