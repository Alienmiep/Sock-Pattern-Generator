import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class GUIPanel extends CustomPanel{

    public static class CastOnPanel extends GUIPanel{

        private JTextField textFieldStitchNr;
        private int stitchNr;

        public CastOnPanel(){
            super();
            JLabel labelStitchNr = new JLabel("Number of stitches: ");
            add(labelStitchNr);

            stitchNr = 60;
            textFieldStitchNr = new JTextField("60");
            textFieldStitchNr.setPreferredSize(new Dimension(30,26));
            add(textFieldStitchNr);

            textFieldStitchNr.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) { update(); }
                public void insertUpdate(DocumentEvent e) { update(); }
                public void removeUpdate(DocumentEvent e) { update(); }
                public void update() {
                    try {
                        stitchNr = Integer.parseInt(textFieldStitchNr.getText());
                        // TODO: Adjust cast on range
                        if(stitchNr > 0){
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

        public CuffPanel(){
            super();
            JLabel labelCuffLength = new JLabel("Cuff length: ");
            add(labelCuffLength);

            cuffLength = 15;
            textFieldCuffLength = new JTextField("15");
            textFieldCuffLength.setPreferredSize(new Dimension(30,26));
            add(textFieldCuffLength);

            textFieldCuffLength.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) { update(); }
                public void insertUpdate(DocumentEvent e) { update(); }
                public void removeUpdate(DocumentEvent e) { update(); }
                public void update() {
                    try {
                        cuffLength = Integer.parseInt(textFieldCuffLength.getText());
                        if(cuffLength >= 0){
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
        }

        public int getCuffLength(){
            return cuffLength;
        }
    }

    public static class LegPanel extends GUIPanel{

        private JTextField textFieldLegLength;
        private int legLength;

        public LegPanel(){
            super();
            JLabel labelLegLength = new JLabel("Leg length: ");
            add(labelLegLength);

            legLength = 50;
            textFieldLegLength = new JTextField("50");
            textFieldLegLength.setPreferredSize(new Dimension(30,26));
            add(textFieldLegLength);

            textFieldLegLength.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) { update(); }
                public void insertUpdate(DocumentEvent e) { update(); }
                public void removeUpdate(DocumentEvent e) { update(); }
                public void update() {
                    try {
                        int input = Integer.parseInt(textFieldLegLength.getText());
                        if(input >= 0 && input < 200){
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

        public HeelPanel(){
            super();

            // TODO: Add heel sectioning (the 11/10/11 thing)
        }
    }

    public static class FootPanel extends GUIPanel{

        private JTextField textFieldShoeSize;
        private JTextField textFieldFootLength;
        private int shoeSize;
        private int footLength;

        public FootPanel(){
            super();
            JLabel labelShoeSize = new JLabel("Shoe size: ");
            add(labelShoeSize);

            shoeSize = 43;
            textFieldShoeSize = new JTextField("43");
            textFieldShoeSize.setPreferredSize(new Dimension(30,26));
            add(textFieldShoeSize);

            JPanel panelMargin = new JPanel();
            panelMargin.setPreferredSize(new Dimension(30,26));
            add(panelMargin);

            JLabel labelFootLength = new JLabel("Foot length: ");
            add(labelFootLength);

            footLength = 66;
            textFieldFootLength = new JTextField("66");
            textFieldFootLength.setPreferredSize(new Dimension(30,26));
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

        public ToeboxPanel(){
            super();
        }
    }
}
