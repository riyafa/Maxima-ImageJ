
import uk.ac.ed.ph.jacomax.JacomaxSimpleConfigurator;
import uk.ac.ed.ph.jacomax.MaximaConfiguration;
import uk.ac.ed.ph.jacomax.MaximaInteractiveProcess;
import uk.ac.ed.ph.jacomax.MaximaProcessLauncher;

/**
 *
 * @author riyafa
 */
public class Maxima_Connect {
    /**
     calls the methods of the Jacomax library to create and start a Maxima process
     */
    public static MaximaInteractiveProcess startMaxima() {
        MaximaConfiguration configuration = JacomaxSimpleConfigurator.configure();
        MaximaProcessLauncher launcher = new MaximaProcessLauncher(configuration);
         MaximaInteractiveProcess process=launcher.launchInteractiveProcess();
        return process;
    }
/**
     calls the method of the process to terminate the process
     * @param process the process to be terminated
     */
    public static void terminateMaxima(MaximaInteractiveProcess process) {
        if (process != null) {
            process.terminate();
        }
    }

    
}
