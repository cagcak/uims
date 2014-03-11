/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Çağrı Çakır
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(iaau.uims.jpa.service.AccountingStatusInfoFacadeREST.class);
        resources.add(iaau.uims.jpa.service.ApplicationsFormsFacadeREST.class);
        resources.add(iaau.uims.jpa.service.CurrentInfoFacadeREST.class);
        resources.add(iaau.uims.jpa.service.GeneralInfoFacadeREST.class);
        resources.add(iaau.uims.jpa.service.InformationDiplomaFacadeREST.class);
        resources.add(iaau.uims.jpa.service.PermsFacadeREST.class);
        resources.add(iaau.uims.jpa.service.RegistrationFacadeREST.class);
        resources.add(iaau.uims.jpa.service.RolesFacadeREST.class);
        resources.add(iaau.uims.jpa.service.SuccessReportFacadeREST.class);
        resources.add(iaau.uims.jpa.service.TrascriptFacadeREST.class);
        resources.add(iaau.uims.jpa.service.UsersFacadeREST.class);
    }
    
}
