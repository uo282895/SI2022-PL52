package giis.demo.tkrun;



import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InscriptionsView {

	private JFrame frame;
	private JTextField nameField;
	private JTextField surnamesField;
	private JTextField dniField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTable table;
	private JTable description;
	private JButton viewCourses;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InscriptionsView window = new InscriptionsView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InscriptionsView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setName("");
		frame.setBounds(100, 100, 680, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel csLabel = new JLabel("Course selection:");
		csLabel.setSize(new Dimension(10, 10));
		csLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		csLabel.setBounds(new Rectangle(56, 10, 130, 42));
		csLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(csLabel);
		
		JLabel infoTitle = new JLabel("Info:");
		infoTitle.setFont(new Font("Arial", Font.PLAIN, 12));
		infoTitle.setBounds(412, 25, 91, 13);
		frame.getContentPane().add(infoTitle);
		
		JLabel lblNewLabel = new JLabel("Total places:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(412, 117, 75, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Places left:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(412, 141, 67, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 261, 646, 13);
		frame.getContentPane().add(separator);
		
		JLabel lblNewLabel_5 = new JLabel("Personal data:");
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(56, 284, 111, 13);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Name:");
		lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(56, 307, 45, 13);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Surnames:");
		lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(251, 307, 67, 13);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("DNI:");
		lblNewLabel_8.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(56, 330, 45, 13);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Phone:");
		lblNewLabel_9.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_9.setBounds(56, 353, 45, 13);
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("E-Mail:");
		lblNewLabel_10.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_10.setBounds(56, 379, 45, 13);
		frame.getContentPane().add(lblNewLabel_10);
		
		nameField = new JTextField();
		nameField.setBounds(111, 304, 96, 19);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		surnamesField = new JTextField();
		surnamesField.setBounds(330, 304, 157, 19);
		frame.getContentPane().add(surnamesField);
		surnamesField.setColumns(10);
		
		dniField = new JTextField();
		dniField.setBounds(94, 327, 96, 19);
		frame.getContentPane().add(dniField);
		dniField.setColumns(10);
		
		phoneField = new JTextField();
		phoneField.setBounds(111, 350, 96, 19);
		frame.getContentPane().add(phoneField);
		phoneField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(111, 376, 182, 19);
		frame.getContentPane().add(emailField);
		emailField.setColumns(10);
		
		JButton confirmButton = new JButton("Confirm and formalize");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to formalize the inscription?"
						+ "\nThis is not reversible and will generate a debt according to the price of the selected formative action.");
				if (confirm == JOptionPane.YES_OPTION) {
					frame.dispose();
				}
			}
		});
		confirmButton.setFont(new Font("Arial", Font.BOLD, 12));
		confirmButton.setBounds(481, 406, 175, 30);
		frame.getContentPane().add(confirmButton);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?");
				if (confirm == JOptionPane.YES_OPTION) {
					frame.dispose();
				}
			}
		});
		backButton.setFont(new Font("Arial", Font.BOLD, 12));
		backButton.setBounds(386, 406, 85, 30);
		frame.getContentPane().add(backButton);
		
		JLabel totPlacesLabel = new JLabel("");
		totPlacesLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		totPlacesLabel.setBounds(497, 117, 35, 13);
		frame.getContentPane().add(totPlacesLabel);
		
		JLabel placesLeftLabel = new JLabel("");
		placesLeftLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		placesLeftLabel.setBounds(486, 141, 35, 13);
		frame.getContentPane().add(placesLeftLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(56, 49, 334, 186);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(412, 48, 225, 59);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1);
		
		description = new JTable();
		scrollPane_1.setViewportView(description);
		
		JButton viewCourses = new JButton("view courses");
		viewCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		viewCourses.setBounds(196, 21, 120, 21);
		frame.getContentPane().add(viewCourses);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);
		
		if (placesLeftLabel.getText().equals("0")) {
			confirmButton.setEnabled(false);;
		}
		
	}
	
	public String getFechaHoy()  { 
		LocalDate now = LocalDate.now(); 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		String strdate = now.format(dtf);
		return strdate; 
	}
	
	public JFrame getFrame() { return this.frame; }
	public JTable getTableCourses() { return this.table; }
	public JTable getDetalleCourses() { return this.description; }
	public JTable getListCourses() { return this.table; }
	public JButton getViewCourses() { return this.viewCourses; }
	public JTextField getnameField() { return this.nameField; }
	public JTextField getsurnamesField() { return this.surnamesField; }
	public JTextField getdniField() { return this.dniField; }
	public JTextField getphoneField() { return this.phoneField; }
	public JTextField getemailField() { return this.emailField; }

}
