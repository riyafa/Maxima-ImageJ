
import uk.ac.ed.ph.jacomax.MaximaInteractiveProcess;
import uk.ac.ed.ph.jacomax.MaximaTimeoutException;

/**
 *
 * @author riyafa
 */
public class Maxima_Evaluate {

    public static String calculate(String expression, MaximaInteractiveProcess process) throws MaximaTimeoutException {
        
        String returnStringFromMaxima = null;
        if (process!=null) {
            returnStringFromMaxima=process.executeCall(expression);
        }
        return returnStringFromMaxima;
    }
    
}
