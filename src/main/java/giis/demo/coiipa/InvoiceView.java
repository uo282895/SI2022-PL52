package giis.demo.coiipa;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.text.NumberFormat;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class InvoiceView {

	private JFrame frmManageInvoices;
	private JTable table;
	private JFormattedTextField tfAmount;
	private JTextField tfDate;
	private JButton btnCancel; 
	private JButton btnConfirm;
	private JTextField tfHour;
	private JLabel lblTodaysDate;
	private JTable table_1;
	private JButton btnRegisterInvoice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InvoiceView window = new InvoiceView();
					window.frmManageInvoices.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InvoiceView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmManageInvoices = new JFrame();
		frmManageInvoices.setTitle("Manage invoices");
		frmManageInvoices.setBounds(150, 150, 940, 490);
		frmManageInvoices.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmManageInvoices.getContentPane().setLayout(null);
		
		JLabel lblInfo = new JLabel("Please, select a teacher and the course he/she has taught for receiving the corresponding invoice:");
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInfo.setBounds(10, 11, 647, 27);
		frmManageInvoices.getContentPane().add(lblInfo);
		
		//Scroll panel and table to show the headers
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 445, 179);
		frmManageInvoices.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//for one selection at a time
		table.setDefaultEditor(Object.class, null); //readonly
		scrollPane.setViewportView(table);
		
		NumberFormat format = NumberFormat.getInstance();
		tfAmount = new JFormattedTextField(format);
		tfAmount.setEnabled(false);
		tfAmount.setBounds(197, 304, 96, 20);
		frmManageInvoices.getContentPane().add(tfAmount);
		tfAmount.setColumns(10);
		
		tfDate = new JTextField();
		tfDate.setEnabled(false);
		tfDate.setBounds(197, 335, 154, 20);
		frmManageInvoices.getContentPane().add(tfDate);
		tfDate.setColumns(10);
		
		JLabel lblInfo2 = new JLabel("Input data related to the payment associated to the selected invoice: ");
		lblInfo2.setBounds(20, 279, 434, 14);
		frmManageInvoices.getContentPane().add(lblInfo2);
		
		JLabel lblAmount = new JLabel("Amount: ");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAmount.setBounds(134, 307, 53, 14);
		frmManageInvoices.getContentPane().add(lblAmount);
		
		JLabel lblDate = new JLabel("Date of payment: ");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDate.setBounds(85, 338, 173, 14);
		frmManageInvoices.getContentPane().add(lblDate);
		
		JLabel lblEuro = new JLabel("€");
		lblEuro.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEuro.setBounds(303, 307, 49, 14);
		frmManageInvoices.getContentPane().add(lblEuro);
		
		JLabel lblFormat = new JLabel("(Format: \"YYYY-MM-DD\")");
		lblFormat.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFormat.setBounds(364, 338, 173, 14);
		frmManageInvoices.getContentPane().add(lblFormat);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(732, 419, 89, 23);
		frmManageInvoices.getContentPane().add(btnCancel);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(831, 419, 89, 23);
		frmManageInvoices.getContentPane().add(btnConfirm);
		
		JLabel lblHour = new JLabel("Hour of payment:");
		lblHour.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHour.setBounds(85, 369, 130, 14);
		frmManageInvoices.getContentPane().add(lblHour);
		
		tfHour = new JTextField();
		tfHour.setEnabled(false);
		tfHour.setBounds(197, 366, 154, 20);
		frmManageInvoices.getContentPane().add(tfHour);
		tfHour.setColumns(10);
		
		JLabel lblFormatHour = new JLabel("(Format: \"HH:MM:SS\")");
		lblFormatHour.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFormatHour.setBounds(364, 369, 173, 14);
		frmManageInvoices.getContentPane().add(lblFormatHour);
		
		lblTodaysDate = new JLabel("");
		lblTodaysDate.setBounds(667, 11, 154, 27);
		frmManageInvoices.getContentPane().add(lblTodaysDate);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(625, 88, 291, 130);
		frmManageInvoices.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNewLabel = new JLabel("Invoice info");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(746, 74, 82, 14);
		frmManageInvoices.getContentPane().add(lblNewLabel);
		
		btnRegisterInvoice = new JButton("Register invoice");
		btnRegisterInvoice.setBounds(478, 139, 137, 23);
		frmManageInvoices.getContentPane().add(btnRegisterInvoice);
	}
	
	
	//Getters and setters used in the controller
	public JFrame getFrame() { return this.frmManageInvoices; }
	public JTable getTableTeachers() { return this.table; }
	public JFormattedTextField getTFAmount() { return this.tfAmount; }
	public JTextField getTFDate() { return this.tfDate; }
	public JTextField getTFHour() { return this.tfHour; }
	public JButton getBtnCancel() { return this.btnCancel; }
	public JButton getBtnConfirm() { return this.btnConfirm; }
	public JTable getTableInvoice() { return this.table_1; }
	public JButton getBtnRegister() { return this.btnRegisterInvoice;}
	
	//Setters
	public void setTodayDate(String date) {this.lblTodaysDate.setText("Today's date: "+date);}
}
