package ca.myjava.update;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import Constant.JdbcConnection;

public class InsertTable {

	public static void main(String[] args) {
		try {

			JdbcConnection jc = new JdbcConnection();
			jc.ininializeDB();

			try (PreparedStatement pstmt = jc.getPStmt("INSERT INTO regions (region_id, region_name) VALUES (?, ?)")) {

				pstmt.setInt(1, 900);
				pstmt.setString(2, "Humber");
				int rowsInserted = pstmt.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println(rowsInserted + " row(s) inserted");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			jc.closeConn();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
