package io.javalambda;

import java.awt.Point;

public class ThisReferenceExample {

	public void doProcess(int i,Process p) {
		p.proces(i);
	}
	public void execute() {
		doProcess(10, i->{
			System.out.println("Value of i is "+ i);
			System.out.println(this);
		});
	}
	public static void main(String[] args) {
		ThisReferenceExample thisReferenceExample = new ThisReferenceExample();
		
//		thisReferenceExample.doProcess(10, i->{
//			System.out.println("Value of i is "+i);
//			System.out.println(this);
//		});
		thisReferenceExample.execute();
	}
	public String toString() {
		return "This is the main thisreferenceExample class instance";
	}
}
