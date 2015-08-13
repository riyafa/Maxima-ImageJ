
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import uk.ac.ed.ph.jacomax.MaximaInteractiveProcess;
import uk.ac.ed.ph.jacomax.MaximaTimeoutException;

/**
 *
 * @author riyafa
 */
public class Maxima_ImageJ_UI {

    //the maxima process used
    private MaximaInteractiveProcess process = null;

    public void start() {

        //run the ui in a separate thread
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                //the frame
                final JFrame frame = new JFrame("Maxima_ImageJ");

                //the panel to include a border
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

                //the text area to show the results
                final JTextArea resutlText = new JTextArea(10, 40);
                resutlText.setLineWrap(true);
                resutlText.setWrapStyleWord(true);
                resutlText.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                resutlText.setEditable(false);
                JScrollPane resultScroll = new JScrollPane(resutlText);
                frame.add(resultScroll, BorderLayout.SOUTH);

                //a panel for the buttons
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
                //this starts maxima
               
                    process = Maxima_Connect.startMaxima();
                    //open frame if frameVisibility is true
                    frame.setVisible(Maxima_Connect.frameVisibility);
                
            }
        });
        //once frame is closed terminate maxima
        Maxima_Connect.terminateMaxima(process);
    }

    
}
