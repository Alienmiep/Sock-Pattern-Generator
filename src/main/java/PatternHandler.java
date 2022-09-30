import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.json.simple.JSONObject;

public class PatternHandler {
    private static GUI gui;
    private int stitchNr, cuffLength, legLength, shoeSize, footLength = 0;
    private Path currentPath;

    public PatternHandler(GUI gui){
        PatternHandler.gui = gui;
        currentPath = Path.of(System.getProperty("user.dir"));
    }

    // TODO: change into getSock and make a Sock class
    private void getSockData(){
        stitchNr = gui.getStitchNr();
        cuffLength = gui.getCuffLength();
        legLength = gui.getLegLength();
        shoeSize = gui.getShoeSize();
        footLength = gui.getFootLength();
        System.out.println("Stitch Nr: " + stitchNr);
        System.out.println("Cuff Length: " + cuffLength);
        System.out.println("Leg Length: " + legLength);
        System.out.println("Shoe Size: " + shoeSize);
        System.out.println("Foot Length: " + footLength);
    }

    public void generatePattern(){
        getSockData();
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
        getSockData();
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
        jsonObject.put("Stitch_Nr", stitchNr);
        jsonObject.put("Cuff_Length", cuffLength);
        jsonObject.put("Leg_Length", legLength);
        jsonObject.put("Shoe_Size", shoeSize);
        jsonObject.put("Foot_Length", footLength);

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
