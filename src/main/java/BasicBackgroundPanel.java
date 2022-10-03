import javax.swing.*;
import java.awt.*;

public class BasicBackgroundPanel extends JPanel {
    private Image background;

    public BasicBackgroundPanel(Image background) {
        this.background = background;
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics g2 = g.create();
        g2.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g2.dispose();
    }
}
