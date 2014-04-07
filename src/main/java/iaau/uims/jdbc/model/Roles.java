/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jdbc.model;

/**
 *
 * @author Çağrı Çakır
 */
public class Roles {

    
    
    private String user_role;
    private String USERS_idnumber;

    
    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getUSERS_idnumber() {
        return USERS_idnumber;
    }

    public void setUSERS_idnumber(String USERS_idnumber) {
        this.USERS_idnumber = USERS_idnumber;
    }

    @Override
    public String toString() {
        return "Roles{" + "user_role=" + user_role + ", USERS_idnumber=" + USERS_idnumber + '}';
    }


    
}
