package Constant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnection {

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
			conn = DriverManager.getConnection(OracleInfo.URL, OracleInfo.U, OracleInfo.P);
			if (conn != null) {
				System.out.println("database is conneted");
			}

			stmt = conn.createStatement();

			System.out.println("Created table in given database...");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Statement getStmt() {
		return stmt;
	}

	public PreparedStatement getPStmt(String sql) throws SQLException {
		return this.getConn().prepareStatement(sql);

	}

	public Connection getConn() {
		return conn;
	}

	public void closeConn() throws SQLException {
		this.stmt.close();
		this.conn.close();
	}

}
