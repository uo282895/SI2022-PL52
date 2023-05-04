package giis.demo.coiipa;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;


import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;


public class CoursesView {

	private JFrame frmConsultFormativeActions;
	private JTable table;
	private JButton btnOK; 
	private JTable tableMoreInfo;
	private JLabel lblTodayDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoursesView window = new CoursesView();
					window.frmConsultFormativeActions.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CoursesView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConsultFormativeActions = new JFrame();
		frmConsultFormativeActions.setTitle("Consult formative actions");
		frmConsultFormativeActions.setBounds(100, 100, 914, 493);
		frmConsultFormativeActions.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmConsultFormativeActions.getContentPane().setLayout(null);
		
		JLabel lblInfo = new JLabel("This is the list of all the courses taught at COIIPA:");
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInfo.setBounds(10, 11, 444, 14);
		frmConsultFormativeActions.getContentPane().add(lblInfo);
		
		//Scroll panel and table to show the headers
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 860, 211);
		frmConsultFormativeActions.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//for one selection at a time
		table.setDefaultEditor(Object.class, null); //readonly
		scrollPane.setViewportView(table);
		
		btnOK = new JButton("OK");
		btnOK.setBounds(775, 422, 89, 23);
		frmConsultFormativeActions.getContentPane().add(btnOK);
		
		JScrollPane scrollPaneMoreInfo = new JScrollPane();
		scrollPaneMoreInfo.setBounds(10, 309, 622, 103);
		frmConsultFormativeActions.getContentPane().add(scrollPaneMoreInfo);
		
		tableMoreInfo = new JTable();
		scrollPaneMoreInfo.setColumnHeaderView(tableMoreInfo);
		
		JLabel lblInfoMore = new JLabel("More information about the selected course");
		lblInfoMore.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInfoMore.setBounds(10, 286, 444, 14);
		frmConsultFormativeActions.getContentPane().add(lblInfoMore);
		
		lblTodayDate = new JLabel("");
		lblTodayDate.setBounds(411, 12, 214, 14);
		frmConsultFormativeActions.getContentPane().add(lblTodayDate);
	}
	
	
	//Getters and setters used in the controller
	public JFrame getFrame() { return this.frmConsultFormativeActions; }
	public JTable getTableCourses() { return this.table; }
	public JButton getBtnOK() {return this.btnOK;}
	public JTable getTableMore() {return this.tableMoreInfo;}
	
	public void setTodayDate(String date) {
		lblTodayDate.setText("Today's date: "+ date);
	}
}
