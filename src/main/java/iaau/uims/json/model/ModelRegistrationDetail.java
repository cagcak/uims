/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */

package iaau.uims.json.model;

/**
 *
 * @author Çağrı Çakır
 */
public class ModelRegistrationDetail {
    
    private String idnumber;
    private String requestedSubject;

    public ModelRegistrationDetail() {
    }

    public ModelRegistrationDetail(String idnumber, String requestedSubject) {
        this.idnumber = idnumber;
        this.requestedSubject = requestedSubject;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getRequestedSubject() {
        return requestedSubject;
    }

    public void setRequestedSubject(String requestedSubject) {
        this.requestedSubject = requestedSubject;
    }

    @Override
    public String toString() {
        return "ModelRegistrationDetail [idnumber=" + idnumber + ", requestedSubject=" + requestedSubject + "]";
    }
    
    
    
}
