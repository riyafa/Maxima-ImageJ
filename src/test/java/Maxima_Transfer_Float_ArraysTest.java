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
import static org.junit.Assert.*;
import uk.ac.ed.ph.jacomax.MaximaInteractiveProcess;

/**
 *
 * @author Riyafas
 */
public class Maxima_Transfer_Float_ArraysTest {

    public Maxima_Transfer_Float_ArraysTest() {
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
     * Test of transferArray method, of class Maxima_Transfer_Float_Arrays.
     */
    @Test
    public void testTransferArray() throws Exception {
        System.out.println("transferArray");
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("jacomax.properties")));
            pw.print("jacomax.maxima.path=C:\\\\Program Files (x86)\\\\Maxima-openmcl-5.36.1\\\\bin\\\\maxima.bat");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        MaximaInteractiveProcess process = Maxima_Connect.startMaxima();

        double[] num = new double[20];
            for (int i = 0; i < num.length; i++) {
                num[i] = i;
            };
        String expResult = "(%o22) [0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, \n"
                + "                                 13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0, 0.0]\n"
                + "(%i23) ";
        String result = Maxima_Transfer_Float_Arrays.transferArray(process, num);
        assertEquals(expResult, result);
        Maxima_Connect.terminateMaxima(process);

    }

}
