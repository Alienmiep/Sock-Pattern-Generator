import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;

public class TestPatternHandler {

    Sock sock = new Sock();
    PatternHandler patternHandler = new PatternHandler(sock);
    String currentPath = System.getProperty("user.dir");

    @Test
    public void testSaveSock() {
        File file = new File(currentPath + "/MySocks/sock.json");
        if(file.exists()) {
            file.delete();
        }
        patternHandler.saveSock();

        assertTrue("Directory MySocks is missing", new File(currentPath + "/MySocks").exists());

        assertTrue("File was not created", file.exists());
        assertTrue("File is empty", file.length() > 0);
    }

}  