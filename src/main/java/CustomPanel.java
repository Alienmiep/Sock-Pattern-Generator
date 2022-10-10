import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {

    private JPanel panelCenter;
    private JPanel panelMarginTop;
    private JPanel panelMarginLeft;
    private JPanel panelMarginRight;

    private int customHeight = 120;
    private int customWidth = 400;
    private int marginTop = 10;
    private int marginLeft = 40;
    private int marginRight = 40;

    public CustomPanel(){
        super();
        setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        setPreferredSize(new Dimension(customWidth, customHeight));
        setLayout(new BorderLayout());

        addSubPanels();
    }

    public CustomPanel(int customWidth, int customHeight){
        super();
        setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        this.customWidth = customWidth;
        this.customHeight = customHeight;
        setPreferredSize(new Dimension(customWidth, customHeight));
        setLayout(new BorderLayout());

        addSubPanels();
    }

    /**
     * Utility function that adds an invisible panel to space out GUI components
     *
     * @param jPanel the panel to add the space to
     * @param width desired panel width
     * @param height desired panel height
     */
    public static void addMarginPanel(JPanel jPanel, int width, int height){
        JPanel panelMargin = new JPanel();
        panelMargin.setPreferredSize(new Dimension(width, height));
        panelMargin.setOpaque(false);
        jPanel.add(panelMargin);
    }

    private void addSubPanels(){
        panelMarginTop = new JPanel();
        panelMarginTop.setPreferredSize(new Dimension(customWidth-10, marginTop));
        panelMarginTop.setOpaque(false);
        add(panelMarginTop, BorderLayout.NORTH);

        panelMarginLeft = new JPanel();
        panelMarginLeft.setPreferredSize(new Dimension(marginLeft, customHeight));
        panelMarginLeft.setOpaque(false);
        add(panelMarginLeft, BorderLayout.WEST);

        panelMarginRight = new JPanel();
        panelMarginRight.setPreferredSize(new Dimension(marginRight, customHeight));
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
        setPreferredSize(new Dimension(customWidth, newHeight));
    }

    public void setMarginTop(int marginTop){
        this.marginTop = marginTop;
        panelMarginTop.setPreferredSize(new Dimension(customWidth-10, marginTop));
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
        panelMarginLeft.setPreferredSize(new Dimension(marginLeft, customHeight));
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
        panelMarginRight.setPreferredSize(new Dimension(marginRight, customHeight));
    }
}
