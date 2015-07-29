
import uk.ac.ed.ph.jacomax.JacomaxSimpleConfigurator;
import uk.ac.ed.ph.jacomax.MaximaConfiguration;
import uk.ac.ed.ph.jacomax.MaximaInteractiveProcess;
import uk.ac.ed.ph.jacomax.MaximaProcessLauncher;

/**
 *
 * @author riyafa
 */
public class Maxima_Connect {

    public static MaximaInteractiveProcess startMaxima() {
        MaximaConfiguration configuration = JacomaxSimpleConfigurator.configure();
        MaximaProcessLauncher launcher = new MaximaProcessLauncher(configuration);
         MaximaInteractiveProcess process=launcher.launchInteractiveProcess();
        return process;
    }

    public static void terminateMaxima(MaximaInteractiveProcess process) {
        if (process != null) {
            process.terminate();
        }
    }

    
}
