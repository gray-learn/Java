package ProductGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FindDisplayProduct extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JRadioButton allRadioButton, keywordRadioButton, priceRangeRadioButton;
	private JTextField keywordTextField, fromTextField, toTextField;
	private ButtonGroup buttonGroup;
	private JTable productsTable;
	private DefaultTableModel tableModel;

	public FindDisplayProduct() {
		super("Find/Display Product");
		// setting
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setResizable(false);
		setVisible(true);

		// Create radio buttons and add them to a button group
		allRadioButton = new JRadioButton("All");
		keywordRadioButton = new JRadioButton("Keyword");
		priceRangeRadioButton = new JRadioButton("Price Range");
		buttonGroup = new ButtonGroup();
		buttonGroup.add(allRadioButton);
		buttonGroup.add(keywordRadioButton);
		buttonGroup.add(priceRangeRadioButton);

		// Create text fields
		keywordTextField = new JTextField();
		fromTextField = new JTextField();
		toTextField = new JTextField();

		// Create a panel for the radio buttons and text fields
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(0, 2));
		inputPanel.add(allRadioButton);
		inputPanel.add(new JLabel());
		inputPanel.add(keywordRadioButton);
		inputPanel.add(keywordTextField);
		inputPanel.add(priceRangeRadioButton);
		inputPanel.add(new JLabel());
		inputPanel.add(new JLabel("From:"));
		inputPanel.add(fromTextField);
		inputPanel.add(new JLabel("To:"));
		inputPanel.add(toTextField);

		// Create a table for displaying the products
		tableModel = new DefaultTableModel(new String[] { "ID", "Name", "Quantity", "Price", "Description" }, 0);
		productsTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(productsTable);

		// Add components to the frame
		add(inputPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.SOUTH);

		// default
		displayAllProducts();
		buttonGroup.setSelected(allRadioButton.getModel(), true);

		// Add action listeners to the radio buttons
		allRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				displayAllProducts();
			}
		});
		keywordRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				displayProductsByKeyword();
			}
		});
		priceRangeRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				displayProductsByPriceRange();
			}
		});
	}

	private void displayAllProducts() {
		List<Product> products = readProductsFromFile();
		tableModel.setRowCount(0);
		if (!products.isEmpty()) {
			for (Product product : products) {
				tableModel.addRow(setProduct(product));
			}
		}
	}

	private void displayProductsByKeyword() {
		List<Product> products = readProductsFromFile();
		tableModel.setRowCount(0);
		if (keywordTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please write the keyword in Name");
			displayAllProducts();
			buttonGroup.setSelected(allRadioButton.getModel(), true);
		} else {
			if (!products.isEmpty()) {
				for (Product product : products) {
					if (product.getName().contains(keywordTextField.getText())) {
						tableModel.addRow(setProduct(product));
					}
				}
			}
		}
	}

	private void displayProductsByPriceRange() {
		List<Product> products = readProductsFromFile();
		tableModel.setRowCount(0);

		if (fromTextField.getText().isEmpty() || toTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please write the price range");
			displayAllProducts();
			buttonGroup.setSelected(allRadioButton.getModel(), true);
		} else {
			double from = Double.parseDouble(fromTextField.getText());
			double to = Double.parseDouble(toTextField.getText());
			if (!products.isEmpty()) {
				for (Product product : products) {
					if (product.getPrice() >= from && product.getPrice() <= to) {
						tableModel.addRow(setProduct(product));
					}
				}
			}
		}
	}

	private Object[] setProduct(Product product) {
		return new Object[] { product.getId(), product.getName(), product.getQuantity(), product.getPrice(),
				product.getDesc() };
	}

	private List<Product> readProductsFromFile() {
		List<Product> products = new ArrayList<>();
		String path = "src/ProductGUI/product.txt";
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(",");
				products.add(new Product(Integer.valueOf(fields[0]), fields[1], Double.valueOf(fields[2]),
						Double.valueOf(fields[3]), fields[4]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return products;
	}

	private static class Product {
		private int id;
		private String name;
		private String desc;
		private double quantity;
		private double price;

		public Product(int id, String name, double quantity, double price, String desc) {
			this.id = id;
			this.name = name;
			this.quantity = quantity;
			this.price = price;
			this.desc = desc;
		}

		public int getId() {
			return id;
		}

		public String getDesc() {
			return desc;
		}

		public String getName() {
			return name;
		}

		public double getQuantity() {
			return quantity;
		}

		public double getPrice() {
			return price;
		}
	}

}