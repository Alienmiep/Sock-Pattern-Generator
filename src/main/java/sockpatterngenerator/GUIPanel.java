package sockpatterngenerator;

import org.javatuples.Pair;

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

    static class RangeVerifier extends InputVerifier {
        private final int min;
        private final int max;
        public RangeVerifier(int min, int max){
            super();
            this.min = min;
            this.max = max;
        }
        @Override
        public boolean verify(JComponent input) {
            JTextField textField = (JTextField) input;
            try {
                var stitchNr = Integer.parseInt(textField.getText());
                if(stitchNr >= min && stitchNr <= max){
                    textField.setBackground(Color.WHITE);
                    return true;
                } else {
                    LOGGER.warning(OUT_OF_RANGE);
                    textField.setBackground(Color.PINK);
                    return false;
                }
            } catch(NumberFormatException nfe){
                textField.setBackground(Color.PINK);
                LOGGER.warning(NOT_AN_INTEGER);
                return false;
            }
        }
    }

    /**
     * Panel that contains the yarn thickness (ply) and number of stitches to cast on
     */
    public static class CastOnPanel extends GUIPanel{

        JTextField textFieldStitchNr;

        public CastOnPanel(Sock sock){
            super();
            setHeight(60);

            var yarnOptions = new String[]{"4-ply Sock Yarn", "6-ply Sock Yarn"};
            JComboBox<String> comboBoxYarn = new JComboBox<>(yarnOptions);
            comboBoxYarn.setPreferredSize(new Dimension(130,30));
            comboBoxYarn.setBackground(Color.WHITE);
            add(comboBoxYarn);

            comboBoxYarn.addItemListener(e -> {
                int ply;
                if (comboBoxYarn.getSelectedIndex() == 1) {
                    ply = 6;
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
            textFieldStitchNr.setInputVerifier(new RangeVerifier(1, 100));
            add(textFieldStitchNr);

            textFieldStitchNr.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) { update(); }
                public void insertUpdate(DocumentEvent e) { update(); }
                public void removeUpdate(DocumentEvent e) { update(); }
                public void update() {
                        if(textFieldStitchNr.getInputVerifier().verify(textFieldStitchNr)){
                            sock.setStitchNr(Integer.parseInt(textFieldStitchNr.getText()));
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
            textFieldCuffLength.setInputVerifier(new RangeVerifier(0, 200));
            add(textFieldCuffLength);

            textFieldCuffLength.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) { update(); }
                public void insertUpdate(DocumentEvent e) { update(); }
                public void removeUpdate(DocumentEvent e) { update(); }
                public void update() {
                    if(textFieldCuffLength.getInputVerifier().verify(textFieldCuffLength)){
                        sock.setCuffLength(Integer.parseInt(textFieldCuffLength.getText()));
                    }
                }
            });

            var labelCuffLengthRounds = new JLabel(ROUNDS);
            add(labelCuffLengthRounds);

            CustomPanel.addMarginPanel(this,150,24);
            CustomPanel.addMarginPanel(this,380,5);

            var labelRibbing = new JLabel("Rib: ");
            var textFieldRibbingKnit = new JTextField("1");
            textFieldRibbingKnit.setInputVerifier(new RangeVerifier(1,10));
            var labelRibbingKnit = new JLabel("Knit,");
            var textFieldRibbingPurl = new JTextField("1");
            textFieldRibbingPurl.setInputVerifier(new RangeVerifier(1,10));
            var labelRibbingPurl = new JLabel("Purl");

            DocumentListener cuffRibListener = new DocumentListener() {
                public void insertUpdate(DocumentEvent e) { update();}
                public void removeUpdate(DocumentEvent e) { update(); }
                public void changedUpdate(DocumentEvent e) { update(); }
                public void update(){
                    if(textFieldRibbingKnit.getInputVerifier().verify(textFieldRibbingKnit) &&
                       textFieldRibbingPurl.getInputVerifier().verify(textFieldRibbingPurl)) {
                        var ribKnit = Integer.parseInt(textFieldRibbingKnit.getText());
                        var ribPurl = Integer.parseInt(textFieldRibbingPurl.getText());
                        sock.setCuffRib(Pair.with(ribKnit, ribPurl));
                    }
                }
            };

            add(labelRibbing);

            textFieldRibbingKnit.setPreferredSize(new Dimension(20,24));
            textFieldRibbingKnit.getDocument().addDocumentListener(cuffRibListener);
            add(textFieldRibbingKnit);
            add(labelRibbingKnit);

            textFieldRibbingPurl.setPreferredSize(new Dimension(20,24));
            textFieldRibbingPurl.getDocument().addDocumentListener(cuffRibListener);
            add(textFieldRibbingPurl);
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
            textFieldLegLength.setInputVerifier(new RangeVerifier(0, 200));
            add(textFieldLegLength);

            textFieldLegLength.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) { update(); }
                public void insertUpdate(DocumentEvent e) { update(); }
                public void removeUpdate(DocumentEvent e) { update(); }
                public void update() {
                    if(textFieldLegLength.getInputVerifier().verify(textFieldLegLength)){
                        sock.setLegLength(Integer.parseInt(textFieldLegLength.getText()));
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
            setHeight(90);

            var heelOptions = new String[]{"German Short Row"};
            JComboBox<String> comboBoxHeel = new JComboBox<>(heelOptions);
            comboBoxHeel.setPreferredSize(new Dimension(140,30));
            comboBoxHeel.setBackground(Color.WHITE);
            add(comboBoxHeel);

            CustomPanel.addMarginPanel(this,100,30);
            CustomPanel.addMarginPanel(this,350,1);

            var labelHeadline = new JLabel("Heel sectioning:");
            add(labelHeadline);

            labelHeelSectioning = new JLabel(sock.getHeelSectioning());
            labelHeelSectioning.setFont(labelHeelSectioning.getFont().deriveFont(14F));
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
            textFieldShoeSize.setInputVerifier(new RangeVerifier(30,60));
            add(textFieldShoeSize);

            CustomPanel.addMarginPanel(this,30,24);

            var labelFootLength = new JLabel("Foot length: ");
            add(labelFootLength);

            textFieldFootLength = new JTextField(Integer.toString(sock.getFootLength()));
            textFieldFootLength.setPreferredSize(new Dimension(30,24));
            textFieldFootLength.setInputVerifier(new RangeVerifier(1,200));
            add(textFieldFootLength);

            textFieldShoeSize.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) { update(); }
                public void insertUpdate(DocumentEvent e) { update(); }
                public void removeUpdate(DocumentEvent e) { update(); }
                public void update() {
                    if(textFieldShoeSize.getInputVerifier().verify(textFieldShoeSize)) {
                        sock.setShoeSize(Integer.parseInt(textFieldShoeSize.getText()));
                        textFieldFootLength.setText(Integer.toString(sock.getFootLength()));
                    }
                }
            });

            textFieldFootLength.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) { update(); }
                public void insertUpdate(DocumentEvent e) { update(); }
                public void removeUpdate(DocumentEvent e) { update(); }
                public void update() {
                    if(textFieldFootLength.getInputVerifier().verify(textFieldFootLength)){
                        sock.setFootLength(Integer.parseInt(textFieldFootLength.getText()));
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
            setHeight(91);

            var labelToebox1a = new JLabel("Decrease every other round for");
            add(labelToebox1a);
            textFieldToebox1 = new JTextField("14");
            textFieldToebox1.setPreferredSize(new Dimension(25,24));
            add(textFieldToebox1);
            var labelToebox1b = new JLabel("total rounds");
            add(labelToebox1b);

            CustomPanel.addMarginPanel(this,380,1);

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

