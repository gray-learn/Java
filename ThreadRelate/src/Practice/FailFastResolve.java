package Practice;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class FailFastResolve extends Thread {
	// Q4
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>();
		Thread addThread = new Thread(() -> {
			int i = 0;

			while (true) {
				try {
					synchronized (set) {// using a synchronized block to prevent concurrent modifications by the second
						set.add(i++);
					}
					Thread.sleep(1000);
				} catch (ConcurrentModificationException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});

		Thread traverseThread = new Thread(() -> {
			Iterator<Integer> iterator;
			while (true) {

				try {
					synchronized (set) {// traverses the set using an iterator obtained within a synchronized block.
						iterator = set.iterator();
						System.out.println("Thread2: ");
						while (iterator.hasNext()) {
							Integer next = iterator.next();
							System.out.print(next + ", ");
						}
						System.out.println();
					}
					Thread.sleep(1000);
				} catch (ConcurrentModificationException e) {
					System.out.println(
							"the underlying set is being modified in the first thread while the set in the second thread is being traversed.");
					e.printStackTrace();
					System.exit(0);// entire Java virtual machine to terminate.

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});

		addThread.start();
		traverseThread.start();
	}
}
