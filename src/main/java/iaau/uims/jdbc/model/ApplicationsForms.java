/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jdbc.model;

/**
 *
 * @author Çağrı Çakır
 */
public class ApplicationsForms {
    
    private String idnumber;
    private String reference;
    private String language;
    private String fullname;

    public ApplicationsForms() {
    }

    public ApplicationsForms(String idnumber, String reference, String language, String fullname) {
        this.idnumber = idnumber;
        this.reference = reference;
        this.language = language;
        this.fullname = fullname;
    }
    
    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "AppsForms [idnumber=" + idnumber + ", reference=" + reference
                + ", language=" + language + ", fullname=" + fullname + "]";
    }
    
}
