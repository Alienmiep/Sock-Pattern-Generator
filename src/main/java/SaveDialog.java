import javax.swing.*;
import java.awt.*;

public class SaveDialog extends JDialog {

    public SaveDialog(Frame owner, Boolean isPattern, PatternHandler patternHandler){
        super(owner, "Save File");

        String ending;
        String recommendedFilename = patternHandler.findFilename(isPattern);

        JLabel label = new JLabel("Filename: ");
        JTextField textField = new JTextField(recommendedFilename);
        textField.setColumns(15);
        JButton buttonSave = new JButton("Save");

        if(isPattern) {
            ending = ".txt";
            buttonSave.addActionListener(e ->  {
                patternHandler.generatePattern(textField.getText());
                // TODO: handle case that file with that name already exists (add (1) at the end?)
                dispose();
            });
        } else {
            ending = ".json";
            buttonSave.addActionListener(e ->  {
                patternHandler.saveSock(textField.getText());
                dispose();
            });
        }

        CustomPanel panelDialog = new CustomPanel();
        panelDialog.setMarginRight(20);
        panelDialog.setMarginLeft(20);

        JLabel labelEnding = new JLabel(ending);
        panelDialog.add(label);

        CustomPanel.addMarginPanel(panelDialog,80,30);

        panelDialog.add(textField);
        panelDialog.add(labelEnding);
        CustomPanel.addMarginPanel(panelDialog, 5,30);
        panelDialog.add(buttonSave);

        add(panelDialog);

        setSize(320,140);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
