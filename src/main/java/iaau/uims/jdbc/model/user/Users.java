/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 */

package iaau.uims.jdbc.model.user;

/**
 *
 * @author Çağrı Çakır
 */
public class Users {
    
    private int iduser;
    private String idnumber;
    private String password;

    public Users(int iduser, String idnumber, String password) {
        this.iduser = iduser;
        this.idnumber = idnumber;
        this.password = password;
    }
    
    public Users(String idnumber, String password) {
        this.idnumber = idnumber;
        this.password = password;
    }

    public Users(int iduser) {
        this.iduser = iduser;
    }

    public Users() {}
    
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
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
        return "Users{" + "iduser=" + iduser + ", idnumber=" + idnumber + ", password=" + password + '}';
    }   
}
