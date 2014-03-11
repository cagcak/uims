/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.service;

import iaau.uims.jpa.entity.myinformation.AccountingStatusInfo;
import iaau.uims.jpa.entity.myinformation.AccountingStatusInfoPK;
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
@Path("iaau.uims.jpa.entity.myinformation.accountingstatusinfo")
public class AccountingStatusInfoFacadeREST extends AbstractFacade<AccountingStatusInfo> {
    @PersistenceContext(unitName = "UIMS")
    private EntityManager em;

    private AccountingStatusInfoPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idAccounting=idAccountingValue;uSERSiduser=uSERSiduserValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        iaau.uims.jpa.entity.myinformation.AccountingStatusInfoPK key = new iaau.uims.jpa.entity.myinformation.AccountingStatusInfoPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idAccounting = map.get("idAccounting");
        if (idAccounting != null && !idAccounting.isEmpty()) {
            key.setIdAccounting(new java.lang.Integer(idAccounting.get(0)));
        }
        java.util.List<String> uSERSiduser = map.get("uSERSiduser");
        if (uSERSiduser != null && !uSERSiduser.isEmpty()) {
            key.setUSERSiduser(new java.lang.Integer(uSERSiduser.get(0)));
        }
        return key;
    }

    public AccountingStatusInfoFacadeREST() {
        super(AccountingStatusInfo.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(AccountingStatusInfo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") PathSegment id, AccountingStatusInfo entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        iaau.uims.jpa.entity.myinformation.AccountingStatusInfoPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public AccountingStatusInfo find(@PathParam("id") PathSegment id) {
        iaau.uims.jpa.entity.myinformation.AccountingStatusInfoPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<AccountingStatusInfo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<AccountingStatusInfo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
