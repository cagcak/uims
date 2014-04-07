/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jdbc.model;

/**
 *
 * @author Çağrı Çakır
 */
public class Perms {
    
    
    private String permission;
    private String ROLES_USERS_idnumber;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getROLES_USERS_idnumber() {
        return ROLES_USERS_idnumber;
    }

    public void setROLES_USERS_idnumber(String ROLES_USERS_idnumber) {
        this.ROLES_USERS_idnumber = ROLES_USERS_idnumber;
    }

    @Override
    public String toString() {
        return "Perms{" + "permission=" + permission + ", ROLES_USERS_idnumber=" + ROLES_USERS_idnumber + '}';
    }

    
    
    
}
