
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
    public static void transferArray(MaximaInteractiveProcess process,double... num) throws MaximaTimeoutException{
        process.executeCall("array (aa,flonum,"+num.length+");");
        for (int i = 0; i < num.length; i++) {
           process.executeCall(" aa ["+i+"] : "+num[i]+";");
        }
        String s="Final array: \n"+process.executeCall("listarray (aa);");
        System.out.println(s);
    }
}
