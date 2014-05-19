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
public class ModelCurrentInfo {
    private String id_number;

    public ModelCurrentInfo() {
    }

    public ModelCurrentInfo(String id_number) {
        this.id_number = id_number;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }
    
    @Override
    public String toString() {
        return id_number;
    }
    
}
