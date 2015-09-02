
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.JOptionPane;
import uk.ac.ed.ph.jacomax.JacomaxRuntimeException;
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
     *this is to be used by the gui of the plugin to start the gui only if 
     * everything seemed ok. true if everything is set to open the gui, false
     * if otherwise.
     */
    public static boolean frameVisibility = false;
    private static MaximaInteractiveProcess process = null;

    /**
     * calls the methods of the Jacomax library to create and start a Maxima
     * process
     * @return Returns a Maxima process as a MaximaInteractiveProcess
     */
    public static MaximaInteractiveProcess startMaxima() {

        try {
            //creates the MaximaInteractiveProcess process
            createProcess();
            //frame can be visible if there's no exceptions
            frameVisibility = true;

        } catch (uk.ac.ed.ph.jacomax.JacomaxRuntimeException ex) {
            System.err.println(ex.getMessage());
            //if there's the above exception check if the os is windows
            if (System.getProperty("os.name").contains("Windows")) {
                //os windows error is due to being unable to locate maxima so locate maxima
                locateMaxima();
            } else {
                try {
                    //if another OS(linux) then exception is because of the jacomax file
                    //delete file
                    Files.delete(new File("jacomax.properties").toPath());
                    createProcess();
                    //open frame
                    frameVisibility = true;

                } catch (IOException ex1) {
                    //cannot delete file
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR",
                            JOptionPane.ERROR_MESSAGE);

                }
            }
        }
        return process;
    }

    private static void locateMaxima() {
        try {
            //use Winregistry to locate maxima in windows registry
            List<String> ls = WinRegistry.readStringSubKeys(WinRegistry.HKEY_LOCAL_MACHINE, "SOFTWARE\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\", WinRegistry.KEY_WOW64_32KEY);
            String key = null;
            for (String s : ls) {
                if (s.matches("Maxima.*")) {
                    key = s;
                }
            }
            // currently our plugin doesn't support sbcl version of Maxima
            if (key.contains("sbcl")) {
                JOptionPane.showMessageDialog(null, "Currently Maxima-ImageJ" +
                        " does not support sbcl version of Maxima", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                frameVisibility = false;
            } else {
                //locates path of Maxima using the class WinRegistry which searches
                //through windows registry to locate maxima
                PrintWriter pw = new PrintWriter(new BufferedWriter(
                        new FileWriter("jacomax.properties")));
                String path = WinRegistry.readString(
                        WinRegistry.HKEY_LOCAL_MACHINE, 
                        "SOFTWARE\\Wow6432Node\\Microsoft\\Windows" + 
                                "\\CurrentVersion\\Uninstall\\" + key, 
                        "Inno Setup: App Path", WinRegistry.KEY_WOW64_32KEY);
                System.out.println(path.replace("\\", "\\\\"));
                pw.print("jacomax.maxima.path=" + path.replace("\\", "\\\\") +
                        "\\\\bin\\\\maxima.bat");
                pw.close();
                createProcess();
                frameVisibility = true;
            }
        } catch (IllegalArgumentException ex) {
            System.err.println(ex);
        } catch (IllegalAccessException ex) {
            System.err.println(ex);
        } catch (InvocationTargetException ex) {
            System.err.println(ex);
        } catch (NoSuchElementException ex) {
            //no maxima in machine
            JOptionPane.showMessageDialog(null, "Maxima-ImageJ could not " +
                    "detect maxima on your computer.", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
            frameVisibility = false;
        } catch (IOException ex) {
            //cannot create jacomax.properties file
            System.err.println(ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR",
                            JOptionPane.ERROR_MESSAGE);
        } catch (JacomaxRuntimeException ex) {
            //no maxima in machine
            JOptionPane.showMessageDialog(null, "Maxima-ImageJ could not " + 
                    "detect maxima on your computer.", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
            frameVisibility = false;
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Maxima-ImageJ could not " +
                    "detect maxima on your computer.", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }
    }

    private static void createProcess() {
        MaximaConfiguration configuration = JacomaxSimpleConfigurator.configure();
        MaximaProcessLauncher launcher = new MaximaProcessLauncher(configuration);
        process = launcher.launchInteractiveProcess();
    }

    /**
     * calls the method of the process to terminate the process
     *
     * @param process the process to be terminated
     */
    public static void terminateMaxima(MaximaInteractiveProcess process) {
        if (process != null) {
            process.terminate();
        }
    }

}
