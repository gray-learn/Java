package ca.myjava.query;

import java.sql.ResultSet;
import java.sql.Statement;

import Constant.JdbcConnection;

public class QueryTableStaticSQL {
	public static void main(String[] args) {
		try {

			JdbcConnection jc = new JdbcConnection();
			jc.ininializeDB();
			Statement stmt = jc.getStmt();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Countries "
					+ "WHERE LIFE_EXPECT_AT_BIRTH BETWEEN 70 AND 71 order by LIFE_EXPECT_AT_BIRTH");
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
