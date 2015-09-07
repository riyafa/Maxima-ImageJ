
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.ac.ed.ph.jacomax.MaximaInteractiveProcess;
import uk.ac.ed.ph.jacomax.MaximaTimeoutException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Riyafas
 */
public class Test {

    public static void main(String[] args) {
        MaximaInteractiveProcess process = Maxima_Connect.startMaxima();

        new Maxima_ImageJ_UI().start();
        try {
            
            double[] some = new double[20];
            for (int i = 0; i < some.length; i++) {
                some[i] = i;
            }
            String s = "Final array: \n" + Maxima_Transfer_Float_Arrays.transferArray(process, some);
            System.out.println(s);

        } catch (MaximaTimeoutException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (process != null) {
                Maxima_Connect.terminateMaxima(process);
            }
        }

    }
}
