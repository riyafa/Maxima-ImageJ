
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import uk.ac.ed.ph.jacomax.JacomaxRuntimeException;
import uk.ac.ed.ph.jacomax.MaximaInteractiveProcess;
import uk.ac.ed.ph.jacomax.MaximaTimeoutException;

/**
 *
 * @author riyafa
 */
public class Maxima_ImageJ_UI {

    private MaximaInteractiveProcess process = null;

    public void start() {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                final JFrame frame = new JFrame("Maxima_ImageJ");

                JPanel panel = new JPanel(new BorderLayout());
                JLabel label = new JLabel("Enter the input for Maxima-Follow each line by a semicolon:");
                panel.add(label, BorderLayout.NORTH);
                final JTextArea textArea = new JTextArea(10, 40);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                JScrollPane scrollPane = new JScrollPane(textArea);
                panel.add(scrollPane, BorderLayout.SOUTH);
                frame.add(panel, BorderLayout.NORTH);

                final JTextArea resutlText = new JTextArea(10, 40);
                resutlText.setLineWrap(true);
                resutlText.setWrapStyleWord(true);
                resutlText.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                resutlText.setEditable(false);
                JScrollPane resultScroll = new JScrollPane(resutlText);
                frame.add(resultScroll, BorderLayout.SOUTH);

                JPanel buttonPanel = new JPanel(new BorderLayout());
                JButton testButton = new JButton("Test");
                testButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                testButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String result;
                            result = textArea.getText();
                            result = Maxima_Evaluate.calculate(result, process);
                            resutlText.append(result + "\n");

                        } catch (MaximaTimeoutException ex) {
                            System.err.println(ex);
                            JOptionPane.showMessageDialog(frame, ex.getMessage(),
                                    "ERROR", JOptionPane.ERROR_MESSAGE);
                        } catch (java.lang.IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                buttonPanel.add(testButton, BorderLayout.CENTER);
                JButton clearButton = new JButton("Clear");
                clearButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textArea.setText("");
                        resutlText.setText("");
                    }
                });
                clearButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                buttonPanel.add(clearButton, BorderLayout.AFTER_LINE_ENDS);
                frame.add(buttonPanel, BorderLayout.CENTER);

                //resultLabel.setPreferredSize(new Dimension(250,100));
                //frame.setSize(500, 400);
                frame.pack();
                try {
                    process = Maxima_Connect.startMaxima();
                    frame.setVisible(true);
                } catch (uk.ac.ed.ph.jacomax.JacomaxRuntimeException ex) {
                    System.err.println(ex.getMessage());
                    if (System.getProperty("os.name").contains("Windows")) {
                        locateMaxima(frame);
                    } else {
                        try {
                            Files.delete(new File("jacomax.properties").toPath());
                            process = Maxima_Connect.startMaxima();
                            frame.setVisible(true);

                        } catch (IOException ex1) {
                            System.err.println(ex);
                        }
                    }
                }
            }
        });
        Maxima_Connect.terminateMaxima(process);
    }

    private void locateMaxima(JFrame frame) {
        try {
            List<String> ls = WinRegistry.readStringSubKeys(WinRegistry.HKEY_LOCAL_MACHINE, "SOFTWARE\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\", WinRegistry.KEY_WOW64_32KEY);
            String key = null;
            for (String s : ls) {
                if (s.matches("Maxima.*")) {
                    key = s;
                }
            }
            if (key.contains("sbcl")) {
                JOptionPane.showMessageDialog(frame, "Currently Maxima-ImageJ" + " does not support sbcl version of Maxima", "ERROR", JOptionPane.ERROR_MESSAGE);
                frame.setVisible(false);
            } else {
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("jacomax.properties")));
                String path = WinRegistry.readString(WinRegistry.HKEY_LOCAL_MACHINE, "SOFTWARE\\Wow6432Node\\Microsoft\\Windows" + "\\CurrentVersion\\Uninstall\\" + key, "Inno Setup: App Path", WinRegistry.KEY_WOW64_32KEY);
                System.out.println(path.replace("\\", "\\\\"));
                pw.print("jacomax.maxima.path=" + path.replace("\\", "\\\\") + "\\\\bin\\\\maxima.bat");
                pw.close();
                process = Maxima_Connect.startMaxima();
                frame.setVisible(true);
            }
        } catch (IllegalArgumentException ex) {
            System.err.println(ex);
        } catch (IllegalAccessException ex) {
            System.err.println(ex);
        } catch (InvocationTargetException ex) {
            System.err.println(ex);
        } catch (NoSuchElementException ex) {
            JOptionPane.showMessageDialog(frame, "Maxima-ImageJ could not " + "detect maxima on your computer.", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
            frame.setVisible(false);
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (JacomaxRuntimeException ex) {
            JOptionPane.showMessageDialog(frame, "Maxima-ImageJ could not " + "detect maxima on your computer.", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
            frame.setVisible(false);
        }
    }
}
