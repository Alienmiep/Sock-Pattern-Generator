import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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
        frame.setResizable(false);

        String resourcePath = System.getProperty("user.dir")+"/src/main/resources/";
        try {
            Image icon = ImageIO.read(new File(resourcePath + "sockIcon.png"));
            frame.setIconImage(icon);
        } catch (IOException e){
            //
        }

        // load background image
        BasicBackgroundPanel panelBackground = null;
        try {
            Image background = ImageIO.read(new File(resourcePath + "sock.png"));
            panelBackground = new BasicBackgroundPanel(background);
            panelBackground.setPreferredSize(new Dimension(500,800));
            frame.add(panelBackground);
        } catch (IOException e){
            //
        }

        panelMain = new JPanel();
        panelMain.setOpaque(false);
        panelMain.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));

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

        assert panelBackground != null;
        panelBackground.add(panelMain, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
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

