/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.controller;

import iaau.uims.jpa.controller.exceptions.NonexistentEntityException;
import iaau.uims.jpa.controller.exceptions.PreexistingEntityException;
import iaau.uims.jpa.entity.apps.Registration;
import iaau.uims.jpa.entity.apps.RegistrationPK;
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
public class RegistrationJpaController implements Serializable {

    public RegistrationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registration registration) throws PreexistingEntityException, Exception {
        if (registration.getRegistrationPK() == null) {
            registration.setRegistrationPK(new RegistrationPK());
        }
        registration.getRegistrationPK().setUSERSiduser(registration.getUsers().getIduser());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users = registration.getUsers();
            if (users != null) {
                users = em.getReference(users.getClass(), users.getIduser());
                registration.setUsers(users);
            }
            em.persist(registration);
            if (users != null) {
                users.getRegistrationCollection().add(registration);
                users = em.merge(users);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegistration(registration.getRegistrationPK()) != null) {
                throw new PreexistingEntityException("Registration " + registration + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registration registration) throws NonexistentEntityException, Exception {
        registration.getRegistrationPK().setUSERSiduser(registration.getUsers().getIduser());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registration persistentRegistration = em.find(Registration.class, registration.getRegistrationPK());
            Users usersOld = persistentRegistration.getUsers();
            Users usersNew = registration.getUsers();
            if (usersNew != null) {
                usersNew = em.getReference(usersNew.getClass(), usersNew.getIduser());
                registration.setUsers(usersNew);
            }
            registration = em.merge(registration);
            if (usersOld != null && !usersOld.equals(usersNew)) {
                usersOld.getRegistrationCollection().remove(registration);
                usersOld = em.merge(usersOld);
            }
            if (usersNew != null && !usersNew.equals(usersOld)) {
                usersNew.getRegistrationCollection().add(registration);
                usersNew = em.merge(usersNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RegistrationPK id = registration.getRegistrationPK();
                if (findRegistration(id) == null) {
                    throw new NonexistentEntityException("The registration with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RegistrationPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registration registration;
            try {
                registration = em.getReference(Registration.class, id);
                registration.getRegistrationPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registration with id " + id + " no longer exists.", enfe);
            }
            Users users = registration.getUsers();
            if (users != null) {
                users.getRegistrationCollection().remove(registration);
                users = em.merge(users);
            }
            em.remove(registration);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registration> findRegistrationEntities() {
        return findRegistrationEntities(true, -1, -1);
    }

    public List<Registration> findRegistrationEntities(int maxResults, int firstResult) {
        return findRegistrationEntities(false, maxResults, firstResult);
    }

    private List<Registration> findRegistrationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registration.class));
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

    public Registration findRegistration(RegistrationPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registration.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistrationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registration> rt = cq.from(Registration.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
