package sockpatterngenerator;

import java.awt.*;
import java.awt.event.ActionListener;

public class SavePatternDialog extends SaveDialog{
    public SavePatternDialog(Frame owner, Boolean isLocked, PatternHandler patternHandler) {
        super(owner, isLocked);

        ActionListener buttonListener = e -> {
            patternHandler.generatePattern(textField.getText());
            // TODO: handle case that file with that name already exists (add (1) at the end?)
            dispose();
        };

        String recommendedFilename = patternHandler.findFilename(true);
        addFilenameGroup(recommendedFilename, ".txt", buttonListener);

        setSize(320,140);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
