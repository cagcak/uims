/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.controller;

import iaau.uims.jpa.controller.exceptions.NonexistentEntityException;
import iaau.uims.jpa.controller.exceptions.PreexistingEntityException;
import iaau.uims.jpa.entity.myinformation.GeneralInfo;
import iaau.uims.jpa.entity.myinformation.GeneralInfoPK;
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
public class GeneralInfoJpaController implements Serializable {

    public GeneralInfoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GeneralInfo generalInfo) throws PreexistingEntityException, Exception {
        if (generalInfo.getGeneralInfoPK() == null) {
            generalInfo.setGeneralInfoPK(new GeneralInfoPK());
        }
        generalInfo.getGeneralInfoPK().setUSERSiduser(generalInfo.getUsers().getIduser());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users = generalInfo.getUsers();
            if (users != null) {
                users = em.getReference(users.getClass(), users.getIduser());
                generalInfo.setUsers(users);
            }
            em.persist(generalInfo);
            if (users != null) {
                users.getGeneralInfoCollection().add(generalInfo);
                users = em.merge(users);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGeneralInfo(generalInfo.getGeneralInfoPK()) != null) {
                throw new PreexistingEntityException("GeneralInfo " + generalInfo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GeneralInfo generalInfo) throws NonexistentEntityException, Exception {
        generalInfo.getGeneralInfoPK().setUSERSiduser(generalInfo.getUsers().getIduser());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GeneralInfo persistentGeneralInfo = em.find(GeneralInfo.class, generalInfo.getGeneralInfoPK());
            Users usersOld = persistentGeneralInfo.getUsers();
            Users usersNew = generalInfo.getUsers();
            if (usersNew != null) {
                usersNew = em.getReference(usersNew.getClass(), usersNew.getIduser());
                generalInfo.setUsers(usersNew);
            }
            generalInfo = em.merge(generalInfo);
            if (usersOld != null && !usersOld.equals(usersNew)) {
                usersOld.getGeneralInfoCollection().remove(generalInfo);
                usersOld = em.merge(usersOld);
            }
            if (usersNew != null && !usersNew.equals(usersOld)) {
                usersNew.getGeneralInfoCollection().add(generalInfo);
                usersNew = em.merge(usersNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                GeneralInfoPK id = generalInfo.getGeneralInfoPK();
                if (findGeneralInfo(id) == null) {
                    throw new NonexistentEntityException("The generalInfo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(GeneralInfoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GeneralInfo generalInfo;
            try {
                generalInfo = em.getReference(GeneralInfo.class, id);
                generalInfo.getGeneralInfoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The generalInfo with id " + id + " no longer exists.", enfe);
            }
            Users users = generalInfo.getUsers();
            if (users != null) {
                users.getGeneralInfoCollection().remove(generalInfo);
                users = em.merge(users);
            }
            em.remove(generalInfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GeneralInfo> findGeneralInfoEntities() {
        return findGeneralInfoEntities(true, -1, -1);
    }

    public List<GeneralInfo> findGeneralInfoEntities(int maxResults, int firstResult) {
        return findGeneralInfoEntities(false, maxResults, firstResult);
    }

    private List<GeneralInfo> findGeneralInfoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GeneralInfo.class));
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

    public GeneralInfo findGeneralInfo(GeneralInfoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GeneralInfo.class, id);
        } finally {
            em.close();
        }
    }

    public int getGeneralInfoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GeneralInfo> rt = cq.from(GeneralInfo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
