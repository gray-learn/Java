package Jdbc_Mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcExample {

	public List<Person> getDatabaseSrc() {
		List<Person> personList = new ArrayList<>();
		ResultSet resultSet = null;
		Connection conn1 = null;
		Connection conn2 = null;
		Connection conn3 = null;

		try {
			// registers Oracle JDBC driver - though this is no longer required
			// since JDBC 4.0, but added here for backward compatibility
			// my sql
			Class.forName("com.mysql.cj.jdbc.Driver");
//			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			System.out.println("driver is loaded");

			// METHOD #2
			String dbURL2 = "jdbc:mysql://localhost/ITC5201";
			String username = "root";
			String password = "grayMysql";
			conn2 = DriverManager.getConnection(dbURL2, username, password);
			if (conn2 != null) {
				System.out.println("database is conneted");
			}

			// 3. statement
			Statement statement = conn2.createStatement();

			String selectSql = "SELECT id, name , age  FROM emp";
			resultSet = statement.executeQuery(selectSql);

			while (resultSet.next()) {
				System.out.println("id: " + resultSet.getString("id") + "\n" + "name: " + resultSet.getString("name")
						+ "\n" + "age: " + resultSet.getString("age") + "\n" + "");

				Person p = new Person();
				p.setId(resultSet.getString("id"));
				p.setName(resultSet.getString("name"));
				p.setAge(resultSet.getString("age"));
				personList.add(p);
				// column label or column number
			}

//			// METHOD #3
//			String dbURL3 = "jdbc:oracle:oci:@ProductDB";
//			Properties properties = new Properties();
//			properties.put("user", "tiger");
//			properties.put("password", "scott");
//			properties.put("defaultRowPrefetch", "20");
//			conn3 = DriverManager.getConnection(dbURL3, properties);
//
//			if (conn3 != null) {
//				System.out.println("Connected with connection #3");
//			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
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

		return personList;
	}

	public JdbcExample() throws SQLException {
		List<Person> resultSet = this.getDatabaseSrc();

	}

	public static void main(String[] args) {

		try {
			JdbcExample a = new JdbcExample();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}