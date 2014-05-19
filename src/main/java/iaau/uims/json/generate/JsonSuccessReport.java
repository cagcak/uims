/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */

package iaau.uims.json.generate;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import iaau.uims.jdbc.factory.ConnectionFactory;
import iaau.uims.jdbc.factory.ConnectionUtility;
import iaau.uims.servlet.UIMSServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Çağrı Çakır
 */
public class JsonSuccessReport  {

    public JsonSuccessReport() {
    }
    
    private Connection connection;
    private Statement statement;
    private JsonObject jsonResponse;
    
//    private static String PATH;
//    private static String LOCAL_PATH = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\UIMS\\src\\main\\webapp\\json\\";
//
//    public void GenerateSuccessReportAsJsonONsubjectNAME(String idNumber) throws SQLException
//    {
//        PATH = UIMSServletContext.getContextPath();
//
//        File folder = new File(LOCAL_PATH , idNumber);
//        folder.mkdir();
//
//        if (!folder.exists()) {
//            try {
//                folder.createNewFile();
//            } catch (IOException ex) {
//                Logger.getLogger(JsonSuccessReport.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        
//        String query = "SELECT SUCCESS_REPORT.subject_name, "
//                + "SUCCESS_REPORT.hours, "
//                + "SUCCESS_REPORT.midterm, "
//                + "SUCCESS_REPORT.`final`, "
//                + "SUCCESS_REPORT.average, "
//                + "SUCCESS_REPORT.attandance, "
//                + "SUCCESS_REPORT.semester, "
//                + "SUCCESS_REPORT.academic_year "
//                + "FROM SUCCESS_REPORT "
//                + "WHERE USERS_idnumber = " + idNumber;
//
//        ResultSet rs = null;
//        
//        try {
//            connection = ConnectionFactory.getConnection();
//            statement = connection.createStatement();
//
//            rs = statement.executeQuery(query);
//
//            JsonObject jsonResponse = new JsonObject();
//            JsonPrimitive subjectNode;
//            JsonObject data = new JsonObject();
//            
//            int unduplicator = 1;
//            while(rs.next())
//            {
//                JsonObject report = new JsonObject();
//                subjectNode = new JsonPrimitive((rs.getString("subject_name")+ "-" + unduplicator));
//                        
//                report.addProperty("subject_name", (rs.getString("subject_name")));
//                report.addProperty("hours", (rs.getString("hours")));
//                report.addProperty("midterm", (rs.getString("midterm")));
//                report.addProperty("final", (rs.getString("final")));
//                report.addProperty("average", (rs.getString("average")));
//                report.addProperty("attandace", (rs.getString("attandance")));
//                report.addProperty("semester", (rs.getString("semester")));
//                report.addProperty("academic_year", (rs.getString("academic_year")));
//                
//                unduplicator++;
//                data.add(subjectNode.getAsString(), report);
//                
//            }
//            
//            jsonResponse.add("SuccessReport_by_subject", data);
//            
//            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
//            gson.toJson(jsonResponse);
//
//            if(jsonResponse.isJsonArray())
//            {
//                System.out.println(gson.toJson(jsonResponse));
//            } else if (jsonResponse.isJsonObject()) {
//                System.out.println(gson.toJson(jsonResponse));
//            }
//            
//            GsonBuilder gsonBuilder = new GsonBuilder();
//            gsonBuilder.registerTypeAdapter(Object.class, new NaturalDeserializer());
//            Gson deserializer = gsonBuilder.create();
//            
//            Object natural = deserializer.fromJson(jsonResponse, Object.class);
//            System.out.println("as object:"+natural.toString());
//            
//            FileOutputStream output = null;
//            File file;
//            String content = gson.toJson(jsonResponse).toString();
//
//            try {
//
//                String folder_location = folder.toString() + "\\";
//                String filename = "SuccessReport_by_subject";
//                file = new File(folder_location + filename.toString() + ".json");
//                output = new FileOutputStream(file);
//
//                if (!file.exists()) {
//                    file.createNewFile();
//                }
//
//                byte[] content_in_bytes = content.getBytes();
//
//                output.write(content_in_bytes);
//                output.flush();
//                output.close();
//
//            } catch (IOException ex) {
//                Logger.getLogger(JsonSuccessReport.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                try {
//                    if (output != null) {
//                        output.close();
//                    }
//                } catch (IOException e) {
//                    Logger.getLogger(JsonSuccessReport.class.getName()).log(Level.SEVERE, null, e);
//                }
//            }
//
//        } finally {
//            ConnectionUtility.close(rs);
//            ConnectionUtility.close(statement);
//            ConnectionUtility.close(connection);
//        }
//    }
//    
//    
//    public void GenerateSuccessReportAsJsonONacademicyear(String idNumber, String year) throws SQLException {
//        PATH = UIMSServletContext.getContextPath();
//
//        File folder = new File(LOCAL_PATH, idNumber);
//        folder.mkdir();
//
//        if (!folder.exists()) {
//            try {
//                folder.createNewFile();
//            } catch (IOException ex) {
//                Logger.getLogger(JsonSuccessReport.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
////        System.out.println(year);
////        System.out.println(year.replace("'", ""));
//        
//        String query = "SELECT "
//                + "SUCCESS_REPORT.subject_name, " 
//                + "SUCCESS_REPORT.hours, " 
//                + "SUCCESS_REPORT.midterm, " 
//                + "SUCCESS_REPORT.`final`, "
//                + "SUCCESS_REPORT.average, "
//                + "SUCCESS_REPORT.attandance, "
//                + "SUCCESS_REPORT.semester, "
//                + "SUCCESS_REPORT.academic_year "
//                + "FROM SUCCESS_REPORT "
//                + "WHERE USERS_idnumber = " + idNumber + 
//                    " AND SUCCESS_REPORT.academic_year = " + "'" + year + "'";
//
//        ResultSet rs = null;
//
//        try {
//            connection = ConnectionFactory.getConnection();
//            statement = connection.createStatement();
//
//            rs = statement.executeQuery(query);
//
//            JsonObject jsonResponse = new JsonObject();
//            JsonPrimitive subjectNode;
//            JsonObject data = new JsonObject();
//
////            String temp = year;
////            String without_quotes;
//////            without_quotes = rs.getString("academic_year".replace("'", ""));
////          
//            int unduplicator = 1;
//            while (rs.next()) {
//                
//                JsonObject report = new JsonObject();
//                subjectNode = new JsonPrimitive((rs.getString("academic_year")+"-"+ unduplicator));
//                
//                report.addProperty("subject_name", (rs.getString("subject_name")));
//                report.addProperty("hours", (rs.getString("hours")));
//                report.addProperty("midterm", (rs.getString("midterm")));
//                report.addProperty("final", (rs.getString("final")));
//                report.addProperty("average", (rs.getString("average")));
//                report.addProperty("attandace", (rs.getString("attandance")));
//                report.addProperty("semester", (rs.getString("semester")));
//                report.addProperty("academic_year", (rs.getString("academic_year")));
//                unduplicator++;
//                data.add(subjectNode.getAsString(), report);
//            } 
//
//            jsonResponse.add("SuccessReport_by_year", data);
//
//            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
//            gson.toJson(jsonResponse);
//
//            if (jsonResponse.isJsonArray()) {
//                System.out.println(gson.toJson(jsonResponse));
//            } else if (jsonResponse.isJsonObject()) {
//                System.out.println(gson.toJson(jsonResponse));
//            }
//
//            GsonBuilder gsonBuilder = new GsonBuilder();
//            gsonBuilder.registerTypeAdapter(Object.class, new NaturalDeserializer());
//            Gson deserializer = gsonBuilder.create();
//
//            Object natural = deserializer.fromJson(jsonResponse, Object.class);
//            System.out.println("as object:" + natural.toString());
//
//            FileOutputStream output = null;
//            File file;
//            String content = gson.toJson(jsonResponse).toString();
//
//            try {
//
//                String folder_location = folder.toString() + "\\";
//                String filename = "SuccessReport_by_year";
//                file = new File(folder_location + filename.toString() + ".json");
//                output = new FileOutputStream(file);
//
//                if (!file.exists()) {
//                    file.createNewFile();
//                }
//
//                byte[] content_in_bytes = content.getBytes();
//
//                output.write(content_in_bytes);
//                output.flush();
//                output.close();
//
//            } catch (IOException ex) {
//                Logger.getLogger(JsonSuccessReport.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                try {
//                    if (output != null) {
//                        output.close();
//                    }
//                } catch (IOException e) {
//                    Logger.getLogger(JsonSuccessReport.class.getName()).log(Level.SEVERE, null, e);
//                }
//            }
//
//        } finally {
//            ConnectionUtility.close(rs);
//            ConnectionUtility.close(statement);
//            ConnectionUtility.close(connection);
//        }
//    }
//    
//    public void GenerateSuccessReportAsJsonONsemester(String idNumber, String semester) throws SQLException {
//        PATH = UIMSServletContext.getContextPath();
//
//        File folder = new File(LOCAL_PATH, idNumber);
//        folder.mkdir();
//
//        if (!folder.exists()) {
//            try {
//                folder.createNewFile();
//            } catch (IOException ex) {
//                Logger.getLogger(JsonSuccessReport.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        String query = "SELECT "
//                + "SUCCESS_REPORT.subject_name, "
//                + "SUCCESS_REPORT.hours, "
//                + "SUCCESS_REPORT.midterm, "
//                + "SUCCESS_REPORT.`final`, "
//                + "SUCCESS_REPORT.average, "
//                + "SUCCESS_REPORT.attandance, "
//                + "SUCCESS_REPORT.semester, "
//                + "SUCCESS_REPORT.academic_year "
//                + "FROM SUCCESS_REPORT "
//                + "WHERE USERS_idnumber = " + idNumber
//                + " AND SUCCESS_REPORT.semester = " + "'" +semester+ "'";
//
//        ResultSet rs = null;
//
//        try {
//            connection = ConnectionFactory.getConnection();
//            statement = connection.createStatement();
//
//            rs = statement.executeQuery(query);
//
//            JsonObject jsonResponse = new JsonObject();
//            JsonPrimitive subjectNode;
//            JsonObject data = new JsonObject();
//
//            int unduplicator = 1;
//            while (rs.next()) {
//                JsonObject report = new JsonObject();
//                subjectNode = new JsonPrimitive((rs.getString("semester")+ "-" + unduplicator));
//
//                report.addProperty("subject_name", (rs.getString("subject_name")));
//                report.addProperty("hours", (rs.getString("hours")));
//                report.addProperty("midterm", (rs.getString("midterm")));
//                report.addProperty("final", (rs.getString("final")));
//                report.addProperty("average", (rs.getString("average")));
//                report.addProperty("attandace", (rs.getString("attandance")));
//                report.addProperty("semester", (rs.getString("semester")));
//                report.addProperty("academic_year", (rs.getString("academic_year")));
//
//                unduplicator++;
//                data.add(subjectNode.getAsString(), report);
//
//            }
//
//            jsonResponse.add("SuccessReport_by_semester", data);
//
//            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
//            gson.toJson(jsonResponse);
//
//            if (jsonResponse.isJsonArray()) {
//                System.out.println(gson.toJson(jsonResponse));
//            } else if (jsonResponse.isJsonObject()) {
//                System.out.println(gson.toJson(jsonResponse));
//            }
//
//            GsonBuilder gsonBuilder = new GsonBuilder();
//            gsonBuilder.registerTypeAdapter(Object.class, new NaturalDeserializer());
//            Gson deserializer = gsonBuilder.create();
//
//            Object natural = deserializer.fromJson(jsonResponse, Object.class);
//            System.out.println("as object:" + natural.toString());
//
//            FileOutputStream output = null;
//            File file;
//            String content = gson.toJson(jsonResponse).toString();
//
//            try {
//
//                String folder_location = folder.toString() + "\\";
//                String filename = "SuccessReport_by_semester";
//                file = new File(folder_location + filename.toString() + ".json");
//                output = new FileOutputStream(file);
//
//                if (!file.exists()) {
//                    file.createNewFile();
//                }
//
//                byte[] content_in_bytes = content.getBytes();
//
//                output.write(content_in_bytes);
//                output.flush();
//                output.close();
//
//            } catch (IOException ex) {
//                Logger.getLogger(JsonSuccessReport.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                try {
//                    if (output != null) {
//                        output.close();
//                    }
//                } catch (IOException e) {
//                    Logger.getLogger(JsonSuccessReport.class.getName()).log(Level.SEVERE, null, e);
//                }
//            }
//
//        } finally {
//            ConnectionUtility.close(rs);
//            ConnectionUtility.close(statement);
//            ConnectionUtility.close(connection);
//        }
//    }
//    
//    public JsonObject GenerateSuccessReportRequested(String idNumber, String year, String semester) throws SQLException
//    {
//        String query = "SELECT "
//                + "SUCCESS_REPORT.subject_name,  "
//                + "SUCCESS_REPORT.hours,  "
//                + "SUCCESS_REPORT.midterm,  "
//                + "SUCCESS_REPORT.`final`, "
//                + "SUCCESS_REPORT.average, "
//                + "SUCCESS_REPORT.attandance, "
//                + "SUCCESS_REPORT.semester, "
//                + "SUCCESS_REPORT.academic_year "
//                + " FROM SUCCESS_REPORT "
//                + " WHERE USERS_idnumber = " + idNumber 
//                + " AND SUCCESS_REPORT.academic_year = " + "'" + year + "'"
//                + " AND SUCCESS_REPORT.semester = " + "'" + semester + "'";
//        
//        
//        ResultSet rs = null;
//
//        try {
//            connection = ConnectionFactory.getConnection();
//            statement = connection.createStatement();
//
//            rs = statement.executeQuery(query);
//
//            jsonResponse = new JsonObject();
//            
//            JsonArray data = new JsonArray();
//                        
//            while (rs.next()) {
//
//                JsonObject report = new JsonObject();
//
//                report.addProperty("subject_name", (rs.getString("subject_name")));
//                report.addProperty("hours", (rs.getString("hours")));
//                report.addProperty("midterm", (rs.getString("midterm")));
//                report.addProperty("final", (rs.getString("final")));
//                report.addProperty("average", (rs.getString("average")));
//                report.addProperty("attandace", (rs.getString("attandance")));
//                report.addProperty("semester", (rs.getString("semester")));
//                report.addProperty("academic_year", (rs.getString("academic_year")));
//                
//                data.add(report);
//            }
//            
//            jsonResponse.add("SuccessReport_Response", data);
//
//            // Pretty formatting json data
//            Gson gson = new GsonBuilder().
//                    setPrettyPrinting().
//                    serializeNulls().
//                    setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).
//                    create();
//            gson.toJson(jsonResponse);
//
//            // Write to output the generated json data
//            if (jsonResponse.isJsonArray()) {
//                System.out.println(gson.toJson(jsonResponse));
//            } else if (jsonResponse.isJsonObject()) {
//                System.out.println(gson.toJson(jsonResponse));
//            }
//
//        } finally {
//            ConnectionUtility.close(rs);
//            ConnectionUtility.close(statement);
//            ConnectionUtility.close(connection);
//        }
//        
//        return jsonResponse;
//    }
    
    
    public JsonObject GenerateSuccessReport(String idNumber, String year, String semester) throws SQLException {
        String query = "SELECT "
                + "SUCCESS_REPORT.subject_name,  "
                + "SUCCESS_REPORT.hours,  "
                + "SUCCESS_REPORT.midterm,  "
                + "SUCCESS_REPORT.`final`, "
                + "SUCCESS_REPORT.average, "
                + "SUCCESS_REPORT.attandance, "
                + "SUCCESS_REPORT.semester, "
                + "SUCCESS_REPORT.academic_year "
                + " FROM SUCCESS_REPORT "
                + " WHERE USERS_idnumber = " + idNumber
                + " AND SUCCESS_REPORT.academic_year = " + "'" + year + "'"
                + " AND SUCCESS_REPORT.semester = " + "'" + semester + "'";

        ResultSet rs = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            rs = statement.executeQuery(query);

            jsonResponse = new JsonObject();

//            JsonPrimitive node;
            JsonObject data = new JsonObject();

            int node = 1;
            while (rs.next()) {
                JsonObject report = new JsonObject();
                
                report.addProperty("subject_name", (rs.getString("subject_name")));
                report.addProperty("hours", (rs.getString("hours")));
                report.addProperty("midterm", (rs.getString("midterm")));
                report.addProperty("final", (rs.getString("final")));
                report.addProperty("average", (rs.getString("average")));
                report.addProperty("attandace", (rs.getString("attandance")));
                report.addProperty("semester", (rs.getString("semester")));
                report.addProperty("academic_year", (rs.getString("academic_year")));

//                data.addProperty(node.toString(), report.toString());
                data.add(String.valueOf(node), report);
                
                node++;
            }

            jsonResponse.add("SuccessReport", data);

            // Pretty formatting json data
            Gson gson = new GsonBuilder().
                    setPrettyPrinting().
                    serializeNulls().
                    setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).
                    create();
            gson.toJson(jsonResponse);

