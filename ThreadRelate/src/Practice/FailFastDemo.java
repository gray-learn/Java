package Practice;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class FailFastDemo {
	// Q3
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>();

		// Add numbers to the set every second
		Thread addThread = new Thread(() -> {
			int i = 0;

			while (true) {
				try {
					set.add(i++);
					Thread.sleep(1000);
				} catch (ConcurrentModificationException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		addThread.start();

		// Traverse the set through the iterator every second
		Thread traverseThread = new Thread(() -> {

			while (true) { // every one second
				Iterator<Integer> iterator = set.iterator();
				try {
					while (iterator.hasNext()) {
						Integer next = iterator.next();
						System.out.println("Thread2: " + next);
					}
					Thread.sleep(1000);
				} catch (ConcurrentModificationException e) {
//					the collection has been structurally modified and is no longer in a consistent state
					System.out.println(
							"the underlying set is being modified in the first thread while the set in the second thread is being traversed.");
					e.printStackTrace();
					System.exit(0);//// entire Java virtual machine to terminate.
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		traverseThread.start();

	}
}
