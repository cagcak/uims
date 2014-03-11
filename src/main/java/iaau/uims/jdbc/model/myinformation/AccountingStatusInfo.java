/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jdbc.model.myinformation;

/**
 *
 * @author Çağrı Çakır
 */
public class AccountingStatusInfo {
    
    private int id_accounting;
    private String registration;
    private String midterm;
    private String final1;
    private int USERS_iduser;

    public int getId_accounting() {
        return id_accounting;
    }

    public void setId_accounting(int id_accounting) {
        this.id_accounting = id_accounting;
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

    public int getUSERS_iduser() {
        return USERS_iduser;
    }

    public void setUSERS_iduser(int USERS_iduser) {
        this.USERS_iduser = USERS_iduser;
    }

    @Override
    public String toString() {
        return "AccountingStatusInfo{" + "id_accounting=" + id_accounting + ", registration=" + registration + ", midterm=" + midterm + ", final1=" + final1 + ", USERS_iduser=" + USERS_iduser + '}';
    }
    
    
}
