package sockpatterngenerator;

import org.junit.AfterClass;
import org.junit.Test;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SaveDialogTest {
    JFrame frame = new JFrame();
    Sock sock = new Sock();
    PatternHandler patternHandler = new PatternHandler(sock);

    Path pathTestDirectory = Path.of(System.getProperty("user.dir") + "/TestDirectory");

    @Test
    public void testEndingSetToTxt(){
        SaveDialog saveDialog = new SaveDialog(frame, true, patternHandler);

        JRootPane rootPane = (JRootPane) saveDialog.getComponent(0);
        JLayeredPane layeredPane = (JLayeredPane) rootPane.getComponent(1);
        JPanel panel = (JPanel) layeredPane.getComponent(0);
        CustomPanel panelDialog = (CustomPanel) panel.getComponent(0);
        JPanel panelMain = (JPanel) panelDialog.getComponent(3);
        JLabel labelEnding = (JLabel) panelMain.getComponent(3);

        assertEquals(".txt", labelEnding.getText());
    }

    @Test
    public void testEndingSetToJson(){
        SaveDialog saveDialog = new SaveDialog(frame, false, patternHandler);

        JRootPane rootPane = (JRootPane) saveDialog.getComponent(0);
        JLayeredPane layeredPane = (JLayeredPane) rootPane.getComponent(1);
        JPanel panel = (JPanel) layeredPane.getComponent(0);
        CustomPanel panelDialog = (CustomPanel) panel.getComponent(0);
        JPanel panelMain = (JPanel) panelDialog.getComponent(3);
        JLabel labelEnding = (JLabel) panelMain.getComponent(3);

        assertEquals(".json", labelEnding.getText());
    }

    @Test
    public void testSavePatternCreatesFile(){
        SaveDialog saveDialog = new SaveDialog(frame, true, patternHandler);

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

        patternHandler.setPathMyPatterns(pathTestDirectory);
        textField.setText("test");
        buttonSave.doClick();

        assertTrue(Files.exists(Path.of(pathTestDirectory + "/test.txt")));
    }

    @Test
    public void testSaveSockCreatesFile(){
        SaveDialog saveDialog = new SaveDialog(frame, false, patternHandler);

        JRootPane rootPane = (JRootPane) saveDialog.getComponent(0);
        JLayeredPane layeredPane = (JLayeredPane) rootPane.getComponent(1);
        JPanel panel = (JPanel) layeredPane.getComponent(0);
        CustomPanel panelDialog = (CustomPanel) panel.getComponent(0);
        JPanel panelMain = (JPanel) panelDialog.getComponent(3);
        JButton buttonSave = (JButton) panelMain.getComponent(5);
        JTextField textField = (JTextField) panelMain.getComponent(2);

        try {
            Files.deleteIfExists(Path.of(pathTestDirectory + "/test.json"));
        } catch (IOException ignored){}

        patternHandler.setPathMySocks(pathTestDirectory);
        textField.setText("test");
        buttonSave.doClick();

        assertTrue(Files.exists(Path.of(pathTestDirectory + "/test.json")));
    }

    @AfterClass
    public static void cleanup(){
        PatternHandlerTest.cleanup();
    }


}
