/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 *********************************************
 * This class tests all tables of the database 
 * by selectional statements.
 */

package iaau.uims.jdbc.factory;

import iaau.uims.jdbc.dao.AccountingStatusDAO;
import iaau.uims.jdbc.dao.ApplicationsFormsDAO;
import iaau.uims.jdbc.dao.CurrentInfoDAO;
import iaau.uims.jdbc.dao.GeneralInfoDAO;
import iaau.uims.jdbc.dao.InformationDiplomaDAO;
import iaau.uims.jdbc.dao.PermsDAO;
import iaau.uims.jdbc.dao.RegistrationDAO;
import iaau.uims.jdbc.dao.RolesDAO;
import iaau.uims.jdbc.dao.SuccessReportDAO;
import iaau.uims.jdbc.dao.TranscriptDAO;
import iaau.uims.jdbc.dao.UsersDAO;
import iaau.uims.jdbc.model.ApplicationsForms;
import iaau.uims.jdbc.model.InformationDiploma;
import iaau.uims.jdbc.model.Registration;
import iaau.uims.jdbc.model.AccountingStatusInfo;
import iaau.uims.jdbc.model.CurrentInfo;
import iaau.uims.jdbc.model.GeneralInfo;
import iaau.uims.jdbc.model.SuccessReport;
import iaau.uims.jdbc.model.Transcript;
import iaau.uims.jdbc.model.Perms;
import iaau.uims.jdbc.model.Roles;
import iaau.uims.jdbc.model.Users;
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
        actionMenu();
    }
    
    private static void showMenu() {
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
    }    
    private static void iterateAction(){
        System.out.println("\nContinue(c) or Exit(-1)");
        Scanner def = new Scanner(System.in);
        String default_selection = def.nextLine();
        if ("c".equals(default_selection)) {
            actionMenu();
        } else {
            System.exit(0);
        }
    }
    private static void actionMenu(){
        int selection;

        showMenu();

        Scanner input = new Scanner(System.in);
        selection = input.nextInt();
/////////////////////////////  TEST MENU ACTION   //////////////////////////////       
        switch (selection) {
            case 1: {
                getUserByIDnumber();
                iterateAction();
                break;
            }
            case 2: {
                getUserRoleByIDnumber();
                iterateAction();
                break;
            }
            case 3: {
                getPermissionOfUserByIDnumber();
                iterateAction();
                break;
            }
            case 4: {
                getGeneralInfoByIDnumber();
                iterateAction();
                break;
            }
            case 5: {
                getCurrentInfoByIDnumber();
                iterateAction();
                break;
            }
            case 6: {
                getAccountingStatusByIDnumber();
                iterateAction();
                break;
            }
            case 7: {
                getSuccessReportByIDnumber();
                iterateAction();
                break;
            }
            case 8: {
                getTranscriptByIDnumber();
                iterateAction();
                break;
            }
            case 9: {
                getAppsFormsByIDnumber();
                iterateAction();
                break;
            }
            case 10: {
                getInfoDiplomaByIDnumber();
                iterateAction();
                break;
            }
            case 11: {
                getRegistrationByIDnumber();
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
////////////////////////////////////////////////////////////////////////////////
//////////////////////   RETRIEVING TABLE RECORDS    ///////////////////////////
////////////////////////////////////////////////////////////////////////////////
    private static void getUserByIDnumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID of record:");
        
        try
        {
            String idnumber = br.readLine().trim();
            UsersDAO userDAO  = new UsersDAO();
            Users user = userDAO.getUserByIDnumber(idnumber);
            
            if ( user != null )
            {
                displayUser(user);
            }else{
                System.out.println("No User with ID number: " + idnumber);
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
        
        try
        {
            String idnumber = br.readLine().trim();
            RolesDAO role = new RolesDAO();
            Roles userrole = role.getUserRoleByIDnumber(idnumber);
            
            if (userrole != null) {
                displayUserRole(userrole);
            } else {
                System.out.println("No User with ID number: " + idnumber);
            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void getPermissionOfUserByIDnumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID number:");
        
        try{
            String idnumber = br.readLine();
            PermsDAO permission = new PermsDAO();
            Perms roleperm = permission.getUserPermissionByIDnumber(idnumber);
            
            if(roleperm != null)
            {
                displayUserRolePerm(roleperm);
            }else{
                System.out.println("No User with ID number: " + idnumber);
            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void getGeneralInfoByIDnumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID number:");
        
        try{
            String idnumber = br.readLine();
            GeneralInfoDAO generalinfo = new GeneralInfoDAO();
            GeneralInfo geninfo = generalinfo.getGeneralInformationByIDnumber(idnumber);
            
            if(geninfo != null)
            {
                displayGeneralInfo(geninfo);
            }else{
                System.out.println("No User with ID number: " + idnumber);
            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void getCurrentInfoByIDnumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID number:");
        
        try{
            String idnumber = br.readLine();
            CurrentInfoDAO currentinfo = new CurrentInfoDAO();
            CurrentInfo current = currentinfo.getCurrentInfoByIDnumber(idnumber);
            
            if(current != null)
            {
                displayCurrentInfo(current);
            }else{
                System.out.println("No User with ID number: " + idnumber);
            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void getAccountingStatusByIDnumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID number:");
        
        try{
            String idnumber = br.readLine();
            AccountingStatusDAO a_status = new AccountingStatusDAO();
            AccountingStatusInfo status = a_status.getAccountingSatatusInfoByIDnumber(idnumber);
            
            if (status != null) {
                displayAccountingStatusInfo(status);
            } else {
                System.out.println("No User with ID number: " + idnumber);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void getSuccessReportByIDnumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID number:");
        
        try{
            String idnumber = br.readLine();
            SuccessReportDAO s_report = new SuccessReportDAO();
            SuccessReport report = s_report.getSuccessReportByIDnumber(idnumber);
            
            if (report != null) {
                displaySuccessReport(report);
            } else {
                System.out.println("No User with ID number: " + idnumber);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void getTranscriptByIDnumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID number:");
        
        try {
            String idnumber = br.readLine();
            TranscriptDAO transcript = new TranscriptDAO();
            Transcript script = transcript.getTranscriptByIDnumber(idnumber);

            if (script != null) {
                displayTranscript(script);
            } else {
                System.out.println("No User with ID number: " + idnumber);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void getAppsFormsByIDnumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID number:");
        
        try {
            String idnumber = br.readLine();
            ApplicationsFormsDAO apps_forms = new ApplicationsFormsDAO();
            ApplicationsForms apps = apps_forms.getAppsFormsByIDnumber(idnumber);

            if (apps != null) {
                displayAppsForms(apps);
            } else {
                System.out.println("No User with ID number: " + idnumber);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void getInfoDiplomaByIDnumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID number:");
        
        try {
            String idnumber = br.readLine();
            InformationDiplomaDAO infodiploma = new InformationDiplomaDAO();
            InformationDiploma info_diplom = infodiploma.getInfoDiplomaByIDnumber(idnumber);

            if (info_diplom != null) {
                displayDiplomaInfo(info_diplom);
            } else {
                System.out.println("No User with ID number: " + idnumber);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void getRegistrationByIDnumber() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the ID number:");
        
        try {
            String idnumber = br.readLine();
            RegistrationDAO regdao = new RegistrationDAO();
            Registration regs = regdao.getRegiatrationByIDnumber(idnumber);

            if (regs != null) {
                displayRegistration(regs);
            } else {
                System.out.println("No User with ID number: " + idnumber);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
////////////////////////////////////////////////////////////////////////////////
//////////////////////    DISPLAY TABLE RECORDS      ///////////////////////////
////////////////////////////////////////////////////////////////////////////////
    private static void displayUser(Users user) {
        System.out.println("idnumber: "+user.getIdnumber());
        System.out.println("password: "+user.getPassword());
        user.toString();
    }
    private static void displayUserRole(Roles userrole) {
        System.out.println("userrole: " + userrole.getUser_role());
        userrole.toString();
    }
    private static void displayUserRolePerm(Perms roleperm) {
        System.out.println("permission: "+roleperm.getPermission());
        roleperm.toString();
    }
    private static void displayGeneralInfo(GeneralInfo geninfo) {
        System.out.println("faculty: "+geninfo.getFaculty());
        System.out.println("department: "+geninfo.getDepartment());
        System.out.println("group_name: "+geninfo.getGroup_name());
        System.out.println("supervisor: "+geninfo.getSupervisor());
        System.out.println("education: "+geninfo.getEducation());
        System.out.println("registration: "+geninfo.getRegistration());
    }
    private static void displayCurrentInfo(CurrentInfo current) {
        System.out.println("fullname: "+current.getFullname());
        System.out.println(""+current.getCurrent_year());
        System.out.println(""+current.getCurrent_semester());
        System.out.println(""+current.getCurrent_month());
        System.out.println(""+current.getCurrent_exam());
    }
    private static void displayAccountingStatusInfo(AccountingStatusInfo status) {
        System.out.println("registration: "+status.getRegistration());
        System.out.println("midterm: "+status.getMidterm());
        System.out.println("final: "+status.getFinal1());
    }
    private static void displaySuccessReport(SuccessReport report) {
        System.out.println("subject name: "+report.getSubject_name());
        System.out.println("hours: "+report.getHours());
        System.out.println("midterm: "+report.getMidterm());
        System.out.println("final: "+report.getFinal1());
        System.out.println("average: "+report.getAverage());
        System.out.println("attandance: "+report.getAttandance());
        System.out.println("semester: "+report.getSemester());
        System.out.println("academic year: "+report.getAcademic_year());
    }
    private static void displayTranscript(Transcript script) {
        System.out.println("subject code: "+script.getSubject_code());
        System.out.println("subject name: "+script.getSubject_name());
        System.out.println("semester: "+script.getSemester());
        System.out.println("year: "+script.getYear());
        System.out.println("credits: "+script.getCredits());
        System.out.println("average: "+script.getAverage());
    }
    private static void displayAppsForms(ApplicationsForms appsforms) {
        System.out.println("reference type"+appsforms.getReference_type());
        System.out.println("language"+appsforms.getLanguage());
    }
    private static void displayDiplomaInfo(InformationDiploma info_diplom) {
        System.out.println("firstname: "+info_diplom.getFirstname());
        System.out.println("lastname: "+info_diplom.getLastname());
        System.out.println("middlename: "+info_diplom.getMiddlename());
        System.out.println("fullname_ru: "+info_diplom.getFullname_ru());
        System.out.println("current_address: "+info_diplom.getCurrent_address());
        System.out.println("passport_no: "+info_diplom.getPassport_no());
        System.out.println("birthday: "+info_diplom.getBirthday());
        System.out.println("phone number: "+info_diplom.getPhone_number());
        System.out.println("thesis project name (en): "+info_diplom.getThesis_project_en());
        System.out.println("thesis project name (ru): "+info_diplom.getThesis_project_ru());
        System.out.println("thesis project name (kg): "+info_diplom.getThesis_project_kg());
        System.out.println("Year of school graduate: "+info_diplom.getYear_of_school_graduation());
    }
    private static void displayRegistration(Registration regs) {
        System.out.println("subject code: "+regs.getSubject_code());
        System.out.println("subject name: "+regs.getSubject_name());
        System.out.println("semester: "+regs.getSemester());
        System.out.println("year: "+regs.getYear());
        System.out.println("hours: "+regs.getHours());
        System.out.println("credits: "+regs.getCredits());
        System.out.println("registration status: "+regs.getRegistration_status());
    }
}