            // Write to output the generated json data
            if (jsonResponse.isJsonArray()) {
                System.out.println(gson.toJson(jsonResponse));
            } else if (jsonResponse.isJsonObject()) {
                System.out.println(gson.toJson(jsonResponse));
            }

        } finally {
            ConnectionUtility.close(rs);
            ConnectionUtility.close(statement);
            ConnectionUtility.close(connection);
        }

        return jsonResponse;
    }
    
    private static class NaturalDeserializer implements JsonDeserializer<Object> {

        public Object deserialize(JsonElement json, Type typeOfT,
                JsonDeserializationContext context) {
            if (json.isJsonNull()) {
                return null;
            } else if (json.isJsonPrimitive()) {
                return handlePrimitive(json.getAsJsonPrimitive());
            } else if (json.isJsonArray()) {
                return handleArray(json.getAsJsonArray(), context);
            } else {
                return handleObject(json.getAsJsonObject(), context);
            }
        }

        private Object handlePrimitive(JsonPrimitive json) {
            if (json.isBoolean()) {
                return json.getAsBoolean();
            } else if (json.isString()) {
                return json.getAsString();
            } else {
                BigDecimal bigDec = json.getAsBigDecimal();
                // Find out if it is an int type
                try {
                    bigDec.toBigIntegerExact();
                    try {
                        return bigDec.intValueExact();
                    } catch (ArithmeticException e) {
                    }
                    return bigDec.longValue();
                } catch (ArithmeticException e) {
                }
                // Just return it as a double
                return bigDec.doubleValue();
            }
        }

        private Object handleArray(JsonArray json, JsonDeserializationContext context) {
            Object[] array = new Object[json.size()];
            for (int i = 0; i < array.length; i++) {
                array[i] = context.deserialize(json.get(i), Object.class);
            }
            return array;
        }

        private Object handleObject(JsonObject json, JsonDeserializationContext context) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                map.put(entry.getKey(), context.deserialize(entry.getValue(), Object.class));
            }
            
            return map;
        }

        
    }
}
