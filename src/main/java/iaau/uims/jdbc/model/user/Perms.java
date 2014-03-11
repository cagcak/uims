/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jdbc.model.user;

/**
 *
 * @author Çağrı Çakır
 */
public class Perms {
    
    private int idPERMS;
    private String userRole;
    private String permission;

    public int getIdPERMS() {
        return idPERMS;
    }

    public void setIdPERMS(int idPERMS) {
        this.idPERMS = idPERMS;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Perms{" + "idPERMS=" + idPERMS + ", userRole=" + userRole + ", permission=" + permission + '}';
    }
    
    
}
