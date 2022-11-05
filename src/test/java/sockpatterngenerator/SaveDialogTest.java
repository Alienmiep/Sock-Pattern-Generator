package sockpatterngenerator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class SaveDialogTest {
    JFrame frame = new JFrame();
    Sock sock = new Sock();
    PatternHandler patternHandler = new PatternHandler(sock);

    Path pathTestDirectory = Path.of(System.getProperty("user.dir") + "/TestDirectory");

    @Test
    void testSavePatternCreatesFile(){
        patternHandler.setPathMyPatterns(pathTestDirectory);
        SavePatternDialog saveDialog = new SavePatternDialog(frame, false, patternHandler);

        JRootPane rootPane = (JRootPane) saveDialog.getComponent(0);
        JLayeredPane layeredPane = (JLayeredPane) rootPane.getComponent(1);
        JPanel panel = (JPanel) layeredPane.getComponent(0);
        CustomPanel panelDialog = (CustomPanel) panel.getComponent(0);
        JPanel panelMain = (JPanel) panelDialog.getComponent(3);
        JButton buttonSave = (JButton) panelMain.getComponent(5);
        JTextField textField = (JTextField) panelMain.getComponent(2);

        try {
            Files.deleteIfExists(Path.of(pathTestDirectory + "/test.txt"));
        } catch (IOException ignored){}

        textField.setText("test");
        buttonSave.doClick();

        Assertions.assertTrue(Files.exists(Path.of(pathTestDirectory + "/test.txt")));
    }

    @Test
    void testSaveSockCreatesFile(){
        patternHandler.setPathMySocks(pathTestDirectory);
        SaveSockDialog saveDialog = new SaveSockDialog(frame, false, patternHandler);
        saveDialog.setModalityType(Dialog.ModalityType.MODELESS);

        JRootPane rootPane = (JRootPane) saveDialog.getComponent(0);
        JLayeredPane layeredPane = (JLayeredPane) rootPane.getComponent(1);
        JPanel panel = (JPanel) layeredPane.getComponent(0);
        CustomPanel panelDialog = (CustomPanel) panel.getComponent(0);
        JPanel panelMain = (JPanel) panelDialog.getComponent(3);
        JButton buttonSave = (JButton) panelMain.getComponent(9);
        JTextField textField = (JTextField) panelMain.getComponent(6);

        try {
            Files.deleteIfExists(Path.of(pathTestDirectory + "/test.json"));
        } catch (IOException ignored){}

        textField.setText("test");
        buttonSave.doClick();

        Assertions.assertTrue(Files.exists(Path.of(pathTestDirectory + "/test.json")));
    }

    @AfterAll
    static void cleanup(){
        PatternHandlerTest.cleanup();
    }


}
