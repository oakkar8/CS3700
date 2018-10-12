/*
 * Assume a system with N elected official threads and one rank thread. 
 * Each elected official thread has an identifying name and an integer rank value,
 * where -∞ is the lowest rank and +∞ is the highest rank, use Random.  
 * Threads do not previously know the rank value of other threads. 
 * As threads are being created they should print out there name, 
 * rank and who they think is the leader, 
 * initially they think they are the leader, 
 * and notify the rank thread that a new elected official has been created, using an interrupt.
 * When the rank thread is interrupted it will check the ranking of all the threads 
 * at the time and will only notify all threads if there is a new leader using an interrupt. 
 * The thread with the largest rank value is to be selected as the leader. 
 * You can use any algorithm that selects one and only one thread as the leader. 
 */

import java.util.Random;

public class RankingThread {

	public static void main(String[] args) throws InterruptedException {
		int n = 5;
		Thread[] t = new Thread[n];
		ThreadOfficial[] to = new  ThreadOfficial[n];
		Random rand = new Random();
		Integer currentLeader = -1;
		for(int i=0; i<n; i++) {
			to[i] = new ThreadOfficial();
			t[i] = new Thread(to[i]);	
			t[i].start();
//			if(currentLeader < t[i].)
			Thread.sleep(2000);
		}
	}

	static class ThreadOfficial implements Runnable {
		int rank;
		String leader;
		Random rand = new Random();

		public ThreadOfficial() {
			this.leader = "self";
			this.rank = rand.nextInt();
		}
		public Integer getRank() {
			return (int) rank;
		}
		public void setLeader(String leader) {
			this.leader = leader;
		}

		@Override
		public void run() {
			System.out.println("MY name: " + Thread.currentThread().getName());
			System.out.println("Rank : " + this.rank);
			System.out.println("leader is: " + this.leader);
//			while(true) {
//
//				if (Thread.currentThread().isInterrupted()){
//
//				}
//
//			}
		}
	}
}


