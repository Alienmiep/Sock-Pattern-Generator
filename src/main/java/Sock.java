import org.javatuples.Pair;

/**
 * Stores all sock parameters and methods to generate sock-related info (like the heel sectioning)
 */
public class Sock {

    private GUI gui;
    private int stitchNr, cuffLength, legLength, shoeSize, footLength;
    private String heelSectioning;
    // private boolean hasFlatSole;
    private int ply, decreaseRounds;
    private Pair<Integer,Integer> heelStitchPair;

    public Sock(){
        // set default sock values
        stitchNr = 60;
        cuffLength = 15;
        legLength = 50;
        shoeSize = 43;
        footLength = 66;
        heelSectioning = generateHeelSectioning();
        // hasFlatSole = true;
        ply = 4;
        decreaseRounds = 14;
    }

    public void setGUI(GUI g){
        gui = g;
    }

    public int getStitchNr() {
        return stitchNr;
    }

    public int getCuffLength() {
        return cuffLength;
    }

    public int getLegLength() {
        return legLength;
    }

    public int getShoeSize() {
        return shoeSize;
    }

    public int getFootLength() {
        return footLength;
    }

    public String getHeelSectioning(){
        return heelSectioning;
    }

    public int getPly() {
        return ply;
    }

    public int getDecreaseRounds() {
        return decreaseRounds;
    }

    public Pair<Integer,Integer> getHeelStitchPair(){
        return this.heelStitchPair;
    }

    public void setStitchNr(int stitchNr) {
        this.stitchNr = stitchNr;
        updateHeel();
        updateToebox();
    }

    public void setCuffLength(int cuffLength) {
        this.cuffLength = cuffLength;
    }

    public void setLegLength(int legLength) {
        this.legLength = legLength;
    }

    public void setShoeSize(int shoeSize) {
        this.shoeSize = shoeSize;
        setFootLength(shoeSizeToFootLength(shoeSize));
    }

    public void setFootLength(int footLength) {
        this.footLength = footLength;
    }

    public void setPly(int ply) {
        this.ply = ply;
    }

    /**
     * Calculates the number of rows needed for the foot, depending on shoe size and yarn thickness (ply)
     *
     * @return the number of rows for the foot
     */
    int shoeSizeToFootLength(int shoeSize){
        // TODO: find a more accurate shoe size formula
        int length = 50 + (shoeSize-39) * 5;
        if(ply == 6) length *= 0.75;
        return length;
    }

    /**
     * Splits the heel stitches into thirds for a German Short Row heel
     *
     * @return finished heel sectioning string
     */
     String generateHeelSectioning(){
        int side, middle;

        int heelStitchNr = stitchNr / 2;
        switch (heelStitchNr % 3) {
            case 0 -> {
                side = heelStitchNr / 3;
                middle = side;
            }
            case 1 -> {
                side = heelStitchNr / 3;
                middle = side + 1;
            }
            default -> {
                side = heelStitchNr / 3 + 1;
                middle = side - 1;
            }
        }
        heelStitchPair = Pair.with(side, middle);
        return side + " / " + middle + " / " + side;
    }

    public void updateHeel(){
        heelSectioning = generateHeelSectioning();
        gui.updateHeel(heelSectioning);
    }

    /**
     * Called when the stitch number is changed.
     * decreaseRounds is the number of rounds to knit until the number of stitches per needle has been halved.
     * Round down for odd numbers.
     */
    public void updateToebox(){
        // calculate in two steps to ensure the number of rounds is even
        decreaseRounds = stitchNr / 8;
        decreaseRounds *= 2;
        gui.updateToebox(decreaseRounds);
    }
}
