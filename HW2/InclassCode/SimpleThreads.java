

public class SimpleThreads {
	static void threadMesssage(String message) {
		String threadName = Thread.currentThread().getName();
		System.out.format("%s: %s%n",threadName,message);
	}
	private static class MessageLoop implements Runnable{
		public void run() {
			String importantInfo[] = {
					"mares eat oats",
					"does eat oats",
					"listtle lambs eat ivy",
					"a kid will eat ivy too"
			};
			try {
				for(int i=0; i<importantInfo.length; i++) {
					Thread.sleep(4000);
					threadMesssage(importantInfo[i]);
				}
			}catch(InterruptedException e) {
				threadMesssage("I wasn't done");
			}
		}
	}

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		long patience = 1000;
		
		if(args.length>0) {
			try {
				patience = Long.parseLong(args[0]) * 1000;
			}catch(NumberFormatException e) {
				System.err.println("Argunment must be an integer.");
				System.exit(1);
			}
		}
		threadMesssage("Waiting for MessageLoop thread to finish");
		long startTime = System.currentTimeMillis();
		Thread t = new Thread(new MessageLoop());
		t.start();
		
		while(t.isAlive()) {
			threadMesssage("Still Waiting...");
			t.join(1000);
			if(((System.currentTimeMillis() - startTime) > patience) && t.isAlive()){
				threadMesssage("Tired of waiting!");
				t.interrupt();
				//shouldn't be long now
				// .. wait indefinitely
				t.join();
			}
		}
		threadMesssage("Finally!");
	}

}
