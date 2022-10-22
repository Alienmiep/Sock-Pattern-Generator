package sockpatterngenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

class GUICastOnPanelTest {

    static Sock sock = new Sock();
    static GUI gui = new GUI(sock);

    @Test
    void testSetSockPly(){
        GUIPanel.CastOnPanel castOnPanel = new GUIPanel.CastOnPanel(sock);

        Assertions.assertEquals(4, sock.getPly());

        JPanel panelCenter = (JPanel) castOnPanel.getComponent(3);
        JComboBox<String> comboBox = (JComboBox<String>) panelCenter.getComponent(0);

        comboBox.setSelectedIndex(1);
        Assertions.assertEquals(6, sock.getPly());
        comboBox.setSelectedIndex(0);
        Assertions.assertEquals(4, sock.getPly());
    }

    @Test
    void testSetStitchNr(){
        GUIPanel.CastOnPanel castOnPanel = new GUIPanel.CastOnPanel(sock);
        sock.setGUI(gui);

        JPanel panelCenter = (JPanel) castOnPanel.getComponent(3);
        JTextField textFieldStitchNr = (JTextField) panelCenter.getComponent(3);

        sock.setStitchNr(50);
        Assertions.assertEquals(50, sock.getStitchNr());

        textFieldStitchNr.setText("44");
        Assertions.assertEquals(44, sock.getStitchNr());

        textFieldStitchNr.setText("-4");
        Assertions.assertNotEquals(-4, sock.getStitchNr());

        textFieldStitchNr.setText("200");
        Assertions.assertNotEquals(200, sock.getStitchNr());
    }
}
