public class PatternHandler {
    private static GUI gui;
    private int stitchNr, cuffLength, legLength, shoeSize, footLength = 0;

    public PatternHandler(GUI gui){
        PatternHandler.gui = gui;
    }

    public void generatePattern(){
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
}
