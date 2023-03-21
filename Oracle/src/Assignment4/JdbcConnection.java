package Assignment4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Constant.OracleInfo;

public class JdbcConnection { // JdbcOracleConnection

	private Statement stmt;
	private Connection conn = null;

	public void ininializeDB() {
		try {

			Class.forName(OracleInfo.DRIVER_CLASS_ORACLE);
			System.out.println("Driver is loded");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			// METHOD #2
			conn = DriverManager.getConnection(OracleInfo.URL, OracleInfo.U, OracleInfo.P);
			if (conn != null) {
				System.out.println("database is conneted");
			}

			// 3. statement
			stmt = conn.createStatement();

			System.out.println("Created table in given database...");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertStaff(Staff stf) {
		String sql = "INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone, email) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, stf.getId()); // id
			statement.setString(2, stf.getLastName()); // lastName
			statement.setString(3, stf.getFirstName()); // firstName
			statement.setString(4, stf.getMi()); // mi
			statement.setString(5, stf.getAddress()); // address
			statement.setString(6, stf.getCity()); // city
			statement.setString(7, stf.getState()); // state
			statement.setString(8, stf.getTelephone()); // telephone
			statement.setString(9, stf.getEmail()); // email

			String queryString = statement.toString();
			System.out.println(queryString);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(null, "A new staff member has been added.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateStaff(Staff stf) {

		String sql = "UPDATE Staff SET lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ?, email = ? "
				+ "WHERE id = ?";

		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, stf.getLastName()); // lastName
			statement.setString(2, stf.getFirstName()); // firstName
			statement.setString(3, stf.getMi()); // mi
			statement.setString(4, stf.getAddress()); // address
			statement.setString(5, stf.getCity()); // city
			statement.setString(6, stf.getState()); // state
			statement.setString(7, stf.getTelephone()); // telephone
			statement.setString(8, stf.getEmail()); // email
			statement.setString(9, stf.getId()); // id

			String queryString = statement.toString();
			System.out.println(queryString);
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				JOptionPane.showMessageDialog(null, "A staff member has been updated.");
			} else {
				JOptionPane.showMessageDialog(null, "No staff member found with the given ID.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Staff> sqlExecute(String sql) {

		List<Staff> stfList = new ArrayList<>();
		try {
			System.out.println("Created table in given database...");
			ResultSet rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next()) {// have rows
				String id = rs.getString(1);
				String lastName = rs.getString(2);
				String firstName = rs.getString(3);
				String mi = rs.getString(4);
				String address = rs.getString(5);
				String city = rs.getString(6);
				String state = rs.getString(7);
				String telephone = rs.getString(8);
				String email = rs.getString(9);
				Staff stf = new Staff();
				stf.setId(id);
				stf.setAddress(address);
				stf.setLastName(lastName);
				stf.setFirstName(firstName);
				stf.setMi(mi);
				stf.setCity(city);
				stf.setState(state);
				stf.setTelephone(telephone);
				stf.setEmail(email);
				stfList.add(stf);
				i++;
			}

			if (i == 0) {
				JOptionPane.showMessageDialog(null, "Staff Not Found");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();

		}

		return stfList;

	}

	public static void main(String[] args) {
		// testing
		JdbcConnection jc = new JdbcConnection();
		jc.ininializeDB();
		jc.sqlExecute("");
	}
}