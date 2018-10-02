import java.util.Random;
public class Consumer implements Runnable{
	private Drop drop;
	public Consumer(Drop drop) {
		this.drop = drop;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Random random = new Random();
		for(String message=drop.take(); !message.equals("Done"); message=drop.take()) {
			System.out.format("Message received: %s%n",message);
			try {
				Thread.sleep(random.nextInt(5000));
			}catch(InterruptedException e) {}
		}
	}

}
