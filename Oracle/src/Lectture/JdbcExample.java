package Lectture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Constant.OracleInfo;

/**
 * This program demonstrates how to make database connection with Oracle
 * database server.
 * 
 * @author www.codejava.net
 *
 */
public class JdbcExample { // JdbcOracleConnection

	public static void ininializeDB(String inputSql) {
		try {

			// registers Oracle JDBC driver - though this is no longer required
			// since JDBC 4.0, but added here for backward compatibility
			// 1.load the driver
			Class.forName(OracleInfo.DRIVER_CLASS_ORACLE);
			System.out.println("Driver is loded");
//			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn1 = null;
		Connection conn2 = null;
		Connection conn3 = null;

		try {
			// METHOD #2
			conn2 = DriverManager.getConnection(OracleInfo.URL, OracleInfo.U, OracleInfo.P);
			if (conn2 != null) {
				System.out.println("database is conneted");
			}

			// 3. statement
			Statement statement = conn2.createStatement();
			String selectSql = inputSql;// "SELECT FIRST_NAME, LAST_NAME FROM EMPLOYEES";

			try {
				ResultSet rs = statement.executeQuery(selectSql);
				if (rs.next()) {
					String firstName = rs.getString(1);
					String lastName = rs.getString(2);
					String title = rs.getString(3);
					String grade = rs.getString(4);

//					JOptionPane.showMessageDialog(null,
//							firstName + " " + lastName + " s grade on course" + title + " is " + grade);
				} else {
//					JOptionPane.showMessageDialog(null, "student or course not found");
				}

			} catch (SQLException e1) {
				e1.printStackTrace();

			}

//			while (resultSet.next()) {
//				System.out.println("FirstName: " + resultSet.getString("FIRST_NAME") + "\n" + "LastName: "
//						+ resultSet.getString("LAST_NAME") + "\n" + "");
//
//				ResultSetMetaData rsm = resultSet.getMetaData();
//
//				System.out.println("total column:=====" + rsm.getColumnCount());
//
//				// column label or column number
//			}
//					 String sql = "CREATE TABLE USER_CLASS " +
//							 "(" +
//							 " first_name VARCHAR(255), " + 
//							 " last_name VARCHAR(255), " + 
//							 " email VARCHAR(255)) " ;
//					 statement.executeUpdate(sql);

//					 String sqlInsert = "CREATE TABLE USER_CLASS " +
//							 "( first_name VARCHAR(255), " + 
//							 " last_name VARCHAR(255), " + 
//							 " email VARCHAR(255)) " ;
//					 statement.executeUpdate(sqlInsert);

			System.out.println("Created table in given database...");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn1 != null && !conn1.isClosed()) {
					conn1.close();
				}
				if (conn2 != null && !conn2.isClosed()) {
					conn2.close();
				}
				if (conn3 != null && !conn3.isClosed()) {
					conn3.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		ininializeDB("");
	}
}