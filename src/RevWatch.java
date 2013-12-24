import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JFrame;

/**
 * <p>A simple stopwatch application. 
 * Has start, stop, and reset functions. </p>
 *
 * @author Andrew Lockhart
 * @version 1.0
 */
public class RevWatch extends JFrame {

    /** serialVersionUID. */
    private static final long serialVersionUID = 2037586777955185189L;
    
    /** Constant - width of the panel / frame. */
    private static final int PANELWIDTH = 250;
    /** Constant - height of the panel / frame. */
    private static final int PANELHEIGHT = 100;

    /** StopWatch Constructor.
     * Creates a JFrame containing a StopWatchPanel. 
     * Inits a number of the frame properties
     * (size, location, resizeably property,etc.). 
     */
    public RevWatch() {
        super("RevWatch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(PANELWIDTH, PANELHEIGHT); 
        RevWatchPanel swPanel = new RevWatchPanel();
        swPanel.setPreferredSize(new Dimension(PANELWIDTH, PANELHEIGHT));
        setMinimumSize(new Dimension(PANELWIDTH, PANELHEIGHT));
        setMaximumSize(new Dimension(PANELWIDTH, PANELHEIGHT));
        setResizable(false);
        setLocationRelativeTo(null); //Centers app on screen
        swPanel.setBackground(Color.black);
        setContentPane(swPanel);
        setVisible(true);
    }
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        new RevWatch();

    }

};
