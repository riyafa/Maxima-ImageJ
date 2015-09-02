
import uk.ac.ed.ph.jacomax.MaximaInteractiveProcess;
import uk.ac.ed.ph.jacomax.MaximaTimeoutException;

/**
 *
 * @author riyafa
 */
public class Maxima_Transfer_Float_Arrays {
    /**
     this method transfers float arrays to Maxima
     * @param process the maxima process to be used created by calling startMaxima method
     * @param num the float array to pass to Maxima
     */
    public static String transferArray(MaximaInteractiveProcess process,double... num) throws MaximaTimeoutException{
        process.executeCall("array (aa,flonum,"+num.length+");");
        for (int i = 0; i < num.length; i++) {
           process.executeCall(" aa ["+i+"] : "+num[i]+";");
        }
        
        return process.executeCall("listarray (aa);");
    }
}
