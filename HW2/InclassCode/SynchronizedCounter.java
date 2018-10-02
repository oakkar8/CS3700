
public class SynchronizedCounter {
	private int c =0;
	public synchronized void increment() {
		c++;
	}
	public synchronized void decrement() {
		c--;
	}
	public synchronized int value() {
		return c;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SynchronizedCounter counter = new SynchronizedCounter();
		
		for(int i=0; i<10; i++) {
			counter.increment();
			counter.decrement();
			counter.increment();
		}
		
		System.out.println(counter.value());
	}

}
