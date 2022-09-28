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

    private GUIPanel.CastOnPanel castOnPanel;
    private GUIPanel.CuffPanel cuffPanel;
    private GUIPanel.LegPanel legPanel;
    private GUIPanel.HeelPanel heelPanel;
    private GUIPanel.FootPanel footPanel;
    private GUIPanel.ToeboxPanel toeboxPanel;

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

        castOnPanel = new GUIPanel.CastOnPanel();
        cuffPanel = new GUIPanel.CuffPanel();
        legPanel = new GUIPanel.LegPanel();
        heelPanel = new GUIPanel.HeelPanel();
        footPanel = new GUIPanel.FootPanel();
        toeboxPanel = new GUIPanel.ToeboxPanel();
        panelMain.add(castOnPanel);
        panelMain.add(cuffPanel);
        panelMain.add(legPanel);
        panelMain.add(heelPanel);
        panelMain.add(footPanel);
        panelMain.add(toeboxPanel);

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

    // potentially consolidate into one getInput method
    public int getStitchNr(){
        return castOnPanel.getStitchNr();
    }

    public int getCuffLength(){
        return cuffPanel.getCuffLength();
    }

    public int getLegLength(){
        return legPanel.getLegLength();
    }

    public int getShoeSize(){
        return footPanel.getShoeSize();
    }

    public int getFootLength(){
        return footPanel.getFootLength();
    }

    public void setFootLength(int length){
        footPanel.setFootLength(length);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //count++;
        //label.setText("Number of clicks: " + count);
    }
}
