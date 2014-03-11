/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.entity.apps;

import iaau.uims.jpa.entity.user.Users;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Çağrı Çakır
 */
@Entity
@Table(name = "information_diploma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InformationDiploma.findAll", query = "SELECT \u0131 FROM InformationDiploma \u0131"),
    @NamedQuery(name = "InformationDiploma.findByIdinformationDiploma", query = "SELECT \u0131 FROM InformationDiploma \u0131 WHERE \u0131.informationDiplomaPK.idinformationDiploma = :idinformationDiploma"),
    @NamedQuery(name = "InformationDiploma.findByFirstname", query = "SELECT \u0131 FROM InformationDiploma \u0131 WHERE \u0131.firstname = :firstname"),
    @NamedQuery(name = "InformationDiploma.findByLastname", query = "SELECT \u0131 FROM InformationDiploma \u0131 WHERE \u0131.lastname = :lastname"),
    @NamedQuery(name = "InformationDiploma.findByMiddlename", query = "SELECT \u0131 FROM InformationDiploma \u0131 WHERE \u0131.middlename = :middlename"),
    @NamedQuery(name = "InformationDiploma.findByFullnameRu", query = "SELECT \u0131 FROM InformationDiploma \u0131 WHERE \u0131.fullnameRu = :fullnameRu"),
    @NamedQuery(name = "InformationDiploma.findByCurrentAddress", query = "SELECT \u0131 FROM InformationDiploma \u0131 WHERE \u0131.currentAddress = :currentAddress"),
    @NamedQuery(name = "InformationDiploma.findByPassportNo", query = "SELECT \u0131 FROM InformationDiploma \u0131 WHERE \u0131.passportNo = :passportNo"),
    @NamedQuery(name = "InformationDiploma.findByBirthday", query = "SELECT \u0131 FROM InformationDiploma \u0131 WHERE \u0131.birthday = :birthday"),
    @NamedQuery(name = "InformationDiploma.findByPhoneNumber", query = "SELECT \u0131 FROM InformationDiploma \u0131 WHERE \u0131.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "InformationDiploma.findByThesisProjectEn", query = "SELECT \u0131 FROM InformationDiploma \u0131 WHERE \u0131.thesisProjectEn = :thesisProjectEn"),
    @NamedQuery(name = "InformationDiploma.findByThesisProjectRu", query = "SELECT \u0131 FROM InformationDiploma \u0131 WHERE \u0131.thesisProjectRu = :thesisProjectRu"),
    @NamedQuery(name = "InformationDiploma.findByThesisProjectKg", query = "SELECT \u0131 FROM InformationDiploma \u0131 WHERE \u0131.thesisProjectKg = :thesisProjectKg"),
    @NamedQuery(name = "InformationDiploma.findByYearOfSchoolGraduation", query = "SELECT \u0131 FROM InformationDiploma \u0131 WHERE \u0131.yearOfSchoolGraduation = :yearOfSchoolGraduation"),
    @NamedQuery(name = "InformationDiploma.findByUSERSiduser", query = "SELECT \u0131 FROM InformationDiploma \u0131 WHERE \u0131.informationDiplomaPK.uSERSiduser = :uSERSiduser")})
public class InformationDiploma implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected InformationDiplomaPK informationDiplomaPK;
    
    @Size(max = 45)
    @Column(name = "firstname")
    private String firstname;
    
    @Size(max = 45)
    @Column(name = "lastname")
    private String lastname;
    
    @Size(max = 45)
    @Column(name = "middlename")
    private String middlename;
    
    @Size(max = 45)
    @Column(name = "fullname_ru")
    private String fullnameRu;
    
    @Size(max = 45)
    @Column(name = "current_address")
    private String currentAddress;
    
    @Size(max = 45)
    @Column(name = "passport_no")
    private String passportNo;
    
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    
    @Size(max = 45)
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Size(max = 45)
    @Column(name = "thesis_project_en")
    private String thesisProjectEn;
    
    @Size(max = 45)
    @Column(name = "thesis_project_ru")
    private String thesisProjectRu;
    
    @Size(max = 45)
    @Column(name = "thesis_project_kg")
    private String thesisProjectKg;
    
    @Column(name = "year_of_school_graduation")
    @Temporal(TemporalType.DATE)
    private Date yearOfSchoolGraduation;
    
    @JoinColumn(name = "USERS_iduser", referencedColumnName = "iduser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public InformationDiploma() {
    }

    public InformationDiploma(InformationDiplomaPK informationDiplomaPK) {
        this.informationDiplomaPK = informationDiplomaPK;
    }

    public InformationDiploma(int idinformationDiploma, int uSERSiduser) {
        this.informationDiplomaPK = new InformationDiplomaPK(idinformationDiploma, uSERSiduser);
    }

    public InformationDiplomaPK getInformationDiplomaPK() {
        return informationDiplomaPK;
    }

    public void setInformationDiplomaPK(InformationDiplomaPK informationDiplomaPK) {
        this.informationDiplomaPK = informationDiplomaPK;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getFullnameRu() {
        return fullnameRu;
    }

    public void setFullnameRu(String fullnameRu) {
        this.fullnameRu = fullnameRu;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getThesisProjectEn() {
        return thesisProjectEn;
    }

    public void setThesisProjectEn(String thesisProjectEn) {
        this.thesisProjectEn = thesisProjectEn;
    }

    public String getThesisProjectRu() {
        return thesisProjectRu;
    }

    public void setThesisProjectRu(String thesisProjectRu) {
        this.thesisProjectRu = thesisProjectRu;
    }

    public String getThesisProjectKg() {
        return thesisProjectKg;
    }

    public void setThesisProjectKg(String thesisProjectKg) {
        this.thesisProjectKg = thesisProjectKg;
    }

    public Date getYearOfSchoolGraduation() {
        return yearOfSchoolGraduation;
    }

    public void setYearOfSchoolGraduation(Date yearOfSchoolGraduation) {
        this.yearOfSchoolGraduation = yearOfSchoolGraduation;
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
        hash += (informationDiplomaPK != null ? informationDiplomaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformationDiploma)) {
            return false;
        }
        InformationDiploma other = (InformationDiploma) object;
        if ((this.informationDiplomaPK == null && other.informationDiplomaPK != null) || (this.informationDiplomaPK != null && !this.informationDiplomaPK.equals(other.informationDiplomaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iaau.uims.InformationDiploma[ informationDiplomaPK=" + informationDiplomaPK + " ]";
    }
    
}
