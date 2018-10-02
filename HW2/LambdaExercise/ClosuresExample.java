package io.javalambda;

public class ClosuresExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 10;
		int b = 20;
		doProcess(a, (i)->System.out.println(i+b));

	}
	public static void doProcess(int i, Process p) {
		p.proces(i);
	}

}

interface Process{
	void proces(int i);
}
