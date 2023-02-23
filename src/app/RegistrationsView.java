package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.JScrollPane;

public class RegistrationsView {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationsView window = new RegistrationsView();
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
	public RegistrationsView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Course registrations");
		frame.setBounds(100, 100, 535, 490);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel labelInfo = new JLabel("These are the current registrations for the selected course:");
		labelInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelInfo.setBounds(10, 11, 444, 14);
		frame.getContentPane().add(labelInfo);
		
		//Scroll panel and table to show the headers
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 501, 262);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//for one selection at a time
		table.setDefaultEditor(Object.class, null); //readonly
		scrollPane.setViewportView(table);
	}
	
	
	//Getters and setters used in the controller
	public JFrame getFrame() { return this.frame; }
	public JTable getTable() { return this.table; }
}
