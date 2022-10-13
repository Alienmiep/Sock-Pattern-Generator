package sockpatterngenerator;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class PatternHandlerTest {

    Sock sock = new Sock();
    PatternHandler patternHandler = new PatternHandler(sock);
    String currentPath = System.getProperty("user.dir");

    @Test
    public void testSaveSock() {
        String filename = "test";
        File file = new File(currentPath + "/MySocks/" + filename + ".json");
        if(file.exists()) {
            file.delete();
        }
        patternHandler.saveSock(filename);

        assertTrue("Directory MySocks is missing", new File(currentPath + "/MySocks").exists());

        assertTrue("File was not created", file.exists());
        assertTrue("File is empty", file.length() > 0);

        file.delete();
    }

}  