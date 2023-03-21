package Lectture;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchStudent extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField jtfId = new JTextField(10);
	private JTextField jtfCouseId = new JTextField(10);
	private JButton jbtnShowGrade = new JButton("Show Grade");
	private Statement stmt;

	private void jbtShowGrade(ActionEvent e) {

		String id = jtfId.getText();

		String courseId = jtfCouseId.getText();

		String sql = "SELECT firstName, lastName, title, grade "

				+ "FROM STUDENT_JAVA, ENROLLMENT_JAVA, COURSE_JAVA "

				+ "WHERE STUDENT_JAVA.ssn = '" + id + "' AND ENROLLMENT_JAVA.courseId "

				+ "= '" + courseId + "' AND ENROLLMENT_JAVA.courseId = COURSE_JAVA.courseId "

				+ "AND ENROLLMENT_JAVA.ssn = STUDENT_JAVA.ssn"

		;

		try {

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				String firstName = rs.getString(1);

				String lastName = rs.getString(2);

				String title = rs.getString(3);

				String grade = rs.getString(4);

				JOptionPane.showMessageDialog(null, firstName + " " + lastName + "'s grade on course "

						+ title + " is " + grade);

			} else {

				JOptionPane.showMessageDialog(null, "Student or Course Not Found");

			}

		} catch (SQLException e1) {

			// TODO Auto-generated catch block

			e1.printStackTrace();

		}

	}

	public SearchStudent() {
		super("(Guess)Enter a letter in word ");

		// DB
//		JdbcExample.ininializeDB(inputSql);

		JPanel jPanel1 = new JPanel();
		jPanel1.add(new JLabel("Student ID"));
		jPanel1.add(jtfId);
		jPanel1.add(new JLabel("Course ID"));
		jPanel1.add(jtfCouseId);
		jPanel1.add(jbtnShowGrade);
		add(jPanel1, BorderLayout.NORTH);
		pack();
		// make sure to center
		setLocationRelativeTo(null);
		// make sure close
		setDefaultCloseOperation(3);
		setVisible(true);

		jbtnShowGrade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// invoke function
				jbtShowGrade(e);
			}

		});

	}

	public static void main(String[] args) {

		new SearchStudent();
	}

}