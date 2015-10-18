package FindAndReplace;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * Created by Dave on 9/21/2015.
 */

@WebService()
public class Main {
    @WebMethod(operationName="runFindAndReplace")
    public void findAndReplace(String inputFile, String outputFile, String findReplaceFile) {
        FindAndReplace findAndReplace = new FindAndReplace(inputFile, outputFile, findReplaceFile);
        findAndReplace.run();
    }

    public static void main(String [] args)  {
        Object implementor = new Main();
        String address = "http://localhost:9000/Main";
        Endpoint.publish(address, implementor);

        FindAndReplace findAndReplace = new FindAndReplace("input.txt", "output.txt", "findandreplace.txt");
        findAndReplace.run();

    }
//    public static void main(String [] args)  {
//        FindAndReplace findAndReplace = new FindAndReplace("input.txt", "output.txt", "findandreplace.txt");
//        findAndReplace.run();
//    }
}
