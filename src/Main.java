import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Dave on 9/21/2015.
 */

//@WebService()
public class Main {

//    @WebMethod
    public static void main(String [] args)  {
        FindAndReplace findAndReplace = new FindAndReplace("input.txt", "output.txt", "findandreplace.txt");
        findAndReplace.run();
    }
}
