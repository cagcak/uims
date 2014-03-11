/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.controller;

import iaau.uims.jpa.controller.exceptions.IllegalOrphanException;
import iaau.uims.jpa.controller.exceptions.NonexistentEntityException;
import iaau.uims.jpa.entity.user.Perms;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import iaau.uims.jpa.entity.user.Roles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Çağrı Çakır
 */
public class PermsJpaController implements Serializable {

    public PermsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Perms perms) {
        if (perms.getRolesCollection() == null) {
            perms.setRolesCollection(new ArrayList<Roles>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Roles> attachedRolesCollection = new ArrayList<Roles>();
            for (Roles rolesCollectionRolesToAttach : perms.getRolesCollection()) {
                rolesCollectionRolesToAttach = em.getReference(rolesCollectionRolesToAttach.getClass(), rolesCollectionRolesToAttach.getRolesPK());
                attachedRolesCollection.add(rolesCollectionRolesToAttach);
            }
            perms.setRolesCollection(attachedRolesCollection);
            em.persist(perms);
            for (Roles rolesCollectionRoles : perms.getRolesCollection()) {
                Perms oldPermsOfRolesCollectionRoles = rolesCollectionRoles.getPerms();
                rolesCollectionRoles.setPerms(perms);
                rolesCollectionRoles = em.merge(rolesCollectionRoles);
                if (oldPermsOfRolesCollectionRoles != null) {
                    oldPermsOfRolesCollectionRoles.getRolesCollection().remove(rolesCollectionRoles);
                    oldPermsOfRolesCollectionRoles = em.merge(oldPermsOfRolesCollectionRoles);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Perms perms) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perms persistentPerms = em.find(Perms.class, perms.getIdPERMS());
            Collection<Roles> rolesCollectionOld = persistentPerms.getRolesCollection();
            Collection<Roles> rolesCollectionNew = perms.getRolesCollection();
            List<String> illegalOrphanMessages = null;
            for (Roles rolesCollectionOldRoles : rolesCollectionOld) {
                if (!rolesCollectionNew.contains(rolesCollectionOldRoles)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Roles " + rolesCollectionOldRoles + " since its perms field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Roles> attachedRolesCollectionNew = new ArrayList<Roles>();
            for (Roles rolesCollectionNewRolesToAttach : rolesCollectionNew) {
                rolesCollectionNewRolesToAttach = em.getReference(rolesCollectionNewRolesToAttach.getClass(), rolesCollectionNewRolesToAttach.getRolesPK());
                attachedRolesCollectionNew.add(rolesCollectionNewRolesToAttach);
            }
            rolesCollectionNew = attachedRolesCollectionNew;
            perms.setRolesCollection(rolesCollectionNew);
            perms = em.merge(perms);
            for (Roles rolesCollectionNewRoles : rolesCollectionNew) {
                if (!rolesCollectionOld.contains(rolesCollectionNewRoles)) {
                    Perms oldPermsOfRolesCollectionNewRoles = rolesCollectionNewRoles.getPerms();
                    rolesCollectionNewRoles.setPerms(perms);
                    rolesCollectionNewRoles = em.merge(rolesCollectionNewRoles);
                    if (oldPermsOfRolesCollectionNewRoles != null && !oldPermsOfRolesCollectionNewRoles.equals(perms)) {
                        oldPermsOfRolesCollectionNewRoles.getRolesCollection().remove(rolesCollectionNewRoles);
                        oldPermsOfRolesCollectionNewRoles = em.merge(oldPermsOfRolesCollectionNewRoles);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = perms.getIdPERMS();
                if (findPerms(id) == null) {
                    throw new NonexistentEntityException("The perms with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perms perms;
            try {
                perms = em.getReference(Perms.class, id);
                perms.getIdPERMS();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perms with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Roles> rolesCollectionOrphanCheck = perms.getRolesCollection();
            for (Roles rolesCollectionOrphanCheckRoles : rolesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Perms (" + perms + ") cannot be destroyed since the Roles " + rolesCollectionOrphanCheckRoles + " in its rolesCollection field has a non-nullable perms field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(perms);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Perms> findPermsEntities() {
        return findPermsEntities(true, -1, -1);
    }

    public List<Perms> findPermsEntities(int maxResults, int firstResult) {
        return findPermsEntities(false, maxResults, firstResult);
    }

    private List<Perms> findPermsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Perms.class));
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

    public Perms findPerms(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Perms.class, id);
        } finally {
            em.close();
        }
    }

    public int getPermsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Perms> rt = cq.from(Perms.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
