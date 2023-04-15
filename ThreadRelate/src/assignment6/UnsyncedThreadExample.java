package assignment6;

import java.util.ArrayList;
import java.util.List;

public class UnsyncedThreadExample {
	static Integer sum = new Integer(0);// Integer wrapper object

	public static void main(String[] args) {
		final int NUM_THREADS = 1000;
		List<Thread> threads = new ArrayList<Thread>();

		for (int i = 0; i < NUM_THREADS; i++) {
			Thread t = new Thread(new Runnable() {
				public void run() {
					sum += 1;
				}
			});
			threads.add(t);
			t.start();
		}

		// Wait for all threads to complete
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Sum without synchronization: " + sum); // could 1000
	}
}
