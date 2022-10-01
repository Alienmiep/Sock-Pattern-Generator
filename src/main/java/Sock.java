public class Sock {

    private static GUI gui;
    private int stitchNr, cuffLength, legLength, shoeSize, footLength;
    private String heelSectioning;
    private boolean hasFlatSole;

    public Sock(){
        // set default sock values
        stitchNr = 60;
        cuffLength = 15;
        legLength = 50;
        shoeSize = 43;
        footLength = 66;
        heelSectioning = "10/10/10";
        hasFlatSole = true;
    }

    public void setGUI(GUI gui){
        Sock.gui = gui;
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
        setFootLength(shoeSizeToFootLength());
    }

    public void setFootLength(int footLength) {
        this.footLength = footLength;
    }

    private int shoeSizeToFootLength(){
        // TODO: find a more accurate shoe size formula
        return 50 + (shoeSize-39) * 5;
    }

    private String generateHeelSectioning(){
        int side, middle = 0;

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
        return side + " / " + middle + " / " + side;
    }

    public void updateHeel(){
        heelSectioning = generateHeelSectioning();
        gui.updateHeel(heelSectioning);
    }

    public void updateToebox(){
        // calculate in two steps to ensure the number of rounds is even
        int decreaseRounds = stitchNr / 8;
        decreaseRounds *= 2;
        gui.updateToebox(decreaseRounds);
    }
}
