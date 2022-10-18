package sockpatterngenerator;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class PatternHandlerTest {

    static Sock sock = new Sock();
    static PatternHandler patternHandler = new PatternHandler(sock);
    static Path pathTestDirectory = Path.of(System.getProperty("user.dir") + "/TestDirectory");;

    private void clearDirectory(Path path){
        if(Files.isDirectory(path)){
            try(var stream = Files.newDirectoryStream(path)){
                for(var s: stream){
                    Files.delete(s);
                }
            } catch(IOException ignored){}
        }
    }

    @Test
    public void testSetPath(){
        Path newPath = Path.of(System.getProperty("user.dir") + "/Test");
        patternHandler.setPathMyPatterns(newPath);
        assertEquals(newPath, patternHandler.getPathMyPatterns());
        assertTrue(Files.exists(newPath));

        patternHandler.setPathMyPatterns(newPath);
        assertEquals(newPath, patternHandler.getPathMyPatterns());
        assertTrue(Files.exists(newPath));
    }

    @Test
    public void testSetPathMySocksNoDirectory(){
        Path newPath = Path.of(System.getProperty("user.dir") + "/Test");
        try {
            Files.deleteIfExists(newPath);
        } catch (IOException ignored){}

        patternHandler.setPathMySocks(newPath);
        assertEquals(newPath, patternHandler.getPathMySocks());
        assertTrue(Files.exists(newPath));
    }

    @Test
    public void testSetPathMyPatternsNoDirectory(){
        Path newPath = Path.of(System.getProperty("user.dir") + "/Test");
        try {
            Files.deleteIfExists(newPath);
        } catch (IOException ignored){}

        patternHandler.setPathMyPatterns(newPath);
        assertEquals(newPath, patternHandler.getPathMyPatterns());
        assertTrue(Files.exists(newPath));
    }

    @Test
    public void testSetPathDirectoryAlreadyExists(){
        Path newPath = Path.of(System.getProperty("user.dir") + "/Test");
        try {
            Files.createDirectory(newPath);
        } catch (IOException e){
            System.out.println("Unable to create Test directory");
        }

        patternHandler.setPathMySocks(newPath);
        assertEquals(newPath, patternHandler.getPathMySocks());
        assertTrue(Files.exists(newPath));

        patternHandler.setPathMyPatterns(newPath);
        assertEquals(newPath, patternHandler.getPathMyPatterns());
        assertTrue(Files.exists(newPath));
    }

    @Test
    public void testFindFilenameEmptyDirectory(){
        patternHandler.setPathMySocks(pathTestDirectory);
        patternHandler.setPathMyPatterns(pathTestDirectory);
        clearDirectory(pathTestDirectory);

        assertEquals("sock0", patternHandler.findFilename(false));
        assertEquals("pattern0", patternHandler.findFilename(true));
    }

    @Test
    public void testFindFilenameNameAvailable(){
        patternHandler.setPathMySocks(pathTestDirectory);
        patternHandler.setPathMyPatterns(pathTestDirectory);
        clearDirectory(pathTestDirectory);

        try{
            Files.createFile(Path.of(pathTestDirectory + "/sock0.json"));
            Files.createFile(Path.of(pathTestDirectory + "/pattern0.txt"));
        } catch(IOException e){
            System.out.println("Unable to create file.");
        }
        assertEquals("sock1", patternHandler.findFilename(false));
        assertEquals("pattern1", patternHandler.findFilename(true));

        try{
            Files.createFile(Path.of(pathTestDirectory + "/sock1.json"));
            Files.createFile(Path.of(pathTestDirectory + "/sock2.json"));
            Files.createFile(Path.of(pathTestDirectory + "/pattern1.txt"));
            Files.createFile(Path.of(pathTestDirectory + "/pattern2.txt"));
        } catch(IOException e){
            System.out.println("Unable to create file.");
        }
        assertEquals("sock3", patternHandler.findFilename(false));
        assertEquals("pattern3", patternHandler.findFilename(true));
    }

    @Test
    public void testFindFilenameNoNameAvailable(){
        patternHandler.setPathMySocks(pathTestDirectory);
        patternHandler.setPathMyPatterns(pathTestDirectory);
        clearDirectory(pathTestDirectory);

        for(int i = 0; i < 20; i++){
            try{
                Files.createFile(Path.of(pathTestDirectory + "/sock" + i + ".json"));
                Files.createFile(Path.of(pathTestDirectory + "/pattern" + i + ".txt"));
            } catch(IOException e){
                System.out.println("Unable to create file.");
                break;
            }
        }

        assertEquals("default", patternHandler.findFilename(false));
        assertEquals("default", patternHandler.findFilename(true));
    }

    @Test
    public void testSaveSock() {
        patternHandler.setPathMySocks(pathTestDirectory);
        String filename = "test";
        Path path = Path.of(pathTestDirectory + "/" + filename + ".json");
        try {
            Files.deleteIfExists(path);
        } catch(IOException ignored) {}

        patternHandler.saveSock(filename);

        assertTrue("File was not created", Files.exists(path));

        //file.delete();
    }

}  