/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.ac.ed.ph.jacomax.MaximaInteractiveProcess;

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
    
    
}
