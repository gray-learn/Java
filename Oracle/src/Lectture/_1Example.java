package Lectture;

import javax.swing.*;

import Constant.OracleInfo;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

public class _1Example extends JFrame {

	private JTextField jtfId = new JTextField(10);

	private JTextField jtfCourseId = new JTextField(10);

	private JButton jbtShowGrade = new JButton("Show Grade");

	private Statement stmt;

	public _1Example() {

// TO DO: DB Connection

		initializeDB();

		JPanel jPanel1 = new JPanel();

		jPanel1.add(new JLabel("Student ID"));

		jPanel1.add(jtfId);

		jPanel1.add(new JLabel("Course ID"));

		jPanel1.add(jtfCourseId);

		jPanel1.add(jbtShowGrade);

		add(jPanel1, BorderLayout.NORTH);

		pack();

		setLocationRelativeTo(null);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setVisible(true);

		jbtShowGrade.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				jbtShowGrade(e);

			}

		});

	}

	private void initializeDB() {

		try {

//			Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName(OracleInfo.DRIVER_CLASS_ORACLE);

			System.out.println("Driver loaded successfully!");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}

		Connection conn;

		try {

			conn = DriverManager.getConnection(OracleInfo.URL, OracleInfo.U, OracleInfo.P);

			System.out.println("Connection to DB is established successfully!");

			stmt = conn.createStatement();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	private void jbtShowGrade(ActionEvent e) {

		String id = jtfId.getText();

		String courseId = jtfCourseId.getText();

		String sql = "SELECT firstName, lastName, title, grade "

				+ "FROM STUDENT_JAVA, ENROLLMENT_JAVA, COURSE_JAVA "

				+ "WHERE STUDENT_JAVA.ssn = '" + id + "' AND ENROLLMENT_JAVA.courseId "

				+ "= '" + courseId + "' AND ENROLLMENT_JAVA.courseId = COURSE_JAVA.courseId "

				+ "AND ENROLLMENT_JAVA.ssn = STUDENT_JAVA.ssn"

//INSERT INTO ENROLLMENT_JAVA (GRADE, COURSEID, SSN)    
//VALUES (85,01,100);
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

	public static void main(String[] args) {

		new _1Example();

	}

}