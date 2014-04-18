/*
 * Awake File: Easy file upload & download through HTTP with Java
 * Awake SQL: Remote JDBC access through HTTP.                                    
 * Copyright (C) 2012, Kawan Softwares S.A.S.
 * (http://www.awakeframework.org). All rights reserved.                                
 *                                                                               
 * Awake File/SQL is free software; you can redistribute it and/or                 
 * modify it under the terms of the GNU Lesser General Public                    
 * License as published by the Free Software Foundation; either                  
 * version 2.1 of the License, or (at your option) any later version.            
 *                                                                               
 * Awake File/SQL is distributed in the hope that it will be useful,               
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU             
 * Lesser General Public License for more details.                               
 *                                                                               
 * You should have received a copy of the GNU Lesser General Public              
 * License along with this library; if not, write to the Free Software           
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  
 * 02110-1301  USA
 *
 * Any modifications to this file must keep this entire header
 * intact.
 * org.awakefw.file.servlet.AwakeFileManager
 */
package iaau.uims.servlet;

// ServerCallerRecv : SimplifyPost - Execute any remote method
// 
// 17/12/05 12:50 NDP  - Creation
// 17/12/05 19:40 NDP  - Trim all parameters values
// 18/12/05 21:35 NDP  - add crSize & test all
// 09/06/07 12:37 ABE - Clean code to avoid warnings
// 
// 24/07/08 20:15 NDP - Clean code & new token mechanism
// 19/08/08 19:40 NDP - login &  Authentication Token may be null
// 02/03/10 15:25 NDP - AwakeFileServer: New name 
// 17/10/10 19:50 NDP : AwakeFileServer: Wrap the HttpServletRequest with HttpServletRequestEncrypted 
//                      for parameters decryption encrypted on the PC
// 15/03/11 17:20 NDP - AwakeFileServer: no more awakeJdbcDriverConfiguratorClassName
// 27/04/11 19:05 NDP - AwakeFileServer: put as much init code in init() instead of doPost()
// 20/06/11 12:45 NDP : AwakeFileManager: new name for AwakeFileServer
// 23/09/11 17:30 NDP : AwakeFileManager: better fault tolerance on first class name capitalization
// 10/10/11 12:55 NDP : AwakeFileManager: add get method to display configuration info
// 10/10/11 15:25 NDP : AwakeFileManager: add debug info configurator
// 14/11/11 17:45 NDP : AwakeFileManager: call AwakeLogger.createLogger(directory);
// 21/11/11 16:45 NDP : AwakeFileManager: cleaner display of init parameters
// 24/03/12 14:20 NDP : AwakeFileManager: configurator members are not anymore static
// 29/03/12 13:45 NDP : AwakeFileManager: trap user configuration errors and send it to client side
// 29/03/12 13:45 NDP : AwakeFileManager: get does a configuration test and displays a status
// 29/03/12 19:40 NDP : AwakeFileManager: test awakeCommonsConfigurator.login()
//
// 02/04/12 16:20 NDP : AwakeFileManager: trap complete exception if configuration occurs
// 03/04/12 13:50 NDP : AwakeFileManager: test configurator methods only in get
// 02/05/12 19:15 NDP : AwakeSqlManager: display Awake SQL version on Tomcat logs + browser
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.awakefw.commons.api.server.AwakeCommonsConfigurator;
import org.awakefw.commons.api.server.DefaultAwakeCommonsConfigurator;
import org.awakefw.file.api.server.AwakeFileConfigurator;
import org.awakefw.file.api.server.DefaultAwakeFileConfigurator;
import org.awakefw.file.api.server.fileaction.AwakeFileActionManager;
import org.awakefw.file.api.server.fileaction.DefaultAwakeFileActionManager;
import org.awakefw.file.http.HttpTransfer;
import org.awakefw.file.servlet.RequestInfoStore;
import org.awakefw.file.servlet.ServerAwakeFileDispatch;
import org.awakefw.file.servlet.ServerUserException;
import org.awakefw.file.util.AwakeLogger;
import org.awakefw.file.util.Tag;
import org.awakefw.file.util.convert.HttpServletRequestConvertor;
import org.awakefw.file.version.AwakeFileVersion;

/**
 * The main Awake File Manager Servlet <br>
 *
 * @author Nicolas de Pomereu
 */
@SuppressWarnings("serial")
public class AwakeFileManager extends HttpServlet {

    public static String CR_LF = System.getProperty("line.separator");

    public static final String AWAKE_FILE_ACTION_MANAGER_CLASS_NAME = "awakeFileActionManagerClassName";
    public static final String AWAKE_FILE_CONFIGURATOR_CLASS_NAME = "awakeFileConfiguratorClassName";
    public static final String AWAKE_COMMONS_CONFIGURATOR_CLASS_NAME = "awakeCommonsConfiguratorClassName";

