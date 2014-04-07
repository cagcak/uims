/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */
package iaau.uims.jdbc.model;

/**
 *
 * @author Çağrı Çakır
 */
public class Users {

    private String idnumber;
    private String password;

    public Users() {
    }

    public Users(String idnumber, String password) {
        this.idnumber = idnumber;
        this.password = password;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
//        return "Users{" + "idnumber=" + idnumber + ", password=" + password + '}';
        return "User [idnumber=" + idnumber + ", password=" + password + "]";    
    }

} 
