import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class GUIPanel extends CustomPanel{

    private static GUI gui;

    public GUIPanel(GUI gui){
        super();
        GUIPanel.gui = gui;
    }

    public static class CastOnPanel extends GUIPanel{

        private JTextField textFieldStitchNr;
        private int stitchNr;

        public CastOnPanel(GUI gui){
            super(gui);
            setHeight(100);

            String[] yarnOptions = {"4-ply Sock Yarn", "6-ply Sock Yarn"};
            JComboBox<String> comboBoxYarn = new JComboBox<>(yarnOptions);
            comboBoxYarn.setPreferredSize(new Dimension(130,30));
            add(comboBoxYarn);

            JPanel panelMargin = new JPanel();
            panelMargin.setPreferredSize(new Dimension(10, 30));
            add(panelMargin);

            JLabel labelStitchNr = new JLabel("Number of stitches: ");
            add(labelStitchNr);

            stitchNr = 60;
            textFieldStitchNr = new JTextField("60");
            textFieldStitchNr.setPreferredSize(new Dimension(30,24));
            add(textFieldStitchNr);

            textFieldStitchNr.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) { update(); }
                public void insertUpdate(DocumentEvent e) { update(); }
                public void removeUpdate(DocumentEvent e) { update(); }
                public void update() {
                    try {
                        stitchNr = Integer.parseInt(textFieldStitchNr.getText());
                        if(stitchNr > 0 && stitchNr <= 100){
                            System.out.println("Boop");
                            gui.updateStitchNr();
                        } else {
                            System.out.println("Out of range.");
                        }
                    }
                    catch(NumberFormatException e){
                        System.out.println("Not an Integer.");
                    }
                }
            });

            // TODO: Add distinction between 4-ply and 6-ply
            // 6-ply using the Schachenmayr table
        }

        public int getStitchNr(){
            return stitchNr;
        }

    }

    public static class CuffPanel extends GUIPanel{

        private JTextField textFieldCuffLength;
        private int cuffLength;

        public CuffPanel(GUI gui){
            super(gui);
            setHeight(130);

            JLabel labelCuffLength = new JLabel("Cuff length: ");
            add(labelCuffLength);

            cuffLength = 15;
            textFieldCuffLength = new JTextField("15");
            textFieldCuffLength.setPreferredSize(new Dimension(25,24));
            add(textFieldCuffLength);

            textFieldCuffLength.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) { update(); }
                public void insertUpdate(DocumentEvent e) { update(); }
                public void removeUpdate(DocumentEvent e) { update(); }
                public void update() {
                    try {
                        cuffLength = Integer.parseInt(textFieldCuffLength.getText());
                        if(cuffLength >= 0 && cuffLength <= 200){
                            System.out.println("Boop");
                        } else {
                            System.out.println("Out of range.");
                        }
                    }
                    catch(NumberFormatException e){
                        System.out.println("Not an Integer.");
                    }
                }
            });

            JLabel labelCuffLengthRows = new JLabel("rows");
            add(labelCuffLengthRows);

            JPanel panelMargin = new JPanel();
            panelMargin.setPreferredSize(new Dimension(150,24));
            add(panelMargin);

            JPanel panelMargin2 = new JPanel();
            panelMargin2.setPreferredSize(new Dimension(380, 5));
            add(panelMargin2);

            JLabel labelRibbing = new JLabel("Rib: ");
            add(labelRibbing);
            JTextField textFieldRibbingKnit = new JTextField("1");
            textFieldRibbingKnit.setPreferredSize(new Dimension(20,24));
            add(textFieldRibbingKnit);
            JLabel labelRibbingKnit = new JLabel("Knit,");
            add(labelRibbingKnit);
            JTextField textFieldRibbingPurl = new JTextField("1");
            textFieldRibbingPurl.setPreferredSize(new Dimension(20,24));
            add(textFieldRibbingPurl);
            JLabel labelRibbingPurl = new JLabel("Purl");
            add(labelRibbingPurl);
        }

        public int getCuffLength(){
            return cuffLength;
        }
    }

    public static class LegPanel extends GUIPanel{

        private JTextField textFieldLegLength;
        private int legLength;

        public LegPanel(GUI gui){
            super(gui);
            setHeight(90);

            JLabel labelLegLength = new JLabel("Leg length: ");
            add(labelLegLength);

            legLength = 50;
            textFieldLegLength = new JTextField("50");
            textFieldLegLength.setPreferredSize(new Dimension(30,24));
            add(textFieldLegLength);

            textFieldLegLength.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) { update(); }
                public void insertUpdate(DocumentEvent e) { update(); }
                public void removeUpdate(DocumentEvent e) { update(); }
                public void update() {
                    try {
                        int input = Integer.parseInt(textFieldLegLength.getText());
                        if(input >= 0 && input <= 200){
                            System.out.println("Boop");
                        } else {
                            System.out.println("Out of range.");
                        }
                    }
                    catch(NumberFormatException e){
                        System.out.println("Not an Integer.");
                    }
                }
            });

            JLabel labelLegLengthRows = new JLabel("rows");
            add(labelLegLengthRows);
        }

        public int getLegLength() {
            return legLength;
        }
    }

    public static class HeelPanel extends GUIPanel{

        private JLabel labelHeelSectioning;

        public HeelPanel(GUI gui){
            super(gui);
            setHeight(90);

            JLabel labelHeadline = new JLabel("Heel sectioning:");
            add(labelHeadline);

            labelHeelSectioning = new JLabel(generateHeelSectioning());
            add(labelHeelSectioning);
        }

        private String generateHeelSectioning(){
            int side, middle = 0;

            int heelStitchNr = gui.getStitchNr() / 2;
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
            labelHeelSectioning.setText(generateHeelSectioning());
        }

    }

    public static class FootPanel extends GUIPanel{

        private JTextField textFieldShoeSize;
        private JTextField textFieldFootLength;
        private int shoeSize;
        private int footLength;

        public FootPanel(GUI gui){
            super(gui);
            setHeight(90);

            JLabel labelShoeSize = new JLabel("Shoe size: ");
            add(labelShoeSize);

            shoeSize = 43;
            textFieldShoeSize = new JTextField("43");
            textFieldShoeSize.setPreferredSize(new Dimension(30,24));
            add(textFieldShoeSize);

            JPanel panelMargin = new JPanel();
            panelMargin.setPreferredSize(new Dimension(30,24));
            panelMargin.setOpaque(false);
            add(panelMargin);

            JLabel labelFootLength = new JLabel("Foot length: ");
            add(labelFootLength);

            footLength = 66;
            textFieldFootLength = new JTextField("66");
            textFieldFootLength.setPreferredSize(new Dimension(30,24));
            add(textFieldFootLength);

            textFieldShoeSize.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) { update(); }
                public void insertUpdate(DocumentEvent e) { update(); }
                public void removeUpdate(DocumentEvent e) { update(); }
                public void update() {
                    try {
                        int length = Integer.parseInt(textFieldShoeSize.getText());
                        if(length < 60 && length > 30){
                            length = 50 + (length-39) * 5;
                            setFootLength(length);
                        } else {
                            System.out.println("Out of range.");
                        }
                    }
                    catch(NumberFormatException e){
                        System.out.println("Not an Integer.");
                    }
                }
            });
            textFieldFootLength.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) { update(); }
                public void insertUpdate(DocumentEvent e) { update(); }
                public void removeUpdate(DocumentEvent e) { update(); }
                public void update() {
                    try {
                        int input = Integer.parseInt(textFieldFootLength.getText());
                        if(input > 0 && input < 200){
                            System.out.println("Boop");
                        } else {
                            System.out.println("Out of range.");
                        }
                    }
                    catch(NumberFormatException e){
                        System.out.println("Not an Integer.");
                    }
                }
            });

            JLabel labelFootLengthRows = new JLabel("rows");
            add(labelFootLengthRows);
        }

        public int getShoeSize() {
            return shoeSize;
        }

        public int getFootLength() {
            return footLength;
        }

        public void setFootLength(int length) {
            this.footLength = length;
            textFieldFootLength.setText(Integer.toString(length));
        }
    }

    public static class ToeboxPanel extends GUIPanel{

        private JTextField textFieldToebox1;

        public ToeboxPanel(GUI gui){
            super(gui);
            setHeight(130);

            JLabel labelToebox1a = new JLabel("Decrease every other round for");
            add(labelToebox1a);
            textFieldToebox1 = new JTextField("14");
            textFieldToebox1.setPreferredSize(new Dimension(25,24));
            add(textFieldToebox1);
            JLabel labelToebox1b = new JLabel("total rounds");
            add(labelToebox1b);

            JPanel panelMargin = new JPanel();
            panelMargin.setPreferredSize(new Dimension(380,5));
            add(panelMargin);

            JLabel labelToebox2a = new JLabel("Decreases every round until");
            add(labelToebox2a);
            JTextField textFieldToebox2 = new JTextField("4");
            textFieldToebox2.setPreferredSize(new Dimension(20,24));
            add(textFieldToebox2);
            JLabel labelToebox2b = new JLabel("stitches/needle");
            add(labelToebox2b);
        }

        public void updateToebox(){
            // calculate in two steps to ensure the number of rounds is even
            int decreaseRounds = gui.getStitchNr() / 8;
            decreaseRounds *= 2;
            textFieldToebox1.setText(Integer.toString(decreaseRounds));
        }
    }
}