    private AwakeCommonsConfigurator awakeCommonsConfigurator = null;
    private AwakeFileConfigurator awakeFileConfigurator = null;
    private AwakeFileActionManager awakeFileActionManager = null;

    /**
     * The init error message trapped
     */
    private String initErrrorMesage = null;

    /**
     * The Exception thrown at init
     */
    private Exception exception = null;

    /**
     * Init.
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

	// Variable use to store the current name when loading, used to
        // detail
        // the exception in the catch clauses
        String classNameToLoad = null;

        String awakeCommonsConfiguratorClassName = config
                .getInitParameter(AWAKE_COMMONS_CONFIGURATOR_CLASS_NAME);
        String awakeFileConfiguratorClassName = config
                .getInitParameter(AWAKE_FILE_CONFIGURATOR_CLASS_NAME);
        String awakeFileActionManagerClassName = config
                .getInitParameter(AWAKE_FILE_ACTION_MANAGER_CLASS_NAME);

        try {

            // Check spelling with first letter capitalized
            if (awakeCommonsConfiguratorClassName == null
                    || awakeCommonsConfiguratorClassName.isEmpty()) {
                String capitalized = StringUtils
                        .capitalize(AWAKE_COMMONS_CONFIGURATOR_CLASS_NAME);
                awakeCommonsConfiguratorClassName = config
                        .getInitParameter(capitalized);
            }

            if (awakeFileConfiguratorClassName == null
                    || awakeFileConfiguratorClassName.isEmpty()) {
                String capitalized = StringUtils
                        .capitalize(AWAKE_FILE_CONFIGURATOR_CLASS_NAME);
                awakeFileConfiguratorClassName = config
                        .getInitParameter(capitalized);
            }

            if (awakeFileActionManagerClassName == null
                    || awakeFileActionManagerClassName.isEmpty()) {
                String capitalized = StringUtils
                        .capitalize(AWAKE_FILE_ACTION_MANAGER_CLASS_NAME);
                awakeFileActionManagerClassName = config
                        .getInitParameter(capitalized);
            }

	    // System.out.println(Tag.AWAKE_START +
            // "AwakeFileManager start...");
	    // Call the specific Awake File Configurator class to use
            classNameToLoad = awakeCommonsConfiguratorClassName;
            if (awakeCommonsConfiguratorClassName != null
                    && !awakeCommonsConfiguratorClassName.isEmpty()) {
                Class<?> c = Class.forName(awakeCommonsConfiguratorClassName);
                awakeCommonsConfigurator = (AwakeCommonsConfigurator) c
                        .newInstance();
            } else {
                awakeCommonsConfigurator = new DefaultAwakeCommonsConfigurator();
            }

            // Immediately create the AwakeLogger
            Logger logger = null;
            try {
                logger = awakeCommonsConfigurator.getLogger();
                AwakeLogger.createLogger(logger);
            } catch (Exception e) {
                initErrrorMesage = Tag.AWAKE_USER_CONFIG_FAIL
                        + "Impossible to create the Logger: " + logger;
                exception = e;

            }

            classNameToLoad = awakeFileConfiguratorClassName;
            if (awakeFileConfiguratorClassName != null
                    && !awakeFileConfiguratorClassName.isEmpty()) {
                Class<?> c = Class.forName(awakeFileConfiguratorClassName);
                awakeFileConfigurator = (AwakeFileConfigurator) c.newInstance();
            } else {
                awakeFileConfigurator = new DefaultAwakeFileConfigurator();
            }

            classNameToLoad = awakeFileActionManagerClassName;
            if (awakeFileActionManagerClassName != null
                    && !awakeFileActionManagerClassName.isEmpty()) {
                Class<?> c = Class.forName(awakeFileActionManagerClassName);
                awakeFileActionManager = (AwakeFileActionManager) c
                        .newInstance();
            } else {
                awakeFileActionManager = new DefaultAwakeFileActionManager();
            }

        } catch (ClassNotFoundException e) {
            initErrrorMesage = Tag.AWAKE_USER_CONFIG_FAIL
                    + "Impossible to load (ClassNotFoundException) Awake Configurator class: "
                    + classNameToLoad;
            exception = e;
        } catch (InstantiationException e) {
            initErrrorMesage = Tag.AWAKE_USER_CONFIG_FAIL
                    + "Impossible to load (InstantiationException) Awake Configurator class: "
                    + classNameToLoad;
            exception = e;
        } catch (IllegalAccessException e) {
            initErrrorMesage = Tag.AWAKE_USER_CONFIG_FAIL
                    + "Impossible to load (IllegalAccessException) Awake File Configurator class: "
                    + classNameToLoad;
            exception = e;
        } catch (Exception e) {
            initErrrorMesage = Tag.AWAKE_PRODUCT_FAIL + "Please contact support support@awakefamework.org";
            exception = e;
        }

        if (awakeCommonsConfigurator == null) {
            awakeCommonsConfiguratorClassName = AWAKE_COMMONS_CONFIGURATOR_CLASS_NAME;
        } else {
            awakeCommonsConfiguratorClassName = awakeCommonsConfigurator
                    .getClass().getName();
        }

        if (awakeFileConfigurator == null) {
            awakeFileConfiguratorClassName = AWAKE_FILE_CONFIGURATOR_CLASS_NAME;
        } else {
            awakeFileConfiguratorClassName = awakeFileConfigurator.getClass()
                    .getName();
        }

        if (awakeFileActionManager == null) {
            awakeFileActionManagerClassName = AWAKE_FILE_ACTION_MANAGER_CLASS_NAME;
        } else {
            awakeFileActionManagerClassName = awakeFileActionManager.getClass()
                    .getName();
        }

        System.out.println();
        System.out.println(Tag.AWAKE_START
                + org.awakefw.file.version.AwakeFileVersion.getVersion());
        System.out.println(Tag.AWAKE_START + this.getClass().getSimpleName()
                + " Servlet:");
        System.out.println(Tag.AWAKE_START
                + "- Init Parameter awakeCommonsConfiguratorClassName = "
                + awakeCommonsConfiguratorClassName);
        System.out.println(Tag.AWAKE_START
                + "- Init Parameter awakeFileConfiguratorClassName    = "
                + awakeFileConfiguratorClassName);
        System.out.println(Tag.AWAKE_START
                + "- Init Parameter awakeFileActionManagerClassName   = "
                + awakeFileActionManagerClassName);

        if (exception == null) {
            System.out.println(Tag.AWAKE_START + "Status: OK & Running.");
        } else {
            System.out.println(Tag.AWAKE_START + "Status: KO.");
            System.out.println(initErrrorMesage);
            System.out.println(ExceptionUtils.getStackTrace(exception));
        }

    }

    /**
     * Test the configurators main methods to see if they throw Exceptions
     */
    private void testConfiguratorMethods() {
        if (exception == null) {
            // Test that the login method does not throw an Exception
            @SuppressWarnings("unused")
            boolean isOk = false;

            try {
                isOk = awakeCommonsConfigurator.login("dummy",
                        "dummy".toCharArray());
            } catch (Exception e) {
                initErrrorMesage = ServerUserException.getErrorMessage(awakeCommonsConfigurator, "login");
                exception = e;
            }
        }

        if (exception == null) {
            try {
                @SuppressWarnings("unused")
                File serverRootFile = awakeFileConfigurator.getServerRoot();
            } catch (Exception e) {
                initErrrorMesage = ServerUserException.getErrorMessage(
                        awakeCommonsConfigurator, "getServerRoot");
                exception = e;
            }
        }

        if (exception == null) {
            try {
                @SuppressWarnings("unused")
                String tokenRecomputed = awakeCommonsConfigurator
                        .computeAuthToken("dummy");
            } catch (Exception e) {
                initErrrorMesage = ServerUserException.getErrorMessage(
                        awakeCommonsConfigurator, "getServerRoot");
                exception = e;
            }
        }

        if (exception == null) {
            try {
                @SuppressWarnings("unused")
                boolean forceHttps = awakeCommonsConfigurator.forceSecureHttp();
            } catch (Exception e) {
                initErrrorMesage = ServerUserException.getErrorMessage(
                        awakeCommonsConfigurator, "forceSecureHttp");
                exception = e;
            }
        }

        if (exception == null) {
            try {
                @SuppressWarnings("unused")
                Set<String> usernameSet = awakeCommonsConfigurator.getBannedUsernames();
            } catch (Exception e) {
                initErrrorMesage = ServerUserException.getErrorMessage(
                        awakeCommonsConfigurator, "getBannedUsernames");
                exception = e;
            }
        }

        if (exception == null) {
            try {
                @SuppressWarnings("unused")
                List<String> bannedIpList = awakeCommonsConfigurator.getBannedIPs();
            } catch (Exception e) {
                initErrrorMesage = ServerUserException.getErrorMessage(
                        awakeCommonsConfigurator, "getBannedIPs");
                exception = e;
            }
        }

        if (exception == null) {
            try {
                @SuppressWarnings("unused")
                boolean doUseOneRootPerUsername = awakeFileConfigurator
                        .useOneRootPerUsername();
            } catch (Exception e) {
                initErrrorMesage = ServerUserException.getErrorMessage(
                        awakeCommonsConfigurator, "useOneRootPerUsername");
                exception = e;
            }
        }
    }

