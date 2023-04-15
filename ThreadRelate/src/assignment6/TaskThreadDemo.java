package assignment6;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TaskThreadDemo extends Thread {

	private Integer sum;
	private static JTextArea area = new JTextArea();
	private static JFrame frame; // create a JFrame to hold the JTextArea

	public TaskThreadDemo(Integer sum) {
		this.sum = sum;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			// Increment the shared integer variable sum 1000 times in a synchronized block
			synchronized (sum) {
				sum++;
			}
		}
	}

	public static void main(String[] args) {

		// Create tasks to print characters and numbers

		Runnable printA = new PrintChar('a', 100);
		Runnable printB = new PrintChar('b', 100);
		Runnable print100 = new PrintNum(100);

		// Create threads for each task

		Thread thread1 = new Thread(printB);
		Thread thread2 = new Thread(print100);
		Thread thread3 = new Thread(printA);

		// Set priorities for the threads

		thread3.setPriority(Thread.MIN_PRIORITY);
		thread1.setPriority(Thread.MAX_PRIORITY);

		// Create the JTextArea to display output

		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setEditable(false);
		area.setColumns(50);
		area.setRows(20);
		Font font = area.getFont();
		area.setFont(new Font(font.getName(), font.getStyle(), font.getSize() + 5));

		// Create a JScrollPane to hold the JTextArea

		JScrollPane scrollPane = new JScrollPane(area);

		// Create a JPanel to hold the JScrollPane

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(scrollPane, BorderLayout.CENTER);

		// Create a JFrame to hold the JPanel and JTextArea

		frame = new JFrame("Concurrent Output");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// Start threads
		thread1.start();
		thread2.start();
		thread3.start();
		// Yield the current thread to allow other threads to run

		Thread.yield();
	}

	private static class PrintChar implements Runnable {
		private char charToPrint;// the char to print
		private int times;// the times to repeat
		// constructor

		public PrintChar(char c, int t) {
			charToPrint = c;
			times = t;
		}

		@Override
		public void run() {
			for (int i = 0; i < times; i++) {
				synchronized (area) {
					area.append(" " + charToPrint + " ");
				}
			}
		}

	}

	private static class PrintNum implements Runnable {
		private int lastNum;

		public PrintNum(int n) {
			lastNum = n;
		}

		@Override
		public void run() {
			for (int i = 1; i <= lastNum; i++) {
				synchronized (area) {
					area.append(" " + i + " ");
				}
			}
		}

	}
}
