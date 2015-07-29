
import uk.ac.ed.ph.jacomax.MaximaInteractiveProcess;
import uk.ac.ed.ph.jacomax.MaximaTimeoutException;

/**
 *
 * @author riyafa
 */
public class Maxima_Transfer_Float_Arrays {
    
    public static void transferArray(MaximaInteractiveProcess process,double... num) throws MaximaTimeoutException{
        process.executeCall("array (aa,flonum,"+num.length+");");
        for (int i = 0; i < num.length; i++) {
           process.executeCall(" aa ["+i+"] : "+num[i]+";");
        }
        String s="Final array: \n"+process.executeCall("listarray (aa);");
        System.out.println(s);
    }
}
