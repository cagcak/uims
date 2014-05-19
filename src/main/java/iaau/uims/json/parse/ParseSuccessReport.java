/*
 * This class is under a licence of IAAU
 * University Information Management System  * 
 * ----------------------------------------  * 
 *   https://github.com/cagricakir/uims.git  * 
 *  ------    ----------     -------------   * 
 *   add -----> commit -----> remote>push    * 
 */

package iaau.uims.json.parse;

import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Çağrı Çakır
 */
public class ParseSuccessReport {

    
    private static String subject_name;
    private static String hours;
    private static String midterm;
    private static String final1;
    private static String average;
    private static String attandance;
    private static String semester;
    private static String academic_year;

     
    private static Map<String, HashMap<String, String>> outer_map = new HashMap<String, HashMap<String, String>>();
    private static Map<String, String> inner_map = new HashMap<String, String>();
    
    public void Parsing(String IDnumber) throws FileNotFoundException, IOException
    {
        String file_location = "src\\main\\webapp\\json\\" + IDnumber + "\\SuccessReport.json";
        JsonReader reader = new JsonReader(new FileReader(file_location));

        reader.beginObject(); // {
        reader.nextName(); // NodeCounter starting 1
        reader.beginObject(); // {

        int nodeCounter = 1;
        while (reader.hasNext()) {
            reader.nextName();
            reader.beginObject(); // {

            reader.nextName(); // "subject_name":
            subject_name = reader.nextString().toString(); // value
            inner_map.put("subject_name", subject_name);

            reader.nextName(); // "hours"
            hours = reader.nextString().toString(); // value
            inner_map.put("hours", hours);

            reader.nextName(); // "midterm":
            midterm = reader.nextString().toString(); // value
            inner_map.put("midterm", midterm);

            reader.nextName(); // "final":
            final1 = reader.nextString().toString(); // value
            inner_map.put("final", final1);

            reader.nextName(); // "average":
            average = reader.nextString().toString(); // value
            inner_map.put("average", average);

            reader.nextName(); // "attandance":
            attandance = reader.nextString().toString(); // value
            inner_map.put("attandance", attandance);

            reader.nextName(); // "semester":
            semester = reader.nextString().toString(); // value
            inner_map.put("semester", semester);

            reader.nextName(); // "academic_year":
            academic_year = reader.nextString().toString(); // value
            inner_map.put("year", academic_year);

            reader.endObject(); // }

            outer_map.put(String.valueOf(nodeCounter), (HashMap<String, String>) inner_map);
            inner_map = new HashMap<String, String>();
            nodeCounter++;
        }

        reader.endObject();  // }
        reader.endObject(); // }
        
            
    }
        
    
    public static void Printing()
    {
        System.out.println("innermap:"+inner_map);
        System.out.println("innermap size:"+inner_map.size());
        System.out.println("outermap:"+outer_map);
        System.out.println("outermap size:"+outer_map.size());
//        System.out.println(outer_map.get("Diploma Project"));
//        System.out.println(outer_map.get("Diploma Project").get("subject_name"));
//        System.out.println(outer_map.get("Computer Networks and Telecommunications II"));
//        System.out.println(outer_map.get("Computer Networks and Telecommunications II").get("subject_name"));
//        System.out.println(outer_map.get("Control Theory"));
//        System.out.println(outer_map.get("Control Theory").get("final1"));

        }
    }
