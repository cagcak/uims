/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jpa.controller;

import iaau.uims.jpa.controller.exceptions.IllegalOrphanException;
import iaau.uims.jpa.controller.exceptions.NonexistentEntityException;

import java.io.Serializable;

import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import iaau.uims.jpa.entity.apps.ApplicationsForms;
import iaau.uims.jpa.entity.transcript.Trascript;
import iaau.uims.jpa.entity.myinformation.GeneralInfo;
import iaau.uims.jpa.entity.apps.InformationDiploma;
import iaau.uims.jpa.entity.user.Roles;
import iaau.uims.jpa.entity.myinformation.AccountingStatusInfo;
import iaau.uims.jpa.entity.myinformation.CurrentInfo;
import iaau.uims.jpa.entity.apps.Registration;
import iaau.uims.jpa.entity.success.SuccessReport;
import iaau.uims.jpa.entity.user.Users;
import javax.persistence.Persistence;

/**
 *
 * @author Çağrı Çakır
 */
public class UsersJpaController implements Serializable {

    public UsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        if (emf == null)
        {
            emf = Persistence.createEntityManagerFactory("UIMS");
        }
        return emf.createEntityManager();
    }

    public void create(Users users) {
        if (users.getApplicationsFormsCollection() == null) {
            users.setApplicationsFormsCollection(new ArrayList<ApplicationsForms>());
        }
        if (users.getTrascriptCollection() == null) {
            users.setTrascriptCollection(new ArrayList<Trascript>());
        }
        if (users.getGeneralInfoCollection() == null) {
            users.setGeneralInfoCollection(new ArrayList<GeneralInfo>());
        }
        if (users.getInformationDiplomaCollection() == null) {
            users.setInformationDiplomaCollection(new ArrayList<InformationDiploma>());
        }
        if (users.getRolesCollection() == null) {
            users.setRolesCollection(new ArrayList<Roles>());
        }
        if (users.getAccountingStatusInfoCollection() == null) {
            users.setAccountingStatusInfoCollection(new ArrayList<AccountingStatusInfo>());
        }
        if (users.getCurrentInfoCollection() == null) {
            users.setCurrentInfoCollection(new ArrayList<CurrentInfo>());
        }
        if (users.getRegistrationCollection() == null) {
            users.setRegistrationCollection(new ArrayList<Registration>());
        }
        if (users.getSuccessReportCollection() == null) {
            users.setSuccessReportCollection(new ArrayList<SuccessReport>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ApplicationsForms> attachedApplicationsFormsCollection = new ArrayList<ApplicationsForms>();
            for (ApplicationsForms applicationsFormsCollectionApplicationsFormsToAttach : users.getApplicationsFormsCollection()) {
                applicationsFormsCollectionApplicationsFormsToAttach = em.getReference(applicationsFormsCollectionApplicationsFormsToAttach.getClass(), applicationsFormsCollectionApplicationsFormsToAttach.getApplicationsFormsPK());
                attachedApplicationsFormsCollection.add(applicationsFormsCollectionApplicationsFormsToAttach);
            }
            users.setApplicationsFormsCollection(attachedApplicationsFormsCollection);
            Collection<Trascript> attachedTrascriptCollection = new ArrayList<Trascript>();
            for (Trascript trascriptCollectionTrascriptToAttach : users.getTrascriptCollection()) {
                trascriptCollectionTrascriptToAttach = em.getReference(trascriptCollectionTrascriptToAttach.getClass(), trascriptCollectionTrascriptToAttach.getTrascriptPK());
                attachedTrascriptCollection.add(trascriptCollectionTrascriptToAttach);
            }
            users.setTrascriptCollection(attachedTrascriptCollection);
            Collection<GeneralInfo> attachedGeneralInfoCollection = new ArrayList<GeneralInfo>();
            for (GeneralInfo generalInfoCollectionGeneralInfoToAttach : users.getGeneralInfoCollection()) {
                generalInfoCollectionGeneralInfoToAttach = em.getReference(generalInfoCollectionGeneralInfoToAttach.getClass(), generalInfoCollectionGeneralInfoToAttach.getGeneralInfoPK());
                attachedGeneralInfoCollection.add(generalInfoCollectionGeneralInfoToAttach);
            }
            users.setGeneralInfoCollection(attachedGeneralInfoCollection);
            Collection<InformationDiploma> attachedInformationDiplomaCollection = new ArrayList<InformationDiploma>();
            for (InformationDiploma informationDiplomaCollectionInformationDiplomaToAttach : users.getInformationDiplomaCollection()) {
                informationDiplomaCollectionInformationDiplomaToAttach = em.getReference(informationDiplomaCollectionInformationDiplomaToAttach.getClass(), informationDiplomaCollectionInformationDiplomaToAttach.getInformationDiplomaPK());
                attachedInformationDiplomaCollection.add(informationDiplomaCollectionInformationDiplomaToAttach);
            }
            users.setInformationDiplomaCollection(attachedInformationDiplomaCollection);
            Collection<Roles> attachedRolesCollection = new ArrayList<Roles>();
            for (Roles rolesCollectionRolesToAttach : users.getRolesCollection()) {
                rolesCollectionRolesToAttach = em.getReference(rolesCollectionRolesToAttach.getClass(), rolesCollectionRolesToAttach.getRolesPK());
                attachedRolesCollection.add(rolesCollectionRolesToAttach);
            }
            users.setRolesCollection(attachedRolesCollection);
            Collection<AccountingStatusInfo> attachedAccountingStatusInfoCollection = new ArrayList<AccountingStatusInfo>();
            for (AccountingStatusInfo accountingStatusInfoCollectionAccountingStatusInfoToAttach : users.getAccountingStatusInfoCollection()) {
                accountingStatusInfoCollectionAccountingStatusInfoToAttach = em.getReference(accountingStatusInfoCollectionAccountingStatusInfoToAttach.getClass(), accountingStatusInfoCollectionAccountingStatusInfoToAttach.getAccountingStatusInfoPK());
                attachedAccountingStatusInfoCollection.add(accountingStatusInfoCollectionAccountingStatusInfoToAttach);
            }
            users.setAccountingStatusInfoCollection(attachedAccountingStatusInfoCollection);
            Collection<CurrentInfo> attachedCurrentInfoCollection = new ArrayList<CurrentInfo>();
            for (CurrentInfo currentInfoCollectionCurrentInfoToAttach : users.getCurrentInfoCollection()) {
                currentInfoCollectionCurrentInfoToAttach = em.getReference(currentInfoCollectionCurrentInfoToAttach.getClass(), currentInfoCollectionCurrentInfoToAttach.getCurrentInfoPK());
                attachedCurrentInfoCollection.add(currentInfoCollectionCurrentInfoToAttach);
            }
            users.setCurrentInfoCollection(attachedCurrentInfoCollection);
            Collection<Registration> attachedRegistrationCollection = new ArrayList<Registration>();
            for (Registration registrationCollectionRegistrationToAttach : users.getRegistrationCollection()) {
                registrationCollectionRegistrationToAttach = em.getReference(registrationCollectionRegistrationToAttach.getClass(), registrationCollectionRegistrationToAttach.getRegistrationPK());
                attachedRegistrationCollection.add(registrationCollectionRegistrationToAttach);
            }
            users.setRegistrationCollection(attachedRegistrationCollection);
            Collection<SuccessReport> attachedSuccessReportCollection = new ArrayList<SuccessReport>();
            for (SuccessReport successReportCollectionSuccessReportToAttach : users.getSuccessReportCollection()) {
                successReportCollectionSuccessReportToAttach = em.getReference(successReportCollectionSuccessReportToAttach.getClass(), successReportCollectionSuccessReportToAttach.getSuccessReportPK());
                attachedSuccessReportCollection.add(successReportCollectionSuccessReportToAttach);
            }
            users.setSuccessReportCollection(attachedSuccessReportCollection);
            em.persist(users);
            for (ApplicationsForms applicationsFormsCollectionApplicationsForms : users.getApplicationsFormsCollection()) {
                Users oldUsersOfApplicationsFormsCollectionApplicationsForms = applicationsFormsCollectionApplicationsForms.getUsers();
                applicationsFormsCollectionApplicationsForms.setUsers(users);
                applicationsFormsCollectionApplicationsForms = em.merge(applicationsFormsCollectionApplicationsForms);
                if (oldUsersOfApplicationsFormsCollectionApplicationsForms != null) {
                    oldUsersOfApplicationsFormsCollectionApplicationsForms.getApplicationsFormsCollection().remove(applicationsFormsCollectionApplicationsForms);
                    oldUsersOfApplicationsFormsCollectionApplicationsForms = em.merge(oldUsersOfApplicationsFormsCollectionApplicationsForms);
                }
            }
            for (Trascript trascriptCollectionTrascript : users.getTrascriptCollection()) {
                Users oldUsersOfTrascriptCollectionTrascript = trascriptCollectionTrascript.getUsers();
                trascriptCollectionTrascript.setUsers(users);
                trascriptCollectionTrascript = em.merge(trascriptCollectionTrascript);
                if (oldUsersOfTrascriptCollectionTrascript != null) {
                    oldUsersOfTrascriptCollectionTrascript.getTrascriptCollection().remove(trascriptCollectionTrascript);
                    oldUsersOfTrascriptCollectionTrascript = em.merge(oldUsersOfTrascriptCollectionTrascript);
                }
            }
            for (GeneralInfo generalInfoCollectionGeneralInfo : users.getGeneralInfoCollection()) {
                Users oldUsersOfGeneralInfoCollectionGeneralInfo = generalInfoCollectionGeneralInfo.getUsers();
                generalInfoCollectionGeneralInfo.setUsers(users);
                generalInfoCollectionGeneralInfo = em.merge(generalInfoCollectionGeneralInfo);
                if (oldUsersOfGeneralInfoCollectionGeneralInfo != null) {
                    oldUsersOfGeneralInfoCollectionGeneralInfo.getGeneralInfoCollection().remove(generalInfoCollectionGeneralInfo);
                    oldUsersOfGeneralInfoCollectionGeneralInfo = em.merge(oldUsersOfGeneralInfoCollectionGeneralInfo);
                }
            }
            for (InformationDiploma informationDiplomaCollectionInformationDiploma : users.getInformationDiplomaCollection()) {
                Users oldUsersOfInformationDiplomaCollectionInformationDiploma = informationDiplomaCollectionInformationDiploma.getUsers();
                informationDiplomaCollectionInformationDiploma.setUsers(users);
                informationDiplomaCollectionInformationDiploma = em.merge(informationDiplomaCollectionInformationDiploma);
                if (oldUsersOfInformationDiplomaCollectionInformationDiploma != null) {
                    oldUsersOfInformationDiplomaCollectionInformationDiploma.getInformationDiplomaCollection().remove(informationDiplomaCollectionInformationDiploma);
                    oldUsersOfInformationDiplomaCollectionInformationDiploma = em.merge(oldUsersOfInformationDiplomaCollectionInformationDiploma);
                }
            }
            for (Roles rolesCollectionRoles : users.getRolesCollection()) {
                Users oldUsersOfRolesCollectionRoles = rolesCollectionRoles.getUsers();
                rolesCollectionRoles.setUsers(users);
                rolesCollectionRoles = em.merge(rolesCollectionRoles);
                if (oldUsersOfRolesCollectionRoles != null) {
                    oldUsersOfRolesCollectionRoles.getRolesCollection().remove(rolesCollectionRoles);
                    oldUsersOfRolesCollectionRoles = em.merge(oldUsersOfRolesCollectionRoles);
                }
            }
            for (AccountingStatusInfo accountingStatusInfoCollectionAccountingStatusInfo : users.getAccountingStatusInfoCollection()) {
                Users oldUsersOfAccountingStatusInfoCollectionAccountingStatusInfo = accountingStatusInfoCollectionAccountingStatusInfo.getUsers();
                accountingStatusInfoCollectionAccountingStatusInfo.setUsers(users);
                accountingStatusInfoCollectionAccountingStatusInfo = em.merge(accountingStatusInfoCollectionAccountingStatusInfo);
                if (oldUsersOfAccountingStatusInfoCollectionAccountingStatusInfo != null) {
                    oldUsersOfAccountingStatusInfoCollectionAccountingStatusInfo.getAccountingStatusInfoCollection().remove(accountingStatusInfoCollectionAccountingStatusInfo);
                    oldUsersOfAccountingStatusInfoCollectionAccountingStatusInfo = em.merge(oldUsersOfAccountingStatusInfoCollectionAccountingStatusInfo);
                }
            }
            for (CurrentInfo currentInfoCollectionCurrentInfo : users.getCurrentInfoCollection()) {
                Users oldUsersOfCurrentInfoCollectionCurrentInfo = currentInfoCollectionCurrentInfo.getUsers();
                currentInfoCollectionCurrentInfo.setUsers(users);
                currentInfoCollectionCurrentInfo = em.merge(currentInfoCollectionCurrentInfo);
                if (oldUsersOfCurrentInfoCollectionCurrentInfo != null) {
                    oldUsersOfCurrentInfoCollectionCurrentInfo.getCurrentInfoCollection().remove(currentInfoCollectionCurrentInfo);
                    oldUsersOfCurrentInfoCollectionCurrentInfo = em.merge(oldUsersOfCurrentInfoCollectionCurrentInfo);
                }
            }
            for (Registration registrationCollectionRegistration : users.getRegistrationCollection()) {
                Users oldUsersOfRegistrationCollectionRegistration = registrationCollectionRegistration.getUsers();
                registrationCollectionRegistration.setUsers(users);
                registrationCollectionRegistration = em.merge(registrationCollectionRegistration);
                if (oldUsersOfRegistrationCollectionRegistration != null) {
                    oldUsersOfRegistrationCollectionRegistration.getRegistrationCollection().remove(registrationCollectionRegistration);
                    oldUsersOfRegistrationCollectionRegistration = em.merge(oldUsersOfRegistrationCollectionRegistration);
                }
            }
            for (SuccessReport successReportCollectionSuccessReport : users.getSuccessReportCollection()) {
                Users oldUsersOfSuccessReportCollectionSuccessReport = successReportCollectionSuccessReport.getUsers();
                successReportCollectionSuccessReport.setUsers(users);
                successReportCollectionSuccessReport = em.merge(successReportCollectionSuccessReport);
                if (oldUsersOfSuccessReportCollectionSuccessReport != null) {
                    oldUsersOfSuccessReportCollectionSuccessReport.getSuccessReportCollection().remove(successReportCollectionSuccessReport);
                    oldUsersOfSuccessReportCollectionSuccessReport = em.merge(oldUsersOfSuccessReportCollectionSuccessReport);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users users) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users persistentUsers = em.find(Users.class, users.getIduser());
            Collection<ApplicationsForms> applicationsFormsCollectionOld = persistentUsers.getApplicationsFormsCollection();
            Collection<ApplicationsForms> applicationsFormsCollectionNew = users.getApplicationsFormsCollection();
            Collection<Trascript> trascriptCollectionOld = persistentUsers.getTrascriptCollection();
            Collection<Trascript> trascriptCollectionNew = users.getTrascriptCollection();
            Collection<GeneralInfo> generalInfoCollectionOld = persistentUsers.getGeneralInfoCollection();
            Collection<GeneralInfo> generalInfoCollectionNew = users.getGeneralInfoCollection();
            Collection<InformationDiploma> informationDiplomaCollectionOld = persistentUsers.getInformationDiplomaCollection();
            Collection<InformationDiploma> informationDiplomaCollectionNew = users.getInformationDiplomaCollection();
            Collection<Roles> rolesCollectionOld = persistentUsers.getRolesCollection();
            Collection<Roles> rolesCollectionNew = users.getRolesCollection();
            Collection<AccountingStatusInfo> accountingStatusInfoCollectionOld = persistentUsers.getAccountingStatusInfoCollection();
            Collection<AccountingStatusInfo> accountingStatusInfoCollectionNew = users.getAccountingStatusInfoCollection();
            Collection<CurrentInfo> currentInfoCollectionOld = persistentUsers.getCurrentInfoCollection();
            Collection<CurrentInfo> currentInfoCollectionNew = users.getCurrentInfoCollection();
            Collection<Registration> registrationCollectionOld = persistentUsers.getRegistrationCollection();
            Collection<Registration> registrationCollectionNew = users.getRegistrationCollection();
            Collection<SuccessReport> successReportCollectionOld = persistentUsers.getSuccessReportCollection();
            Collection<SuccessReport> successReportCollectionNew = users.getSuccessReportCollection();
            List<String> illegalOrphanMessages = null;
            for (ApplicationsForms applicationsFormsCollectionOldApplicationsForms : applicationsFormsCollectionOld) {
                if (!applicationsFormsCollectionNew.contains(applicationsFormsCollectionOldApplicationsForms)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ApplicationsForms " + applicationsFormsCollectionOldApplicationsForms + " since its users field is not nullable.");
                }
            }
            for (Trascript trascriptCollectionOldTrascript : trascriptCollectionOld) {
                if (!trascriptCollectionNew.contains(trascriptCollectionOldTrascript)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Trascript " + trascriptCollectionOldTrascript + " since its users field is not nullable.");
                }
            }
            for (GeneralInfo generalInfoCollectionOldGeneralInfo : generalInfoCollectionOld) {
                if (!generalInfoCollectionNew.contains(generalInfoCollectionOldGeneralInfo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain GeneralInfo " + generalInfoCollectionOldGeneralInfo + " since its users field is not nullable.");
                }
            }
            for (InformationDiploma informationDiplomaCollectionOldInformationDiploma : informationDiplomaCollectionOld) {
                if (!informationDiplomaCollectionNew.contains(informationDiplomaCollectionOldInformationDiploma)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain InformationDiploma " + informationDiplomaCollectionOldInformationDiploma + " since its users field is not nullable.");
                }
            }
            for (Roles rolesCollectionOldRoles : rolesCollectionOld) {
                if (!rolesCollectionNew.contains(rolesCollectionOldRoles)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Roles " + rolesCollectionOldRoles + " since its users field is not nullable.");
                }
            }
            for (AccountingStatusInfo accountingStatusInfoCollectionOldAccountingStatusInfo : accountingStatusInfoCollectionOld) {
                if (!accountingStatusInfoCollectionNew.contains(accountingStatusInfoCollectionOldAccountingStatusInfo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AccountingStatusInfo " + accountingStatusInfoCollectionOldAccountingStatusInfo + " since its users field is not nullable.");
                }
            }
            for (CurrentInfo currentInfoCollectionOldCurrentInfo : currentInfoCollectionOld) {
                if (!currentInfoCollectionNew.contains(currentInfoCollectionOldCurrentInfo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CurrentInfo " + currentInfoCollectionOldCurrentInfo + " since its users field is not nullable.");
                }
            }
            for (Registration registrationCollectionOldRegistration : registrationCollectionOld) {
                if (!registrationCollectionNew.contains(registrationCollectionOldRegistration)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registration " + registrationCollectionOldRegistration + " since its users field is not nullable.");
                }
            }
            for (SuccessReport successReportCollectionOldSuccessReport : successReportCollectionOld) {
                if (!successReportCollectionNew.contains(successReportCollectionOldSuccessReport)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SuccessReport " + successReportCollectionOldSuccessReport + " since its users field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<ApplicationsForms> attachedApplicationsFormsCollectionNew = new ArrayList<ApplicationsForms>();
            for (ApplicationsForms applicationsFormsCollectionNewApplicationsFormsToAttach : applicationsFormsCollectionNew) {
                applicationsFormsCollectionNewApplicationsFormsToAttach = em.getReference(applicationsFormsCollectionNewApplicationsFormsToAttach.getClass(), applicationsFormsCollectionNewApplicationsFormsToAttach.getApplicationsFormsPK());
                attachedApplicationsFormsCollectionNew.add(applicationsFormsCollectionNewApplicationsFormsToAttach);
            }
            applicationsFormsCollectionNew = attachedApplicationsFormsCollectionNew;
            users.setApplicationsFormsCollection(applicationsFormsCollectionNew);
            Collection<Trascript> attachedTrascriptCollectionNew = new ArrayList<Trascript>();
            for (Trascript trascriptCollectionNewTrascriptToAttach : trascriptCollectionNew) {
                trascriptCollectionNewTrascriptToAttach = em.getReference(trascriptCollectionNewTrascriptToAttach.getClass(), trascriptCollectionNewTrascriptToAttach.getTrascriptPK());
                attachedTrascriptCollectionNew.add(trascriptCollectionNewTrascriptToAttach);
            }
            trascriptCollectionNew = attachedTrascriptCollectionNew;
            users.setTrascriptCollection(trascriptCollectionNew);
            Collection<GeneralInfo> attachedGeneralInfoCollectionNew = new ArrayList<GeneralInfo>();
            for (GeneralInfo generalInfoCollectionNewGeneralInfoToAttach : generalInfoCollectionNew) {
                generalInfoCollectionNewGeneralInfoToAttach = em.getReference(generalInfoCollectionNewGeneralInfoToAttach.getClass(), generalInfoCollectionNewGeneralInfoToAttach.getGeneralInfoPK());
                attachedGeneralInfoCollectionNew.add(generalInfoCollectionNewGeneralInfoToAttach);
            }
            generalInfoCollectionNew = attachedGeneralInfoCollectionNew;
            users.setGeneralInfoCollection(generalInfoCollectionNew);
            Collection<InformationDiploma> attachedInformationDiplomaCollectionNew = new ArrayList<InformationDiploma>();
            for (InformationDiploma informationDiplomaCollectionNewInformationDiplomaToAttach : informationDiplomaCollectionNew) {
                informationDiplomaCollectionNewInformationDiplomaToAttach = em.getReference(informationDiplomaCollectionNewInformationDiplomaToAttach.getClass(), informationDiplomaCollectionNewInformationDiplomaToAttach.getInformationDiplomaPK());
                attachedInformationDiplomaCollectionNew.add(informationDiplomaCollectionNewInformationDiplomaToAttach);
            }
            informationDiplomaCollectionNew = attachedInformationDiplomaCollectionNew;
            users.setInformationDiplomaCollection(informationDiplomaCollectionNew);
            Collection<Roles> attachedRolesCollectionNew = new ArrayList<Roles>();
            for (Roles rolesCollectionNewRolesToAttach : rolesCollectionNew) {
                rolesCollectionNewRolesToAttach = em.getReference(rolesCollectionNewRolesToAttach.getClass(), rolesCollectionNewRolesToAttach.getRolesPK());
                attachedRolesCollectionNew.add(rolesCollectionNewRolesToAttach);
            }
            rolesCollectionNew = attachedRolesCollectionNew;
            users.setRolesCollection(rolesCollectionNew);
            Collection<AccountingStatusInfo> attachedAccountingStatusInfoCollectionNew = new ArrayList<AccountingStatusInfo>();
            for (AccountingStatusInfo accountingStatusInfoCollectionNewAccountingStatusInfoToAttach : accountingStatusInfoCollectionNew) {
                accountingStatusInfoCollectionNewAccountingStatusInfoToAttach = em.getReference(accountingStatusInfoCollectionNewAccountingStatusInfoToAttach.getClass(), accountingStatusInfoCollectionNewAccountingStatusInfoToAttach.getAccountingStatusInfoPK());
                attachedAccountingStatusInfoCollectionNew.add(accountingStatusInfoCollectionNewAccountingStatusInfoToAttach);
            }
            accountingStatusInfoCollectionNew = attachedAccountingStatusInfoCollectionNew;
            users.setAccountingStatusInfoCollection(accountingStatusInfoCollectionNew);
            Collection<CurrentInfo> attachedCurrentInfoCollectionNew = new ArrayList<CurrentInfo>();
            for (CurrentInfo currentInfoCollectionNewCurrentInfoToAttach : currentInfoCollectionNew) {
                currentInfoCollectionNewCurrentInfoToAttach = em.getReference(currentInfoCollectionNewCurrentInfoToAttach.getClass(), currentInfoCollectionNewCurrentInfoToAttach.getCurrentInfoPK());
                attachedCurrentInfoCollectionNew.add(currentInfoCollectionNewCurrentInfoToAttach);
            }
            currentInfoCollectionNew = attachedCurrentInfoCollectionNew;
            users.setCurrentInfoCollection(currentInfoCollectionNew);
            Collection<Registration> attachedRegistrationCollectionNew = new ArrayList<Registration>();
            for (Registration registrationCollectionNewRegistrationToAttach : registrationCollectionNew) {
                registrationCollectionNewRegistrationToAttach = em.getReference(registrationCollectionNewRegistrationToAttach.getClass(), registrationCollectionNewRegistrationToAttach.getRegistrationPK());
                attachedRegistrationCollectionNew.add(registrationCollectionNewRegistrationToAttach);
            }
            registrationCollectionNew = attachedRegistrationCollectionNew;
            users.setRegistrationCollection(registrationCollectionNew);
            Collection<SuccessReport> attachedSuccessReportCollectionNew = new ArrayList<SuccessReport>();
            for (SuccessReport successReportCollectionNewSuccessReportToAttach : successReportCollectionNew) {
                successReportCollectionNewSuccessReportToAttach = em.getReference(successReportCollectionNewSuccessReportToAttach.getClass(), successReportCollectionNewSuccessReportToAttach.getSuccessReportPK());
                attachedSuccessReportCollectionNew.add(successReportCollectionNewSuccessReportToAttach);
            }
            successReportCollectionNew = attachedSuccessReportCollectionNew;
            users.setSuccessReportCollection(successReportCollectionNew);
            users = em.merge(users);
            for (ApplicationsForms applicationsFormsCollectionNewApplicationsForms : applicationsFormsCollectionNew) {
                if (!applicationsFormsCollectionOld.contains(applicationsFormsCollectionNewApplicationsForms)) {
                    Users oldUsersOfApplicationsFormsCollectionNewApplicationsForms = applicationsFormsCollectionNewApplicationsForms.getUsers();
                    applicationsFormsCollectionNewApplicationsForms.setUsers(users);
                    applicationsFormsCollectionNewApplicationsForms = em.merge(applicationsFormsCollectionNewApplicationsForms);
                    if (oldUsersOfApplicationsFormsCollectionNewApplicationsForms != null && !oldUsersOfApplicationsFormsCollectionNewApplicationsForms.equals(users)) {
                        oldUsersOfApplicationsFormsCollectionNewApplicationsForms.getApplicationsFormsCollection().remove(applicationsFormsCollectionNewApplicationsForms);
                        oldUsersOfApplicationsFormsCollectionNewApplicationsForms = em.merge(oldUsersOfApplicationsFormsCollectionNewApplicationsForms);
                    }
                }
            }
            for (Trascript trascriptCollectionNewTrascript : trascriptCollectionNew) {
                if (!trascriptCollectionOld.contains(trascriptCollectionNewTrascript)) {
                    Users oldUsersOfTrascriptCollectionNewTrascript = trascriptCollectionNewTrascript.getUsers();
                    trascriptCollectionNewTrascript.setUsers(users);
                    trascriptCollectionNewTrascript = em.merge(trascriptCollectionNewTrascript);
                    if (oldUsersOfTrascriptCollectionNewTrascript != null && !oldUsersOfTrascriptCollectionNewTrascript.equals(users)) {
                        oldUsersOfTrascriptCollectionNewTrascript.getTrascriptCollection().remove(trascriptCollectionNewTrascript);
                        oldUsersOfTrascriptCollectionNewTrascript = em.merge(oldUsersOfTrascriptCollectionNewTrascript);
                    }
                }
            }
            for (GeneralInfo generalInfoCollectionNewGeneralInfo : generalInfoCollectionNew) {
                if (!generalInfoCollectionOld.contains(generalInfoCollectionNewGeneralInfo)) {
                    Users oldUsersOfGeneralInfoCollectionNewGeneralInfo = generalInfoCollectionNewGeneralInfo.getUsers();
                    generalInfoCollectionNewGeneralInfo.setUsers(users);
                    generalInfoCollectionNewGeneralInfo = em.merge(generalInfoCollectionNewGeneralInfo);
                    if (oldUsersOfGeneralInfoCollectionNewGeneralInfo != null && !oldUsersOfGeneralInfoCollectionNewGeneralInfo.equals(users)) {
                        oldUsersOfGeneralInfoCollectionNewGeneralInfo.getGeneralInfoCollection().remove(generalInfoCollectionNewGeneralInfo);
                        oldUsersOfGeneralInfoCollectionNewGeneralInfo = em.merge(oldUsersOfGeneralInfoCollectionNewGeneralInfo);
                    }
                }
            }
            for (InformationDiploma informationDiplomaCollectionNewInformationDiploma : informationDiplomaCollectionNew) {
                if (!informationDiplomaCollectionOld.contains(informationDiplomaCollectionNewInformationDiploma)) {
                    Users oldUsersOfInformationDiplomaCollectionNewInformationDiploma = informationDiplomaCollectionNewInformationDiploma.getUsers();
                    informationDiplomaCollectionNewInformationDiploma.setUsers(users);
                    informationDiplomaCollectionNewInformationDiploma = em.merge(informationDiplomaCollectionNewInformationDiploma);
                    if (oldUsersOfInformationDiplomaCollectionNewInformationDiploma != null && !oldUsersOfInformationDiplomaCollectionNewInformationDiploma.equals(users)) {
                        oldUsersOfInformationDiplomaCollectionNewInformationDiploma.getInformationDiplomaCollection().remove(informationDiplomaCollectionNewInformationDiploma);
                        oldUsersOfInformationDiplomaCollectionNewInformationDiploma = em.merge(oldUsersOfInformationDiplomaCollectionNewInformationDiploma);
                    }
                }
            }
            for (Roles rolesCollectionNewRoles : rolesCollectionNew) {
                if (!rolesCollectionOld.contains(rolesCollectionNewRoles)) {
                    Users oldUsersOfRolesCollectionNewRoles = rolesCollectionNewRoles.getUsers();
                    rolesCollectionNewRoles.setUsers(users);
                    rolesCollectionNewRoles = em.merge(rolesCollectionNewRoles);
                    if (oldUsersOfRolesCollectionNewRoles != null && !oldUsersOfRolesCollectionNewRoles.equals(users)) {
                        oldUsersOfRolesCollectionNewRoles.getRolesCollection().remove(rolesCollectionNewRoles);
                        oldUsersOfRolesCollectionNewRoles = em.merge(oldUsersOfRolesCollectionNewRoles);
                    }
                }
            }
            for (AccountingStatusInfo accountingStatusInfoCollectionNewAccountingStatusInfo : accountingStatusInfoCollectionNew) {
                if (!accountingStatusInfoCollectionOld.contains(accountingStatusInfoCollectionNewAccountingStatusInfo)) {
                    Users oldUsersOfAccountingStatusInfoCollectionNewAccountingStatusInfo = accountingStatusInfoCollectionNewAccountingStatusInfo.getUsers();
                    accountingStatusInfoCollectionNewAccountingStatusInfo.setUsers(users);
                    accountingStatusInfoCollectionNewAccountingStatusInfo = em.merge(accountingStatusInfoCollectionNewAccountingStatusInfo);
                    if (oldUsersOfAccountingStatusInfoCollectionNewAccountingStatusInfo != null && !oldUsersOfAccountingStatusInfoCollectionNewAccountingStatusInfo.equals(users)) {
                        oldUsersOfAccountingStatusInfoCollectionNewAccountingStatusInfo.getAccountingStatusInfoCollection().remove(accountingStatusInfoCollectionNewAccountingStatusInfo);
                        oldUsersOfAccountingStatusInfoCollectionNewAccountingStatusInfo = em.merge(oldUsersOfAccountingStatusInfoCollectionNewAccountingStatusInfo);
                    }
                }
            }
            for (CurrentInfo currentInfoCollectionNewCurrentInfo : currentInfoCollectionNew) {
                if (!currentInfoCollectionOld.contains(currentInfoCollectionNewCurrentInfo)) {
                    Users oldUsersOfCurrentInfoCollectionNewCurrentInfo = currentInfoCollectionNewCurrentInfo.getUsers();
                    currentInfoCollectionNewCurrentInfo.setUsers(users);
                    currentInfoCollectionNewCurrentInfo = em.merge(currentInfoCollectionNewCurrentInfo);
                    if (oldUsersOfCurrentInfoCollectionNewCurrentInfo != null && !oldUsersOfCurrentInfoCollectionNewCurrentInfo.equals(users)) {
                        oldUsersOfCurrentInfoCollectionNewCurrentInfo.getCurrentInfoCollection().remove(currentInfoCollectionNewCurrentInfo);
                        oldUsersOfCurrentInfoCollectionNewCurrentInfo = em.merge(oldUsersOfCurrentInfoCollectionNewCurrentInfo);
                    }
                }
            }
            for (Registration registrationCollectionNewRegistration : registrationCollectionNew) {
                if (!registrationCollectionOld.contains(registrationCollectionNewRegistration)) {
                    Users oldUsersOfRegistrationCollectionNewRegistration = registrationCollectionNewRegistration.getUsers();
                    registrationCollectionNewRegistration.setUsers(users);
                    registrationCollectionNewRegistration = em.merge(registrationCollectionNewRegistration);
                    if (oldUsersOfRegistrationCollectionNewRegistration != null && !oldUsersOfRegistrationCollectionNewRegistration.equals(users)) {
                        oldUsersOfRegistrationCollectionNewRegistration.getRegistrationCollection().remove(registrationCollectionNewRegistration);
                        oldUsersOfRegistrationCollectionNewRegistration = em.merge(oldUsersOfRegistrationCollectionNewRegistration);
                    }
                }
            }
            for (SuccessReport successReportCollectionNewSuccessReport : successReportCollectionNew) {
                if (!successReportCollectionOld.contains(successReportCollectionNewSuccessReport)) {
                    Users oldUsersOfSuccessReportCollectionNewSuccessReport = successReportCollectionNewSuccessReport.getUsers();
                    successReportCollectionNewSuccessReport.setUsers(users);
                    successReportCollectionNewSuccessReport = em.merge(successReportCollectionNewSuccessReport);
                    if (oldUsersOfSuccessReportCollectionNewSuccessReport != null && !oldUsersOfSuccessReportCollectionNewSuccessReport.equals(users)) {
                        oldUsersOfSuccessReportCollectionNewSuccessReport.getSuccessReportCollection().remove(successReportCollectionNewSuccessReport);
                        oldUsersOfSuccessReportCollectionNewSuccessReport = em.merge(oldUsersOfSuccessReportCollectionNewSuccessReport);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = users.getIduser();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
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
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getIduser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ApplicationsForms> applicationsFormsCollectionOrphanCheck = users.getApplicationsFormsCollection();
            for (ApplicationsForms applicationsFormsCollectionOrphanCheckApplicationsForms : applicationsFormsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the ApplicationsForms " + applicationsFormsCollectionOrphanCheckApplicationsForms + " in its applicationsFormsCollection field has a non-nullable users field.");
            }
            Collection<Trascript> trascriptCollectionOrphanCheck = users.getTrascriptCollection();
            for (Trascript trascriptCollectionOrphanCheckTrascript : trascriptCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the Trascript " + trascriptCollectionOrphanCheckTrascript + " in its trascriptCollection field has a non-nullable users field.");
            }
            Collection<GeneralInfo> generalInfoCollectionOrphanCheck = users.getGeneralInfoCollection();
            for (GeneralInfo generalInfoCollectionOrphanCheckGeneralInfo : generalInfoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the GeneralInfo " + generalInfoCollectionOrphanCheckGeneralInfo + " in its generalInfoCollection field has a non-nullable users field.");
            }
            Collection<InformationDiploma> informationDiplomaCollectionOrphanCheck = users.getInformationDiplomaCollection();
            for (InformationDiploma informationDiplomaCollectionOrphanCheckInformationDiploma : informationDiplomaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the InformationDiploma " + informationDiplomaCollectionOrphanCheckInformationDiploma + " in its informationDiplomaCollection field has a non-nullable users field.");
            }
            Collection<Roles> rolesCollectionOrphanCheck = users.getRolesCollection();
            for (Roles rolesCollectionOrphanCheckRoles : rolesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the Roles " + rolesCollectionOrphanCheckRoles + " in its rolesCollection field has a non-nullable users field.");
            }
            Collection<AccountingStatusInfo> accountingStatusInfoCollectionOrphanCheck = users.getAccountingStatusInfoCollection();
            for (AccountingStatusInfo accountingStatusInfoCollectionOrphanCheckAccountingStatusInfo : accountingStatusInfoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the AccountingStatusInfo " + accountingStatusInfoCollectionOrphanCheckAccountingStatusInfo + " in its accountingStatusInfoCollection field has a non-nullable users field.");
            }
            Collection<CurrentInfo> currentInfoCollectionOrphanCheck = users.getCurrentInfoCollection();
            for (CurrentInfo currentInfoCollectionOrphanCheckCurrentInfo : currentInfoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the CurrentInfo " + currentInfoCollectionOrphanCheckCurrentInfo + " in its currentInfoCollection field has a non-nullable users field.");
            }
            Collection<Registration> registrationCollectionOrphanCheck = users.getRegistrationCollection();
            for (Registration registrationCollectionOrphanCheckRegistration : registrationCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the Registration " + registrationCollectionOrphanCheckRegistration + " in its registrationCollection field has a non-nullable users field.");
            }
            Collection<SuccessReport> successReportCollectionOrphanCheck = users.getSuccessReportCollection();
            for (SuccessReport successReportCollectionOrphanCheckSuccessReport : successReportCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the SuccessReport " + successReportCollectionOrphanCheckSuccessReport + " in its successReportCollection field has a non-nullable users field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(users);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
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

    public Users findUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
