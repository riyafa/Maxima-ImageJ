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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import uk.ac.ed.ph.jacomax.MaximaInteractiveProcess;

/**
 *
 * @author riyafa
 */
public class Maxima_ConnectTest {

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
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of startMaxima method, of class Maxima_Connect.
     */
    @Test(expected = uk.ac.ed.ph.jacomax.JacomaxRuntimeException.class)
    public void testStartMaxima() {
        System.out.println("startMaxima");

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("jacomax.properties")));
        } catch (IOException ex) {
            System.err.println(ex);
        }
        Maxima_Connect.startMaxima();
    }

    /**
     * Test of terminateMaxima method, of class Maxima_Connect.
     */
    @Test
    public void testTerminateMaximaNull() {
        System.out.println("terminateMaxima");
        MaximaInteractiveProcess process = null;
        Maxima_Connect.terminateMaxima(process);

    }

}
