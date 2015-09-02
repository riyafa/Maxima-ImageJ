
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.ac.ed.ph.jacomax.MaximaInteractiveProcess;
import uk.ac.ed.ph.jacomax.MaximaTimeoutException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Riyafas
 */
public class Test {

    public static void main(String[] args) {
        MaximaInteractiveProcess process = Maxima_Connect.startMaxima();

        new Maxima_ImageJ_UI().start();
        try {
            /*try {
             String value = WinRegistry.readString(WinRegistry.HKEY_LOCAL_MACHINE,
             "SOFTWARE\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\Maxima-sbcl-5.36.1_is1",
             "Inno Setup: App Path", WinRegistry.KEY_WOW64_32KEY);
             System.out.println("Windows Distribution = " + value);
             System.out.println(System.getProperty("os.name"));
             System.out.println(System.getenv("ProgramFiles(X86)"));
             List<String> ls = WinRegistry.readStringSubKeys(WinRegistry.HKEY_LOCAL_MACHINE,
             "SOFTWARE\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\",WinRegistry.KEY_WOW64_32KEY);
             String key = ls.stream().filter(new Predicate<String>() {
            
             @Override
             public boolean test(String t) {
             return t.matches("Maxima.*");
             }
             }).findAny().get();
             System.out.println(key);
             } catch (IllegalArgumentException ex) {
             System.err.println(ex);
             } catch (IllegalAccessException ex) {
             System.err.println(ex);
             } catch (InvocationTargetException ex) {
             System.err.println(ex);
             }*/
            double[] some = new double[20];
            for (int i = 0; i < some.length; i++) {
                some[i] = i;
            }
            String s = "Final array: \n" + Maxima_Transfer_Float_Arrays.transferArray(process, some);
            System.out.println(s);

        } catch (MaximaTimeoutException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (process != null) {
                Maxima_Connect.terminateMaxima(process);
            }
        }

    }
}
