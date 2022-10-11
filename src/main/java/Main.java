import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        var logger = Logger.getLogger("MyLogger");
        logger.info("Logger init test message");

        var sock = new Sock();
        var gui = new GUI(sock);
        var patternHandler = new PatternHandler(sock);
        gui.setPatternHandler(patternHandler);
        sock.setGUI(gui);

    }
}
