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
public class ModelGeneralInfo {

    private String id_Number;

    public ModelGeneralInfo() {
    }

    public ModelGeneralInfo(String idnumber) {
        this.id_Number = idnumber;
    }

    public String getIdNumber() {
        return id_Number;
    }

    public void setIdNumber(String idnumber) {
        this.id_Number = idnumber;
    }

    @Override
    public String toString() {
        return id_Number;
    }
}
