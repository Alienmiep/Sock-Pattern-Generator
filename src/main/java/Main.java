public class Main {

    private static Sock sock;
    private static GUI gui;
    private static PatternHandler patternHandler;

    public static void main(String[] args) {
        sock = new Sock();
        gui = new GUI(sock);
        patternHandler = new PatternHandler(sock);
        gui.setPatternHandler(patternHandler);
        sock.setGUI(gui);

    }
}