    /**
     * Post request.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // If init fail, say it cleanly client, instead of bad 500 Servlet Error
        if (exception != null) {
            OutputStream out = response.getOutputStream();

            writeLine(out, HttpTransfer.SEND_FAILED);
            writeLine(out, exception.getClass().getName()); // Exception class name
            writeLine(out, initErrrorMesage + " Reason: " + exception.getMessage()); // Exception message
            writeLine(out, ExceptionUtils.getStackTrace(exception)); // stack trace
            return;

        }

        // Store the host info in RequestInfoStore
        RequestInfoStore.init(request);

	// Wrap the HttpServletRequest with HttpServletRequestEncrypted for
        // parameters decryption
        HttpServletRequestConvertor requestEncrypted = new HttpServletRequestConvertor(
                request, awakeCommonsConfigurator);

        // System.out.println(Tag.AWAKE_START + "AwakeFileManager started!");
        ServerAwakeFileDispatch dispatch = new ServerAwakeFileDispatch();
        dispatch.executeRequest(requestEncrypted, response,
                awakeCommonsConfigurator, awakeFileConfigurator,
                awakeFileActionManager);
    }

    /**
     * Write a line of string on the servlet output stream. Will add the
     * necessary CR_LF
     *
     * @param out the servlet output stream
     * @param s the string to write
     * @throws IOException
     */
    private void writeLine(OutputStream out, String s) throws IOException {
        out.write((s + CR_LF).getBytes());
    }

