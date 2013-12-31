import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class GameClock {
	
	//Constants
	private static int HH_FACTOR = 60 * 60 * 1000;
	private static int MM_FACTOR = 60 * 1000;
	private static int SS_FACTOR = 1000;
	private static int HS_FACTOR = 10;
	
	// Time Vars
    private static long total_ms;
    private static long elapsed_ms = 0;
    
	// Timer vars
    private static final long UPDATE_FREQ = 1;
    
    // Time string formatting vars
    private DecimalFormat timeFormat = new DecimalFormat("00");
    private DecimalFormat hundredths_fmt = new DecimalFormat("000");
    
    public GameClock(int hh, int mm, int ss, int hs) {
    	//TODO: Add validation to the constructor. values must be legal positive ints. 
    	total_ms = 
    			hs * HS_FACTOR
    			+ ss * SS_FACTOR
    			+ mm * MM_FACTOR
    			+ hh * HH_FACTOR;
    	getFormattedTimeString(total_ms);
    }
    
    private String getFormattedTimeString(long in_ms) {
    	long temp_ms = in_ms;
    	long temp_hours =  temp_ms / HH_FACTOR;
    	temp_ms = in_ms % HH_FACTOR;
    	long temp_minutes = temp_ms / MM_FACTOR;
    	temp_ms = in_ms % MM_FACTOR;
    	long temp_seconds = temp_ms / SS_FACTOR;
    	temp_ms = in_ms % SS_FACTOR;
    	long temp_hundredths = temp_ms / HS_FACTOR;
    	
    	String tString = "" + timeFormat.format(temp_hours)
                + ":" + timeFormat.format(temp_minutes)
                + ":" + timeFormat.format(temp_seconds)
                + ":" + hundredths_fmt.format(temp_hundredths).substring(1,3);
    	return tString;
    }
    
    private void countDown() {
    	if (total_ms > 0) {
    		total_ms--;
    		elapsed_ms++;
    	}
        System.out.println(total_ms);
        System.out.println(elapsed_ms);
        System.out.println("T: " + getFormattedTimeString(total_ms));
        System.out.println("E: " + getFormattedTimeString(elapsed_ms));
    }
     
    private class CountDownTask extends TimerTask {
    	public void run() {
    		if (total_ms > 0) {
    			countDown();
    		} 
    		else 
    		{
    			cancel();
    			System.out.println("Timer Ended");
    		}
    	}
    }
	
	public static void main(String[] args) {
		GameClock cTimer = new GameClock(0, 0, 30, 0);
		Timer timer = new Timer("ClockMachine", false);
		timer.scheduleAtFixedRate(cTimer.new CountDownTask(), 0, UPDATE_FREQ);
	}

}
