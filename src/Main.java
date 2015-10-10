/**
 * Created by Dave on 9/21/2015.
 */
public class Main {

    public static void main(String [] args)  {
        FindAndReplace findAndReplace = new FindAndReplace("input.txt", "output.txt", "findandreplace.txt");
        findAndReplace.run();
    }
}
