import java.text.DecimalFormat;
import java.util.Timer;

public class ChessTimer2 {
	
	// Time Vars
	private static int hundredths;
    private static int seconds;
    private static int minutes;
    private static int hours; 
	
    // Time string formatting vars
    private String timeString = "";
    private DecimalFormat timeFormat = new DecimalFormat("00");
    
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
    	hundredths--;
    	if (hundredths <= 0) {
    		seconds--;
    		hundredths = 100;
    	}
    	if (seconds <= 0 ) {
    		minutes--;
    		seconds = 60;
    	}
    	if (minutes <=0) {
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
                + ":" + timeFormat.format(hundredths);
        timeString = tString;
    }
	

	public static void main(String[] args) {
		ChessTimer2 clock = new ChessTimer2(1, 59, 33, 87);
		int i = 0;
		while ( i >= 0) {
			i++;
			if (i % 60 == 0) {
				clock.countDown();
			}
		}
	}

}
