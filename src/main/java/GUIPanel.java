import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class GUIPanel extends CustomPanel{

    public GUIPanel(){
        super();
    }

    public static void addMarginPanel(JPanel jPanel, int width, int height){
        JPanel panelMargin = new JPanel();
        panelMargin.setPreferredSize(new Dimension(width, height));
        panelMargin.setOpaque(false);
        jPanel.add(panelMargin);
    }

    public static class CastOnPanel extends GUIPanel{

        private JTextField textFieldStitchNr;
        private int stitchNr;

        public CastOnPanel(Sock sock){
            super();
            setHeight(100);

            String[] yarnOptions = {"4-ply Sock Yarn", "6-ply Sock Yarn"};
            JComboBox<String> comboBoxYarn = new JComboBox<>(yarnOptions);
            comboBoxYarn.setPreferredSize(new Dimension(130,30));
            add(comboBoxYarn);

            GUIPanel.addMarginPanel(this,10,30);

            JLabel labelStitchNr = new JLabel("Number of stitches: ");
            add(labelStitchNr);

            stitchNr = sock.getStitchNr();
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

    public static class CuffPanel extends GUIPanel{

        private JTextField textFieldCuffLength;
        private int cuffLength;

        public CuffPanel(Sock sock){
            super();
            setHeight(130);

            JLabel labelCuffLength = new JLabel("Cuff length: ");
            add(labelCuffLength);

            cuffLength = sock.getCuffLength();
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

            JLabel labelCuffLengthRows = new JLabel("rows");
            add(labelCuffLengthRows);

            GUIPanel.addMarginPanel(this,150,24);
            GUIPanel.addMarginPanel(this,380,5);

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

    public static class LegPanel extends GUIPanel{

        private JTextField textFieldLegLength;
        private int legLength;

        public LegPanel(Sock sock){
            super();
            setHeight(90);

            JLabel labelLegLength = new JLabel("Leg length: ");
            add(labelLegLength);

            legLength = sock.getLegLength();
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
                            sock.setLegLength(legLength);
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

    }

    public static class HeelPanel extends GUIPanel{

        private JLabel labelHeelSectioning;

        public HeelPanel(Sock sock){
            super();
            setHeight(90);

            JLabel labelHeadline = new JLabel("Heel sectioning:");
            add(labelHeadline);

            labelHeelSectioning = new JLabel(sock.getHeelSectioning());
            add(labelHeelSectioning);
        }

        public void updateHeel(String heelSectioning){
            labelHeelSectioning.setText(heelSectioning);
        }

    }

    public static class FootPanel extends GUIPanel{

        private JTextField textFieldShoeSize;
        private JTextField textFieldFootLength;
        private int shoeSize;
        private int footLength;

        public FootPanel(Sock sock){
            super();
            setHeight(90);

            JLabel labelShoeSize = new JLabel("Shoe size: ");
            add(labelShoeSize);

            shoeSize = sock.getShoeSize();
            textFieldShoeSize = new JTextField("43");
            textFieldShoeSize.setPreferredSize(new Dimension(30,24));
            add(textFieldShoeSize);

            GUIPanel.addMarginPanel(this,30,24);

            JLabel labelFootLength = new JLabel("Foot length: ");
            add(labelFootLength);

            footLength = sock.getFootLength();
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

            JLabel labelFootLengthRows = new JLabel("rows");
            add(labelFootLengthRows);
        }

    }

    public static class ToeboxPanel extends GUIPanel{

        private JTextField textFieldToebox1;

        public ToeboxPanel(Sock sock){
            super();
            setHeight(130);

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
