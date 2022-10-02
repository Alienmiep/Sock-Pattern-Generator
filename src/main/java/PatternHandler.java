import java.io.FileWriter;
import java.io.IOException;
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

    public PatternHandler(Sock sock){
        PatternHandler.sock = sock;
        currentPath = Path.of(System.getProperty("user.dir"));
    }

    public void generatePattern(){
        Path pathMyPatterns = Path.of(currentPath + "/MyPatterns");

        if(!Files.exists(pathMyPatterns)){
            try {
                Files.createDirectory(pathMyPatterns);
            }
            catch(IOException e){
                System.out.println("Unable to create MyPatterns directory.");
                e.printStackTrace();
                return;
            }
        }
    }

    public void saveSock(){
        Path pathMySocks = Path.of(currentPath + "/MySocks");

        if(!Files.exists(pathMySocks)){
            try {
                Files.createDirectory(pathMySocks);
            }
            catch(IOException e){
                System.out.println("Unable to create MySocks directory.");
                e.printStackTrace();
                return;
            }
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Stitch_Nr", sock.getStitchNr());
        jsonObject.put("Cuff_Length", sock.getCuffLength());
        jsonObject.put("Leg_Length", sock.getLegLength());
        jsonObject.put("Shoe_Size", sock.getShoeSize());
        jsonObject.put("Foot_Length", sock.getFootLength());
        // TODO: ask user for any additional notes/yarn name

        try {
            FileWriter output = new FileWriter(pathMySocks + "/sock.json");
            output.write(jsonObject.toJSONString());
            output.close();
        } catch (IOException e) {
            System.out.println("Unable to create JSON file.");
            e.printStackTrace();
        }

    }
}
