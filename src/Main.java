import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Dave on 9/21/2015.
 */

@WebService()
public class Main {
    @WebMethod(operationName="runFindAndReplace")
    public void findAndReplace(String inputFile, String outputFile, String findReplaceFile){
        FindAndReplace findAndReplace = new FindAndReplace(inputFile, outputFile, findReplaceFile);
        findAndReplace.run();
    }

    public static void main(String [] args)  {
        Object implementor = new Main();
        FindAndReplace findAndReplace = new FindAndReplace("input.txt", "output.txt", "findandreplace.txt");
        findAndReplace.run();
    }
//    public static void main(String [] args)  {
//        FindAndReplace findAndReplace = new FindAndReplace("input.txt", "output.txt", "findandreplace.txt");
//        findAndReplace.run();
//    }
}
