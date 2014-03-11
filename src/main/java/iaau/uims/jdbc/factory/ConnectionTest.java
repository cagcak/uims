/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 *********************************************
 * This class tests all tables of the database 
 * by selectional statements.
 */

package iaau.uims.jdbc.factory;

import iaau.uims.jdbc.dao.UsersDAO;
import iaau.uims.jdbc.model.user.Users;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Çağrı Çakır
 */
public class ConnectionTest {
    
    public static void main(String[] args)
    {
        int selection;
        
        System.out.println("Make a selection ");
        System.out.println("*****************");
        System.out.println("Press, ");
        System.out.println("\"1\", to test a specific User");
        System.out.println("\"2\", to check role of a specific User");
        System.out.println("\"3\", to learn permission of a specific User's role");
        System.out.println("****************************************************");
        System.out.println("\"4\", to get General Information of a specific User");
        System.out.println("\"5\", to get Current Information of a specific User");
        System.out.println("\"6\", to get Accounting Status of a specific User");
        System.out.println("****************************************************");
        System.out.println("\"7\", to retrieve Success Report of a specific User");
        System.out.println("****************************************************");
        System.out.println("\"8\", to show Trascript for a specific User");
        System.out.println("****************************************************");
        System.out.println("\"9\", to test AppsForms activities for a specific User");
        System.out.println("\"10\", to test InfoDiploma of a specific User");
        System.out.println("\"11\", to test Registration of a specific User");
        System.out.println("\"-1\", QUIT");
        
        Scanner input = new Scanner(System.in);
        selection = input.nextInt();
        
        switch (selection)
        {
            case 1:
            {
                getUserById();
                break;
            }
            case 2:
            {
                getUserRoleByIDnumber();
            }
            case 3:
            {
                getPermissionOfUserByIDnumber();
            }
            case 4:
            {
                getGeneralInfoByIDnumber();
            }
            case 5:
            {
                getCurrentInfoByIDnumber();
            }
            case 6:
            {
                getAccountingStatusByIDnumber();
            }
            case 7:
            {
                getSuccessReportByIDnumber();
            }
            case 8:
            {
                getTranscriptByIDnumber();
            }
            case 9:
            {
                getAppsFormsByIDnumber();
            }
            case 10:
            {
                getInfoDiplomaByIDnumber();
            }
            case 11:
            {
                getRegistrationByIDnumber();
            }
            case -1:
            {
                break;
            }
            default:
            {
                // To do something..
            }
        }
    }

    private static void getUserById() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID of record:");
        
        try
        {
            //String IDnumber = br.toString();
            int iduser = Integer.parseInt(br.readLine());
            UsersDAO userDAO  = new UsersDAO();
            Users user = userDAO.getUserByTableID(iduser);
            
            if ( user != null )
            {
                displayUser(user);
            }else{
                System.out.println("No User with ID number: " + iduser);
            }
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getUserRoleByIDnumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID number:");
    }

    private static void getPermissionOfUserByIDnumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID number:");
    }

    private static void getGeneralInfoByIDnumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID number:");
    }

    private static void getCurrentInfoByIDnumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID number:");
    }

    private static void getAccountingStatusByIDnumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID number:");
    }

    private static void getSuccessReportByIDnumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID number:");
    }

    private static void getTranscriptByIDnumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID number:");
    }

    private static void getAppsFormsByIDnumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID number:");
    }

    private static void getInfoDiplomaByIDnumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID number:");
    }

    private static void getRegistrationByIDnumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID number:");
    }

    private static void displayUser(Users user) {
        System.out.println("iduser: "+user.getIduser());
        System.out.println("idnumber: "+user.getIdnumber());
        System.out.println("password: "+user.getPassword());
        user.toString();
    }
}
