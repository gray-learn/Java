package presentation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.Person;
import data.RandomIO;

/**
 * 
 * @name KUORUI, CHIANG
 *
 */

public class PersonGUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField recordNumberField, firstNameField, lastNameField, ageField, phoneField;
	private JButton addButton, findButton;
	private String filePath = "people.dat";

	public PersonGUI() {
		super("Random File Processing");

		// create components
		JLabel recordNumberLabel = new JLabel("Record#:");
		JLabel firstNameLabel = new JLabel("First Name:");
		JLabel lastNameLabel = new JLabel("Last Name:");
		JLabel ageLabel = new JLabel("Age:");
		JLabel phoneLabel = new JLabel("Phone:");

		recordNumberField = new JTextField(10);
		firstNameField = new JTextField(20);
		lastNameField = new JTextField(25);
		ageField = new JTextField(3);
		phoneField = new JTextField(10);

		addButton = new JButton("Add");
		findButton = new JButton("Find");

		// add components to layout
		JPanel inputPanel = new JPanel(new GridLayout(5, 2));
		inputPanel.add(recordNumberLabel);
		inputPanel.add(recordNumberField);
		inputPanel.add(firstNameLabel);
		inputPanel.add(firstNameField);
		inputPanel.add(lastNameLabel);
		inputPanel.add(lastNameField);
		inputPanel.add(ageLabel);
		inputPanel.add(ageField);
		inputPanel.add(phoneLabel);
		inputPanel.add(phoneField);

		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(addButton);
		buttonPanel.add(findButton);

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(inputPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		setContentPane(mainPanel);
		pack();

		// add action listeners to buttons
		addButton.addActionListener(this);
		findButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) {
		try {
			if (event.getSource() == addButton) {
				// Validate
				String recordNum = recordNumberField.getText();
				String firstName = firstNameField.getText();
				String lastName = lastNameField.getText();
				int age = Integer.parseInt(ageField.getText());
				String phone = phoneField.getText();
				if (recordNum.length() > RandomIO.RECORD_NUMBER_SIZE || //
						firstName.length() > RandomIO.FIRST_NAME_SIZE || //
						lastName.length() > RandomIO.LAST_NAME_SIZE || //
						phone.length() > RandomIO.PHONE_SIZE || //
						ageField.getText().length() > RandomIO.AGE_SIZE

				) {
					throw new WrongInputException("The input need to follow the rule: \n"
							+ "First name, string type, max size 20 characters- \n"
							+ "Last name, string type, max size 25 characters- \n"
							+ "Phone, string type, max size 10 characters- \n" + "Age, int type");
				}
				// add new person to file
				Person person = new Person(recordNum, firstName, lastName, phone, age);
				RandomIO.addPersonToFile(person, filePath);
				JOptionPane.showMessageDialog(this, "Person added to file.");
			} else if (event.getSource() == findButton) {
				// Validate
				String recordNum = recordNumberField.getText();
				Pattern digitPattern = Pattern.compile("[+-]?[0-9][0-9]*");
				if (recordNum.length() > RandomIO.RECORD_NUMBER_SIZE || //
						!digitPattern.matcher(recordNum).matches()) {
					throw new WrongInputException("The input need to follow the rule: \n"
							+ "record Number should be number, and do not go over 4 degit");
				}
				// find person by record number and display details
				File fileSrc = new File(filePath);
				if (fileSrc.createNewFile()) {
					throw new WrongInputException("Can not find this person by recordNumber");
				} else {
					Person person = RandomIO.findPersonByRecordNumber(recordNum, filePath);
					if (null != person) {
						firstNameField.setText(person.getFirstName());
						lastNameField.setText(person.getLastName());
						ageField.setText(Integer.toString(person.getAge()));
						phoneField.setText(person.getPhone());
					} else {
						throw new WrongInputException("Can not find this person by recordNumber");
					}
				}

			}
		} catch (Exception e) {
			firstNameField.setText("");
			lastNameField.setText("");
			ageField.setText("");
			phoneField.setText("");
			e.printStackTrace(); // debug
			JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		PersonGUI gui = new PersonGUI();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
	}

	class WrongInputException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		WrongInputException(String s) {
			super(s);
		}
	}
}
