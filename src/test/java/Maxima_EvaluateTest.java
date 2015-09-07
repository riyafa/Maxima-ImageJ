/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.ac.ed.ph.jacomax.MaximaInteractiveProcess;
import uk.ac.ed.ph.jacomax.MaximaTimeoutException;

/**
 *
 * @author riyafa
 */
public class Maxima_EvaluateTest {
    
    public Maxima_EvaluateTest() {
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
     * Test of calculate method for null process, of class Maxima_Evaluate.
     * @throws java.lang.Exception
     */
    @Test
    public void testCalculateNull() throws Exception {
        System.out.println("calculate");
        String expression = "1+1;";
        MaximaInteractiveProcess process = null;
        String expResult = null;
        String result = Maxima_Evaluate.calculate(expression, process);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCalculate(){
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("jacomax.properties")));
            pw.print("jacomax.maxima.path=C:\\\\Program Files (x86)\\\\Maxima-openmcl-5.36.1\\\\bin\\\\maxima.bat");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        MaximaInteractiveProcess process = Maxima_Connect.startMaxima();
        try {
            assertEquals(Maxima_Evaluate.calculate("1+1;",process),"(%o1)                                  2\n" +
                    "(%i2) ");
        } catch (MaximaTimeoutException ex) {
            System.out.println(ex);
        }
                Maxima_Connect.terminateMaxima(process);

    }
}
