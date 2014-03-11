/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.controller;

import iaau.uims.jpa.controller.exceptions.NonexistentEntityException;
import iaau.uims.jpa.controller.exceptions.PreexistingEntityException;
import iaau.uims.jpa.entity.apps.ApplicationsForms;
import iaau.uims.jpa.entity.apps.ApplicationsFormsPK;
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
public class ApplicationsFormsJpaController implements Serializable {

    public ApplicationsFormsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ApplicationsForms applicationsForms) throws PreexistingEntityException, Exception {
        if (applicationsForms.getApplicationsFormsPK() == null) {
            applicationsForms.setApplicationsFormsPK(new ApplicationsFormsPK());
        }
        applicationsForms.getApplicationsFormsPK().setUSERSiduser(applicationsForms.getUsers().getIduser());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users = applicationsForms.getUsers();
            if (users != null) {
                users = em.getReference(users.getClass(), users.getIduser());
                applicationsForms.setUsers(users);
            }
            em.persist(applicationsForms);
            if (users != null) {
                users.getApplicationsFormsCollection().add(applicationsForms);
                users = em.merge(users);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findApplicationsForms(applicationsForms.getApplicationsFormsPK()) != null) {
                throw new PreexistingEntityException("ApplicationsForms " + applicationsForms + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ApplicationsForms applicationsForms) throws NonexistentEntityException, Exception {
        applicationsForms.getApplicationsFormsPK().setUSERSiduser(applicationsForms.getUsers().getIduser());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ApplicationsForms persistentApplicationsForms = em.find(ApplicationsForms.class, applicationsForms.getApplicationsFormsPK());
            Users usersOld = persistentApplicationsForms.getUsers();
            Users usersNew = applicationsForms.getUsers();
            if (usersNew != null) {
                usersNew = em.getReference(usersNew.getClass(), usersNew.getIduser());
                applicationsForms.setUsers(usersNew);
            }
            applicationsForms = em.merge(applicationsForms);
            if (usersOld != null && !usersOld.equals(usersNew)) {
                usersOld.getApplicationsFormsCollection().remove(applicationsForms);
                usersOld = em.merge(usersOld);
            }
            if (usersNew != null && !usersNew.equals(usersOld)) {
                usersNew.getApplicationsFormsCollection().add(applicationsForms);
                usersNew = em.merge(usersNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ApplicationsFormsPK id = applicationsForms.getApplicationsFormsPK();
                if (findApplicationsForms(id) == null) {
                    throw new NonexistentEntityException("The applicationsForms with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ApplicationsFormsPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ApplicationsForms applicationsForms;
            try {
                applicationsForms = em.getReference(ApplicationsForms.class, id);
                applicationsForms.getApplicationsFormsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The applicationsForms with id " + id + " no longer exists.", enfe);
            }
            Users users = applicationsForms.getUsers();
            if (users != null) {
                users.getApplicationsFormsCollection().remove(applicationsForms);
                users = em.merge(users);
            }
            em.remove(applicationsForms);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ApplicationsForms> findApplicationsFormsEntities() {
        return findApplicationsFormsEntities(true, -1, -1);
    }

    public List<ApplicationsForms> findApplicationsFormsEntities(int maxResults, int firstResult) {
        return findApplicationsFormsEntities(false, maxResults, firstResult);
    }

    private List<ApplicationsForms> findApplicationsFormsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ApplicationsForms.class));
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

    public ApplicationsForms findApplicationsForms(ApplicationsFormsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ApplicationsForms.class, id);
        } finally {
            em.close();
        }
    }

    public int getApplicationsFormsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ApplicationsForms> rt = cq.from(ApplicationsForms.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
