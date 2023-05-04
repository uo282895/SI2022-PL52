package giis.demo.coiipa;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ConsultView {

	private JFrame frame;
	private JLabel state;
	private JTable registered;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultView window = new ConsultView();
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
	public ConsultView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Consult status and registrations");
		frame.setBounds(100, 100, 874, 455);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Click on a course:");
		lblNewLabel.setBounds(37, 20, 120, 13);
		frame.getContentPane().add(lblNewLabel);
		
		state = new JLabel("");
		state.setBounds(37, 196, 45, 13);
		state.setOpaque(true);
		state.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(state);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(37, 242, 552, 133);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_2.add(scrollPane_2);
		
		registered = new JTable();
		registered.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"", "", "", "", "", "", ""
				}
		));
		registered.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		registered.setDefaultEditor(Object.class, null);
		scrollPane_2.setViewportView(registered);
		
		JLabel lblNewLabel_2 = new JLabel("List of registrations:");
		lblNewLabel_2.setBounds(37, 219, 140, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.setBounds(765, 386, 85, 21);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(37, 43, 552, 143);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"", "", "", "", ""
			}
		));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
	}
	
	public JFrame getFrame() { return this.frame; }
	public JTable getTableCourses() { return this.table; }
	public JTable getProffReg() { return this.registered; }
	public JTable getListCourses() { return this.table; }
	public JLabel getstate() {return this.state; }
}
