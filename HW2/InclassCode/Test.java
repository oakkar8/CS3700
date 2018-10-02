
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SynchronizedRGB color = new SynchronizedRGB(0, 0, 0, "Ptiche black");
		int myColorInt = color.getRGB();      //Statement 1 

		String myColorName = color.getName(); //Statement 2 



		myColorInt = color.getRGB();      //T1  -- Statement 1 

		color.set(0, 0, 0, "Black");                    //T2 -- Runs the method before T1 statement 2 

		myColorName = color.getName(); // T
	}

}
