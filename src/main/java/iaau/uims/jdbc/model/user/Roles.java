/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jdbc.model.user;

/**
 *
 * @author Çağrı Çakır
 */
public class Roles {

    private int idRoles;
    private String idnumber;
    private String userRole;
    private int PERMS_idPERMS;
    private int USERS_idusers;

    public int getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(int idRoles) {
        this.idRoles = idRoles;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getPERMS_idPERMS() {
        return PERMS_idPERMS;
    }

    public void setPERMS_idPERMS(int PERMS_idPERMS) {
        this.PERMS_idPERMS = PERMS_idPERMS;
    }

    public int getUSERS_idusers() {
        return USERS_idusers;
    }

    public void setUSERS_idusers(int USERS_idusers) {
        this.USERS_idusers = USERS_idusers;
    }

    @Override
    public String toString() {
        return "Roles{" + "idRoles=" + idRoles + ", idnumber=" + idnumber + ", userRole=" + userRole + ", PERMS_idPERMS=" + PERMS_idPERMS + ", USERS_idusers=" + USERS_idusers + '}';
    }
    
    
}
