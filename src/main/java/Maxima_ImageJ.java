
import ij.plugin.PlugIn;

/**
 *
 * @author riyafa
 */
public class Maxima_ImageJ implements PlugIn {

    private boolean startUI;

    public Maxima_ImageJ(boolean startUI) {
        this.startUI = startUI;
    }
    //default is false
    public Maxima_ImageJ(){
        this(false);
    }
    @Override
    public void run(String string) {
        if (startUI) {
            new Maxima_ImageJ_UI().start();

        }else{
            Maxima_Connect.startMaxima();
        }

    }

    public static void main(String[] args) {
        new Maxima_ImageJ(true).run("");
    }

}
