import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Utility class that houses the individual GUI panels (to not clutter up the GUI class)
 * The individual GUI panels are built and formatted here
 * Inputs are checked for format and range here too
 */
public class GUIPanel extends CustomPanel{

    public GUIPanel(){
        super();
        setOpaque(false);
    }

    /**
     * Utility function that adds an invisible panel to space out GUI components
     *
     * @param jPanel the panel to add the space to
     * @param width desired panel width
     * @param height desired panel height
     */
    public static void addMarginPanel(JPanel jPanel, int width, int height){
        JPanel panelMargin = new JPanel();
        panelMargin.setPreferredSize(new Dimension(width, height));
        panelMargin.setOpaque(false);
        jPanel.add(panelMargin);
    }

    /**
     * Panel that contains the yarn thickness (ply) and number of stitches to cast on
     */
    public static class CastOnPanel extends GUIPanel{

        private JTextField textFieldStitchNr;

        public CastOnPanel(Sock sock){
            super();
            setHeight(60);

            String[] yarnOptions = {"4-ply Sock Yarn", "6-ply Sock Yarn"};
            JComboBox<String> comboBoxYarn = new JComboBox<>(yarnOptions);
            comboBoxYarn.setPreferredSize(new Dimension(130,30));
            add(comboBoxYarn);

            GUIPanel.addMarginPanel(this,10,30);

            JLabel labelStitchNr = new JLabel("Number of stitches: ");
            add(labelStitchNr);

            textFieldStitchNr = new JTextField(Integer.toString(sock.getStitchNr()));
            textFieldStitchNr.setPreferredSize(new Dimension(30,24));
            add(textFieldStitchNr);

            textFieldStitchNr.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) { update(); }
                public void insertUpdate(DocumentEvent e) { update(); }
                public void removeUpdate(DocumentEvent e) { update(); }
                public void update() {
                    try {
                        int stitchNr = Integer.parseInt(textFieldStitchNr.getText());
                        if(stitchNr > 0 && stitchNr <= 100){
                            System.out.println("Boop");
                            sock.setStitchNr(stitchNr);
                        } else {
                            System.out.println("Out of range.");
                        }
                    }
                    catch(NumberFormatException e){
                        System.out.println("Not an Integer.");
                    }
                }
            });

        }

    }

    /**
     * Panel that contains the length of the sock cuff (0 is permitted, since the cuff is an optional part of the sock)
     * The user can also set the ribbing pattern of the cuff (patterns other than rib aren't supported)
     */
    public static class CuffPanel extends GUIPanel{

        private JTextField textFieldCuffLength;

        public CuffPanel(Sock sock){
            super();
            setHeight(95);

            JLabel labelCuffLength = new JLabel("Cuff length: ");
            add(labelCuffLength);

            textFieldCuffLength = new JTextField(Integer.toString(sock.getCuffLength()));
            textFieldCuffLength.setPreferredSize(new Dimension(25,24));
            add(textFieldCuffLength);

            textFieldCuffLength.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) { update(); }
                public void insertUpdate(DocumentEvent e) { update(); }
                public void removeUpdate(DocumentEvent e) { update(); }
                public void update() {
                    try {
                        int cuffLength = Integer.parseInt(textFieldCuffLength.getText());
                        if(cuffLength >= 0 && cuffLength <= 200){
                            System.out.println("Boop");
                            sock.setCuffLength(cuffLength);
                        } else {
                            System.out.println("Out of range.");
                        }
                    }
                    catch(NumberFormatException e){
                        System.out.println("Not an Integer.");
                    }
                }
            });

            JLabel labelCuffLengthRounds = new JLabel("rounds");
            add(labelCuffLengthRounds);

            GUIPanel.addMarginPanel(this,150,24);
            GUIPanel.addMarginPanel(this,380,5);

            // TODO: save cuff rib pattern in sock object
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

    }

    /**
     * Panel that contains the length of the leg part in rounds
     * The user can also set stripes, rib or other patterns here (yet to be implemented)
     */
    public static class LegPanel extends GUIPanel{

        private JTextField textFieldLegLength;

        public LegPanel(Sock sock){
            super();
            setHeight(230);

            JLabel labelLegLength = new JLabel("Leg length: ");
            add(labelLegLength);

            textFieldLegLength = new JTextField(Integer.toString(sock.getLegLength()));
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
                            sock.setLegLength(input);
                        } else {
                            System.out.println("Out of range.");
                        }
                    }
                    catch(NumberFormatException e){
                        System.out.println("Not an Integer.");
                    }
                }
            });

            JLabel labelLegLengthRounds = new JLabel("rounds");
            add(labelLegLengthRounds);
        }

    }

    /**
     * Panel that displays the heel sectioning for a German Short Row heel
     * Other heel types like gusset heels may be implemented in the future
     */
    public static class HeelPanel extends GUIPanel{

        private JLabel labelHeelSectioning;

        public HeelPanel(Sock sock){
            super();
            setHeight(50);

            JLabel labelHeadline = new JLabel("Heel sectioning:");
            add(labelHeadline);

            labelHeelSectioning = new JLabel(sock.getHeelSectioning());
            add(labelHeelSectioning);
        }

        public void updateHeel(String heelSectioning){
            labelHeelSectioning.setText(heelSectioning);
        }

    }

    /**
     * Panel that contains the shoe size and calculated foot length in rounds
     * (The user is able to overwrite the calculated number of foot rounds)
     * If the sock has a 3d pattern (like a rib), the user can set the sole to be flat
     */
    public static class FootPanel extends GUIPanel{

        private JTextField textFieldShoeSize;
        private JTextField textFieldFootLength;

        public FootPanel(Sock sock){
            super();
            setHeight(140);

            JLabel labelShoeSize = new JLabel("Shoe size: ");
            add(labelShoeSize);

            textFieldShoeSize = new JTextField(Integer.toString(sock.getShoeSize()));
            textFieldShoeSize.setPreferredSize(new Dimension(30,24));
            add(textFieldShoeSize);

            GUIPanel.addMarginPanel(this,30,24);

            JLabel labelFootLength = new JLabel("Foot length: ");
            add(labelFootLength);

            textFieldFootLength = new JTextField(Integer.toString(sock.getFootLength()));
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
                            sock.setShoeSize(length);
                            textFieldFootLength.setText(Integer.toString(sock.getFootLength()));
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
                            sock.setFootLength(input);
                        } else {
                            System.out.println("Out of range.");
                        }
                    }
                    catch(NumberFormatException e){
                        System.out.println("Not an Integer.");
                    }
                }
            });

            JLabel labelFootLengthRounds = new JLabel("rounds");
            add(labelFootLengthRounds);
        }

    }

    /**
     * Panel that displays the number of rounds for the first phase of the toebox (decreasing every other round)
     * This number is calculated based on the number of cast on stitches, but the user may overwrite it
     * The user can also specify the final number of stitches per needle
     */
    public static class ToeboxPanel extends GUIPanel{

        private JTextField textFieldToebox1;

        public ToeboxPanel(Sock sock){
            super();
            setHeight(105);

            JLabel labelToebox1a = new JLabel("Decrease every other round for");
            add(labelToebox1a);
            textFieldToebox1 = new JTextField("14");
            textFieldToebox1.setPreferredSize(new Dimension(25,24));
            add(textFieldToebox1);
            JLabel labelToebox1b = new JLabel("total rounds");
            add(labelToebox1b);

            GUIPanel.addMarginPanel(this,380,5);

            JLabel labelToebox2a = new JLabel("Decreases every round until");
            add(labelToebox2a);
            JTextField textFieldToebox2 = new JTextField("4");
            textFieldToebox2.setPreferredSize(new Dimension(20,24));
            add(textFieldToebox2);
            JLabel labelToebox2b = new JLabel("stitches/needle");
            add(labelToebox2b);
        }

        public void updateToebox(int decreaseRounds) {
            textFieldToebox1.setText(Integer.toString(decreaseRounds));
        }
    }
}

