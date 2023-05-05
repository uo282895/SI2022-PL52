package giis.demo.coiipa;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class CoursesCreationView {

	private JFrame frame;
	private JTextField Course_name_textfield;
	private JTextField course_place_textField;
	private JButton accept_NewButton;
	private JButton close_NewButton;
	private JFormattedTextField course_fee_formattedTextField;
	private JTextArea course_description_textArea;
	private JTextArea course_main_contents_textArea;
	private JFormattedTextField session_n_hours_formattedTextField;
	private JDateChooser Date_of_erollement_begining_dateChooser;
	private JDateChooser Date_of_enrollement_end_dateChooser;
	@SuppressWarnings("rawtypes")
	private JComboBox session_modality_comboBox;
	@SuppressWarnings("rawtypes")
	private JComboBox course_teacher_comboBox;
	private JFormattedTextField teachers_remuneration_formattedTextField_2;
	private JLabel course_palce_label;
	JFormattedTextField course_fee_formattedTextField_1;
	private JTable table;
	private JButton delete_session_NewButton;
	private JButton add_session_NewButton;
	private JDateChooser session_date_dateChooser;
	private DefaultTableModel table_model;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoursesCreationView window = new CoursesCreationView();
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
	public CoursesCreationView() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 25, 794, 672);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Plan a new Formative Action");
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 158, 748, 18);
		frame.getContentPane().add(separator);		
		
		JLabel lblNewLabel = new JLabel("Plan a New Formative Action");
		lblNewLabel.setBounds(330, 11, 171, 25);
		frame.getContentPane().add(lblNewLabel);
		
		Course_name_textfield = new JTextField();
		Course_name_textfield.setBounds(174, 65, 161, 20);
		frame.getContentPane().add(Course_name_textfield);
		Course_name_textfield.setColumns(10);
		
		JLabel Course_name_label = new JLabel("Name of the course:");
		Course_name_label.setBounds(25, 67, 166, 17);
		frame.getContentPane().add(Course_name_label);
		
		JLabel Teachers_name_label_1 = new JLabel("Course fee:");
		Teachers_name_label_1.setBounds(25, 98, 123, 17);
		frame.getContentPane().add(Teachers_name_label_1);
		
		course_description_textArea = new JTextArea();
		course_description_textArea.setBounds(25, 444, 721, 46);
		course_description_textArea.setWrapStyleWord(true);
		course_description_textArea.setLineWrap(true);
		frame.getContentPane().add(course_description_textArea);
		
		JLabel course_description_label = new JLabel("Course objectives:");
		course_description_label.setBounds(25, 416, 166, 17);
		frame.getContentPane().add(course_description_label);
		
		JLabel course_main_contents_label = new JLabel("Course main contents:");
		course_main_contents_label.setBounds(25, 501, 166, 17);
		frame.getContentPane().add(course_main_contents_label);
		
		course_main_contents_textArea = new JTextArea();
		course_main_contents_textArea.setWrapStyleWord(true);
		course_main_contents_textArea.setBounds(25, 529, 721, 46);
		course_main_contents_textArea.setLineWrap(true);
		frame.getContentPane().add(course_main_contents_textArea);
		
		JLabel course_number_of_hours_label = new JLabel("Number of hours of the session:");
		course_number_of_hours_label.setBounds(25, 209, 191, 17);
		frame.getContentPane().add(course_number_of_hours_label);
		
		session_n_hours_formattedTextField = new JFormattedTextField();
		session_n_hours_formattedTextField.setBounds(221, 207, 62, 20);
		session_n_hours_formattedTextField.setText("1");
		frame.getContentPane().add(session_n_hours_formattedTextField);
		
		JLabel course_number_of_hours_label_1 = new JLabel("hours");
		course_number_of_hours_label_1.setBounds(304, 209, 42, 17);
		frame.getContentPane().add(course_number_of_hours_label_1);
		
		JLabel Teachers_name_label_1_1 = new JLabel("Enrolment start date:");
		Teachers_name_label_1_1.setBounds(25, 126, 171, 17);
		frame.getContentPane().add(Teachers_name_label_1_1);
		
		JLabel Teachers_name_label_1_1_1 = new JLabel("Enrolment end date:");
		Teachers_name_label_1_1_1.setBounds(386, 130, 166, 17);
		frame.getContentPane().add(Teachers_name_label_1_1_1);
		
		Date_of_erollement_begining_dateChooser = new JDateChooser();
		Date_of_erollement_begining_dateChooser.setBounds(174, 126, 161, 20);
		Date_of_erollement_begining_dateChooser.setDateFormatString("dd/MM/yy");
		frame.getContentPane().add(Date_of_erollement_begining_dateChooser);
		
		Date_of_enrollement_end_dateChooser = new JDateChooser();
		Date_of_enrollement_end_dateChooser.setBounds(587, 127, 159, 20);
		Date_of_enrollement_end_dateChooser.setDateFormatString("dd/MM/yy");
		frame.getContentPane().add(Date_of_enrollement_end_dateChooser);
		
		course_fee_formattedTextField = new JFormattedTextField();
		course_fee_formattedTextField.setBounds(174, 96, 62, 20);
		frame.getContentPane().add(course_fee_formattedTextField);
		
		JLabel course_number_of_hours_label_1_1 = new JLabel("€");
		course_number_of_hours_label_1_1.setBounds(246, 98, 42, 17);
		frame.getContentPane().add(course_number_of_hours_label_1_1);
		
		JLabel Course_name_label_1 = new JLabel("Session's modality:");
		Course_name_label_1.setBounds(25, 177, 137, 17);
		frame.getContentPane().add(Course_name_label_1);
		
		course_palce_label = new JLabel("Session's place:");
		course_palce_label.setBounds(386, 177, 145, 17);
		frame.getContentPane().add(course_palce_label);
		
		course_place_textField = new JTextField();
		course_place_textField.setBounds(587, 175, 159, 20);
		course_place_textField.setColumns(10);
		frame.getContentPane().add(course_place_textField);
		
		session_modality_comboBox = new JComboBox();
				
		session_modality_comboBox.setBounds(174, 174, 161, 22);
		frame.getContentPane().add(session_modality_comboBox);
		
		JLabel Teachers_name_label_1_2 = new JLabel("Total number of places available:");
		Teachers_name_label_1_2.setBounds(202, 399, 227, 17);
		frame.getContentPane().add(Teachers_name_label_1_2);
		
		course_fee_formattedTextField_1 = new JFormattedTextField();
		course_fee_formattedTextField_1.setBounds(439, 397, 62, 20);
		frame.getContentPane().add(course_fee_formattedTextField_1);
		
		close_NewButton = new JButton("Close");
		
		close_NewButton.setBounds(25, 586, 159, 36);
		frame.getContentPane().add(close_NewButton);
		
		course_teacher_comboBox = new JComboBox();
		course_teacher_comboBox.setBounds(585, 64, 161, 22);
		frame.getContentPane().add(course_teacher_comboBox);
		
		JLabel Course_name_label_1_1 = new JLabel("Select the teacher:");
		Course_name_label_1_1.setBounds(386, 67, 137, 17);
		frame.getContentPane().add(Course_name_label_1_1);
		
		JLabel Teachers_name_label_1_3 = new JLabel("Teacher's remuneration:");
		Teachers_name_label_1_3.setBounds(386, 98, 166, 17);
		frame.getContentPane().add(Teachers_name_label_1_3);
		
		teachers_remuneration_formattedTextField_2 = new JFormattedTextField();
		teachers_remuneration_formattedTextField_2.setBounds(587, 96, 95, 20);
		frame.getContentPane().add(teachers_remuneration_formattedTextField_2);
		
		JLabel course_number_of_hours_label_1_1_1 = new JLabel("€");
		course_number_of_hours_label_1_1_1.setBounds(692, 98, 42, 17);
		frame.getContentPane().add(course_number_of_hours_label_1_1_1);
		
		accept_NewButton = new JButton("Check and accept");
		
		
		accept_NewButton.setBounds(587, 586, 159, 36);
		frame.getContentPane().add(accept_NewButton);
		
		JLabel course_begining_date_begining_label_1 = new JLabel("Date of the session:");
		course_begining_date_begining_label_1.setBounds(386, 212, 191, 17);
		frame.getContentPane().add(course_begining_date_begining_label_1);
		
		session_date_dateChooser = new JDateChooser();
		session_date_dateChooser.setDateFormatString("dd/MM/yy HH:mm");
		session_date_dateChooser.setBounds(587, 209, 159, 20);
		frame.getContentPane().add(session_date_dateChooser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 291, 721, 84);
		frame.getContentPane().add(scrollPane);
		
		table_model = new DefaultTableModel();
		table_model.addColumn("Session date");
		table_model.addColumn("Session hour");
		table_model.addColumn("Session duration (hours)");
		table_model.addColumn("Session place");
		table = new JTable(table_model);
	    table.setRowSelectionAllowed(true);
	    table.setColumnSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//for one selection at a time
		scrollPane.setViewportView(table);
		
		add_session_NewButton = new JButton("Add session");
		add_session_NewButton.setBounds(172, 244, 163, 36);
		frame.getContentPane().add(add_session_NewButton);
		
		delete_session_NewButton = new JButton("Delete selected session");
		delete_session_NewButton.setBounds(386, 244, 191, 36);
		frame.getContentPane().add(delete_session_NewButton);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 387, 748, 18);
		frame.getContentPane().add(separator_1);
	}
	
	
	//Getters and setters used in the controller
	

		public JDateChooser getSession_date_dateChooser() {return session_date_dateChooser;}
		public void setSession_date_dateChooser(JDateChooser session_date_dateChooser) {
			this.session_date_dateChooser = session_date_dateChooser;}
		public JFrame getFrame() {return this.frame; }
		public JTable getTable() {return table;}
		public void setTable(JTable table) {this.table = table;}
		public JButton getDelete_session_NewButton() {return delete_session_NewButton;}
		public void setDelete_session_NewButton(JButton delete_session_NewButton) {this.delete_session_NewButton = delete_session_NewButton;}
		public JButton getAdd_session_NewButton() {return add_session_NewButton;}
		public void setAdd_session_NewButton(JButton add_session_NewButton) {this.add_session_NewButton = add_session_NewButton;}
		public JButton getAcceptButton() {return this.accept_NewButton; }
		public JButton getCloseButton () {return this.close_NewButton;}
		public JTextField getCourse_name_textfield() {return this.Course_name_textfield;};
		public JTextField getcourse_place_textField () {return this.course_place_textField ;}
		public JFormattedTextField getcourse_fee_formattedTextField() {return this.course_fee_formattedTextField;}
		public JTextArea getcourse_description_textArea() {return this.course_description_textArea;}
		public JTextArea getcourse_main_contents_textArea() {return this.course_main_contents_textArea;}
		public JFormattedTextField getformattedTextField() {return this.session_n_hours_formattedTextField;}
		public JDateChooser getDate_of_erollement_begining_dateChooser() {return this.Date_of_erollement_begining_dateChooser;}
		public JDateChooser getDate_of_enrollement_end_dateChooser() {return this.Date_of_enrollement_end_dateChooser;}
		@SuppressWarnings("rawtypes")
		public JComboBox getcourse_modality_comboBox() {return this.session_modality_comboBox;}
		@SuppressWarnings("rawtypes")
		public JComboBox getcourse_teacher_comboBox() {return this.course_teacher_comboBox;}
		public JFormattedTextField getteachers_remuneration_formattedTextField_2() {return this.teachers_remuneration_formattedTextField_2;}
		public JLabel getcourse_palce_label() { return this.course_palce_label;}
		public JFormattedTextField getCourse_fee_formattedTextField_1() {return course_fee_formattedTextField_1;}
		public void setCourse_fee_formattedTextField_1(JFormattedTextField course_fee_formattedTextField_1) {
			this.course_fee_formattedTextField_1 = course_fee_formattedTextField_1;}
		public DefaultTableModel getTable_model() {return table_model;}
		public void setTable_model(DefaultTableModel table_model) {this.table_model = table_model;}

}
