import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {

    private JPanel panelCenter;
    private JPanel panelMarginTop;
    private JPanel panelMarginLeft;
    private JPanel panelMarginRight;

    private int height = 120;
    private int width = 400;
    private int marginTop = 30;
    private int marginLeft = 40;
    private int marginRight = 40;

    public CustomPanel(){
        super();
        setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout());

        addSubPanels();
    }

    public CustomPanel(int customWidth, int customHeight){
        super();
        setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        this.width = customWidth;
        this.height = customHeight;
        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout());

        addSubPanels();
    }

    private void addSubPanels(){
        panelMarginTop = new JPanel();
        panelMarginTop.setPreferredSize(new Dimension(width-10, marginTop));
        panelMarginTop.setOpaque(false);
        add(panelMarginTop, BorderLayout.NORTH);

        panelMarginLeft = new JPanel();
        panelMarginLeft.setPreferredSize(new Dimension(marginLeft, height));
        panelMarginLeft.setOpaque(false);
        add(panelMarginLeft, BorderLayout.WEST);

        panelMarginRight = new JPanel();
        panelMarginRight.setPreferredSize(new Dimension(marginRight, height));
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

    public void setHeight(int newHeight){
        setPreferredSize(new Dimension(width, newHeight));
    }

    public void setMarginTop(int marginTop){
        this.marginTop = marginTop;
        panelMarginTop.setPreferredSize(new Dimension(width-10, marginTop));
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
        panelMarginLeft.setPreferredSize(new Dimension(marginLeft, height));
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
        panelMarginRight.setPreferredSize(new Dimension(marginRight, height));
    }
}
