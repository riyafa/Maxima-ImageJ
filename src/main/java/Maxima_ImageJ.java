
import ij.plugin.PlugIn;


/**
 *
 * @author riyafa
 */
public class Maxima_ImageJ implements PlugIn{

    @Override
    public void run(String string) {
        new Maxima_ImageJ_UI().start();
        
    }
    public static void main(String[] args) {
        new ij.ImageJ();
        new Maxima_ImageJ().run("");
    }
    
    
}
