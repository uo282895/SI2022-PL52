

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;

public class InscriptionsView {

	private JFrame frame;
	private JTextField nameField;
	private JTextField surnamesField;
	private JTextField dniField;
	private JTextField phoneField;
	private JTextField emailField;

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
		frame.setBounds(100, 100, 680, 483);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel csLabel = new JLabel("Course selection:");
		csLabel.setSize(new Dimension(10, 10));
		csLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		csLabel.setBounds(new Rectangle(56, 10, 130, 42));
		csLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(csLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(56, 49, 237, 172);
		frame.getContentPane().add(scrollPane);
		
		JLabel infoTitle = new JLabel("Info:");
		infoTitle.setFont(new Font("Arial", Font.PLAIN, 12));
		infoTitle.setBounds(359, 25, 91, 13);
		frame.getContentPane().add(infoTitle);
		
		JLabel infoText = new JLabel("");
		infoText.setBounds(359, 49, 250, 57);
		frame.getContentPane().add(infoText);
		
		JLabel lblNewLabel = new JLabel("Total places:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(359, 116, 75, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Places left:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(359, 139, 67, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Price:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(359, 162, 45, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Date start:");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(359, 185, 75, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Date end:");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(359, 208, 67, 13);
		frame.getContentPane().add(lblNewLabel_4);
		
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
		confirmButton.setFont(new Font("Arial", Font.BOLD, 12));
		confirmButton.setBounds(499, 406, 157, 30);
		frame.getContentPane().add(confirmButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Arial", Font.BOLD, 12));
		backButton.setBounds(402, 406, 85, 30);
		frame.getContentPane().add(backButton);
		
		JLabel totPlacesLabel = new JLabel("");
		totPlacesLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		totPlacesLabel.setBounds(444, 118, 45, 13);
		frame.getContentPane().add(totPlacesLabel);
		
		JLabel placesLeftLabel = new JLabel("");
		placesLeftLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		placesLeftLabel.setBounds(436, 141, 45, 13);
		frame.getContentPane().add(placesLeftLabel);
		
		JLabel priceLabel = new JLabel("");
		priceLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		priceLabel.setBounds(414, 164, 45, 13);
		frame.getContentPane().add(priceLabel);
		
		JLabel dateStartLabel = new JLabel("");
		dateStartLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		dateStartLabel.setHorizontalAlignment(SwingConstants.LEFT);
		dateStartLabel.setBounds(436, 187, 52, 13);
		frame.getContentPane().add(dateStartLabel);
		
		JLabel dateEndLabel = new JLabel("");
		dateEndLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		dateEndLabel.setHorizontalAlignment(SwingConstants.LEFT);
		dateEndLabel.setBounds(436, 210, 67, 13);
		frame.getContentPane().add(dateEndLabel);
	}
}