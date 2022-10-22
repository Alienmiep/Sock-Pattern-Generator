package sockpatterngenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

class GUICuffPanelTest {

    static Sock sock = new Sock();
    static GUI gui = new GUI(sock);

    @Test
    void testSetCuffLength(){
        GUIPanel.CuffPanel cuffPanel = new GUIPanel.CuffPanel(sock);
        sock.setGUI(gui);

        JPanel panelCenter = (JPanel) cuffPanel.getComponent(3);
        JTextField textFieldCuffLength = (JTextField) panelCenter.getComponent(1);

        sock.setCuffLength(50);
        Assertions.assertEquals(50, sock.getCuffLength());

        textFieldCuffLength.setText("44");
        Assertions.assertEquals(44, sock.getCuffLength());

        textFieldCuffLength.setText("-4");
        Assertions.assertNotEquals(-4, sock.getCuffLength());

        textFieldCuffLength.setText("250");
        Assertions.assertNotEquals(250, sock.getCuffLength());
    }

}
