import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class ChessTimer2 {
	
	// Time Vars
	private static Integer hundredths;
    private static int seconds;
    private static int minutes;
    private static int hours;
    
	// Timer vars
    private static final long updateFreq = 10;
    
    // Time string formatting vars
    private String timeString = "";
    private DecimalFormat timeFormat = new DecimalFormat("00");
    private DecimalFormat hundredths_fmt = new DecimalFormat("000");
    
    public ChessTimer2(int hh, int mm, int ss, int hs) {
    	//TODO: once validation added, use the setters in the constructor. 
    	hours = hh;
    	minutes = mm;
    	seconds = ss;
    	hundredths = hs;
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
    
    private void countDown() {
    	//TODO: This entire logic tree is broken. Fix it. 
    	//TODO: Change this to use a single long value that represents milliseconds? 
    	if (hundredths > 0) {
    		hundredths--;
    	}
    	
    	if ( (hundredths <= 0) && (seconds > 0) ) {
    		seconds--;
    		hundredths = 100;
    	}
    	
    	if ( (seconds <= 0 ) && (minutes > 0) ){
    		minutes--;
    		seconds = 60;
    	}
    	
    	if ( (minutes <= 0) && (hours > 0) ){
    		hours--;
    		minutes = 60;
    	}
    	if (hours <= 0) {
    		hours = 0;
    	}
    	       
        updateTimeString();
        System.out.println(timeString);
    }
    
    private void updateTimeString() {
        String tString = "" + timeFormat.format(hours)
                + ":" + timeFormat.format(minutes)
                + ":" + timeFormat.format(seconds)
                + ":" + hundredths_fmt.format(hundredths).substring(1,3);
        timeString = tString;
    }
    
    // 
    private class CountDownTask extends TimerTask {
    	public void run() {
    		if ( (hours > 0) || (minutes > 0) || (seconds > 0) || (hundredths > 0) ) {
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
		ChessTimer2 cTimer = new ChessTimer2(1, 0, 0, 0);
		Timer timer = new Timer("Chess Timer", false);
		timer.scheduleAtFixedRate(cTimer.new CountDownTask(), 0, updateFreq);
	}

}
