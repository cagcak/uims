/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.controller;

import iaau.uims.jpa.controller.exceptions.NonexistentEntityException;
import iaau.uims.jpa.controller.exceptions.PreexistingEntityException;
import iaau.uims.jpa.entity.transcript.Trascript;
import iaau.uims.jpa.entity.transcript.TrascriptPK;
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
public class TrascriptJpaController implements Serializable {

    public TrascriptJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Trascript trascript) throws PreexistingEntityException, Exception {
        if (trascript.getTrascriptPK() == null) {
            trascript.setTrascriptPK(new TrascriptPK());
        }
        trascript.getTrascriptPK().setUSERSiduser(trascript.getUsers().getIduser());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users = trascript.getUsers();
            if (users != null) {
                users = em.getReference(users.getClass(), users.getIduser());
                trascript.setUsers(users);
            }
            em.persist(trascript);
            if (users != null) {
                users.getTrascriptCollection().add(trascript);
                users = em.merge(users);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTrascript(trascript.getTrascriptPK()) != null) {
                throw new PreexistingEntityException("Trascript " + trascript + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Trascript trascript) throws NonexistentEntityException, Exception {
        trascript.getTrascriptPK().setUSERSiduser(trascript.getUsers().getIduser());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Trascript persistentTrascript = em.find(Trascript.class, trascript.getTrascriptPK());
            Users usersOld = persistentTrascript.getUsers();
            Users usersNew = trascript.getUsers();
            if (usersNew != null) {
                usersNew = em.getReference(usersNew.getClass(), usersNew.getIduser());
                trascript.setUsers(usersNew);
            }
            trascript = em.merge(trascript);
            if (usersOld != null && !usersOld.equals(usersNew)) {
                usersOld.getTrascriptCollection().remove(trascript);
                usersOld = em.merge(usersOld);
            }
            if (usersNew != null && !usersNew.equals(usersOld)) {
                usersNew.getTrascriptCollection().add(trascript);
                usersNew = em.merge(usersNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TrascriptPK id = trascript.getTrascriptPK();
                if (findTrascript(id) == null) {
                    throw new NonexistentEntityException("The trascript with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(TrascriptPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Trascript trascript;
            try {
                trascript = em.getReference(Trascript.class, id);
                trascript.getTrascriptPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The trascript with id " + id + " no longer exists.", enfe);
            }
            Users users = trascript.getUsers();
            if (users != null) {
                users.getTrascriptCollection().remove(trascript);
                users = em.merge(users);
            }
            em.remove(trascript);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Trascript> findTrascriptEntities() {
        return findTrascriptEntities(true, -1, -1);
    }

    public List<Trascript> findTrascriptEntities(int maxResults, int firstResult) {
        return findTrascriptEntities(false, maxResults, firstResult);
    }

    private List<Trascript> findTrascriptEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Trascript.class));
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

    public Trascript findTrascript(TrascriptPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Trascript.class, id);
        } finally {
            em.close();
        }
    }

    public int getTrascriptCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Trascript> rt = cq.from(Trascript.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
