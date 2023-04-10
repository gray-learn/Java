package ca.myjava.query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Constant.JdbcConnection;

public class QueryTablePreparedStmt {
	public static void main(String[] args) {
		try {

			JdbcConnection jc = new JdbcConnection();
			jc.ininializeDB();
			PreparedStatement pstmt = jc.getConn()
					.prepareStatement("SELECT * FROM Countries WHERE LIFE_EXPECT_AT_BIRTH BETWEEN ? AND ?");
			pstmt.setDouble(1, 70);
			pstmt.setDouble(2, 71);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("Country_Name") + " - " + rs.getString("LIFE_EXPECT_AT_BIRTH"));
			}
			rs.close();
			jc.closeConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
