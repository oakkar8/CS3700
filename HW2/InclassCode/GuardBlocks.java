//
//public class GuardBlocks {
//	boolean joy = false;
//	public void guardedJoy() {
//		while(!joy) {
//			System.out.println("Joy has been achieved");
//		}
//	}
//	public synchronized void guardedJoy() {
//		whlie (!joy) {
//			try {
//				wait();
//				
//			}catch(InterruptedException e) {
//				
//			}
//			System.out.println("Joy and effiency has been achieved");
//	}
//	public synchronized notifyJoy() {
//		joy = true;
//		notifyAll();
//	}
//}
