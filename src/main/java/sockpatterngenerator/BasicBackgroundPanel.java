package sockpatterngenerator;

import javax.swing.*;
import java.awt.*;

public class BasicBackgroundPanel extends JPanel {
    private transient Image customBackground;

    public BasicBackgroundPanel(Image background) {
        this.customBackground = background;
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics g2 = g.create();
        g2.drawImage(customBackground, 0, 0, getWidth(), getHeight(), null);
        g2.dispose();
    }
}
