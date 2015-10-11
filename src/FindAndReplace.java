import java.io.*;
import java.util.HashMap;
import java.util.Map;
//import java.util.logging.Logger;  // getting a java logger, not the apache logger (takes string and not implementing various outputs
import org.apache.log4j.Logger;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Dave on 9/12/2015.
 *
 * Read in a file that contains place holder text. input.txt
 * Read in a csv file that contains the placeholder words and the words that should be inserted wherever the placeholders are found. findandreplace.txt
 * Find and replace each placeholder word with the proper value.
 * Create a new file with the replaced words. bwOutput.txt
 *
 */

@WebService()
public class FindAndReplace {

    String inputFile;
    String outputFile;
    String findReplaceFile;
    String inputLine = null;
    String replaceLine = null;
    Map<String, String> replacementMap = new HashMap<>();
    private final Logger logger = Logger.getLogger(FindAndReplace.class);  // this won't compile, fails.
//    private static Logger logger = Logger.getLogger(FindAndReplace.class.getName()); // tutorial says this, works.
//    private final Logger logger = Logger.getLogger(this.getClass().toString()); // another way to get a string that works for java logging.

    @WebMethod
    public FindAndReplace(String inputFile, String outputFile, String findReplaceFile) {
        logger.info("In the constructor");
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.findReplaceFile = findReplaceFile;

    }

    public void run() {
        buildReplacementMap();
        processReplaceText();
    }

    public void buildReplacementMap() {
        try {
            FileReader frReplace = new FileReader(findReplaceFile);
            BufferedReader brReplace = new BufferedReader(frReplace);
            // first, parse out from the CSV the key value pairs separated by commas into newKeyValuePair.
            while ((replaceLine = brReplace.readLine()) != null) {
                String[] newKeyValuePair = replaceLine.split(",");
                for (String KeyValuePair : newKeyValuePair) {
                    String[] furtherKeyValuePair = KeyValuePair.split(":");
                    replacementMap.put(furtherKeyValuePair[0], furtherKeyValuePair[1]);
//                    String[] furtherKeyValuePair = splitKeyValuePair(KeyValuePair);
//                    putKeyValueInMap(furtherKeyValuePair[0], furtherKeyValuePair[1]);
//                    replacementMap.put(furtherKeyValuePair[0], furtherKeyValuePair[1]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not Found ");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // further split key from value for each newKeyValuePair, then put in the map to be checked when parsing and replacing.
//    public String[] splitKeyValuePair(String keyValuePair) {
//        String splitPair = keyValuePair;
//            String[] furtherKeyValuePair = splitPair.split(":");
//        return furtherKeyValuePair;
//    }
//
//    public void putKeyValueInMap (String key, String value) {
//        String k = key;
//        String v = value;
//        replacementMap.put(k, v);
//    }

    public void processReplaceText(){
        try {
            FileReader frInput = new FileReader(inputFile);
            BufferedReader brInput = new BufferedReader(frInput);
            PrintWriter out = createOutputFile();
            // read the input.txt file and compare the text in each line, to every possible key/value pair in the map replacementMap.
            // where the key is the value to match an existing replacement word, with a value that is the new replacement word.
            while ((inputLine = brInput.readLine()) != null) {
                for (Map.Entry<String, String> entry : replacementMap.entrySet()) {
                    if (inputLine.contains(entry.getKey()))
                        inputLine = inputLine.replace(entry.getKey(), entry.getValue()+" ");
                }
                out.println(inputLine);
            }
            brInput.close();
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not Found ");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PrintWriter createOutputFile() {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
            return out;
        } catch (IOException e) {
            logger.info(e.getMessage());  //ASK - Can't get other technique to work. logger is string. logger.error("error here", e);
//            e.printStackTrace();
        }
        return null;
    }

}