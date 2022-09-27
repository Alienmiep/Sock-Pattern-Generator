public class Main {
private static GUI gui;
private static PatternHandler patternHandler;
    public static void main(String[] args) {
        gui = new GUI();
        patternHandler = new PatternHandler(gui);
        gui.setPatternHandler(patternHandler);

    }
}
