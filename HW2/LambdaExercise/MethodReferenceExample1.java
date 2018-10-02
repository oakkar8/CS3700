package io.javalambda;

public class MethodReferenceExample1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t =  new Thread(MethodReferenceExample1::printMessage);
		t.start();
	}
	public static void printMessage() {
		System.out.println("Hello");
	}
}
