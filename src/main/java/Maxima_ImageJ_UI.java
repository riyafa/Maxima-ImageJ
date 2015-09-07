
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
public class Maxima_ImageJ_UI extends JFrame{

    /*the constants to be used to create the actionlisteners for the testButton
     and the clearButton
     */
    private final int TEST = 1, CLEAR = 2;
    //the borderPanel to include a border   
    private JPanel borderPanel;
    private JLabel firstLabel;
    private JTextArea inputTextArea;
    private JScrollPane inputScrollPane;
    private JTextArea resutlTextArea;
    private JScrollPane resultScrollPane;
    //a borderPanel for the buttons
    private JPanel buttonPanel;
    private JButton testButton;
    private JButton clearButton;
    //the maxima process used
    private MaximaInteractiveProcess process;

    public Maxima_ImageJ_UI() {
        this.process = null;
        init();
    }

    public void start() {
        //run the ui in a separate thread
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                borderPanel.add(firstLabel, BorderLayout.NORTH);
                inputTextArea.setLineWrap(true);
                inputTextArea.setWrapStyleWord(true);
                inputTextArea.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                borderPanel.add(inputScrollPane, BorderLayout.SOUTH);
                add(borderPanel, BorderLayout.NORTH);

                //the text area to show the results
                resutlTextArea.setLineWrap(true);
                resutlTextArea.setWrapStyleWord(true);
                resutlTextArea.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                resutlTextArea.setEditable(false);
                add(resultScrollPane, BorderLayout.SOUTH);

                //the button to test the input
                testButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                testButton.addActionListener(getActions(TEST));
                buttonPanel.add(testButton, BorderLayout.CENTER);
                //the button to clear the inputTextArea
                clearButton.addActionListener(getActions(CLEAR));
                clearButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                buttonPanel.add(clearButton, BorderLayout.AFTER_LINE_ENDS);
                add(buttonPanel, BorderLayout.CENTER);

                pack();

                //this starts maxima
                process = Maxima_Connect.startMaxima();
                //open frame if frameVisibility is true
                setVisible(Maxima_Connect.frameVisibility);
            }
        });

        //once frame is closed terminate maxima
        Maxima_Connect.terminateMaxima(process);
    }

    /**
     * This method initializes the UI components of the class
     */
    private void init() {
        borderPanel = new JPanel(new BorderLayout());
        firstLabel = new JLabel("Enter the input for Maxima-Follow each line by a semicolon:");
        inputTextArea = new JTextArea(10, 40);
        inputTextArea.setName("InputTextArea");
        inputScrollPane = new JScrollPane(inputTextArea);
        resutlTextArea = new JTextArea(10, 40);
        resutlTextArea.setName("ResutlTextArea");
        resultScrollPane = new JScrollPane(resutlTextArea);
        buttonPanel = new JPanel(new BorderLayout());
        testButton = new JButton("Test");
        testButton.setName("TestButton");
        clearButton = new JButton("Clear");
        clearButton.setName("ClearButton");
    }

    /**
     * This method was created to avoid a lengthy start method. It creates the
     * ActionListeners required by the testButton and clearButton
     *
     */
    private ActionListener getActions(int name) {
        switch (name) {
            case TEST:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String result;
                            result = inputTextArea.getText();
                            //calculates the input
                            result = Maxima_Evaluate.calculate(result, process);
                            resutlTextArea.append(result + "\n");

                        } catch (MaximaTimeoutException ex) {
                            System.err.println(ex);
                            JOptionPane.showMessageDialog(null, ex.getMessage(),
                                    "ERROR", JOptionPane.ERROR_MESSAGE);
                        } catch (java.lang.IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }catch(java.lang.StringIndexOutOfBoundsException ex){
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "No data", JOptionPane.ERROR_MESSAGE);                            
                        }
                    }
                };
            case CLEAR:
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        inputTextArea.setText("");
                        resutlTextArea.setText("");
                    }
                };
            default:
                return null;
        }
    }

}
