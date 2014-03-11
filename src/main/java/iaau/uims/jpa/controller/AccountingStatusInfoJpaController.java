/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.controller;

import iaau.uims.jpa.controller.exceptions.NonexistentEntityException;
import iaau.uims.jpa.controller.exceptions.PreexistingEntityException;
import iaau.uims.jpa.entity.myinformation.AccountingStatusInfo;
import iaau.uims.jpa.entity.myinformation.AccountingStatusInfoPK;
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
public class AccountingStatusInfoJpaController implements Serializable {

    public AccountingStatusInfoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AccountingStatusInfo accountingStatusInfo) throws PreexistingEntityException, Exception {
        if (accountingStatusInfo.getAccountingStatusInfoPK() == null) {
            accountingStatusInfo.setAccountingStatusInfoPK(new AccountingStatusInfoPK());
        }
        accountingStatusInfo.getAccountingStatusInfoPK().setUSERSiduser(accountingStatusInfo.getUsers().getIduser());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users = accountingStatusInfo.getUsers();
            if (users != null) {
                users = em.getReference(users.getClass(), users.getIduser());
                accountingStatusInfo.setUsers(users);
            }
            em.persist(accountingStatusInfo);
            if (users != null) {
                users.getAccountingStatusInfoCollection().add(accountingStatusInfo);
                users = em.merge(users);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAccountingStatusInfo(accountingStatusInfo.getAccountingStatusInfoPK()) != null) {
                throw new PreexistingEntityException("AccountingStatusInfo " + accountingStatusInfo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AccountingStatusInfo accountingStatusInfo) throws NonexistentEntityException, Exception {
        accountingStatusInfo.getAccountingStatusInfoPK().setUSERSiduser(accountingStatusInfo.getUsers().getIduser());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AccountingStatusInfo persistentAccountingStatusInfo = em.find(AccountingStatusInfo.class, accountingStatusInfo.getAccountingStatusInfoPK());
            Users usersOld = persistentAccountingStatusInfo.getUsers();
            Users usersNew = accountingStatusInfo.getUsers();
            if (usersNew != null) {
                usersNew = em.getReference(usersNew.getClass(), usersNew.getIduser());
                accountingStatusInfo.setUsers(usersNew);
            }
            accountingStatusInfo = em.merge(accountingStatusInfo);
            if (usersOld != null && !usersOld.equals(usersNew)) {
                usersOld.getAccountingStatusInfoCollection().remove(accountingStatusInfo);
                usersOld = em.merge(usersOld);
            }
            if (usersNew != null && !usersNew.equals(usersOld)) {
                usersNew.getAccountingStatusInfoCollection().add(accountingStatusInfo);
                usersNew = em.merge(usersNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AccountingStatusInfoPK id = accountingStatusInfo.getAccountingStatusInfoPK();
                if (findAccountingStatusInfo(id) == null) {
                    throw new NonexistentEntityException("The accountingStatusInfo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AccountingStatusInfoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AccountingStatusInfo accountingStatusInfo;
            try {
                accountingStatusInfo = em.getReference(AccountingStatusInfo.class, id);
                accountingStatusInfo.getAccountingStatusInfoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The accountingStatusInfo with id " + id + " no longer exists.", enfe);
            }
            Users users = accountingStatusInfo.getUsers();
            if (users != null) {
                users.getAccountingStatusInfoCollection().remove(accountingStatusInfo);
                users = em.merge(users);
            }
            em.remove(accountingStatusInfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AccountingStatusInfo> findAccountingStatusInfoEntities() {
        return findAccountingStatusInfoEntities(true, -1, -1);
    }

    public List<AccountingStatusInfo> findAccountingStatusInfoEntities(int maxResults, int firstResult) {
        return findAccountingStatusInfoEntities(false, maxResults, firstResult);
    }

    private List<AccountingStatusInfo> findAccountingStatusInfoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AccountingStatusInfo.class));
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

    public AccountingStatusInfo findAccountingStatusInfo(AccountingStatusInfoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AccountingStatusInfo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccountingStatusInfoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AccountingStatusInfo> rt = cq.from(AccountingStatusInfo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
