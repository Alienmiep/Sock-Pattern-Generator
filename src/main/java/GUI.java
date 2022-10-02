import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class responsible for building the GUI around the 6 GUI panels and managing the buttons at the bottom
 * The panels follow the structure of a cuff-to-toe sock: Cast on, Cuff, Leg, Heel, Foot and Toebox
 */
public class GUI {

    private PatternHandler patternHandler = null;
    private static Sock sock;

    private JFrame frame;
    private JPanel panelMain;

    private GUIPanel.CastOnPanel castOnPanel;
    private GUIPanel.CuffPanel cuffPanel;
    private GUIPanel.LegPanel legPanel;
    private GUIPanel.HeelPanel heelPanel;
    private GUIPanel.FootPanel footPanel;
    private GUIPanel.ToeboxPanel toeboxPanel;

    public GUI(Sock sock){
        GUI.sock = sock;

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

        castOnPanel = new GUIPanel.CastOnPanel(GUI.sock);
        cuffPanel = new GUIPanel.CuffPanel(GUI.sock);
        legPanel = new GUIPanel.LegPanel(GUI.sock);
        heelPanel = new GUIPanel.HeelPanel(GUI.sock);
        footPanel = new GUIPanel.FootPanel(GUI.sock);
        toeboxPanel = new GUIPanel.ToeboxPanel(GUI.sock);
        panelMain.add(castOnPanel);
        panelMain.add(cuffPanel);
        panelMain.add(legPanel);
        panelMain.add(heelPanel);
        panelMain.add(footPanel);
        panelMain.add(toeboxPanel);

        GUIPanel.addMarginPanel(panelMain, 380,20);

        JButton buttonPattern = new JButton("Generate Pattern!");
        buttonPattern.addActionListener(e -> { if(patternHandler!=null) patternHandler.generatePattern();});
        panelMain.add(buttonPattern);

        GUIPanel.addMarginPanel(panelMain, 30,30);

        JButton buttonSockData = new JButton("Save Sock Data!");
        buttonSockData.addActionListener(e -> { if(patternHandler!=null) patternHandler.saveSock();});
        panelMain.add(buttonSockData);

        frame.add(panelMain, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void setPatternHandler(PatternHandler handler){
        this.patternHandler = handler;
    }

    public Sock getSock(){
        return sock;
    }

    public void updateHeel(String heelSectioning){
        heelPanel.updateHeel(heelSectioning);
    }

    public void updateToebox(int decreaseRounds){
        toeboxPanel.updateToebox(decreaseRounds);
    }

}
