/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.controller;

import iaau.uims.jpa.controller.exceptions.NonexistentEntityException;
import iaau.uims.jpa.controller.exceptions.PreexistingEntityException;
import iaau.uims.jpa.entity.success.SuccessReport;
import iaau.uims.jpa.entity.success.SuccessReportPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import iaau.uims.jpa.entity.user.Users;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Çağrı Çakır
 */
public class SuccessReportJpaController implements Serializable {

    public SuccessReportJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SuccessReport successReport) throws PreexistingEntityException, Exception {
        if (successReport.getSuccessReportPK() == null) {
            successReport.setSuccessReportPK(new SuccessReportPK());
        }
        successReport.getSuccessReportPK().setUSERSiduser(successReport.getUsers().getIduser());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users = successReport.getUsers();
            if (users != null) {
                users = em.getReference(users.getClass(), users.getIduser());
                successReport.setUsers(users);
            }
            em.persist(successReport);
            if (users != null) {
                users.getSuccessReportCollection().add(successReport);
                users = em.merge(users);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSuccessReport(successReport.getSuccessReportPK()) != null) {
                throw new PreexistingEntityException("SuccessReport " + successReport + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SuccessReport successReport) throws NonexistentEntityException, Exception {
        successReport.getSuccessReportPK().setUSERSiduser(successReport.getUsers().getIduser());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SuccessReport persistentSuccessReport = em.find(SuccessReport.class, successReport.getSuccessReportPK());
            Users usersOld = persistentSuccessReport.getUsers();
            Users usersNew = successReport.getUsers();
            if (usersNew != null) {
                usersNew = em.getReference(usersNew.getClass(), usersNew.getIduser());
                successReport.setUsers(usersNew);
            }
            successReport = em.merge(successReport);
            if (usersOld != null && !usersOld.equals(usersNew)) {
                usersOld.getSuccessReportCollection().remove(successReport);
                usersOld = em.merge(usersOld);
            }
            if (usersNew != null && !usersNew.equals(usersOld)) {
                usersNew.getSuccessReportCollection().add(successReport);
                usersNew = em.merge(usersNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                SuccessReportPK id = successReport.getSuccessReportPK();
                if (findSuccessReport(id) == null) {
                    throw new NonexistentEntityException("The successReport with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(SuccessReportPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SuccessReport successReport;
            try {
                successReport = em.getReference(SuccessReport.class, id);
                successReport.getSuccessReportPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The successReport with id " + id + " no longer exists.", enfe);
            }
            Users users = successReport.getUsers();
            if (users != null) {
                users.getSuccessReportCollection().remove(successReport);
                users = em.merge(users);
            }
            em.remove(successReport);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SuccessReport> findSuccessReportEntities() {
        return findSuccessReportEntities(true, -1, -1);
    }

    public List<SuccessReport> findSuccessReportEntities(int maxResults, int firstResult) {
        return findSuccessReportEntities(false, maxResults, firstResult);
    }

    private List<SuccessReport> findSuccessReportEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SuccessReport.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public SuccessReport findSuccessReport(SuccessReportPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SuccessReport.class, id);
        } finally {
            em.close();
        }
    }

    public int getSuccessReportCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SuccessReport> rt = cq.from(SuccessReport.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
