package io.javalambda;

public class RunnableExample {

	public static void main(String[] args) {
		Thread myThread = new Thread (new Runnable() {
			public void run() {
				System.out.println("printed inside runndable");
			}
		});
		
		myThread.run();
		
		Thread myLambdaThread = new Thread(()-> System.out.println("Printed insdie lambda runndable"));
		
		myLambdaThread.run();

	}

}
