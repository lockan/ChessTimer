import java.util.Timer;
import java.util.TimerTask;

public class ChessTimerOld {
	
	private static final long updateFreq = 1000;
	
	private long t_time = 0;
	private long t_remain = 0;
	
	public ChessTimerOld(long inTime) {
		t_time = inTime;
		t_remain = inTime;
	}
	
	private class CountDownTask extends TimerTask {    
		public void run() {
        	if (t_remain > 0) {
        		t_remain--;
        		System.out.println(t_remain);
        	}
        	else {
        		cancel();
        	}
        }
    }
	
	public static void main(String[] args) {
		ChessTimerOld ct = new ChessTimerOld(5);
		Timer countDown = new Timer("Game Clock", false);	//arg2 - bool isDaemon
		//REMINDER: if Timer is daemon, JVM will terminate main thread because no non-daemon threads exist. 
		//If need Daemon, force main thread to wait. 
		countDown.scheduleAtFixedRate(ct.new CountDownTask(), 0, updateFreq);
	}
	
	public void initTimer(long inTime) {
		t_time = inTime;
		t_remain = t_time;
		System.out.println("Timer init to " + t_time);
	}

}
