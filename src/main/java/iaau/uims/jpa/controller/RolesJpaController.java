/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.controller;

import iaau.uims.jpa.controller.exceptions.NonexistentEntityException;
import iaau.uims.jpa.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import iaau.uims.jpa.entity.user.Perms;
import iaau.uims.jpa.entity.user.Roles;
import iaau.uims.jpa.entity.user.RolesPK;
import iaau.uims.jpa.entity.user.Users;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Çağrı Çakır
 */
public class RolesJpaController implements Serializable {

    public RolesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Roles roles) throws PreexistingEntityException, Exception {
        if (roles.getRolesPK() == null) {
            roles.setRolesPK(new RolesPK());
        }
        roles.getRolesPK().setUSERSiduser(roles.getUsers().getIduser());
        roles.getRolesPK().setPERMSidPERMS(roles.getPerms().getIdPERMS());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perms perms = roles.getPerms();
            if (perms != null) {
                perms = em.getReference(perms.getClass(), perms.getIdPERMS());
                roles.setPerms(perms);
            }
            Users users = roles.getUsers();
            if (users != null) {
                users = em.getReference(users.getClass(), users.getIduser());
                roles.setUsers(users);
            }
            em.persist(roles);
            if (perms != null) {
                perms.getRolesCollection().add(roles);
                perms = em.merge(perms);
            }
            if (users != null) {
                users.getRolesCollection().add(roles);
                users = em.merge(users);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRoles(roles.getRolesPK()) != null) {
                throw new PreexistingEntityException("Roles " + roles + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Roles roles) throws NonexistentEntityException, Exception {
        roles.getRolesPK().setUSERSiduser(roles.getUsers().getIduser());
        roles.getRolesPK().setPERMSidPERMS(roles.getPerms().getIdPERMS());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles persistentRoles = em.find(Roles.class, roles.getRolesPK());
            Perms permsOld = persistentRoles.getPerms();
            Perms permsNew = roles.getPerms();
            Users usersOld = persistentRoles.getUsers();
            Users usersNew = roles.getUsers();
            if (permsNew != null) {
                permsNew = em.getReference(permsNew.getClass(), permsNew.getIdPERMS());
                roles.setPerms(permsNew);
            }
            if (usersNew != null) {
                usersNew = em.getReference(usersNew.getClass(), usersNew.getIduser());
                roles.setUsers(usersNew);
            }
            roles = em.merge(roles);
            if (permsOld != null && !permsOld.equals(permsNew)) {
                permsOld.getRolesCollection().remove(roles);
                permsOld = em.merge(permsOld);
            }
            if (permsNew != null && !permsNew.equals(permsOld)) {
                permsNew.getRolesCollection().add(roles);
                permsNew = em.merge(permsNew);
            }
            if (usersOld != null && !usersOld.equals(usersNew)) {
                usersOld.getRolesCollection().remove(roles);
                usersOld = em.merge(usersOld);
            }
            if (usersNew != null && !usersNew.equals(usersOld)) {
                usersNew.getRolesCollection().add(roles);
                usersNew = em.merge(usersNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RolesPK id = roles.getRolesPK();
                if (findRoles(id) == null) {
                    throw new NonexistentEntityException("The roles with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RolesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles roles;
            try {
                roles = em.getReference(Roles.class, id);
                roles.getRolesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The roles with id " + id + " no longer exists.", enfe);
            }
            Perms perms = roles.getPerms();
            if (perms != null) {
                perms.getRolesCollection().remove(roles);
                perms = em.merge(perms);
            }
            Users users = roles.getUsers();
            if (users != null) {
                users.getRolesCollection().remove(roles);
                users = em.merge(users);
            }
            em.remove(roles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Roles> findRolesEntities() {
        return findRolesEntities(true, -1, -1);
    }

    public List<Roles> findRolesEntities(int maxResults, int firstResult) {
        return findRolesEntities(false, maxResults, firstResult);
    }

    private List<Roles> findRolesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Roles.class));
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

    public Roles findRoles(RolesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Roles.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Roles> rt = cq.from(Roles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
