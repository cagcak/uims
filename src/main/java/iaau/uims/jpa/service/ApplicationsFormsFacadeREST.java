/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.service;

import iaau.uims.jpa.entity.apps.ApplicationsForms;
import iaau.uims.jpa.entity.apps.ApplicationsFormsPK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author Çağrı Çakır
 */
@Stateless
@Path("iaau.uims.jpa.entity.apps.applicationsforms")
public class ApplicationsFormsFacadeREST extends AbstractFacade<ApplicationsForms> {
    @PersistenceContext(unitName = "UIMS")
    private EntityManager em;

    private ApplicationsFormsPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idapplicationsForms=idapplicationsFormsValue;uSERSiduser=uSERSiduserValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        iaau.uims.jpa.entity.apps.ApplicationsFormsPK key = new iaau.uims.jpa.entity.apps.ApplicationsFormsPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idapplicationsForms = map.get("idapplicationsForms");
        if (idapplicationsForms != null && !idapplicationsForms.isEmpty()) {
            key.setIdapplicationsForms(new java.lang.Integer(idapplicationsForms.get(0)));
        }
        java.util.List<String> uSERSiduser = map.get("uSERSiduser");
        if (uSERSiduser != null && !uSERSiduser.isEmpty()) {
            key.setUSERSiduser(new java.lang.Integer(uSERSiduser.get(0)));
        }
        return key;
    }

    public ApplicationsFormsFacadeREST() {
        super(ApplicationsForms.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(ApplicationsForms entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") PathSegment id, ApplicationsForms entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        iaau.uims.jpa.entity.apps.ApplicationsFormsPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public ApplicationsForms find(@PathParam("id") PathSegment id) {
        iaau.uims.jpa.entity.apps.ApplicationsFormsPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<ApplicationsForms> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<ApplicationsForms> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
