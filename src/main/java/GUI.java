import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private PatternHandler patternHandler = null;

    private JFrame frame;
    private JPanel panelMain;

    private JTextField textFieldStitchNr;
    private JTextField textFieldCuffLength;
    private JTextField textFieldLegLength;
    private JTextField textFieldShoeSize;
    private JTextField textFieldFootLength;

    public GUI(){
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sock Pattern Generator 1.0");
        frame.setSize(500,800);
        frame.setResizable(false);

        panelMain = new JPanel(new FlowLayout(FlowLayout.CENTER,0,5));

        JPanel panelWestBorder = new JPanel();
        JPanel panelEastBorder = new JPanel();
        panelEastBorder.setPreferredSize(new Dimension(40,800));
        panelWestBorder.setPreferredSize(new Dimension(40,800));
        frame.add(panelWestBorder, BorderLayout.WEST);
        frame.add(panelEastBorder, BorderLayout.EAST);

        buildCastOnPanel();
        buildCuffPanel();
        buildLegPanel();
        buildHeelPanel();
        buildFootPanel();
        buildToeboxPanel();

        JButton button = new JButton("Generate Pattern!");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                patternHandler.generatePattern();
            }
        });
        panelMain.add(button);
        frame.add(panelMain, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void setPatternHandler(PatternHandler handler){
        this.patternHandler = handler;
    }

    DocumentListener listenerTextField = new DocumentListener() {
        public void changedUpdate(DocumentEvent e) {
            update();
        }

        public void insertUpdate(DocumentEvent e) {
            update();
        }

        public void removeUpdate(DocumentEvent e) {
            update();
        }

        public void update() {
            System.out.print("Boop ");
        }
    };

    private void buildCastOnPanel(){
        CustomPanel panelCastOn = new CustomPanel();
        panelCastOn.setBackground(Color.cyan);

        JLabel labelStitchNr = new JLabel("Number of stitches: ");
        panelCastOn.add(labelStitchNr);

        textFieldStitchNr = new JTextField("60");
        textFieldStitchNr.setPreferredSize(new Dimension(30,26));
        panelCastOn.add(textFieldStitchNr);

        textFieldStitchNr.getDocument().addDocumentListener(listenerTextField);

        panelMain.add(panelCastOn);
    }

    private void buildCuffPanel(){
        CustomPanel panelCuff = new CustomPanel();

        JLabel labelCuffLength = new JLabel("Cuff length: ");
        panelCuff.add(labelCuffLength);

        textFieldCuffLength = new JTextField("15");
        textFieldCuffLength.setPreferredSize(new Dimension(30,26));
        panelCuff.add(textFieldCuffLength);

        textFieldCuffLength.getDocument().addDocumentListener(listenerTextField);

        JLabel labelCuffLengthRows = new JLabel("rows");
        panelCuff.add(labelCuffLengthRows);

        panelMain.add(panelCuff);
    }

    private void buildLegPanel(){
        CustomPanel panelLeg = new CustomPanel();

        JLabel labelLegLength = new JLabel("Leg length: ");
        panelLeg.add(labelLegLength);

        textFieldLegLength = new JTextField("50");
        textFieldLegLength.setPreferredSize(new Dimension(30,26));
        panelLeg.add(textFieldLegLength);

        textFieldLegLength.getDocument().addDocumentListener(listenerTextField);

        JLabel labelLegLengthRows = new JLabel("rows");
        panelLeg.add(labelLegLengthRows);

        panelMain.add(panelLeg);
    }

    private void buildHeelPanel(){
        CustomPanel panelHeel = new CustomPanel();

        panelHeel.setPreferredSize(new Dimension(400,50));

        panelMain.add(panelHeel);
    }

    private void buildFootPanel(){
        CustomPanel panelFoot = new CustomPanel();

        JLabel labelShoeSize = new JLabel("Shoe size: ");
        panelFoot.add(labelShoeSize);

        textFieldShoeSize = new JTextField("43");
        textFieldShoeSize.setPreferredSize(new Dimension(30,26));
        panelFoot.add(textFieldShoeSize);

        JLabel labelFootLength = new JLabel("Foot length: ");
        panelFoot.add(labelFootLength);

        textFieldFootLength = new JTextField("66");
        textFieldFootLength.setPreferredSize(new Dimension(30,26));
        panelFoot.add(textFieldFootLength);

        textFieldShoeSize.getDocument().addDocumentListener(listenerTextField);
        textFieldFootLength.getDocument().addDocumentListener(listenerTextField);
        textFieldShoeSize.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                convertSizeToLength();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                convertSizeToLength();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                convertSizeToLength();
            }
            private void convertSizeToLength(){
                int length = Integer.parseInt(textFieldShoeSize.getText());
                if(length < 60 && length > 30){
                    length = 50 + (length-39) * 5;
                    textFieldFootLength.setText(Integer.toString(length));
                }
            }
        });

        JLabel labelFootLengthRows = new JLabel("rows");
        panelFoot.add(labelFootLengthRows);

        panelMain.add(panelFoot);
    }

    private void buildToeboxPanel(){
        CustomPanel panelToebox = new CustomPanel();

        panelToebox.setPreferredSize(new Dimension(400,50));

        panelMain.add(panelToebox);
    }

    // potentially consolidate into one getInput method
    public int getStitchNr(){
        return Integer.parseInt(textFieldStitchNr.getText());
    }

    public int getCuffLength(){
        return Integer.parseInt(textFieldCuffLength.getText());
    }

    public int getLegLength(){
        return Integer.parseInt(textFieldLegLength.getText());
    }

    public int getShoeSize(){
        return Integer.parseInt(textFieldShoeSize.getText());
    }

    public int getFootLength(){
        return Integer.parseInt(textFieldFootLength.getText());
    }

    public void setFootLength(int length){
        textFieldFootLength.setText(Integer.toString(length));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //count++;
        //label.setText("Number of clicks: " + count);
    }
}
