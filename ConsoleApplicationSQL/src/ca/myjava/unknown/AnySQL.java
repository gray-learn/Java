package ca.myjava.unknown;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Constant.JdbcConnection;

public class AnySQL {
	public static void main(String[] args) {
		try {
			JdbcConnection jc = new JdbcConnection();
			jc.ininializeDB();
			Scanner scanner = new Scanner(System.in);
			Statement stmt = jc.getStmt();
			do {
				System.out.print("Enter SQL command: or text 'Q' to leave \n \n");
				String sql = scanner.nextLine();
				sql = sql.toLowerCase();
				if (sql.equals("q")) {
					break;
				}
				try {

					if (stmt.execute(sql)) {
						ResultSet rs = stmt.getResultSet();
						ResultSetMetaData rsmd = rs.getMetaData();
						int columnCount = rsmd.getColumnCount();
						for (int i = 1; i <= columnCount; i++) {
							// column name
							System.out.print(rsmd.getColumnName(i) + "\t");
						}
						System.out.println();
						int cnt = 0;
						while (rs.next()) {
							for (int i = 1; i <= columnCount; i++) {
								System.out.print(rs.getString(i) + "\t");
							}
							System.out.println();
							cnt++;
						}
						if (cnt == 0) {
							System.out.println("Data row Not Found");
						}
						rs.close();
					} else {
						int rows = stmt.getUpdateCount();
						System.out.println(rows + " row(s) affected");
					}

				} catch (SQLException e) {
					System.out.println("table or view does not exist");
//					e.printStackTrace();
					continue;
				} catch (Exception e) {
					e.printStackTrace();
				}

			} while (true);
			jc.closeConn();
			System.out.println("the program stop");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
