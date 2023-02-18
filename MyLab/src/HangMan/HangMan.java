package HangMan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HangMan extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static JFrame THIS_FRAME;
	private JTextField letterField;
	private JButton guessButton;
	private JLabel[] displayLabel;
	private JLabel missesLabel;
	private ArrayList<String> wordList;
	private char[] currentWord;
	private int misses;
	private ArrayList<Character> missesList;

	public HangMan() {
		super("(Guess)Enter a letter in word ");
		wordList = new ArrayList<>();
		missesList = new ArrayList<>();
		try {
			String path = "src/HangMan/hangman.txt";
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String word;
			while ((word = reader.readLine()) != null) {
				wordList.add(word);
			}
			reader.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "File not found.");
			System.exit(0);
		}
		Random rand = new Random();
		int randomIndex = rand.nextInt(wordList.size());
		currentWord = wordList.get(randomIndex).toCharArray();
		displayLabel = new JLabel[currentWord.length];
		for (int i = 0; i < displayLabel.length; i++) {
			displayLabel[i] = new JLabel("*");
		}

		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(new GridLayout(1, displayLabel.length));
		for (int i = 0; i < displayLabel.length; i++) {
			displayPanel.add(displayLabel[i]);
		}
		
		
		JPanel panelOne = new JPanel();
		JPanel msgPanel = new JPanel();
		JPanel tailPanel = new JPanel();
		msgPanel.setLayout(new GridLayout(1, displayLabel.length));
		msgPanel.add(new JLabel("(Guess)Enter a letter in word "));
		tailPanel.add(new JLabel(" > "));
		panelOne.add(msgPanel);
		panelOne.add(displayPanel);
		panelOne.add(tailPanel);
		
		
		missesLabel = new JLabel("Misses: 0");
		JPanel inputPanel = new JPanel();
		letterField = new JTextField(20);
		letterField.addActionListener(this);
		guessButton = new JButton("Guess");
		guessButton.addActionListener(this);
		inputPanel.add(new JLabel("Enter a letter:"));
		inputPanel.add(letterField);
		inputPanel.add(guessButton);
		// Layout
		add(panelOne, BorderLayout.PAGE_START);
		add(missesLabel, BorderLayout.SOUTH);
		add(inputPanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String letter = letterField.getText()//
				.toLowerCase(); // user input

		if (letter.length() != 1) {
			JOptionPane.showMessageDialog(this, "Please enter one letter only.");
			letterField.setText("");
			return;
		}
		letterField.setText("");
		boolean correctGuess = false;
		for (int i = 0; i < currentWord.length; i++) {
			if (Character.toLowerCase(currentWord[i]) == letter.charAt(0)) {
				if (displayLabel[i].getText().equalsIgnoreCase(letter)) {
					//
					JOptionPane.showMessageDialog(this, letter + " is already in the word.");
					return;
				} else {
					displayLabel[i].setText(letter);
					correctGuess = true;
				}
			}
		}
		if (!correctGuess) {
			if (missesList.contains(letter.charAt(0))) {
				JOptionPane.showMessageDialog(this, "You've already tried this letter, try another letter.");
				return;
			}
			missesList.add(letter.charAt(0));
			misses++;
			missesLabel.setText("Misses: " + misses);
		}
		boolean complete = true;
		for (int i = 0; i < displayLabel.length; i++) {
			if (displayLabel[i].getText().equals("*")) { // match--> no star
				complete = false;
				break;
			}
		}
		if (complete) {
			int option = JOptionPane.showConfirmDialog(this,
					"Congratulations! You won with " + misses + " misses. Do you want to play again?");
			if (option == JOptionPane.YES_OPTION) {
				String newWord = JOptionPane.showInputDialog(this, "Enter a new word to be added in the memory");
				if (newWord != null && !newWord.equals("")) {
					String path = "src/HangMan/hangman.txt";
					try (FileWriter writer = new FileWriter(path, true);) {
						// Use try-with-resources or close
						writer.append(newWord + "\n");
						wordList.add(newWord);
						System.out.println(wordList);
						System.out.println(currentWord);
						wordList.remove(new String(currentWord));
						System.out.println(wordList);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(this, "Error adding new word to file.");
					}
				}

				String keepGuess = "";
				do {
					keepGuess = JOptionPane.showInputDialog(this, "Do you want to guess another word? Enter y or n");
					if (keepGuess.equalsIgnoreCase("Y")) {
						resetGame();
					} else if (keepGuess.equalsIgnoreCase("N")) {
						System.exit(0);
					} else {
						JOptionPane.showMessageDialog(this, "Please Enter y or n ");
						keepGuess = "error";
					}
				} while (keepGuess.equals("error"));

			} else {
				System.exit(0);
			}
		}
	}

	private void resetGame() {
		// Pick new Word
		Random rand = new Random();
		int randomIndex = rand.nextInt(wordList.size());
		// Change new Word
		currentWord = wordList.get(randomIndex).toCharArray();
		// reset
		misses = 0;
		missesList.clear();
		missesLabel.setText("Misses: 0");
		displayLabel = new JLabel[currentWord.length];

		for (int i = 0; i < currentWord.length; i++) {
			displayLabel[i] = new JLabel("*");
		}
		THIS_FRAME.dispose();
		resetFrame();
	}

	public static void main(String[] args) {
		resetFrame();
	}

	private static void resetFrame() {
		THIS_FRAME = new HangMan();
		THIS_FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		THIS_FRAME.setSize(500, 300);
		THIS_FRAME.setBackground(Color.RED);
		THIS_FRAME.setForeground(Color.RED);
		THIS_FRAME.setVisible(true);
	}

}