package sockpatterngenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

class CustomPanelTest {

    @Test
    void testCustomPanel(){
        CustomPanel panelDefaultSize = new CustomPanel();
        Assertions.assertEquals(new Dimension(400,120), panelDefaultSize.getPreferredSize());

        CustomPanel panelCustomSize = new CustomPanel(100,80);
        Assertions.assertEquals(new Dimension(100,80),panelCustomSize.getPreferredSize());
    }

    @Test
    void testAddSubPanels(){
        CustomPanel panel = new CustomPanel();

        JPanel panelTop = (JPanel) panel.getComponent(0);
        JPanel panelLeft = (JPanel) panel.getComponent(1);
        JPanel panelRight = (JPanel) panel.getComponent(2);
        JPanel panelCenter = (JPanel) panel.getComponent(3);

        Assertions.assertFalse(panelTop.isOpaque());
        Assertions.assertFalse(panelLeft.isOpaque());
        Assertions.assertFalse(panelRight.isOpaque());
        Assertions.assertFalse(panelCenter.isOpaque());

    }

    @Test
    void testSetHeight(){
        CustomPanel panel = new CustomPanel(100,100);
        Assertions.assertEquals(new Dimension(100,100), panel.getPreferredSize());

        panel.setHeight(50);
        Assertions.assertEquals(new Dimension(100,50), panel.getPreferredSize());
    }

    @Test
    void testSetMargins(){
        CustomPanel panel = new CustomPanel(100,100);
        panel.setMarginTop(3);
        Assertions.assertEquals(new Dimension(90,3), panel.getComponent(0).getPreferredSize());
        panel.setMarginLeft(13);
        Assertions.assertEquals(new Dimension(13,100), panel.getComponent(1).getPreferredSize());
        panel.setMarginRight(23);
        Assertions.assertEquals(new Dimension(23,100), panel.getComponent(2).getPreferredSize());
    }
}
