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
    
    private int idapplications_forms;
    private String reference_type;
    private String language;
    private int USERS_iduser;

    public int getIdapplications_forms() {
        return idapplications_forms;
    }

    public void setIdapplications_forms(int idapplications_forms) {
        this.idapplications_forms = idapplications_forms;
    }

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

    public int getUSERS_iduser() {
        return USERS_iduser;
    }

    public void setUSERS_iduser(int USERS_iduser) {
        this.USERS_iduser = USERS_iduser;
    }

    @Override
    public String toString() {
        return "ApplicationsForms{" + "idapplications_forms=" + idapplications_forms + ", reference_type=" + reference_type + ", language=" + language + ", USERS_iduser=" + USERS_iduser + '}';
    }
    
    
}
