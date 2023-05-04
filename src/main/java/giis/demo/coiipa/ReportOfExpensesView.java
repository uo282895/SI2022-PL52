package giis.demo.coiipa;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

public class ReportOfExpensesView {

	private JFrame frame;
	
	private JDateChooser start_of_timeslot;
	private JDateChooser end_of_timeslot;
	private JTable Expenses_JTable;
	private JButton Search_courses_button;
	private JButton Close_button;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportOfExpensesView window = new ReportOfExpensesView();
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
	public ReportOfExpensesView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 924, 477);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Report of Expenses of the courses");
		
		start_of_timeslot = new JDateChooser();
		start_of_timeslot.setBounds(62, 67, 113, 20);
		start_of_timeslot.setDateFormatString("dd/MM/yy");
		frame.getContentPane().add(start_of_timeslot);
		
		end_of_timeslot = new JDateChooser();
		end_of_timeslot.setBounds(239, 67, 113, 20);
		end_of_timeslot.setDateFormatString("dd/MM/yy");
		frame.getContentPane().add(end_of_timeslot);
		
		JLabel lblNewLabel = new JLabel("Start date");
		lblNewLabel.setBounds(85, 42, 90, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblEndDate = new JLabel("End date");
		lblEndDate.setBounds(273, 42, 100, 14);
		frame.getContentPane().add(lblEndDate);
		
		JLabel lblEndDate_1 = new JLabel("-");
		lblEndDate_1.setBounds(199, 73, 30, 14);
		frame.getContentPane().add(lblEndDate_1);
		
		//Scroll panel and table to show the headers
		JScrollPane courses_expenses_scrollPane = new JScrollPane();
		courses_expenses_scrollPane.setBounds(10, 101, 878, 234);
		frame.getContentPane().add(courses_expenses_scrollPane);
		
		Expenses_JTable = new JTable();
		Expenses_JTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//for one selection at a time
		Expenses_JTable.setDefaultEditor(Object.class, null); //readonly
		courses_expenses_scrollPane.setViewportView(Expenses_JTable);
		
		Search_courses_button = new JButton("Search courses");
		Search_courses_button.setBounds(468, 42, 171, 48);
		frame.getContentPane().add(Search_courses_button);
		
		Close_button = new JButton("Close");
		Close_button.setBounds(729, 396, 171, 33);
		frame.getContentPane().add(Close_button);
		
	}
	
	
	public JButton getClose_button() {return Close_button;}
	public void setClose_button(JButton close_button) {Close_button = close_button;}
	public JButton getSearch_courses_button() {return Search_courses_button;}
	public void setSearch_courses_button(JButton search_courses_button) {
		Search_courses_button = search_courses_button;}
	public JFrame getFrame() {return frame;}
	public void setFrame(JFrame frame) {this.frame = frame;}
	public JDateChooser getStart_of_timeslot() {return start_of_timeslot;}
	public void setStart_of_timeslot(JDateChooser start_of_timeslot) {
		this.start_of_timeslot = start_of_timeslot;}
	public JDateChooser getEnd_of_timeslot() {return end_of_timeslot;}
	public void setEnd_of_timeslot(JDateChooser end_of_timeslot) {this.end_of_timeslot = end_of_timeslot;}
	public JTable getExpenses_JTable() {return Expenses_JTable;}
	public void setExpenses_JTable(JTable expenses_JTable) {Expenses_JTable = expenses_JTable;}
}
