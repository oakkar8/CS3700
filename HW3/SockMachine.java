import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
/*
There are four threads that each make a random number of socks (1-100). 
Each sock thread produces a sock that is one of four colors,
Red, Green, Blue, Orange.  The socks are then passed to a single matching thread. 
The matching thread finds two socks that are the same color. 
It then passes the pair of socks to the washer thread.  The washer thread destroys the socks. 
In the console announce which thread is printing and what occurred: 
(Make sure your program ends.  When there is no more work to finish it should terminate) 
EXAMPLE OUTPUT
Red Sock: Produced 4 of 35 Red Socks
Green Sock: Produced 7 of 19 Green Socks
Matching Thread: Send Blue Socks to Washer. Total socks 234. Total inside queue 3
Washer Thread: Destroyed Blue socks
 */
public class SockMachine {
	private static AtomicInteger c = new AtomicInteger(0);
	private static boolean done = false;
	public static void main(String[] args) throws InterruptedException  {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(400);
		BlockingQueue<String> toBeDestroyed = new ArrayBlockingQueue<String>(400);
		SockFactory redSockFactory = new SockFactory(queue, "Red");
		SockFactory greenSockFactory = new SockFactory(queue, "Green");
		SockFactory blueSockFactory = new SockFactory(queue, "Blue");
		SockFactory orangeSockFactory = new SockFactory(queue, "Orange");
		MatchMachine matchMachine =  new MatchMachine(queue,toBeDestroyed);
		WasherMachine washerMachine = new WasherMachine(toBeDestroyed);

		Thread t1 = new Thread(redSockFactory);
		Thread t2 = new Thread(greenSockFactory);
		Thread t3 = new Thread(blueSockFactory);
		Thread t4 = new Thread(orangeSockFactory);
		Thread t5 = new Thread(matchMachine);
		Thread t6 = new Thread(washerMachine);	
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		while(t5.isAlive()) {
			if(c.get() == 0) {
				t5.interrupt();
				t6.interrupt();
			}
		}

	}
	static class MatchMachine implements Runnable {
		private BlockingQueue<String>queue;
		private BlockingQueue<String> green = new ArrayBlockingQueue<String>(2);
		private BlockingQueue<String> blue =new ArrayBlockingQueue<String>(2);
		private BlockingQueue<String> orange =new ArrayBlockingQueue<String>(2);
		private BlockingQueue<String> red =new ArrayBlockingQueue<String>(2);
		private BlockingQueue<String> toBeDestroyed =new ArrayBlockingQueue<String>(2);
		private String sock;
		public MatchMachine(BlockingQueue queue,BlockingQueue toBeDestroyed) {
			this.queue = queue;
			this.toBeDestroyed = toBeDestroyed;
		}

		@SuppressWarnings("null")
		@Override
		public void run() {

			try {
				while(!Thread.currentThread().isInterrupted())
				{

					while(!done) {
						sock = queue.take();
						if(sock.equals("Red")) {
							red.put(sock);

						}else if(sock.equals("Green")) {
							green.put(sock);

						}else if(sock.equals("Orange")) {
							orange.put(sock);

						}else if(sock.equals("Blue")) {
							blue.put(sock);

						}
						
						if(red.size()==2) {
							toBeDestroyed.put(red.take());
							System.out.println("Matching Thread: Send Red Socks to Washer. Total inside queue " + queue.size());
							red.clear();
							//						toBeDestroyed.clear();
						}else if(green.size()==2) {
							toBeDestroyed.put(green.take());
							System.out.println("Matching Thread: Send Green Socks to Washer. Total inside queue " + queue.size());
							green.clear();
						}else if(orange.size()==2) {
							toBeDestroyed.put(orange.take());
			
							System.out.println("Matching Thread: Send Orange Socks to Washer. Total inside queue " + queue.size());
							orange.clear();
						}else if(blue.size()==2) {
							toBeDestroyed.put(blue.take());
							
							System.out.println("Matching Thread: Send Blue Socks to Washer. Total inside queue " + queue.size());
							blue.clear();
						}
						c.decrementAndGet();
						if(c.get() == 0) {
							done = true;
						}
					}
				}
			} catch (InterruptedException e) {
				System.out.println("Matcher has been stop ***************************************");
			}
		}
	}
	static class WasherMachine implements Runnable{
		private BlockingQueue<String>toBeDestroyed = new ArrayBlockingQueue<String>(2);
		public WasherMachine(BlockingQueue<String> toBeDestroyed) throws InterruptedException {
			this.toBeDestroyed = toBeDestroyed;
		}

		@Override
		public void run() {
			String sock = "";
			try {
				while(!Thread.currentThread().isInterrupted())
				{
//					while(!done){
						sock = toBeDestroyed.take();
						if(sock != null) {
							System.out.println("Washer Thread: Destroyed "+sock+" socks");
						}
//					}
				}
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Washer has been stopped***************************************");
			}
		}
	}

	static class SockFactory implements Runnable { 

		private BlockingQueue<String> queue;
		private String color;
		public SockFactory(BlockingQueue queue,String color) { 
			this.color = color;
			this.queue = queue; 

		}
		@Override 
		public void run() {  

			Random rand = new Random();
			int random = (rand.nextInt(99) + 1);
			c.set(c.get() + random);
			for(int i=0; i<random; i++) {
				try {
					System.out.println(color + " Sock: " + "Produced " + (i+1) + " of " + random + " " + color + " Socks");
					queue.put(color);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} 
		} 
	} 
}
