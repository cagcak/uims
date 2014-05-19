/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */

package iaau.uims.servlet;

/**
 *
 * @author Çağrı Çakır
 */
public class UIMSServletContext {
    
    private static String contextPath;

    private UIMSServletContext() {
    }

    public static String getContextPath() {
        return contextPath;
    }

    public static void setContextPath(String cp) {
        contextPath = cp;
    }
}
