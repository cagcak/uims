/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */

package iaau.uims.json.parse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Çağrı Çakır
 */
public class ParseJsonTest {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        actionMenu();
    }

    private static void actionMenu() throws IOException {
        int selection;

        showMenu();

        Scanner input = new Scanner(System.in);
        selection = input.nextInt();
        
        switch (selection)
        {
            case 1: {
                getParsedJsonUser();
                iterateAction();
                break;
            }
            case 2: {
                getParsedJsonUserRole();
                iterateAction();
                break;
            }
            case 3: {
                getParsedJsonUserPermission();
                iterateAction();
                break;
            }
            case 4: {
                getParsedJsonGeneralInfo();
                iterateAction();
                break;
            }
            case 5: {
                getParsedJsonCurrentInfo();
                iterateAction();
                break;
            }
            case 6: {
                getParsedJsonAccountingStatus();
                iterateAction();
                break;
            }
            case 7: {
                getParsedJsonSuccessReport();
                iterateAction();
                break;
            }
            case 8: {
                getParsedJsonTranscript();
                iterateAction();
                break;
            }
            case 9: {
                getParsedJsonAppsForms();
                iterateAction();
                break;
            }
            case 10: {
                getParsedJsonInformationDiploma();
                iterateAction();
                break;
            }
            case 11: {
                getParsedJsonRegistration();
                iterateAction();
                break;
            }
            case -1: {
                break;
            }
            default: {
                System.out.println("\tWrong option!\n");
                iterateAction();
            }
        }
    }

    private static void showMenu() {
        System.out.println("||||||||||||| Parse JSON |||||||||||||||| ");
        System.out.println("|1| User Login ");
        System.out.println("|2| User role");
        System.out.println("|3| User Role permission");
        System.out.println("|4| General Information");
        System.out.println("|5| Current Information");
        System.out.println("|6| Accounting Status Information");
        System.out.println("|7| Success Report");
        System.out.println("|8| Transcript");
        System.out.println("|9| Applications & Forms info");
        System.out.println("|10| Information of Diploma");
        System.out.println("|11| Registration Info");
        System.out.println("|-1| EXIT ");
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||");
    }

    private static void iterateAction() throws IOException {
        System.out.println("\nContinue(c) or Exit(-1)");
        Scanner def = new Scanner(System.in);
        String default_selection = def.nextLine();
        if ("c".equals(default_selection)) {
            actionMenu();
        } else {
            System.exit(0);
        }
    }

    private static void getParsedJsonUser() {
    }

    private static void getParsedJsonUserRole() {
    }

    private static void getParsedJsonUserPermission() {
    }

    private static void getParsedJsonGeneralInfo() throws IOException {
        ParseGeneralInfo general_info = new ParseGeneralInfo();
        Scanner is = new Scanner(System.in);
        System.out.println("id: ");
        String a = is.nextLine();
        general_info.Parsing(a);
        general_info.Printing();
    }

    private static void getParsedJsonCurrentInfo() throws IOException {
        ParseCurrentInfo current_info = new ParseCurrentInfo();
        Scanner is = new Scanner(System.in);
        System.out.println("id: ");
        String a = is.nextLine();
        current_info.Parsing(a);
        current_info.Printing();
    }

    private static void getParsedJsonAccountingStatus() {
    }

    private static void getParsedJsonSuccessReport() {
    }

    private static void getParsedJsonTranscript() {
    }

    private static void getParsedJsonAppsForms() {
    }

    private static void getParsedJsonInformationDiploma() {
    }

    private static void getParsedJsonRegistration() {
    }
    
}
