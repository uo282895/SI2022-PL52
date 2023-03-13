package giis.demo.tkrun;


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

public class PaymentsView {

	private JFrame frame;
	private JTable table;
	private JFormattedTextField tfAmount;
	private JTextField tfDate;
	private JButton btnCancel; 
	private JButton btnConfirm;
	private JTextField tfHour;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentsView window = new PaymentsView();
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
	public PaymentsView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Registration of payments");
		frame.setBounds(100, 100, 656, 490);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInfo = new JLabel("These are the pending payments for active courses:");
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInfo.setBounds(10, 11, 444, 14);
		frame.getContentPane().add(lblInfo);
		
		//Scroll panel and table to show the headers
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 622, 211);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//for one selection at a time
		table.setDefaultEditor(Object.class, null); //readonly
		scrollPane.setViewportView(table);
		
		NumberFormat format = NumberFormat.getInstance();
		tfAmount = new JFormattedTextField(format);
		tfAmount.setEnabled(false);
		tfAmount.setBounds(197, 304, 96, 20);
		frame.getContentPane().add(tfAmount);
		tfAmount.setColumns(10);
		
		tfDate = new JTextField();
		tfDate.setEnabled(false);
		tfDate.setBounds(197, 335, 154, 20);
		frame.getContentPane().add(tfDate);
		tfDate.setColumns(10);
		
		JLabel lblInfo2 = new JLabel("Input data related to the payment associated to the selected registration: ");
		lblInfo2.setBounds(20, 279, 434, 14);
		frame.getContentPane().add(lblInfo2);
		
		JLabel lblAmount = new JLabel("Amount: ");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAmount.setBounds(134, 307, 53, 14);
		frame.getContentPane().add(lblAmount);
		
		JLabel lblDate = new JLabel("Date of payment: ");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDate.setBounds(85, 338, 173, 14);
		frame.getContentPane().add(lblDate);
		
		JLabel lblEuro = new JLabel("€");
		lblEuro.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEuro.setBounds(303, 307, 49, 14);
		frame.getContentPane().add(lblEuro);
		
		JLabel lblFormat = new JLabel("(Format: \"YYYY-MM-DD\")");
		lblFormat.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFormat.setBounds(364, 338, 173, 14);
		frame.getContentPane().add(lblFormat);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(444, 419, 89, 23);
		frame.getContentPane().add(btnCancel);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(543, 419, 89, 23);
		frame.getContentPane().add(btnConfirm);
		
		JLabel lblHour = new JLabel("Hour of payment:");
		lblHour.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHour.setBounds(85, 369, 130, 14);
		frame.getContentPane().add(lblHour);
		
		tfHour = new JTextField();
		tfHour.setEnabled(false);
		tfHour.setBounds(197, 366, 154, 20);
		frame.getContentPane().add(tfHour);
		tfHour.setColumns(10);
	}
	
	
	//Getters and setters used in the controller
	public JFrame getFrame() { return this.frame; }
	public JTable getTablePayments() { return this.table; }
	public JFormattedTextField getTFAmount() {return this.tfAmount;}
	public JTextField getTFDate() {return this.tfDate;}
	public JTextField getTFHour() {return this.tfHour;}
	public JButton getBtnCancel() {return this.btnCancel;}
	public JButton getBtnConfirm() {return this.btnConfirm;}
}
