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

/**
 *
 * @author Çağrı Çakır
 */
public class ParseUser {
    
    private String firstElement;
    private String secondElement;
    
    public void Parsing(String IDnumber) throws FileNotFoundException, IOException
    {
        String file_location = "src\\main\\json\\" + IDnumber + "\\User.json";
        JsonReader reader = new JsonReader(new FileReader(file_location));

        reader.beginArray(); // [
        reader.beginArray(); // [

        firstElement = reader.nextString().toString();
        secondElement = reader.nextString().toString();
        
        reader.endArray(); // ]
        reader.endArray(); // ]
    }
    
    public void Printing() {
        String parsedArray = "[\n\t[\n\t\t"
                + firstElement + ",\n\t\t"
                + secondElement + "\n\t]\n]\n";

        System.out.print(parsedArray);
    }
}
