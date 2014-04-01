/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jdbc.model.apps;

/**
 *
 * @author Çağrı Çakır
 */
public class ApplicationsForms {
    
    
    private String reference_type;
    private String language;
    private String USERS_idnumber;

    public String getReference_type() {
        return reference_type;
    }

    public void setReference_type(String reference_type) {
        this.reference_type = reference_type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUSERS_idnumber() {
        return USERS_idnumber;
    }

    public void setUSERS_idnumber(String USERS_idnumber) {
        this.USERS_idnumber = USERS_idnumber;
    }

    @Override
    public String toString() {
        return "ApplicationsForms{" + "reference_type=" + reference_type + ", language=" + language + ", USERS_idnumber=" + USERS_idnumber + '}';
    }

    
    
    
}