    /**
     * Get request.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String status = "</font><font face=\"Arial\" color=\"green\">"
                + "OK & Running.";

        // Test all methods
        testConfiguratorMethods();

        if (exception != null) {

            String stackTrace = ExceptionUtils.getStackTrace(exception);

            BufferedReader bufferedReader = new BufferedReader(
                    new StringReader(stackTrace));
            StringBuffer sb = new StringBuffer();

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                // All subsequent lines contain the result
                sb.append(line);
                sb.append("<br>");
            }

            status = "</font><font face=\"Arial\" color=\"red\">"
                    + initErrrorMesage + "<br>"
                    + sb.toString();
        }

        String awakeCommonsConfiguratorClassName = null;
        if (awakeCommonsConfigurator == null) {
            awakeCommonsConfiguratorClassName = AWAKE_COMMONS_CONFIGURATOR_CLASS_NAME;
        } else {
            awakeCommonsConfiguratorClassName = awakeCommonsConfigurator
                    .getClass().getName();
        }

        String awakeFileConfiguratorClassName = null;
        if (awakeFileConfigurator == null) {
            awakeFileConfiguratorClassName = AWAKE_FILE_CONFIGURATOR_CLASS_NAME;
        } else {
            awakeFileConfiguratorClassName = awakeFileConfigurator.getClass()
                    .getName();
        }

        String awakeFileActionManagerClassName = null;
        if (awakeFileActionManager == null) {
            awakeFileActionManagerClassName = AWAKE_FILE_ACTION_MANAGER_CLASS_NAME;
        } else {
            awakeFileActionManagerClassName = awakeFileActionManager.getClass()
                    .getName();
        }

        out.println("<font face=\"Arial\">");
        out.println("<b>");
        out.println("<font color=\"#F87D23\">" + AwakeFileVersion.getVersion() + "</font>");
        out.println("<br>");
        out.println("<br>");
        out.println(this.getClass().getSimpleName() + " Servlet Configuration");
        out.println("</b>");

        out.println("<br><br>");
        out.println("<table cellpadding=\"3\" border=\"1\">");

        out.println("<tr>");
        out.println("<td align=\"center\"> <b>Configurator Parameter</b> </td>");
        out.println("<td align=\"center\"> <b>Configurator Value</b> </td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td> " + AWAKE_COMMONS_CONFIGURATOR_CLASS_NAME + "</td>");
        out.println("<td> " + awakeCommonsConfiguratorClassName + "</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td> " + AWAKE_FILE_CONFIGURATOR_CLASS_NAME + "</td>");
        out.println("<td> " + awakeFileConfiguratorClassName + "</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td> " + AWAKE_FILE_ACTION_MANAGER_CLASS_NAME + "</td>");
        out.println("<td> " + awakeFileActionManagerClassName + "</td>");
        out.println("</tr>");

        out.println("</table>");

        out.println("<br><br>");
        out.println("<table cellpadding=\"3\" border=\"1\">");
        out.println("<tr>");
        out.println("<td align=\"center\"> <b>Awake File Configuration Status</b> </td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td> " + status + "</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("</font>");

    }

}
