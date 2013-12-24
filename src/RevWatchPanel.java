import java.awt.Font;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.text.DecimalFormat;

/** 
 * Creates a Stopwatch in a JPanel. 
 * Includes start, stop, reset buttons and a text display 
 * for the time. 
 * @author Andrew Lockhart
 * @version 1.0
 */
public class RevWatchPanel extends JPanel {

    /** serialVersionUID. */
    private static final long serialVersionUID = 5033874431736687510L;
    /** Constant - 
     * Threshold for the timer, beyond which we increment the next field. 
     * See the calcTime() method. 
     */
    private static final int TIME_THRESHOLD = 59;
    /** Similar to TIME_THRESHOLD. 
     * Only applies to counter for fractional seconds. */
    private static final int FRAC_THRESHOLD = 99;
    /** Delay for the Timer. (How frequent actionPerformed gets fired.) */
    private static final int DELAY = 10;
    /** Font size for the time display. */
    private static final int FONT_SIZE = 24;
    /** Gridspan - used in the gridbaglayout. 
     * Used to span the timer display across 3 columns. 
     */
    private static final int GRIDSPAN = 3;
    /** field that tracks elapsed hundredths of a second. */ 
    private static int hundredths;
    /** field that tracks elapsed seconds. */
    private static int seconds;
    /** field that tracks elapsed minutes. */
    private static int minutes;
    /** field that tracks elapsed hours. */
    private static int hours;
    
    /** Formatter for time display. */
    private DecimalFormat timeFormat = new DecimalFormat("00");
    /** Variable reference to the formatted time string. */
    private String timerString = "";
            
    /** Text label for the time display. */
    private JLabel timerText = new JLabel(timerString);
    /** Start Button - starts the timer. */
    private JButton startButton = new JButton("Start");
    /** Stop Button - stops the timer. */
    private JButton stopButton = new JButton("Stop");
    /** Reset button - sets the timer to zero. */
    private JButton resetButton = new JButton("Reset");
    /** ActionListener for all buttons and the time display. */
    private ButtonListener bListener = new ButtonListener();
    /** The timer instance that powers this stopwatch. */
    private Timer stopwatch = new Timer(DELAY, bListener);

    /** StopWatchPanel constructor. No required params. */
    public RevWatchPanel() {
        //Format timer text label
        timerText.setFont(new Font("Lucida Console", Font.BOLD, FONT_SIZE));
        timerText.setForeground(Color.cyan);
        // Add buttons to panel
        startButton.addActionListener(bListener);
        stopButton.addActionListener(bListener);
        resetButton.addActionListener(bListener);
        
        // Set up gridbaglayout and add components. 
        GridBagLayout gBag = new GridBagLayout();
        this.setLayout(gBag);  
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = GRIDSPAN;
        this.add(timerText, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        this.add(startButton, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        this.add(stopButton, constraints);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        this.add(resetButton, constraints);
                
        timerText.setText(updateTimerString());
    }

    /** ButtonListener definition for handling input button actions. 
     * Also drives the timer functions. */
    private class ButtonListener implements ActionListener {

        /** actionPerformed method - Controls the timer by
         * responding to button events. 
         * @param e - the actionEvent to respond to. */
        public void actionPerformed(ActionEvent e) {
            //Start/Resume Timer
            if (e.getSource() == startButton) {
                stopwatch.start();
                timerText.setText(updateTimerString());
            }
            //Stop timer
            if (e.getSource() == stopButton) {
                stopwatch.stop();
                timerText.setText(updateTimerString());
            }
            // Reset time to zero
            if (e.getSource() == resetButton) {
                resetStopWatch();
                timerText.setText(updateTimerString());
            }
            // If the timer is active update the time and UI. 
            if (stopwatch.isRunning()) {
                calcTime();
                timerText.setText(updateTimerString());
            }
        }

    }
    
    /** Increments the time every time actionPerformed is called. */
    private void calcTime() {
        // Count hundredths of a second
        // Each time we exceed 99 hundredths increment second counter. 
        if (hundredths < FRAC_THRESHOLD) {
            hundredths++;    
        } else {
            seconds++;
            hundredths = 0;
        }
        // Count minutes each time we exceed 59s.          
        if (seconds > TIME_THRESHOLD) {
            minutes++;
            seconds = 0;
        }
        // Count hours each time we exceed 59 mins.  
        if (minutes > TIME_THRESHOLD)  {
            hours++;
            minutes = 0;
        }
        System.out.println(timerString);
    }
    
    /** Resets all time counter variables to 0. */
    private void resetStopWatch() {
        hundredths = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
    }
    
    /** Updates the formatted time string. 
     * 
     * @return tString - the formatted time string. 
     */
    private String updateTimerString() {
        String tString = "" + timeFormat.format(hours)
                + ":" + timeFormat.format(minutes)
                + ":" + timeFormat.format(seconds)
                + ":" + timeFormat.format(hundredths);
        return tString;
    }
}
