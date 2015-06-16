
import ij.plugin.PlugIn;


/**
 *
 * @author riyafa
 */
public class Maxima_ImageJ implements PlugIn{

    @Override
    public void run(String string) {
        new Maxima_ImageJ_UI().start();
        /*try {
            //String s=calculate("f2(x,y):=sin(x^2+y^2);").replaceFirst("\\(.*?\\) ?", " ");
            //s=s.substring(0,s.lastIndexOf('('));
            String s=calculate("f2(x,y):=sin(x^2+y^2);");
            IJ.showMessage(s);
            //System.out.println(s);
           System.out.println(s);
        } catch (MaximaTimeoutException ex) {
           IJ.showMessageWithCancel("Error", ex.getMessage());
        }*/
    }
    public static void main(String[] args) {
        new ij.ImageJ();
        new Maxima_ImageJ().run("");
    }
    
    
}
