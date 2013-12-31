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
	private static long hundredths;
    private static long seconds;
    private static long minutes;
    private static long hours;
    private static long total_ms;
    private static long elapsed_ms = 0;
    
	// Timer vars
    private static final long UPDATE_FREQ = 1;
    
    // Time string formatting vars
    private String timeString = "";
    private DecimalFormat timeFormat = new DecimalFormat("00");
    private DecimalFormat hundredths_fmt = new DecimalFormat("000");
    
    public GameClock(int hh, int mm, int ss, int hs) {
    	//TODO: once validation added, use the setters in the constructor. 
    	/*
    	hours = hh; 
    	minutes = mm;
    	seconds = ss;
    	hundredths = hs;
    	*/
    	total_ms = 
    			hs * HS_FACTOR
    			+ ss * SS_FACTOR
    			+ mm * MM_FACTOR
    			+ hh * HH_FACTOR;
    	calcTimes();
    }

    //TODO: Add int validation to setters. 
    public void setHours(int hh) {
    	hours = hh;
    }
    
    public void setMinutes(int mm) {
    	minutes = mm;
    }
    
    public void setSeconds(int ss) {
    	seconds = ss;
    }
    
    //TODO: Revise this to generalize it. should take a long param (ms).
    // Goal is to make it flexible. Want to use for both countdown and elapsed times. 
    // Return a formatted string for the time based on the input number of ms. 
    private void calcTimes() {
    	long temp_ms = total_ms;
    	hours =  temp_ms / HH_FACTOR;
    	temp_ms = total_ms % HH_FACTOR;
    	minutes = temp_ms / MM_FACTOR;
    	temp_ms = total_ms % MM_FACTOR;
    	seconds = temp_ms / SS_FACTOR;
    	temp_ms = total_ms % SS_FACTOR;
    	hundredths = temp_ms / HS_FACTOR;
    }
    
    private void countDown() {
    	if (total_ms > 0) {
    		total_ms--;
    		elapsed_ms++;
    	}
        calcTimes();
    	updateTimeString();
        System.out.println(total_ms);
        System.out.println(elapsed_ms);
        System.out.println(timeString);
    }
    
    //TODO: Revise this when you revise calcTimes. May become redundant. 
    private void updateTimeString() {
        String tString = "" + timeFormat.format(hours)
                + ":" + timeFormat.format(minutes)
                + ":" + timeFormat.format(seconds)
                + ":" + hundredths_fmt.format(hundredths).substring(1,3);
        timeString = tString;
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
