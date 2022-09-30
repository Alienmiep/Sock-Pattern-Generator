import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

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

        castOnPanel = new GUIPanel.CastOnPanel(this);
        cuffPanel = new GUIPanel.CuffPanel(this);
        legPanel = new GUIPanel.LegPanel(this);
        heelPanel = new GUIPanel.HeelPanel(this);
        footPanel = new GUIPanel.FootPanel(this);
        toeboxPanel = new GUIPanel.ToeboxPanel(this);
        panelMain.add(castOnPanel);
        panelMain.add(cuffPanel);
        panelMain.add(legPanel);
        panelMain.add(heelPanel);
        panelMain.add(footPanel);
        panelMain.add(toeboxPanel);

        JPanel panelMargin = new JPanel();
        panelMargin.setPreferredSize(new Dimension(380, 20));
        panelMain.add(panelMargin);

        JButton button = new JButton("Generate Pattern!");
        button.addActionListener(e -> { if(patternHandler!=null) patternHandler.generatePattern();});
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

    public void updateStitchNr(){
        heelPanel.updateHeel();
        toeboxPanel.updateToebox();
    }

}
