package ProductGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModifyProduct extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel lblId, lblName, lblQuantity, lblPrice, lblDesc;
	private JTextField txtId, txtName, txtQuantity, txtPrice, txtDesc;
	private JButton btnFirst, btnPrevious, btnNext, btnLast, btnAdd, btnUpdate;

	private List<String> productIds;
	private List<Product> products;
	private int currentIndex = -1;
	private String thisProductId;
	private String path = "src/ProductGUI/product.txt";

	public ModifyProduct() {
		super("Add/Update Product");
		// setting
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);
		setResizable(false);

		// init data
		productIds = new ArrayList<>();
		products = readProducts();
		thisProductId = "";
		//
		lblId = new JLabel("Product ID: ");
		add(lblId);
		txtId = new JTextField(10);
		add(txtId);

		lblName = new JLabel("Product Name: ");
		add(lblName);
		txtName = new JTextField(10);
		add(txtName);

		lblQuantity = new JLabel("Quantity In Hand: ");
		add(lblQuantity);
		txtQuantity = new JTextField(10);
		add(txtQuantity);

		lblPrice = new JLabel("Price: ");
		add(lblPrice);
		txtPrice = new JTextField(10);
		add(txtPrice);

		lblDesc = new JLabel("Description: ");
		add(lblDesc);
		txtDesc = new JTextField(10);
		add(txtDesc);

		btnFirst = new JButton("First");
		add(btnFirst);
		btnFirst.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product product = getFirstProduct();
				if (product == null) {
					return;
				}
				displayProduct(product);
			}
		});

		btnPrevious = new JButton("Previous");
		add(btnPrevious);
		btnPrevious.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product product = getPreviousProduct();
				if (product == null) {
					return;
				}
				displayProduct(product);
			}
		});

		btnNext = new JButton("Next");
		add(btnNext);
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product product = getNextProduct();
				if (product == null) {
					return;
				}
				displayProduct(product);
			}
		});

		btnLast = new JButton("Last");
		add(btnLast);
		btnLast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product product = getLastProduct();
				if (product == null) {
					return;
				}
				displayProduct(product);
			}
		});

		btnAdd = new JButton("Add");
		add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(txtId.getText());
					String name = txtName.getText();
					int quantity = Integer.parseInt(txtQuantity.getText());
					double price = Double.parseDouble(txtPrice.getText());
					String desc = txtDesc.getText();
					Product product = new Product(id, name, quantity, price, desc);
					if (id <= 0 || name == null || name.isEmpty() || desc == null) {
						return;
					}

					if (quantity <= 0 || price <= 0) {
						JOptionPane.showInputDialog("quantity in hand and unit price should be number and above 0");
					}
					if (productIds.contains(String.valueOf(product.getId()))) {
						JOptionPane.showInputDialog("Product Id " + id + " is used, please change");
					} else {
						addProduct(product);
						displayProduct(product);
					}

				} catch (NumberFormatException nfe) {
					System.err.println(nfe.getMessage());
					JOptionPane.showInputDialog("quantity in hand and unit price should be number and above 0");
					System.err.println(nfe);
				} catch (Exception ex) {
					System.err.println(ex);
				}

			}
		});

		btnUpdate = new JButton("Update");
		add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				String name = txtName.getText();
				String desc = txtDesc.getText();
				int quantity = Integer.parseInt(txtQuantity.getText());
				double price = Double.parseDouble(txtPrice.getText());

				if (id <= 0 || name == null || name.isEmpty() || quantity <= 0 || price <= 0) {
					return;
				}
				if (quantity <= 0 || price <= 0) {
					JOptionPane.showInputDialog("quantity in hand and unit price should be number and above 0");
				}
				Product product = new Product(id, name, quantity, price, desc);
				if (productIds.contains(String.valueOf(product.getId())) && !thisProductId.equals(txtId.getText())) {
					JOptionPane.showInputDialog("Product Id " + id + " is used, please change");
				} else {
					updateProduct(product);
					displayProduct(product);
				}
			}
		});

		JPanel button = new JPanel();
		button.add(btnFirst);
		button.add(btnPrevious);
		button.add(btnNext);
		button.add(btnLast);
		button.add(btnAdd);
		button.add(btnUpdate);

		JPanel inputL = new JPanel();
		JPanel inputW = new JPanel();
		//
		JPanel inputId = new JPanel();
		JPanel inputName = new JPanel();
		JPanel inputQ = new JPanel();
		JPanel inputPrice = new JPanel();
		JPanel inputDesc = new JPanel();

		// fix layout
		inputId.add(lblId);
		inputId.add(txtId);
		inputName.add(lblName);
		inputName.add(txtName);
		inputQ.add(lblQuantity);
		inputQ.add(txtQuantity);
		inputPrice.add(lblPrice);
		inputPrice.add(txtPrice);
		inputDesc.add(lblDesc);
		inputDesc.add(txtDesc);
		inputL.add(inputId);
		inputL.add(inputName);
		inputL.add(inputDesc);
		inputW.add(inputQ);
		inputW.add(inputPrice);
		add(inputL, BorderLayout.WEST);
		add(inputW, BorderLayout.EAST);
		add(button, BorderLayout.PAGE_END);
	}

	private Product getFirstProduct() {
		if (products.isEmpty()) {
			return null;
		}
		currentIndex = 0;
		return products.get(currentIndex);
	}

	private Product getPreviousProduct() {
		if (products.isEmpty() || currentIndex <= 0) {
			return null;
		}
		currentIndex--;
		return products.get(currentIndex);
	}

	private Product getNextProduct() {
		if (products.isEmpty() || currentIndex >= products.size() - 1) {
			return null;
		}
		currentIndex++;
		return products.get(currentIndex);
	}

	private Product getLastProduct() {
		if (products.isEmpty()) {
			return null;
		}
		currentIndex = products.size() - 1;
		return products.get(currentIndex);
	}

	private void addProduct(Product product) {
		products.add(product);
		// write
		try (FileWriter writer = new FileWriter(path, true);) {
			// Use try-with-resources or close

			StringBuilder newWord = new StringBuilder();
			newWord.append(product.getId());
			newWord.append(",");
			newWord.append(product.getName());
			newWord.append(",");
			newWord.append(product.getPrice());
			newWord.append(",");
			newWord.append(product.getQuantity());
			newWord.append(",");
			newWord.append(product.getDesc());
			newWord.append("\n");
			writer.append(newWord.toString());
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	private void updateProduct(Product product) {
		products.set(currentIndex, product);
		try {
			List<String> lines = new ArrayList<>();
			File file = new File(path);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			String id = String.valueOf(product.getId());
			while ((line = reader.readLine()) != null) {
				if (line.contains(id)) {
					// new product
					StringBuilder newWord = new StringBuilder();
					newWord.append(product.getId());
					newWord.append(",");
					newWord.append(product.getName());
					newWord.append(",");
					newWord.append(product.getPrice());
					newWord.append(",");
					newWord.append(product.getQuantity());
					newWord.append(",");
					newWord.append(product.getDesc());
					lines.add(newWord.toString());
				} else {
					lines.add(line);
				}
			}
			reader.close();
			FileWriter writer = new FileWriter(file);
			for (String str : lines) {
				// write new one
				writer.write(str + System.lineSeparator());
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void displayProduct(Product product) {
		thisProductId = String.valueOf(product.getId());
		txtId.setText(thisProductId);
		txtName.setText(product.getName());
		txtQuantity.setText(String.valueOf(product.getQuantity()));
		txtPrice.setText(String.valueOf(product.getPrice()));
		txtDesc.setText(String.valueOf(product.getDesc()));
	}

	private List<Product> readProducts() {
		List<Product> productList = new ArrayList<>();
		productIds = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String word;
			while ((word = reader.readLine()) != null) {
				String[] p = word.split(",");
				String idStr = p[0];
				int id = Integer.parseInt(idStr);
				String name = p[1];
				double price = Double.parseDouble(p[2]);
				int quantity = Integer.parseInt(p[3]);
				String desc = p[4];
				productIds.add(idStr); // for unique
				productList.add(new Product(id, name, quantity, price, desc));
			}
			reader.close();
		} catch (IOException ex) {
			System.err.println(ex);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			JOptionPane.showMessageDialog(this, "File not found.");
			System.exit(0);
		}
		return productList;
	};
}

class Product {
	private int id;
	private String name;
	private String desc;
	private int quantity;
	private double price;

	public Product(int id, String name, int quantity, double price, String desc) {
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

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}
}