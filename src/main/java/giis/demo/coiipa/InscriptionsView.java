package giis.demo.coiipa;



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
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.lang.ModuleLayer.Controller;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class InscriptionsView {

	private JFrame frame;
	private JTextField nameField;
	private JTextField surnamesField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTable table;
	private JTable description;
	private JButton confirmButton;

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
		frame.setTitle("Enroll in a formative action");
		frame.getContentPane().setName("");
		frame.setBounds(100, 100, 680, 507);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel csLabel = new JLabel("Please, select a course:");
		csLabel.setSize(new Dimension(10, 10));
		csLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		csLabel.setBounds(new Rectangle(56, 10, 151, 42));
		csLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(csLabel);
		
		JLabel infoTitle = new JLabel("Info:");
		infoTitle.setFont(new Font("Arial", Font.PLAIN, 12));
		infoTitle.setBounds(412, 25, 91, 13);
		frame.getContentPane().add(infoTitle);
		
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
		
		phoneField = new JTextField();
		phoneField.setBounds(111, 350, 96, 19);
		frame.getContentPane().add(phoneField);
		phoneField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(111, 376, 182, 19);
		frame.getContentPane().add(emailField);
		emailField.setColumns(10);
		
		confirmButton = new JButton("ENROLL!");
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
		
		JPanel panel = new JPanel();
		panel.setBounds(56, 49, 334, 186);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C1", "C2", "C3", "C4", "C5"
			}
		));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(412, 48, 225, 187);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1);
		
		description = new JTable();
		scrollPane_1.setViewportView(description);
	}
	/*
	public String getFechaHoy()  { 
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
		String date = currentDate.format(formatter);
		return date;			
	}
	*/
	public JFrame getFrame() { return this.frame; }
	public JTable getTableCourses() { return this.table; }
	public JTable getDetalleCourses() { return this.description; }
	public JTable getListCourses() { return this.table; }
	public JButton getConfirm() { return this.confirmButton; }
	public JTextField getnameField() { return this.nameField; }
	public JTextField getsurnamesField() { return this.surnamesField; }
	public JTextField getphoneField() { return this.phoneField; }
	public JTextField getemailField() { return this.emailField; }
}
