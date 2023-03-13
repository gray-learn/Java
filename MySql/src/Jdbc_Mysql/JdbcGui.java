package Jdbc_Mysql;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JdbcGui extends JFrame {

	public JdbcGui() throws SQLException {
		JdbcExample jdbcExapmle = new JdbcExample();
		setTitle("JDBC Table Demo");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Set up the table model
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Age");

		List<Person> rs = jdbcExapmle.getDatabaseSrc();

		for (Person p : rs) {
			String id = p.getId();
			String name = p.getName();
			String age = p.getAge();
			model.addRow(new Object[] { id, name, age });
		}

		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane);

	}

	public static void main(String[] args) {

		JFrame frame;
		try {
			frame = new JdbcGui();
			frame.setDefaultCloseOperation(3);
			frame.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}