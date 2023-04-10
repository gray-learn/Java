package ca.myjava.update;

import java.sql.SQLException;
import java.sql.Statement;

import Constant.JdbcConnection;

public class DeleteTable {

	public static void main(String[] args) throws SQLException {

		try {

			JdbcConnection jc = new JdbcConnection();
			jc.ininializeDB();
			String sql = "DELETE FROM regions " + "WHERE region_id = 900";

			Statement stmt = jc.getStmt();
			int rowsInserted = stmt.executeUpdate(sql);
			if (rowsInserted > 0) {
				System.out.println(rowsInserted + " row(s) deleted");
			}
			jc.closeConn();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
