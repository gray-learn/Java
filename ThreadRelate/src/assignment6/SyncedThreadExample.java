package assignment6;

public class SyncedThreadExample {
	static Integer sum = new Integer(0);//Integer wrapper object


	public static void main(String[] args) throws InterruptedException {
		final int NUM_THREADS = 1000;
		for (int i = 0; i < NUM_THREADS; i++) {
			new Thread(() -> {
				synchronized (sum) {
					sum += 1;
				}
			}).start();
		}
		Thread.sleep(1000);
		System.out.println("Sum with synchronization: " + sum); // 1000
	}
}
