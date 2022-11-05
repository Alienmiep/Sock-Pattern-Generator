package sockpatterngenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SaveSockDialog extends SaveDialog{
    public SaveSockDialog(Frame owner, Boolean isLocked, PatternHandler patternHandler) {
        super(owner, isLocked);

        var labelNotes = new JLabel("Additional Notes: ");
        panelDialog.add(labelNotes);
        CustomPanel.addMarginPanel(panelDialog, 100, 20);
        var textAreaNotes = new JTextArea(3,25);
        panelDialog.add(textAreaNotes);

        CustomPanel.addMarginPanel(panelDialog,200, 5);

        ActionListener buttonListener = e -> {
            patternHandler.saveSock(textField.getText(), textAreaNotes.getText());
            dispose();
        };

        String recommendedFilename = patternHandler.findFilename(false);
        addFilenameGroup(recommendedFilename, ".json", buttonListener);

        setSize(320,230);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
