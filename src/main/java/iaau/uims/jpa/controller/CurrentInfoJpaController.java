/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.controller;

import iaau.uims.jpa.controller.exceptions.NonexistentEntityException;
import iaau.uims.jpa.controller.exceptions.PreexistingEntityException;
import iaau.uims.jpa.entity.myinformation.CurrentInfo;
import iaau.uims.jpa.entity.myinformation.CurrentInfoPK;
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
public class CurrentInfoJpaController implements Serializable {

    public CurrentInfoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CurrentInfo currentInfo) throws PreexistingEntityException, Exception {
        if (currentInfo.getCurrentInfoPK() == null) {
            currentInfo.setCurrentInfoPK(new CurrentInfoPK());
        }
        currentInfo.getCurrentInfoPK().setUSERSiduser(currentInfo.getUsers().getIduser());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users = currentInfo.getUsers();
            if (users != null) {
                users = em.getReference(users.getClass(), users.getIduser());
                currentInfo.setUsers(users);
            }
            em.persist(currentInfo);
            if (users != null) {
                users.getCurrentInfoCollection().add(currentInfo);
                users = em.merge(users);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCurrentInfo(currentInfo.getCurrentInfoPK()) != null) {
                throw new PreexistingEntityException("CurrentInfo " + currentInfo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CurrentInfo currentInfo) throws NonexistentEntityException, Exception {
        currentInfo.getCurrentInfoPK().setUSERSiduser(currentInfo.getUsers().getIduser());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CurrentInfo persistentCurrentInfo = em.find(CurrentInfo.class, currentInfo.getCurrentInfoPK());
            Users usersOld = persistentCurrentInfo.getUsers();
            Users usersNew = currentInfo.getUsers();
            if (usersNew != null) {
                usersNew = em.getReference(usersNew.getClass(), usersNew.getIduser());
                currentInfo.setUsers(usersNew);
            }
            currentInfo = em.merge(currentInfo);
            if (usersOld != null && !usersOld.equals(usersNew)) {
                usersOld.getCurrentInfoCollection().remove(currentInfo);
                usersOld = em.merge(usersOld);
            }
            if (usersNew != null && !usersNew.equals(usersOld)) {
                usersNew.getCurrentInfoCollection().add(currentInfo);
                usersNew = em.merge(usersNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CurrentInfoPK id = currentInfo.getCurrentInfoPK();
                if (findCurrentInfo(id) == null) {
                    throw new NonexistentEntityException("The currentInfo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CurrentInfoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CurrentInfo currentInfo;
            try {
                currentInfo = em.getReference(CurrentInfo.class, id);
                currentInfo.getCurrentInfoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The currentInfo with id " + id + " no longer exists.", enfe);
            }
            Users users = currentInfo.getUsers();
            if (users != null) {
                users.getCurrentInfoCollection().remove(currentInfo);
                users = em.merge(users);
            }
            em.remove(currentInfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CurrentInfo> findCurrentInfoEntities() {
        return findCurrentInfoEntities(true, -1, -1);
    }

    public List<CurrentInfo> findCurrentInfoEntities(int maxResults, int firstResult) {
        return findCurrentInfoEntities(false, maxResults, firstResult);
    }

    private List<CurrentInfo> findCurrentInfoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CurrentInfo.class));
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

    public CurrentInfo findCurrentInfo(CurrentInfoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CurrentInfo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCurrentInfoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CurrentInfo> rt = cq.from(CurrentInfo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
