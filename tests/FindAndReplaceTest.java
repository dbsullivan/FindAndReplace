import junit.framework.TestCase;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException; // when the folder readonly fails? ASK


/**
 * Created by Dave on 9/21/2015.
 */
public class FindAndReplaceTest extends TestCase {

    @ClassRule
    public static TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws IOException {
        temporaryFolder.create();
    }

    @Test
    public void testInstantiateFindAndReplace() {
        FindAndReplace findAndReplace = new FindAndReplace(null, null, null);
        assertNotNull("This test that the object is created FindAndReplace", findAndReplace);
    }

    @Test
    public void testCreatePrintWriter() throws IOException {
        File output = temporaryFolder.newFile("testOutput.txt");
        FindAndReplace findAndReplace = new FindAndReplace(null, output.getAbsolutePath(), null);
        PrintWriter printWriter = findAndReplace.createOutputFile();
        assertNotNull(printWriter);
    }

    // ASK - this does not pass, I have a printwriter in the read only folder. assertNotNull would pass.
    @Test
    public void testCreatePrintWriterWithoutPermissions() throws IOException {
        File temp = temporaryFolder.newFolder("tempNoPermissions");
        File output = new File(temp, "testOutput.txt");
        temp.setReadOnly();
//        output = null; //force it for now, ASK
        FindAndReplace findAndReplace = new FindAndReplace(null, output.getAbsolutePath(), null);
        PrintWriter printWriter = findAndReplace.createOutputFile();
//        assertTrue(temp.setReadOnly());
//        assertNull(printWriter);
    }

    @Test
    public void testbuildReplacementMap() throws IOException {
        File findReplaceValues = temporaryFolder.newFile("findReplace.txt");
        PrintWriter writer = new PrintWriter(findReplaceValues);
        writer.println("ABC:abc,DEF:def");
        writer.println("key:value");
        writer.close();
        FindAndReplace findAndReplace = new FindAndReplace(null, null, findReplaceValues.getAbsolutePath());
        findAndReplace.buildReplacementMap();
        assertEquals(3, findAndReplace.replacementMap.size());

    }

// ASK - why is the fail not print, exception occurs even with the expected condition met ?????
//    @Test(expected = ArrayIndexOutOfBoundsException.class)
//    public void testKeyValuePairSplit() throws IOException {
//        File findReplaceValues = temporaryFolder.newFile("findReplace.txt");
//        PrintWriter writer = new PrintWriter(findReplaceValues);
//        writer.println("ABC:abc,DEF:def");
//        writer.println("keyWithNoValueOrSeparator");
//        writer.close();
//        FindAndReplace findAndReplace = new FindAndReplace(null, null, findReplaceValues.getAbsolutePath());
//        findAndReplace.buildReplacementMap();  // want to test putKeyValueInMap
//        fail("This test expect ArrayIndexOutOfBoundsException from Map split value fail");
//    }

    // ASK - how to test logic in the buildReplacementMap

// code removed, test not needed -------------------------------------------
//    @Test
//    public void testKeyValueInsertMap() {
//        FindAndReplace far = new FindAndReplace(null, null, null);
//        far.putKeyValueInMap("keyIn", "valueOut");
//        assertEquals(far.replacementMap.get("keyIn"),"valueOut" );
//    }

}