/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.finder.DialogFinder;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author riyafa
 */
public class Maxima_ImageJ_UITest extends JFCTestCase {

    private Maxima_ImageJ_UI ui;
    private PrintWriter pw;

    public Maxima_ImageJ_UITest(String name) {
        super(name);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        setHelper(new JFCTestHelper());
        ui = new Maxima_ImageJ_UI();
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter("jacomax.properties")));
            pw.print("jacomax.maxima.path=C:\\\\Program Files (x86)\\\\Maxima-openmcl-5.36.1\\\\bin\\\\maxima.bat");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        ui.start();
    }

    @After
    public void tearDown() throws Exception {
        pw.close();
        ui = null;
        getHelper().cleanUp(this);
        super.tearDown();
    }

    /**
     * Tests if the correct error message is shown if the input is empty
     */
    @Test
    public void testNullString() {
        NamedComponentFinder finder = new NamedComponentFinder(JComponent.class,
                "TestButton");
        JButton testButton = (JButton) finder.find(ui, 0);
        assertNotNull("Could not find the TEst button", testButton);

        finder.setName("ClearButton");
        JButton clearButton = (JButton) finder.find(ui, 0);
        assertNotNull("Could not find the Clear button", clearButton);

        finder.setName("InputTextArea");
        JTextArea inputTextArea = (JTextArea) finder.find(ui, 0);
        assertNotNull("Could not find the InputTextArea", inputTextArea);
        assertEquals("Input text area  is empty", "", inputTextArea.getText());

        getHelper().enterClickAndLeave(new MouseEventData(this, testButton));
        DialogFinder dFinder = new DialogFinder("No data");
        List showingDialogs = dFinder.findAll();
        assertEquals("Number of dialogs showing is wrong", 1, showingDialogs.size());
        JDialog dialog = (JDialog) showingDialogs.get(0);
        assertEquals("Wrong dialog showing up", "No data", dialog.getTitle());
        getHelper().disposeWindow(dialog, this);
    }

    /**
     * Tests if the correct error message is shown if the input string is
     * syntactically wrong
     */
    @Test
    public void testInvalidString() {
        NamedComponentFinder finder = new NamedComponentFinder(JComponent.class,
                "TestButton");
        JButton testButton = (JButton) finder.find(ui, 0);
        assertNotNull("Could not find the TEst button", testButton);

        finder.setName("InputTextArea");
        JTextArea inputTextArea = (JTextArea) finder.find(ui, 0);
        assertNotNull("Could not find the InputTextArea", inputTextArea);
        assertEquals("Input text area  is empty", "", inputTextArea.getText());
        inputTextArea.setText("1+1");

        getHelper().enterClickAndLeave(new MouseEventData(this, testButton));
        DialogFinder dFinder = new DialogFinder("Error");
        List showingDialogs = dFinder.findAll();
        assertEquals("Number of dialogs showing is wrong", 1, showingDialogs.size());
        JDialog dialog = (JDialog) showingDialogs.get(0);
        assertEquals("Wrong dialog showing up", "Error", dialog.getTitle());
        getHelper().disposeWindow(dialog, this);
    }

    @Test
    public void testClearButton() {
        NamedComponentFinder finder = new NamedComponentFinder(JComponent.class,
                "ClearButton");
        JButton clearButton = (JButton) finder.find(ui, 0);
        assertNotNull("Could not find the Clear button", clearButton);

        finder.setName("InputTextArea");
        JTextArea inputTextArea = (JTextArea) finder.find(ui, 0);
        assertNotNull("Could not find the InputTextArea", inputTextArea);
        inputTextArea.setText("1+1;");

        finder.setName("ResutlTextArea");
        JTextArea resultTextArea = (JTextArea) finder.find(ui, 0);
        assertNotNull("Could not find the resultTextArea", resultTextArea);

        finder.setName("TestButton");
        JButton testButton = (JButton) finder.find(ui, 0);
        assertNotNull("Could not find the TEst button", testButton);

        getHelper().enterClickAndLeave(new MouseEventData(this, testButton));
        getHelper().enterClickAndLeave(new MouseEventData(this, clearButton));
        
        assertEquals("Input text area  is empty", "", inputTextArea.getText());
        assertEquals("Result text area  is empty", "", resultTextArea.getText());
    }
}
