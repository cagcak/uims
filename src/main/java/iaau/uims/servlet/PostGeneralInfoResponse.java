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
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import iaau.uims.json.generate.JsonGeneralInfo;
import iaau.uims.json.model.ModelGeneralInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Çağrı Çakır
 */
public class PostGeneralInfoResponse extends HttpServlet 
{
    private static JsonGeneralInfo jsonGenerationMethodinstance;
    private static final long serialVersionUID = 1L;

    List<ModelGeneralInfo> reqList = new ArrayList<ModelGeneralInfo>();

    public PostGeneralInfoResponse() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), reqList);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        // Taking request parameter
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String params = "";
        params = br.readLine();

        // Registering to naturally deserialize anything, 
        // defaulting JsonObjects to Map<String, Object> and JsonArrays to Object[]s, 
        // where all the children are similarly deserialized.
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Object.class, new NaturalDeserializer());
        Gson deserializer = gsonBuilder.create();

        // Calling deserializer to convert strem to java object and taking as string
        Object natural = deserializer.toJson(params, Object.class);
        params = natural.toString();
        
        // Signing taken parameter to equivalent objects
        ModelGeneralInfo members;
        members = mapper.readValue(params, ModelGeneralInfo.class);

        // Setting Response type
        response.setContentType("application/json");

        // Adding signed parameter objects into list
        reqList.add(members);
        int listSize = reqList.size() - 1;

        // Informing admin about added signed parameters from list
        String idnumber = reqList.get(listSize).getIdNumber();

        // Printing POST request coming from client
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Requested parameters in list: " + reqList);
        System.out.println("list size: " + reqList.size());
        System.out.println(idnumber);

        // Transmition data from generation
        JsonObject generatedJson;
        jsonGenerationMethodinstance = new JsonGeneralInfo();
        
        try {
            // Generating JSON object data with specific resultset
            generatedJson = jsonGenerationMethodinstance.GenerateGeneralInfoAsJson(idnumber).getAsJsonObject();
            
            // Pretty formatting json data
            Gson gson = new GsonBuilder().
                    setPrettyPrinting().
                    serializeNulls().
                    setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).
                    create();
            gson.toJson(generatedJson);
            
            // Writing output to server screen
            System.out.println("Generated GeneralInfo JSON representation");
            System.out.println("-------------------------------------------------------------------------------------------------");
            System.out.println(gson.toJson(generatedJson));
            System.out.println("-------------------------------------------------------------------------------------------------");

            // Putting data to output and serving it for client
            mapper.writeValueAsString("Requested JSON data is generated");
//            mapper.writeValue(response.getOutputStream(), generatedJson);
            response.getWriter().write(gson.toJson(generatedJson).toString());
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
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
