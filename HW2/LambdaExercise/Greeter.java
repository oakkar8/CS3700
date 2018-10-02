package io.javalambda;

public class Greeter {
	public void greet(Greeting greeting) {
		greeting.perform();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Greeter greeter = new Greeter();
		Greeting helloWorldGreeting = new HelloWorldGreeting();
		
		Greeting lambdaGreeting = () -> System.out.println("Hello World from lambda!");
		Greeting innerClassGreeting = new Greeting() {
			public void perform() {
				System.out.println("Hello WOrld from inner class");
			}
		};
		
		greeter.greet(lambdaGreeting);
		greeter.greet(innerClassGreeting);
		

	}

}


interface MyAdd{
	int add(int a, int b);
}