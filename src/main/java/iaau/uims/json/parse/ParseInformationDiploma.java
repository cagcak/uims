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
public class ParseInformationDiploma {
    
    private String firstElement;
    private String secondElement;
    private String thirdElement;
    private String fourthElement;
    private String fifthElement;
    private String sixthElement;
    private String seventhElement;
    private String eigthElement;
    private String ninthElement;
    private String tenthElement;
    private String elevenElement;
    private String twelveElement;
    
    public void Parsing(String IDnumber) throws FileNotFoundException, IOException
    {
        String file_location = "src\\main\\json\\" + IDnumber + "\\InformationDiploma.json";
        JsonReader reader = new JsonReader(new FileReader(file_location));

        reader.beginArray(); // [
          reader.beginArray(); // [

            firstElement = reader.nextString().toString();
            secondElement = reader.nextString().toString();
            thirdElement = reader.nextString().toString();
            fourthElement = reader.nextString().toString();
            fifthElement = reader.nextString().toString();
            sixthElement = reader.nextString().toString();
            seventhElement = reader.nextString().toString();
            eigthElement = reader.nextString().toString();
            ninthElement = reader.nextString().toString();
            tenthElement = reader.nextString().toString();
            elevenElement = reader.nextString().toString();
            twelveElement = reader.nextString().toString();

          reader.endArray(); // ]
        reader.endArray(); // ]
    }
    
    public void Printing() {
        String parsedArray = "[\n\t[\n\t\t"
                + firstElement + ",\n\t\t"
                + secondElement + ",\n\t\t"
                + thirdElement + ",\n\t\t"
                + fourthElement + ",\n\t\t"
                + fifthElement + ",\n\t\t"
                + sixthElement + ",\n\t\t"
                + seventhElement + ",\n\t\t"
                + eigthElement + ",\n\t\t"
                + ninthElement + ",\n\t\t"
                + tenthElement + ",\n\t\t"
                + elevenElement + ",\n\t\t"
                + twelveElement + "\n\t]\n]\n";

        System.out.print(parsedArray);
    }
}
