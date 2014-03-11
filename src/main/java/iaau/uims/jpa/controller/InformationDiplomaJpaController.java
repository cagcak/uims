/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.controller;

import iaau.uims.jpa.controller.exceptions.NonexistentEntityException;
import iaau.uims.jpa.controller.exceptions.PreexistingEntityException;
import iaau.uims.jpa.entity.apps.InformationDiploma;
import iaau.uims.jpa.entity.apps.InformationDiplomaPK;
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
public class InformationDiplomaJpaController implements Serializable {

    public InformationDiplomaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InformationDiploma informationDiploma) throws PreexistingEntityException, Exception {
        if (informationDiploma.getInformationDiplomaPK() == null) {
            informationDiploma.setInformationDiplomaPK(new InformationDiplomaPK());
        }
        informationDiploma.getInformationDiplomaPK().setUSERSiduser(informationDiploma.getUsers().getIduser());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users = informationDiploma.getUsers();
            if (users != null) {
                users = em.getReference(users.getClass(), users.getIduser());
                informationDiploma.setUsers(users);
            }
            em.persist(informationDiploma);
            if (users != null) {
                users.getInformationDiplomaCollection().add(informationDiploma);
                users = em.merge(users);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInformationDiploma(informationDiploma.getInformationDiplomaPK()) != null) {
                throw new PreexistingEntityException("InformationDiploma " + informationDiploma + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InformationDiploma informationDiploma) throws NonexistentEntityException, Exception {
        informationDiploma.getInformationDiplomaPK().setUSERSiduser(informationDiploma.getUsers().getIduser());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InformationDiploma persistentInformationDiploma = em.find(InformationDiploma.class, informationDiploma.getInformationDiplomaPK());
            Users usersOld = persistentInformationDiploma.getUsers();
            Users usersNew = informationDiploma.getUsers();
            if (usersNew != null) {
                usersNew = em.getReference(usersNew.getClass(), usersNew.getIduser());
                informationDiploma.setUsers(usersNew);
            }
            informationDiploma = em.merge(informationDiploma);
            if (usersOld != null && !usersOld.equals(usersNew)) {
                usersOld.getInformationDiplomaCollection().remove(informationDiploma);
                usersOld = em.merge(usersOld);
            }
            if (usersNew != null && !usersNew.equals(usersOld)) {
                usersNew.getInformationDiplomaCollection().add(informationDiploma);
                usersNew = em.merge(usersNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                InformationDiplomaPK id = informationDiploma.getInformationDiplomaPK();
                if (findInformationDiploma(id) == null) {
                    throw new NonexistentEntityException("The informationDiploma with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(InformationDiplomaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InformationDiploma informationDiploma;
            try {
                informationDiploma = em.getReference(InformationDiploma.class, id);
                informationDiploma.getInformationDiplomaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The informationDiploma with id " + id + " no longer exists.", enfe);
            }
            Users users = informationDiploma.getUsers();
            if (users != null) {
                users.getInformationDiplomaCollection().remove(informationDiploma);
                users = em.merge(users);
            }
            em.remove(informationDiploma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InformationDiploma> findInformationDiplomaEntities() {
        return findInformationDiplomaEntities(true, -1, -1);
    }

    public List<InformationDiploma> findInformationDiplomaEntities(int maxResults, int firstResult) {
        return findInformationDiplomaEntities(false, maxResults, firstResult);
    }

    private List<InformationDiploma> findInformationDiplomaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InformationDiploma.class));
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

    public InformationDiploma findInformationDiploma(InformationDiplomaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InformationDiploma.class, id);
        } finally {
            em.close();
        }
    }

    public int getInformationDiplomaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InformationDiploma> rt = cq.from(InformationDiploma.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
