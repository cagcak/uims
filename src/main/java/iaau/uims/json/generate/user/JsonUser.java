/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */
package iaau.uims.json.generate.user;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import iaau.uims.jdbc.factory.ConnectionFactory;
import iaau.uims.jdbc.factory.ConnectionUtility;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Çağrı Çakır
 */
public class JsonUser {

    private Connection connection;
    private Statement statement;

    public JsonUser() {

    }

    public void GenerateUserAsJson(String idNumber) throws SQLException {

        String query = "SELECT USERS.idnumber, "
                + "USERS.password "
                + "FROM USERS"
                + "WHERE USERS.idnumber = " + idNumber;
        ResultSet rs = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            rs = statement.executeQuery(query);

            JsonObject jsonResponse = new JsonObject();
            JsonArray data = new JsonArray();

//          while (rs.next())
            if (rs.next()) {
                JsonArray row = new JsonArray();
                row.add(new JsonPrimitive(rs.getString("idnumber")));
                row.add(new JsonPrimitive(rs.getString("password")));
                data.add(row);
            }
            jsonResponse.add("jsonUser", data);
            System.out.println("JSONArray form: "+data);
//          Generated JSON String of Output must be like
//          [["08010101865","00011011"]] as [["idnumber","password"]]
            String a = data.toString();
            System.out.println("String form: " +a);
        } finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }
    }

}


///*
// * This class is under a licence of IAAU
// * University Information Management System  * 
// * ----------------------------------------  * 
// *   https://github.com/cagricakir/uims.git  * 
// *  ------    ----------     -------------   * 
// *   add -----> commit -----> remote>push    * 
// */
//package iaau.uims.json.generate;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonPrimitive;
//import iaau.uims.jdbc.factory.ConnectionFactory;
//import iaau.uims.jdbc.factory.ConnectionUtility;
//import iaau.uims.jdbc.model.user.Users;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//
//import java.util.Scanner;
//
///**
// *
// * @author Çağrı Çakır
// */
//public class JsonUser {
//
////    private Statement statement;
////    private Connection connection;
//    public JsonUser() {
//
//    }
//
//    public static void GenerateUserAsJson(String idNumber) throws SQLException {
//
//        String query = "SELECT * FROM USERS WHERE idnumber = " + idNumber;
//        ResultSet rs = null;
//        Statement statement = null;
//        Connection connection = null;
//
//        try {
//            connection = ConnectionFactory.getConnection();
//            statement = connection.createStatement();
//
//            rs = statement.executeQuery(query);
//
//            JsonObject jsonResponse = new JsonObject();
//            JsonArray data = new JsonArray();
//
//            if (rs.next()) {
//                JsonArray row = new JsonArray();
//                row.add(new JsonPrimitive(rs.getString("idnumber")));
//                row.add(new JsonPrimitive(rs.getString("password")));
//                data.add(row);
//            }
//            jsonResponse.add("jsonUser", data);
//            System.out.println(data);
////          Generated JSON String of Output must be like
////          [["08010101865","00011011"]] as [["idnumber","password"]]
//
//        } finally {
//            ConnectionUtility.close(rs);
//            ConnectionUtility.close(statement);
//            ConnectionUtility.close(connection);
//        }
//    }
//
//    public static void main(String[] args) throws SQLException {
//        Scanner is = new Scanner(System.in);
//        System.out.println("id: ");
//        String a = is.nextLine();
//        GenerateUserAsJson(a);
//    }
//
//}
