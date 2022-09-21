import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private JFrame frame;
    private JPanel panelMain;

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
        panelMain.add(button);
        frame.add(panelMain, BorderLayout.CENTER);
        frame.setVisible(true);
    }


    private void buildCastOnPanel(){
        CustomPanel panelCastOn = new CustomPanel();
        panelCastOn.setBackground(Color.cyan);

        JLabel labelStitchNr = new JLabel("Number of stitches: ");
        panelCastOn.add(labelStitchNr);

        JTextField textFieldStitchNr = new JTextField("60");
        textFieldStitchNr.setPreferredSize(new Dimension(30,26));
        panelCastOn.add(textFieldStitchNr);

        panelMain.add(panelCastOn);
    }

    private void buildCuffPanel(){
        CustomPanel panelCuff = new CustomPanel();

        JLabel labelCuffLength = new JLabel("Cuff length: ");
        panelCuff.add(labelCuffLength);

        JTextField textFieldCuffLength = new JTextField("15");
        textFieldCuffLength.setPreferredSize(new Dimension(30,26));
        panelCuff.add(textFieldCuffLength);

        JLabel labelCuffLengthRows = new JLabel("rows");
        panelCuff.add(labelCuffLengthRows);

        panelMain.add(panelCuff);
    }

    private void buildLegPanel(){
        CustomPanel panelLeg = new CustomPanel();

        JLabel labelLegLength = new JLabel("Leg length: ");
        panelLeg.add(labelLegLength);

        JTextField textFieldLegLength = new JTextField("50");
        textFieldLegLength.setPreferredSize(new Dimension(30,26));
        panelLeg.add(textFieldLegLength);

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

        JTextField textFieldShoeSize = new JTextField("43");
        textFieldShoeSize.setPreferredSize(new Dimension(30,26));
        panelFoot.add(textFieldShoeSize);

        JLabel labelFootLength = new JLabel("Foot length: ");
        panelFoot.add(labelFootLength);

        JTextField textFieldFootLength = new JTextField("66");
        textFieldFootLength.setPreferredSize(new Dimension(30,26));
        panelFoot.add(textFieldFootLength);

        JLabel labelFootLengthRows = new JLabel("rows");
        panelFoot.add(labelFootLengthRows);

        panelMain.add(panelFoot);
    }

    private void buildToeboxPanel(){
        CustomPanel panelToebox = new CustomPanel();

        panelToebox.setPreferredSize(new Dimension(400,50));

        panelMain.add(panelToebox);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //count++;
        //label.setText("Number of clicks: " + count);
    }
}
