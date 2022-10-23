package sockpatterngenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

class GUILegPanelTest {

    static Sock sock = new Sock();
    static GUI gui = new GUI(sock);

    @Test
    void testSetLegLength(){
        GUIPanel.LegPanel legPanel = new GUIPanel.LegPanel(sock);
        sock.setGUI(gui);

        JPanel panelCenter = (JPanel) legPanel.getComponent(3);
        JTextField textFieldLegLength = (JTextField) panelCenter.getComponent(1);

        sock.setLegLength(50);
        Assertions.assertEquals(50, sock.getLegLength());

        textFieldLegLength.setText("44");
        Assertions.assertEquals(44, sock.getLegLength());

        textFieldLegLength.setText("-4");
        Assertions.assertNotEquals(-4, sock.getLegLength());

        textFieldLegLength.setText("250");
        Assertions.assertNotEquals(250, sock.getLegLength());
    }

}
