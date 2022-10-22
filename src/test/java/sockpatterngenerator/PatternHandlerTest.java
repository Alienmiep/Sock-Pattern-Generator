package sockpatterngenerator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


class PatternHandlerTest {

    static Sock sock = new Sock();
    static PatternHandler patternHandler = new PatternHandler(sock);
    static Path pathTestDirectory = Path.of(System.getProperty("user.dir") + "/TestDirectory");

    static void clearDirectory(Path path){
        if(Files.isDirectory(path)){
            try(var stream = Files.newDirectoryStream(path)){
                for(var s: stream){
                    Files.delete(s);
                }
            } catch(IOException ignored){}
        }
    }

    @Test
    void testSetPath(){
        Path newPath = Path.of(System.getProperty("user.dir") + "/Test");
        patternHandler.setPathMyPatterns(newPath);
        Assertions.assertEquals(newPath, patternHandler.getPathMyPatterns());
        Assertions.assertTrue(Files.exists(newPath));

        patternHandler.setPathMyPatterns(newPath);
        Assertions.assertEquals(newPath, patternHandler.getPathMyPatterns());
        Assertions.assertTrue(Files.exists(newPath));
    }

    @Test
    void testSetPathMySocksNoDirectory(){
        Path newPath = Path.of(System.getProperty("user.dir") + "/Test");
        try {
            Files.deleteIfExists(newPath);
        } catch (IOException ignored){}

        patternHandler.setPathMySocks(newPath);
        Assertions.assertEquals(newPath, patternHandler.getPathMySocks());
        Assertions.assertTrue(Files.exists(newPath));
    }

    @Test
    void testSetPathMyPatternsNoDirectory(){
        Path newPath = Path.of(System.getProperty("user.dir") + "/Test");
        try {
            Files.deleteIfExists(newPath);
        } catch (IOException ignored){}

        patternHandler.setPathMyPatterns(newPath);
        Assertions.assertEquals(newPath, patternHandler.getPathMyPatterns());
        Assertions.assertTrue(Files.exists(newPath));
    }

    @Test
    void testSetPathDirectoryAlreadyExists(){
        Path newPath = Path.of(System.getProperty("user.dir") + "/Test");
        try {
            Files.createDirectory(newPath);
        } catch (IOException e){
            System.out.println("Unable to create Test directory");
        }

        patternHandler.setPathMySocks(newPath);
        Assertions.assertEquals(newPath, patternHandler.getPathMySocks());
        Assertions.assertTrue(Files.exists(newPath));

        patternHandler.setPathMyPatterns(newPath);
        Assertions.assertEquals(newPath, patternHandler.getPathMyPatterns());
        Assertions.assertTrue(Files.exists(newPath));
    }

    @Test
    void testFindFilenameEmptyDirectory(){
        patternHandler.setPathMySocks(pathTestDirectory);
        patternHandler.setPathMyPatterns(pathTestDirectory);
        clearDirectory(pathTestDirectory);

        Assertions.assertEquals("sock0", patternHandler.findFilename(false));
        Assertions.assertEquals("pattern0", patternHandler.findFilename(true));
    }

    @Test
    void testFindFilenameNameAvailable(){
        patternHandler.setPathMySocks(pathTestDirectory);
        patternHandler.setPathMyPatterns(pathTestDirectory);
        clearDirectory(pathTestDirectory);

        try{
            Files.createFile(Path.of(pathTestDirectory + "/sock0.json"));
            Files.createFile(Path.of(pathTestDirectory + "/pattern0.txt"));
        } catch(IOException e){
            System.out.println("Unable to create file.");
        }
        Assertions.assertEquals("sock1", patternHandler.findFilename(false));
        Assertions.assertEquals("pattern1", patternHandler.findFilename(true));

        try{
            Files.createFile(Path.of(pathTestDirectory + "/sock1.json"));
            Files.createFile(Path.of(pathTestDirectory + "/sock2.json"));
            Files.createFile(Path.of(pathTestDirectory + "/pattern1.txt"));
            Files.createFile(Path.of(pathTestDirectory + "/pattern2.txt"));
        } catch(IOException e){
            System.out.println("Unable to create file.");
        }
        Assertions.assertEquals("sock3", patternHandler.findFilename(false));
        Assertions.assertEquals("pattern3", patternHandler.findFilename(true));
    }

    @Test
    void testFindFilenameNoNameAvailable(){
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

        Assertions.assertEquals("default", patternHandler.findFilename(false));
        Assertions.assertEquals("default", patternHandler.findFilename(true));
    }

    @Test
    void testSaveSock() {
        patternHandler.setPathMySocks(pathTestDirectory);
        String filename = "test";
        Path path = Path.of(pathTestDirectory + "/" + filename + ".json");
        try {
            Files.deleteIfExists(path);
        } catch(IOException ignored) {}

        patternHandler.saveSock(filename);

        Assertions.assertTrue(Files.exists(path), "File was not created");

    }

    @Test
    void testGeneratePattern(){
        patternHandler.setPathMyPatterns(pathTestDirectory);
        String filename = "test";
        Path path = Path.of(pathTestDirectory + "/" + filename + ".txt");

        patternHandler.generatePattern("test");
        Assertions.assertTrue(Files.exists(path), "File was not created");
        //TODO: read in file and check the contents
    }


    @AfterAll
    static void cleanup(){
        try {
            clearDirectory(pathTestDirectory);
            Files.deleteIfExists(pathTestDirectory);
            Files.deleteIfExists(Path.of(System.getProperty("user.dir") + "/Test"));
        } catch (IOException e){
            System.out.println(pathTestDirectory);
        }
    }
}  