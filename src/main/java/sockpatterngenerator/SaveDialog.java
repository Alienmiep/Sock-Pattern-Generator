package sockpatterngenerator;

import javax.swing.*;
import java.awt.*;

public class SaveDialog extends JDialog {

    public SaveDialog(Frame owner, Boolean isPattern, Boolean isLocked, PatternHandler patternHandler){
        super(owner, "Save File");

        if(Boolean.TRUE.equals(isLocked)) setModalityType(ModalityType.DOCUMENT_MODAL);
        String ending;
        String recommendedFilename = patternHandler.findFilename(isPattern);

        var panelDialog = new CustomPanel();
        panelDialog.setMarginRight(20);
        panelDialog.setMarginLeft(20);


        var label = new JLabel("Filename: ");
        var textField = new JTextField(recommendedFilename);
        textField.setColumns(15);
        var buttonSave = new JButton("Save");

        if(Boolean.TRUE.equals(isPattern)) {
            ending = ".txt";
            buttonSave.addActionListener(e ->  {
                patternHandler.generatePattern(textField.getText());
                // TODO: handle case that file with that name already exists (add (1) at the end?)
                dispose();
            });
        } else {
            var labelNotes = new JLabel("Additional Notes: ");
            panelDialog.add(labelNotes);
            CustomPanel.addMarginPanel(panelDialog, 100, 20);
            var textAreaNotes = new JTextArea(3,25);
            panelDialog.add(textAreaNotes);
            CustomPanel.addMarginPanel(panelDialog,200, 5);
            ending = ".json";
            buttonSave.addActionListener(e ->  {
                patternHandler.saveSock(textField.getText(), textAreaNotes.getText());
                dispose();
            });
        }

        var labelEnding = new JLabel(ending);
        panelDialog.add(label);

        CustomPanel.addMarginPanel(panelDialog,80,30);

        panelDialog.add(textField);
        panelDialog.add(labelEnding);
        CustomPanel.addMarginPanel(panelDialog, 5,30);
        panelDialog.add(buttonSave);

        add(panelDialog);

        setSize(320,220);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
