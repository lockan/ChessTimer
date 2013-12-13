public class ChessTimer {

	public static void main(String[] args) {
		
		long start = 1000000;
		long end = 0;
		
		System.out.println("Start: " + start);
		
		for (long i = start; i > end; i--)
		{
			start--;
		}
		
		long stop = System.currentTimeMillis();
		System.out.println("Stop: " + start);

		System.out.println("Pushed from hackbox");

	
	}

}
