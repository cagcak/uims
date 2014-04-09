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
public class ParseSuccessReport {

    
    private String subject_name;
    private String hours;
    private String midterm;
    private String final1;
    private String average;
    private String attandance;
    private String semester;
    private String academic_year;

    private static List<String> IteratedArray = new LinkedList();

    
    public void Parsing(String IDnumber) throws FileNotFoundException, IOException
    {
        String file_location = "src\\main\\json\\" + IDnumber + "\\SuccessReport.json";
        JsonReader reader = new JsonReader(new FileReader(file_location));

        reader.beginArray(); // [
        
            while(reader.hasNext())
            {
                reader.beginArray(); // [
                    while(reader.hasNext())
                    {
                        subject_name = reader.nextString().toString();
                        hours = reader.nextString().toString();
                        midterm = reader.nextString().toString();
                        final1 = reader.nextString().toString();
                        average = reader.nextString().toString();
                        attandance = reader.nextString().toString();
                        semester = reader.nextString().toString();
                        academic_year = reader.nextString().toString();
                    }
                reader.endArray(); // ]

                IteratedArray.add(subject_name);
                IteratedArray.add(hours);
                IteratedArray.add(midterm);
                IteratedArray.add(final1);
                IteratedArray.add(average);
                IteratedArray.add(attandance);
                IteratedArray.add(semester);
                IteratedArray.add(academic_year);
               
            }
            
        reader.endArray(); // ]
    }
    
    public void Printing()
    {

        System.out.println("Full list:" + IteratedArray+"\n");

        for (int i=0 ; i < IteratedArray.size(); i+=8)
        {
            System.out.println("subject: " + IteratedArray.get(i));
        }
        System.out.println();
        for (int i=1; i < IteratedArray.size(); i += 8) {
            System.out.println("hours: " + IteratedArray.get(i));
        }
        System.out.println();
        for (int i = 2; i < IteratedArray.size(); i += 8) {
            System.out.println("midterm: " + IteratedArray.get(i));
        }
        System.out.println();
        for (int i = 3; i < IteratedArray.size(); i += 8) {
            System.out.println("final: " + IteratedArray.get(i));
        }
        System.out.println();
        for (int i = 4; i < IteratedArray.size(); i += 8) {
            System.out.println("average: " + IteratedArray.get(i));
        }
        System.out.println();
        for (int i = 5; i < IteratedArray.size(); i += 8) {
            System.out.println("attandance: " + IteratedArray.get(i));
        }
        System.out.println();
        for (int i = 6; i < IteratedArray.size(); i += 8) {
            System.out.println("semester: " + IteratedArray.get(i));
        }
        System.out.println();
        for (int i = 7; i < IteratedArray.size(); i += 8) {
            System.out.println("academic year: " + IteratedArray.get(i));
        }
    }
    
}
