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
public class ModelAccountingStatusInfo {
    private String id_Number;

    public ModelAccountingStatusInfo() {
    }

    public ModelAccountingStatusInfo(String idnumber) {
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
        return "ModelAccountingStatusInfo[" + " idNumber=" + id_Number + "]";
    }
}
