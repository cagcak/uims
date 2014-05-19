/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */
package iaau.uims.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import iaau.uims.jdbc.model.ApplicationsForms;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Çağrı Çakır
 */
public class GetFormRequest extends HttpServlet {

    public GetFormRequest() {
        super();
    }
    
    private static final long serialVersionUID = 1L;
    List<ApplicationsForms> forms = new ArrayList<ApplicationsForms>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);

        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json");

        mapper.writeValue(response.getOutputStream(), forms);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "";
    }

}
