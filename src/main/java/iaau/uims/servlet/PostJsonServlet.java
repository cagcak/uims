/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */

package iaau.uims.servlet;

import iaau.uims.json.generate.JsonApplicationsForms;
import iaau.uims.json.generate.JsonInformationDiploma;
import iaau.uims.json.generate.JsonRegistration;
import iaau.uims.json.generate.JsonAccountingStatusInfo;
import iaau.uims.json.generate.JsonCurrentInfo;
import iaau.uims.json.generate.JsonGeneralInfo;
import iaau.uims.json.generate.JsonSuccessReport;
import iaau.uims.json.generate.JsonTranscript;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Çağrı Çakır
 */
public class PostJsonServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
//            out.print(jsonObject);
            out.flush();
            out.close();
        
    }
    
    public void GenerateJsonFiles(String idNumber)
    {
//      1- Generating CurrentInfo.json file
//         Location: UIMS\src\main\ <idnumber> \CurrentInfo.json
        try {
            JsonCurrentInfo current_info = new JsonCurrentInfo();
            current_info.GenerateCurrentInfoAsJson(idNumber);
        } catch (SQLException ex) {
            Logger.getLogger(PostJsonServlet.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
//      2- Generating GeneralInfo.json file
//         Location: UIMS\src\main\ <idnumber> \GeneralInfo.json
        try {
            JsonGeneralInfo general_info = new JsonGeneralInfo();
            general_info.GenerateGeneralInfoAsJson(idNumber);
        } catch (SQLException ex) {
            Logger.getLogger(PostJsonServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//      3- Generating AccountingStatusInfo.json file
//         Location: UIMS\src\main\ <idnumber> \AccountingStatusInfo.json
        try {
            JsonAccountingStatusInfo account = new JsonAccountingStatusInfo();
            account.GenerateAccountingStatusInfoAsJson(idNumber);
        } catch (SQLException ex) {
            Logger.getLogger(PostJsonServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//      4- Generating SuccessReport.json file
//         Location: UIMS\src\main\ <idnumber> \SuccessReport.json
        try {
            JsonSuccessReport success = new JsonSuccessReport();
            success.GenerateSuccessReportAsJson(idNumber);
        } catch (SQLException ex) {
            Logger.getLogger(PostJsonServlet.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
//      5- Generating Transcript.json file
//         Location: UIMS\src\main\ <idnumber> \Transcript.json
        try {
            JsonTranscript transcript = new JsonTranscript();
            transcript.GenerateTranscriptAsJson(idNumber);
        } catch (SQLException ex) {
            Logger.getLogger(PostJsonServlet.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
//      6- Generating InformationDiploma.json file
//         Location: UIMS\src\main\ <idnumber> \InformationDiploma.json
        try {
            JsonInformationDiploma diplom = new JsonInformationDiploma();
            diplom.GenerateDiplomaInfoAsJson(idNumber);
        } catch (SQLException ex) {
            Logger.getLogger(PostJsonServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//      7- Generating Registration.json file
//         Location: UIMS\src\main\ <idnumber> \Registration.json
        try {
            JsonRegistration register = new JsonRegistration();
            register.GenerateRegistrationAsJson(idNumber);
        } catch (SQLException ex) {
            Logger.getLogger(PostJsonServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//      8- Generating InformationDiploma.json file
//         Location: UIMS\src\main\ <idnumber> \InformationDiploma.json
        try {
            JsonApplicationsForms apps = new JsonApplicationsForms();
            apps.GenerateAppsFormsAsJson(idNumber);
        } catch (SQLException ex) {
            Logger.getLogger(PostJsonServlet.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
    }
    
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
