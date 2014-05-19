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
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Çağrı Çakır
 */
public class ParseTranscript {
    
    private String subject_code;
    private String subject_name;
    private String semester;
    private String year;
    private String credits;
    private String average;
    
    private static List<String> IteratedArray = new LinkedList();
    
    public void Parsing(String IDnumber) throws FileNotFoundException, IOException
    {
        String file_location = "src\\main\\webapp\\json\\" + IDnumber + "\\Transcript.json";
        JsonReader reader = new JsonReader(new FileReader(file_location));
        
        reader.beginArray(); // [

        while (reader.hasNext()) {
            reader.beginArray(); // [
            while (reader.hasNext()) {
                subject_code = reader.nextString().toString();
                subject_name = reader.nextString().toString();
                semester = reader.nextString().toString();
                year = reader.nextString().toString();
                credits = reader.nextString().toString();
                average = reader.nextString().toString();
            }
            reader.endArray(); // ]

            IteratedArray.add(subject_code);
            IteratedArray.add(subject_name);
            IteratedArray.add(semester);
            IteratedArray.add(year);
            IteratedArray.add(credits);
            IteratedArray.add(average);
        }

        reader.endArray(); // ]
    }

    
    public void Printing() {

        System.out.println("Full list:" + IteratedArray + "\n");

        for (int i = 0; i < IteratedArray.size(); i += 6) {
            System.out.println("subject code: " + IteratedArray.get(i));
        }
        System.out.println();
        for (int i = 1; i < IteratedArray.size(); i += 6) {
            System.out.println("subject name: " + IteratedArray.get(i));
        }
        System.out.println();
        for (int i = 2; i < IteratedArray.size(); i += 6) {
            System.out.println("semester: " + IteratedArray.get(i));
        }
        System.out.println();
        for (int i = 3; i < IteratedArray.size(); i += 6) {
            System.out.println("year: " + IteratedArray.get(i));
        }
        System.out.println();
        for (int i = 4; i < IteratedArray.size(); i += 6) {
            System.out.println("credits: " + IteratedArray.get(i));
        }
        System.out.println();
        for (int i = 5; i < IteratedArray.size(); i += 6) {
            System.out.println("average: " + IteratedArray.get(i));
        }
    }
    
}
