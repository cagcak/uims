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
public class ModelRegistration {
    
    private String id_Number;

    public ModelRegistration(String id_Number) {
        this.id_Number = id_Number;
    }
    
    public String getId_Number() {
        return id_Number;
    }

    public void setId_Number(String id_Number) {
        this.id_Number = id_Number;
    }
    
    @Override
    public String toString() {
        return id_Number;
    }
    
    
}
