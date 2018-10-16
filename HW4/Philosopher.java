import java.util.Random;

public class Philosopher implements Runnable {

	private Object leftFork;
	private Object rightFork;

	public Philosopher(Object leftFork, Object rightFork) {
		this.leftFork = leftFork;
		this.rightFork = rightFork;
	}

	@Override
	public void run() {
		try {
			while (true) {

				// thinking
				doAction(": Thinking");
				synchronized (leftFork) {
					doAction(": Picked up left fork");
					synchronized (rightFork) {
						// eating
						doAction(": Picked up right fork - eating"); 
						doAction(": Put down right fork");
					}
					// Back to thinking
					doAction(": Put down left fork. Back to thinking");
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		}
	}
	private void doAction(String action) throws InterruptedException {
		Random rand = new Random();
		long time;
		
		if(action.contains("Thinking")) {
			time =rand.nextInt(4)+1;
			System.out.println(Thread.currentThread().getName() + " " + action + " for " + time + " second");
			Thread.sleep(time * 1000);
		}else if (action.contains("eating")) {
			time = rand.nextInt(9)+1;
			System.out.println(Thread.currentThread().getName() + " " + action + " for " + time + " second");
			Thread.sleep(time * 1000);
		}
	}
}
