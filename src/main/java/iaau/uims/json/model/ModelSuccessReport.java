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
public class ModelSuccessReport {
    
    private String idnumber;
    private String year;
    private String semester;

    
    public ModelSuccessReport(String idnumber, String year, String semester) {
        this.idnumber = idnumber;
        this.year = year;
        this.semester = semester;
    }

    public ModelSuccessReport() {
    }
    
    

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    @Override
    public String toString() 
    {
        return "SuccessReportRequest [idnumber=" + idnumber + ", year=" + year + ", semester=" + semester + "]";
    }
    
}
