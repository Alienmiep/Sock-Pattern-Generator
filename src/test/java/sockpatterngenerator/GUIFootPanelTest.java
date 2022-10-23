package sockpatterngenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

class GUIFootPanelTest {

    static Sock sock = new Sock();
    static GUI gui = new GUI(sock);

    @Test
    void testSetShoeSize(){
        GUIPanel.FootPanel footPanel = new GUIPanel.FootPanel(sock);
        sock.setGUI(gui);

        JPanel panelCenter = (JPanel) footPanel.getComponent(3);
        JTextField textFieldShoeSize = (JTextField) panelCenter.getComponent(1);

        sock.setShoeSize(40);
        Assertions.assertEquals(40, sock.getShoeSize());

        textFieldShoeSize.setText("44");
        Assertions.assertEquals(44, sock.getShoeSize());

        textFieldShoeSize.setText("29");
        Assertions.assertNotEquals(29, sock.getShoeSize());
        Assertions.assertEquals(44, sock.getShoeSize());

        textFieldShoeSize.setText("61");
        Assertions.assertNotEquals(61, sock.getShoeSize());
        Assertions.assertEquals(44, sock.getShoeSize());
    }

    @Test
    void testSetFootLength(){
        GUIPanel.FootPanel footPanel = new GUIPanel.FootPanel(sock);
        sock.setGUI(gui);

        JPanel panelCenter = (JPanel) footPanel.getComponent(3);
        JTextField textFieldFootLength = (JTextField) panelCenter.getComponent(4);

        sock.setFootLength(50);
        Assertions.assertEquals(50, sock.getFootLength());

        textFieldFootLength.setText("44");
        Assertions.assertEquals(44, sock.getFootLength());

        textFieldFootLength.setText("0");
        Assertions.assertNotEquals(0, sock.getFootLength());
        Assertions.assertEquals(44, sock.getFootLength());

        textFieldFootLength.setText("250");
        Assertions.assertNotEquals(250, sock.getFootLength());
        Assertions.assertEquals(44, sock.getFootLength());
    }

}
