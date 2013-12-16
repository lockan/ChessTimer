import java.util.Timer;
import java.util.TimerTask;

public class ChessTimer {
	
	private static final long updateFreq = 1000;
	
	private long t_time = 0;
	private long t_remain = 0;
	
	public ChessTimer(long inTime) {
		t_time = inTime;
		t_remain = inTime;
	}
	
	private class CountDownTask extends TimerTask {    
		public void run() {
            t_remain--;
            System.out.println(t_remain);
        }
    }
	
	public static void main(String[] args) {
		ChessTimer ct = new ChessTimer(1000000);
		Timer countDown = new Timer("Game Clock", true);	//isDaemon - for repeated tasks. 		
		countDown.scheduleAtFixedRate(ct.new CountDownTask(), 0, updateFreq);
	}
	
	public void initTimer(long inTime) {
		t_time = inTime;
		t_remain = t_time;
		System.out.println("Timer init to " + t_time);
	}
}
