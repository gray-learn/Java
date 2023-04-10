package ca.myjava.update;

import java.sql.Statement;

import Constant.JdbcConnection;

public class UpdateTable {
	public static void main(String[] args) {
		try {
			JdbcConnection jc = new JdbcConnection();
			jc.ininializeDB();

			Statement stmt = jc.getStmt();
			int rows = stmt.executeUpdate("update regions set region_name = 'Number' WHERE region_id = 900");
			
			System.out.println(rows + " row(s) updated");
			jc.closeConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
