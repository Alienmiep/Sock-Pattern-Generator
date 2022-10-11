import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.logging.Logger;

/**
 * Utility class that houses the individual GUI panels (to not clutter up the GUI class)
 * The individual GUI panels are built and formatted here
 * Inputs are checked for format and range here too
 */
public class GUIPanel extends CustomPanel{
    private static final String ROUNDS = "rounds";
    private static final String OUT_OF_RANGE = "Value out of range.";
    private static final String NOT_AN_INTEGER = "Value is not an Integer.";

    private static final Logger LOGGER = Logger.getLogger("MyLogger");

    public GUIPanel(){
        super();
        setOpaque(false);
    }

    /**
     * Panel that contains the yarn thickness (ply) and number of stitches to cast on
     */
    public static class CastOnPanel extends GUIPanel{

        private JTextField textFieldStitchNr;

        public CastOnPanel(Sock sock){
            super();
            setHeight(60);

            var yarnOptions = new String[]{"4-ply Sock Yarn", "6-ply Sock Yarn"};
            JComboBox<String> comboBoxYarn = new JComboBox<>(yarnOptions);
            comboBoxYarn.setPreferredSize(new Dimension(130,30));
            add(comboBoxYarn);

            comboBoxYarn.addItemListener(e -> {
                int ply;
                if (comboBoxYarn.getSelectedIndex() == 1) {
                    ply = 6;  // TODO: potentially convert stitchNr if ply changed
                } else {
                    ply = 4;
                }
                sock.setPly(ply);
            });

            CustomPanel.addMarginPanel(this,10,30);

            var labelStitchNr = new JLabel("Number of stitches: ");
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
                        var stitchNr = Integer.parseInt(textFieldStitchNr.getText());
                        if(stitchNr > 0 && stitchNr <= 100){
                            sock.setStitchNr(stitchNr);
                        } else {
                            LOGGER.warning(OUT_OF_RANGE);
                        }
                    }
                    catch(NumberFormatException e){
                        LOGGER.warning(NOT_AN_INTEGER);
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

            var labelCuffLength = new JLabel("Cuff length: ");
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
                        var cuffLength = Integer.parseInt(textFieldCuffLength.getText());
                        if(cuffLength >= 0 && cuffLength <= 200){
                            sock.setCuffLength(cuffLength);
                        } else {
                            LOGGER.warning(OUT_OF_RANGE);
                        }
                    }
                    catch(NumberFormatException e){
                        LOGGER.warning(NOT_AN_INTEGER);
                    }
                }
            });

            var labelCuffLengthRounds = new JLabel(ROUNDS);
            add(labelCuffLengthRounds);

            CustomPanel.addMarginPanel(this,150,24);
            CustomPanel.addMarginPanel(this,380,5);

            // TODO: save cuff rib pattern in sock object
            var labelRibbing = new JLabel("Rib: ");
            add(labelRibbing);
            var textFieldRibbingKnit = new JTextField("1");
            textFieldRibbingKnit.setPreferredSize(new Dimension(20,24));
            add(textFieldRibbingKnit);
            var labelRibbingKnit = new JLabel("Knit,");
            add(labelRibbingKnit);
            var textFieldRibbingPurl = new JTextField("1");
            textFieldRibbingPurl.setPreferredSize(new Dimension(20,24));
            add(textFieldRibbingPurl);
            var labelRibbingPurl = new JLabel("Purl");
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

            var labelLegLength = new JLabel("Leg length: ");
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
                        var input = Integer.parseInt(textFieldLegLength.getText());
                        if(input >= 0 && input <= 200){
                            sock.setLegLength(input);
                        } else {
                            LOGGER.warning(OUT_OF_RANGE);
                        }
                    }
                    catch(NumberFormatException e){
                        LOGGER.warning(NOT_AN_INTEGER);
                    }
                }
            });

            var labelLegLengthRounds = new JLabel(ROUNDS);
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

            var labelHeadline = new JLabel("Heel sectioning:");
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

            var labelShoeSize = new JLabel("Shoe size: ");
            add(labelShoeSize);

            textFieldShoeSize = new JTextField(Integer.toString(sock.getShoeSize()));
            textFieldShoeSize.setPreferredSize(new Dimension(30,24));
            add(textFieldShoeSize);

            CustomPanel.addMarginPanel(this,30,24);

            var labelFootLength = new JLabel("Foot length: ");
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
                        var length = Integer.parseInt(textFieldShoeSize.getText());
                        if(length < 60 && length > 30){
                            sock.setShoeSize(length);
                            textFieldFootLength.setText(Integer.toString(sock.getFootLength()));
                        } else {
                            LOGGER.warning(OUT_OF_RANGE);
                        }
                    }
                    catch(NumberFormatException e){
                        LOGGER.warning(NOT_AN_INTEGER);
                    }
                }
            });
            textFieldFootLength.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) { update(); }
                public void insertUpdate(DocumentEvent e) { update(); }
                public void removeUpdate(DocumentEvent e) { update(); }
                public void update() {
                    try {
                        var input = Integer.parseInt(textFieldFootLength.getText());
                        if(input > 0 && input < 200){
                            sock.setFootLength(input);
                        } else {
                            LOGGER.warning(OUT_OF_RANGE);
                        }
                    }
                    catch(NumberFormatException e){
                        LOGGER.warning(NOT_AN_INTEGER);
                    }
                }
            });

            var labelFootLengthRounds = new JLabel(ROUNDS);
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

        public ToeboxPanel(){
            super();
            setHeight(105);

            var labelToebox1a = new JLabel("Decrease every other round for");
            add(labelToebox1a);
            textFieldToebox1 = new JTextField("14");
            textFieldToebox1.setPreferredSize(new Dimension(25,24));
            add(textFieldToebox1);
            var labelToebox1b = new JLabel("total rounds");
            add(labelToebox1b);

            CustomPanel.addMarginPanel(this,380,5);

            var labelToebox2a = new JLabel("Decreases every round until");
            add(labelToebox2a);
            var textFieldToebox2 = new JTextField("4");
            textFieldToebox2.setPreferredSize(new Dimension(20,24));
            add(textFieldToebox2);
            var labelToebox2b = new JLabel("stitches/needle");
            add(labelToebox2b);
        }

        public void updateToebox(int decreaseRounds) {
            textFieldToebox1.setText(Integer.toString(decreaseRounds));
        }
    }
}

