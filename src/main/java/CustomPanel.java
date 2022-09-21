import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {

    private JPanel panelCenter;

    public CustomPanel(){
        super();
        setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        setPreferredSize(new Dimension(400,100));
        setLayout(new BorderLayout());

        JPanel panelMarginTop = new JPanel();
        panelMarginTop.setPreferredSize(new Dimension(390, 25));
        panelMarginTop.setOpaque(false);
        add(panelMarginTop, BorderLayout.NORTH);

        JPanel panelMarginLeft = new JPanel();
        panelMarginLeft.setPreferredSize(new Dimension(50, 100));
        panelMarginLeft.setOpaque(false);
        add(panelMarginLeft, BorderLayout.WEST);

        JPanel panelMarginRight = new JPanel();
        panelMarginRight.setPreferredSize(new Dimension(50, 100));
        panelMarginRight.setOpaque(false);
        add(panelMarginRight, BorderLayout.EAST);

        panelCenter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCenter.setOpaque(false);
        add(panelCenter, BorderLayout.CENTER);
    }

    public Component add(Component c){
        panelCenter.add(c);
        return this;
    }
}
