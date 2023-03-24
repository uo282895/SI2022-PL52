package giis.demo.coiipa;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import com.toedter.calendar.JDateChooser;


public class RegistrationCancellationView {

	private JFrame frame;
	private JTable table;
	private JDateChooser cancellation_dateJDateChooser;
	private JLabel selected_registration_label;
	private JButton Closebutton;
	private JButton Acept_cancellation_JButton;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationCancellationView window = new RegistrationCancellationView();
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
	public RegistrationCancellationView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1315, 569);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Register a Cancellation to a Formative Action");
		

		JLabel labelInfo = new JLabel("These are the registrations to courses that have not been cancelled:");
		labelInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelInfo.setBounds(10, 24, 519, 14);
		frame.getContentPane().add(labelInfo);
		
		//Scroll panel and table to show the headers
		JScrollPane registrations_scrollPane = new JScrollPane();
		registrations_scrollPane.setBounds(20, 54, 1260, 260);
		frame.getContentPane().add(registrations_scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//for one selection at a time
		table.setDefaultEditor(Object.class, null); //readonly
		registrations_scrollPane.setViewportView(table);
		
		Closebutton = new JButton("Close");
		Closebutton.setBounds(1019, 474, 139, 45);
		frame.getContentPane().add(Closebutton);
		
		cancellation_dateJDateChooser = new JDateChooser();
		cancellation_dateJDateChooser.setBounds(297, 377, 159, 20);
		cancellation_dateJDateChooser.setDateFormatString("dd/MM/yy HH:mm");
        frame.getContentPane().add(cancellation_dateJDateChooser);
        
        JLabel Registration_Datal_Label = new JLabel("Enter the date and hour of the cancellation:");
        Registration_Datal_Label.setBounds(30, 377, 277, 23);
        frame.getContentPane().add(Registration_Datal_Label);
        
        JLabel lblTheSelectedRegistration = new JLabel("The selected registration is: ");
        lblTheSelectedRegistration.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTheSelectedRegistration.setBounds(30, 355, 211, 14);
        frame.getContentPane().add(lblTheSelectedRegistration);
        
        selected_registration_label = new JLabel("");
        selected_registration_label.setBounds(297, 355, 510, 14);
        frame.getContentPane().add(selected_registration_label);
        
        Acept_cancellation_JButton = new JButton("Accept and register cancellation");
        Acept_cancellation_JButton.setBounds(20, 449, 267, 70);
        frame.getContentPane().add(Acept_cancellation_JButton);
		
	}
	
	public JDateChooser getCancellation_dateJDateChooser() {
		return cancellation_dateJDateChooser;
	}

	public void setCancellation_dateJDateChooser(JDateChooser cancellation_dateJDateChooser) {
		this.cancellation_dateJDateChooser = cancellation_dateJDateChooser;
	}

	public JLabel getSelected_registration_label() {
		return selected_registration_label;
	}

	public void setSelected_registration_label(JLabel selected_registration_label) {
		this.selected_registration_label = selected_registration_label;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	public JButton getClosebutton() {
		return Closebutton;
	}

	public void setClosebutton(JButton closebutton) {
		Closebutton = closebutton;
	}
	public JButton getAcept_cancellation_JButton() {
		return Acept_cancellation_JButton;
	}

	public void setAcept_cancellation_JButton(JButton acept_cancellation_JButton) {
		Acept_cancellation_JButton = acept_cancellation_JButton;
	}


}
