import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

import org.json.simple.JSONObject;

/**
 * The class responsible for exporting
 * a) the sock pattern as a text file (with knitting instructions) and
 * b) the sock parameters and eventual notes as a JSON file (for storing past sock projects)
 */
public class PatternHandler {
    private Sock sock;
    private final Path pathMySocks;
    private final Path pathMyPatterns;

    private static final Logger LOGGER = Logger.getLogger("MyLogger");

    private static final String JSON = ".json";

    public PatternHandler(Sock s){
        sock = s;
        Path currentPath = Path.of(System.getProperty("user.dir"));

        // check if the two necessary directories MySocks and MyPatterns are there
        pathMyPatterns = Path.of(currentPath + "/MyPatterns");
        if(!Files.exists(pathMyPatterns)){
            try {
                Files.createDirectory(pathMyPatterns);
            }
            catch(IOException e){
                LOGGER.warning("Unable to create MyPatterns directory.");
            }
        }

        pathMySocks = Path.of(currentPath + "/MySocks");
        if(!Files.exists(pathMySocks)){
            try {
                Files.createDirectory(pathMySocks);
            }
            catch(IOException e){
                LOGGER.warning("Unable to create MySocks directory.");
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
                    Files.delete(Path.of(pathMyPatterns + "/"+ filename + ".txt"));
                    return filename;
                } catch (IOException e) {
                    i++;
                }
            }
        } else {
            while(i < 20){
                try {
                    filename = "sock" + i;
                    Files.createFile(Path.of(pathMySocks + "/"+ filename + JSON));
                    Files.delete(Path.of(pathMySocks + "/"+ filename + JSON));
                    return filename;
                } catch (IOException e) {
                    i++;
                }
            }
        }
        return "default";
    }

    public void generatePattern(String filename){
        int side = sock.getHeelStitchPair().getValue0();
        int middle = sock.getHeelStitchPair().getValue1();

        String pattern = filename.replace("_", " ") + "\n" +
                "- Cast on " + sock.getStitchNr() + " stitches (using 2 2.5 mm needles)\n" +
                "- Divide stitches onto 4 2 mm needles\n" +
                "- " + sock.getCuffLength() + " rounds of 1x1 ribbing\n" +  // TODO: getCuffRib
                "- Switch to 2.5 mm needles\n" +
                "- " + sock.getLegLength() + " rounds of stockinette\n" + "\n" +
                "The heel (German Short Row) is worked in stockinette\n" +
                "Stitch distribution: " + sock.getHeelSectioning() + "\n" +
                "- Knit across half of the stitches, combining needles 1 and 4 onto one needle, and turn\n" +
                "- Slip the first stitch knitwise together with the yarn, " +
                "pull outwards to create a double stitch, and purl across\n" +
                "- Repeat until there are " + sock.getHeelSectioning().substring(0,2) +
                " double stitches on either end (alternating knit and purl rows)\n" +
                "- Knit over all needles for 2 rounds, working double stitches as k2tog through the back loop\n" +
                "- Knit across " + (side + middle + 1) + " stitches of the combined heel needle and turn\n" +
                "- Create a double stitch, purl " + (middle + 1) + " and turn\n" +
                "- Create a double stitch, work until and over the double stitch (treating it like one stitch), " +
                "and work one extra stitch\n" +
                "- Repeat until all the heel needle stitches are \"used up\"\n" + "\n" +
                "- Divide the heel needles stitches onto 2 needles again\n" +
                "- " + sock.getLegLength() + " rounds of stockinette\n" + "\n" +
                "The toebox is worked in stockinette\n" +
                "- On needles 1 and 3: Knit until the last 3 stitches, k2tog, knit 1\n" +
                "- On needles 2 and 4: Knit 1, SSK and knit the remaining stitches\n" +
                "- Alternate these decrease rounds with plain knit rounds for a total of " + sock.getDecreaseRounds() +
                " rounds (until the stitch number is halved)\n" +
                "- Decrease every round until 4 stitches per needle remain\n" + "\n" +
                "Kitchener stitch preparation: purl keep, knit keep\n" +
                "Kitchener stitch repeat: knit slip, purl keep / purl slip, knit keep";

        try (var output = new FileWriter(pathMyPatterns + "/" + filename + ".txt")) {
            output.write(pattern);
        } catch (IOException e) {
            LOGGER.warning("Unable to create textfile.");
        }
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

        try (var output = new FileWriter(pathMySocks + "/" + filename + JSON)){
            output.write(jsonObject.toJSONString());
        } catch (IOException e) {
            LOGGER.warning("Unable to create JSON file.");
        }

    }
}
