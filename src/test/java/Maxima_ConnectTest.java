/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import uk.ac.ed.ph.jacomax.MaximaInteractiveProcess;

/**
 *
 * @author riyafa
 */
public class Maxima_ConnectTest {

    /*private PrintWriter pw;

    public Maxima_ConnectTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter("jacomax.properties")));
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    @After
    public void tearDown() {
        pw.close();
    }

    /**
     * Test createProcess() method of Maxima_Connect for windows
     
    public void testcreateProcess() {
        System.out.println("startMaxima");

        pw.print("jacomax.maxima.path=C:\\\\Program Files (x86)\\\\Maxima-openmcl-5.36.1\\\\bin\\\\maxima.bat");

        MaximaInteractiveProcess process = Maxima_Connect.startMaxima();
        assertNotNull(process);
        Maxima_Connect.terminateMaxima(process);
    }

    /**
     * Test FrameVisibility value of Maxima_Connect for windows
     
    @Test
    public void testFrameVisibility() {
        pw.print("jacomax.maxima.path=C:\\\\Program Files (x86)\\\\Maxima-openmcl-5.36.1\\\\bin\\\\maxima.bat");
        MaximaInteractiveProcess process = Maxima_Connect.startMaxima();
        assertFalse(!Maxima_Connect.frameVisibility);
        Maxima_Connect.terminateMaxima(process);
    }

    /**
     * Test of terminateMaxima method, of class Maxima_Connect.
     
    @Test
    public void testTerminateMaximaNull() {
        System.out.println("terminateMaxima");
        MaximaInteractiveProcess process = null;
        Maxima_Connect.terminateMaxima(process);

    }

    /**
     * Test the deleting of jacomax.properties file for ubuntu *
     *
     * @Test public void testDeleteJacomax(){ assertFalse(new
     * File("jacomax.properties").exists()); }
     */
}
