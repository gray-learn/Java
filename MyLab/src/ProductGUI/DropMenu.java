package ProductGUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * 
 * @name KUORUI, CHIANG
 *
 */
public class DropMenu extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int FRAME_WIDTH = 300;

	public JLabel displayLabel;
	private String type;
	private String size;

	public DropMenu() {
		setTitle("Product Main GUI");
		setSize(FRAME_WIDTH, FRAME_WIDTH);
		displayLabel = new JLabel("Product Management System");
		add(displayLabel, BorderLayout.CENTER);
		// create the menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar); // to the Frame
		menuBar.add(createFileMenu()); // JMenu(file ) add to the JMenuBar
		menuBar.add(createProductMenu());

	}

	public JMenu createFileMenu() {
		JMenu menuFile = new JMenu("File");
		menuFile.add(createFileMenuItem());
		return menuFile;
	}

	public JMenuItem createFileMenuItem() {
		JMenuItem exitItem = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}

		ActionListener listener = new MenuItemListener();
		exitItem.addActionListener(listener);

		return exitItem;
	}

	public JMenu createProductMenu() {
		JMenu menuOrder = new JMenu("Product");
		menuOrder.add(createAddUpdateSubMenu());
		menuOrder.add(createFindDisplaySubMenu());
		return menuOrder;
	}

	public JMenuItem createFindDisplaySubMenu() {
		JMenuItem item = new JMenuItem("Find/Display");
		class MenuItemListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FindDisplayProduct();
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}

	public JMenuItem createAddUpdateSubMenu() {
		JMenuItem item = new JMenuItem("Add/Update");
		class MenuItemListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ModifyProduct();
			}
		}
		ActionListener listener = new MenuItemListener();
		item.addActionListener(listener);
		return item;
	}

	public void displayOrder() {
		displayLabel.setText(" Your order is " + size + type);
	}

}
