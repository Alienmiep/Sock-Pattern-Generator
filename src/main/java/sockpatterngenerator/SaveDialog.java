package sockpatterngenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SaveDialog extends JDialog {

    CustomPanel panelDialog;
    JLabel labelEnding;
    JTextField textField;
    JButton buttonSave;

    public SaveDialog(Frame owner, Boolean isLocked){
        super(owner, "Save File");

        if(Boolean.TRUE.equals(isLocked)) setModalityType(ModalityType.DOCUMENT_MODAL);

        panelDialog = new CustomPanel();
        panelDialog.setMarginRight(20);
        panelDialog.setMarginLeft(20);

        add(panelDialog);
    }

    public void addFilenameGroup(String recommendedFilename, String ending, ActionListener buttonListener){
        var label = new JLabel("Filename: ");
        panelDialog.add(label);
        CustomPanel.addMarginPanel(panelDialog,80,30);

        textField = new JTextField();
        textField.setColumns(15);
        textField.setText(recommendedFilename);
        buttonSave = new JButton("Save");
        buttonSave.addActionListener(buttonListener);
        labelEnding = new JLabel(ending);

        panelDialog.add(textField);
        panelDialog.add(labelEnding);

        CustomPanel.addMarginPanel(panelDialog, 5,30);
        panelDialog.add(buttonSave);
    }
}
