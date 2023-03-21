package Assignment4;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StaffGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void clearBtn(ActionEvent e) {
		lastNameField.setText("");
		firstNameField.setText("");
		miField.setText("");
		addressField.setText("");
		cityField.setText("");
		telephoneField.setText("");
		stateField.setText("");
		emailField.setText("");
	}

	// Declare GUI components
	private List<Staff> stfList = new ArrayList<>();
	private JButton viewButton, updateButton, insertButton, clearButton;
	// Declare variables for the GUI components
	private JLabel idLabel, lastNameLabel, firstNameLabel, miLabel, addressLabel, cityLabel, stateLabel, telephoneLabel,
			emailLabel;
	private JTextField idField, lastNameField, firstNameField, miField, addressField, cityField, stateField,
			telephoneField, emailField;
	private JPanel inputPanel, buttonPanel;
	private String inputSql = "SELECT * FROM Staff ";

	public StaffGUI() {
		// Set window properties
		setTitle("Staff Table");
		setSize(800, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JdbcConnection jc = new JdbcConnection();
		jc.ininializeDB();
		stfList = jc.sqlExecute(inputSql);

		// Create the input fields and labels
		idLabel = new JLabel("ID:");
		lastNameLabel = new JLabel("Last Name:");
		firstNameLabel = new JLabel("First Name:");
		miLabel = new JLabel("Mi:");// Middle Initial:
		addressLabel = new JLabel("Address:");
		cityLabel = new JLabel("City:");
		stateLabel = new JLabel("State:");
		telephoneLabel = new JLabel("Telephone:");
		emailLabel = new JLabel("Email:");

		idField = new JTextField(10);
		lastNameField = new JTextField(15);
		firstNameField = new JTextField(15);
		miField = new JTextField(1);
		addressField = new JTextField(20);
		cityField = new JTextField(20);
		stateField = new JTextField(2);
		telephoneField = new JTextField(10);
		emailField = new JTextField(40);

		// Create the input panel with GridLayout
		inputPanel = new JPanel(new GridLayout(9, 2));
		inputPanel.add(idLabel);
		inputPanel.add(idField);
		inputPanel.add(lastNameLabel);
		inputPanel.add(lastNameField);
		inputPanel.add(firstNameLabel);
		inputPanel.add(firstNameField);
		inputPanel.add(miLabel);
		inputPanel.add(miField);
		inputPanel.add(addressLabel);
		inputPanel.add(addressField);
		inputPanel.add(cityLabel);
		inputPanel.add(cityField);
		inputPanel.add(stateLabel);
		inputPanel.add(stateField);
		inputPanel.add(telephoneLabel);
		inputPanel.add(telephoneField);
		inputPanel.add(emailLabel);
		inputPanel.add(emailField);

		// Create button panel and buttons
		viewButton = new JButton("View");
		updateButton = new JButton("Update");
		insertButton = new JButton("Insert");
		clearButton = new JButton("Clear");
		buttonPanel = new JPanel(new GridLayout(1, 4));
		buttonPanel.add(viewButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(insertButton);
		buttonPanel.add(clearButton);
		getContentPane().add(inputPanel, BorderLayout.NORTH);
		// Add button panel to frame

		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		// Add action listeners to buttons
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearBtn(e);
				if (!idField.getText().isEmpty()) {
					String id = idField.getText();
					// refresh
					stfList = jc.sqlExecute(inputSql);
					Staff stf = findUsingIterator(id);
					if (null != stf) {
						lastNameField.setText(stf.getLastName());
						firstNameField.setText(stf.getFirstName());
						miField.setText(stf.getMi());
						addressField.setText(stf.getAddress());
						cityField.setText(stf.getCity());
						telephoneField.setText(stf.getTelephone());
						stateField.setText(stf.getState());
						emailField.setText(stf.getEmail());
					} else {
						JOptionPane.showMessageDialog(null, "Staff Not Found in this id");
					}
				}
			}
		});
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idField.getText();
				if (findUsingIterator(id) == null) {
					Staff stf = new Staff();
					stf.setId(id);
					stf.setLastName(lastNameField.getText());
					stf.setFirstName(firstNameField.getText());
					stf.setMi(miField.getText());
					stf.setAddress(addressField.getText());
					stf.setCity(cityField.getText());
					stf.setTelephone(telephoneField.getText());
					stf.setState(stateField.getText());
					stf.setEmail(emailField.getText());
					jc.insertStaff(stf);
				} else {
					JOptionPane.showMessageDialog(null, "Staff id exist");
				}

			}
		});
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idField.getText();
				if (findUsingIterator(id) != null) {
					Staff stf = new Staff();
					stf.setId(id);
					stf.setLastName(lastNameField.getText());
					stf.setFirstName(firstNameField.getText());
					stf.setMi(miField.getText());
					stf.setAddress(addressField.getText());
					stf.setCity(cityField.getText());
					stf.setTelephone(telephoneField.getText());
					stf.setState(stateField.getText());
					stf.setEmail(emailField.getText());
					jc.updateStaff(stf);
				} else {
					JOptionPane.showMessageDialog(null, "Staff id do not exist");
				}

			}
		});

		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearBtn(e);
				idField.setText("");
			}
		});
	}

	private Staff findUsingIterator(String id) {
		for (Staff st : stfList) {
			if (id.equals(st.getId())) {
				return st;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		StaffGUI gui = new StaffGUI();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
	}
}
