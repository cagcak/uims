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
    
    
    private String registration;
    private String midterm;
    private String final1;
    private String USERS_idnumber;

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

    public String getUSERS_idnumber() {
        return USERS_idnumber;
    }

    public void setUSERS_idnumber(String USERS_idnumber) {
        this.USERS_idnumber = USERS_idnumber;
    }

    @Override
    public String toString() {
        return "AccountingStatusInfo{" + "registration=" + registration + ", midterm=" + midterm + ", final1=" + final1 + ", USERS_idnumber=" + USERS_idnumber + '}';
    }
    
}
