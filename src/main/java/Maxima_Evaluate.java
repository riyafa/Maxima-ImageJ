
import uk.ac.ed.ph.jacomax.MaximaInteractiveProcess;
import uk.ac.ed.ph.jacomax.MaximaTimeoutException;

/**
 *
 * @author riyafa
 */
public class Maxima_Evaluate {
/**
 *calls the methods of the jacomax library to  compute the string expression 
     * @param expression the expression to pass to Maxima and obtain results
     * @param process   the maxima process to be used created by calling startMaxima method
     * @throws uk.ac.ed.ph.jacomax.MaximaTimeoutException Rethrows the exception 
     * thrown by calling the executeCall method of the MaximaInteractiveProcess
 */
    public static String calculate(String expression, MaximaInteractiveProcess process) throws MaximaTimeoutException {
        
        String returnStringFromMaxima = null;
        if (process!=null) {
            returnStringFromMaxima=process.executeCall(expression);
        }
        return returnStringFromMaxima;
    }
    
}
