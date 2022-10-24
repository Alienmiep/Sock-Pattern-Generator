package sockpatterngenerator;

import org.javatuples.Pair;
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

    @Test
    void testSetCuffRib(){
        GUIPanel.CuffPanel cuffPanel = new GUIPanel.CuffPanel(sock);
        sock.setGUI(gui);

        JPanel panelCenter = (JPanel) cuffPanel.getComponent(3);
        JTextField textFieldKnit = (JTextField) panelCenter.getComponent(6);
        JTextField textFieldPurl = (JTextField) panelCenter.getComponent(8);

        textFieldKnit.setText("3");
        Assertions.assertEquals(Pair.with(3,1), sock.getCuffRib());
        textFieldPurl.setText("2");
        Assertions.assertEquals(Pair.with(3,2), sock.getCuffRib());

        textFieldKnit.setText("0");
        Assertions.assertEquals(Pair.with(3,2), sock.getCuffRib());
        textFieldPurl.setText("0");
        Assertions.assertEquals(Pair.with(3,2), sock.getCuffRib());

        textFieldKnit.setText("11");
        Assertions.assertEquals(Pair.with(3,2), sock.getCuffRib());
        textFieldPurl.setText("11");
        Assertions.assertEquals(Pair.with(3,2), sock.getCuffRib());
    }

}
