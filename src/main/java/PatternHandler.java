import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.json.simple.JSONObject;

/**
 * The class responsible for exporting
 * a) the sock pattern as a text file (with knitting instructions) and
 * b) the sock parameters and eventual notes as a JSON file (for storing past sock projects)
 */
public class PatternHandler {
    private static Sock sock;
    private final Path currentPath;
    private final Path pathMySocks;
    private final Path pathMyPatterns;

    public PatternHandler(Sock sock){
        PatternHandler.sock = sock;
        currentPath = Path.of(System.getProperty("user.dir"));

        // check if the two necessary directories MySocks and MyPatterns are there
        pathMyPatterns = Path.of(currentPath + "/MyPatterns");
        if(!Files.exists(pathMyPatterns)){
            try {
                Files.createDirectory(pathMyPatterns);
            }
            catch(IOException e){
                System.out.println("Unable to create MyPatterns directory.");
                e.printStackTrace();
            }
        }

        pathMySocks = Path.of(currentPath + "/MySocks");
        if(!Files.exists(pathMySocks)){
            try {
                Files.createDirectory(pathMySocks);
            }
            catch(IOException e){
                System.out.println("Unable to create MySocks directory.");
                e.printStackTrace();
            }
        }  // TODO: handle case that directories can't be created
    }

    /**
     * Utility function that finds an available filename in case the user doesn't specify the name
     *
     * @param isPattern information whether the filename is for a sock.json or a pattern.txt
     * @return a filename string without extension
     */
    public String findFilename(boolean isPattern){
        String filename;
        int i = 0;
        if(isPattern){
            while(i < 20){
                try {
                    filename = "pattern" + i;
                    Files.createFile(Path.of(pathMyPatterns + "/"+ filename + ".txt"));
                    return filename;
                } catch (IOException e) {
                    i++;
                }
            }
        } else {
            while(i < 20){
                try {
                    filename = "sock" + i;
                    Files.createFile(Path.of(pathMySocks + "/"+ filename + ".json"));
                    return filename;
                } catch (IOException e) {
                    i++;
                }
            }
        }
        return "default";
    }

    // TODO: either ask user for filename or find a free sock1/2/3/...
    public void generatePattern(String filename){

    }

    public void saveSock(String filename){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Stitch_Nr", sock.getStitchNr());
        jsonObject.put("Cuff_Length", sock.getCuffLength());
        jsonObject.put("Leg_Length", sock.getLegLength());
        jsonObject.put("Shoe_Size", sock.getShoeSize());
        jsonObject.put("Foot_Length", sock.getFootLength());
        jsonObject.put("Yarn Ply", sock.getPly());
        // TODO: ask user for any additional notes/yarn name

        try {
            FileWriter output = new FileWriter(pathMySocks + "/" + filename + ".json");
            output.write(jsonObject.toJSONString());
            output.close();
        } catch (IOException e) {
            System.out.println("Unable to create JSON file.");
            e.printStackTrace();
        }

    }
}
