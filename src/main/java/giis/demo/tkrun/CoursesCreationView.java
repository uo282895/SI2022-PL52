package giis.demo.tkrun;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CoursesCreationView {

	private JFrame frame;
	private JTextField Course_name_textfield;
	private JTextField course_place_textField;
	private JButton accept_NewButton;
	private JButton close_NewButton;
	private JFormattedTextField course_fee_formattedTextField;
	private JDateChooser course_start_date_dateChooser;
	private JTextArea course_description_textArea;
	private JTextArea course_main_contents_textArea;
	private JFormattedTextField formattedTextField;
	private JDateChooser Date_of_erollement_begining_dateChooser;
	private JDateChooser Date_of_enrollement_end_dateChooser;
	@SuppressWarnings("rawtypes")
	private JComboBox course_modality_comboBox;
	@SuppressWarnings("rawtypes")
	private JComboBox course_teacher_comboBox;
	private JFormattedTextField teachers_remuneration_formattedTextField_2;
	private JLabel course_palce_label;
	JFormattedTextField course_fee_formattedTextField_1;
	

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
		frame.setBounds(100, 100, 797, 646);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Plan a new Formative Action");
		
		
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
		
		course_start_date_dateChooser = new JDateChooser();
		course_start_date_dateChooser.setBounds(587, 65, 159, 20);
        course_start_date_dateChooser.setDateFormatString("dd/MM/yy HH:mm");
        frame.getContentPane().add(course_start_date_dateChooser);
		
		course_description_textArea = new JTextArea();
		course_description_textArea.setBounds(25, 289, 721, 98);
		course_description_textArea.setWrapStyleWord(true);
		course_description_textArea.setLineWrap(true);
		frame.getContentPane().add(course_description_textArea);
		
		JLabel course_description_label = new JLabel("Course objectives:");
		course_description_label.setBounds(25, 261, 166, 17);
		frame.getContentPane().add(course_description_label);
		
		JLabel course_main_contents_label = new JLabel("Course main contents:");
		course_main_contents_label.setBounds(25, 398, 166, 17);
		frame.getContentPane().add(course_main_contents_label);
		
		course_main_contents_textArea = new JTextArea();
		course_main_contents_textArea.setWrapStyleWord(true);
		course_main_contents_textArea.setBounds(25, 426, 721, 98);
		course_main_contents_textArea.setLineWrap(true);
		frame.getContentPane().add(course_main_contents_textArea);
		
		JLabel course_begining_date_begining_label = new JLabel("Date of the course begining:");
		course_begining_date_begining_label.setBounds(386, 67, 191, 17);
		frame.getContentPane().add(course_begining_date_begining_label);
		
		JLabel course_number_of_hours_label = new JLabel("Number of hours of the course:");
		course_number_of_hours_label.setBounds(386, 98, 191, 17);
		frame.getContentPane().add(course_number_of_hours_label);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(587, 96, 62, 20);
		formattedTextField.setText("2");
		frame.getContentPane().add(formattedTextField);
		
		JLabel course_number_of_hours_label_1 = new JLabel("hours");
		course_number_of_hours_label_1.setBounds(669, 98, 42, 17);
		frame.getContentPane().add(course_number_of_hours_label_1);
		
		JLabel Teachers_name_label_1_1 = new JLabel("Enrolment start date:");
		Teachers_name_label_1_1.setBounds(25, 126, 171, 17);
		frame.getContentPane().add(Teachers_name_label_1_1);
		
		JLabel Teachers_name_label_1_1_1 = new JLabel("Enrolment end date:");
		Teachers_name_label_1_1_1.setBounds(386, 130, 166, 17);
		frame.getContentPane().add(Teachers_name_label_1_1_1);
		
		Date_of_erollement_begining_dateChooser = new JDateChooser();
		Date_of_erollement_begining_dateChooser.setBounds(174, 126, 161, 20);
		Date_of_erollement_begining_dateChooser.setDateFormatString("dd/MM/yy HH:mm");
		frame.getContentPane().add(Date_of_erollement_begining_dateChooser);
		
		Date_of_enrollement_end_dateChooser = new JDateChooser();
		Date_of_enrollement_end_dateChooser.setBounds(587, 127, 159, 20);
		Date_of_enrollement_end_dateChooser.setDateFormatString("dd/MM/yy HH:mm");
		frame.getContentPane().add(Date_of_enrollement_end_dateChooser);
		
		course_fee_formattedTextField = new JFormattedTextField();
		course_fee_formattedTextField.setBounds(174, 96, 62, 20);
		frame.getContentPane().add(course_fee_formattedTextField);
		
		JLabel course_number_of_hours_label_1_1 = new JLabel("???");
		course_number_of_hours_label_1_1.setBounds(246, 98, 42, 17);
		frame.getContentPane().add(course_number_of_hours_label_1_1);
		
		JLabel Course_name_label_1 = new JLabel("Course modality:");
		Course_name_label_1.setBounds(25, 163, 137, 17);
		frame.getContentPane().add(Course_name_label_1);
		
		course_palce_label = new JLabel("Course place:");
		course_palce_label.setBounds(386, 164, 145, 17);
		frame.getContentPane().add(course_palce_label);
		
		course_place_textField = new JTextField();
		course_place_textField.setBounds(587, 161, 159, 20);
		course_place_textField.setColumns(10);
		frame.getContentPane().add(course_place_textField);
		
		course_modality_comboBox = new JComboBox();
				
		course_modality_comboBox.setBounds(174, 160, 161, 22);
		frame.getContentPane().add(course_modality_comboBox);
		
		JLabel Teachers_name_label_1_2 = new JLabel("Total number of places available:");
		Teachers_name_label_1_2.setBounds(216, 243, 227, 17);
		frame.getContentPane().add(Teachers_name_label_1_2);
		
		course_fee_formattedTextField_1 = new JFormattedTextField();
		course_fee_formattedTextField_1.setBounds(440, 241, 62, 20);
		frame.getContentPane().add(course_fee_formattedTextField_1);
		
		close_NewButton = new JButton("Close");
		
		close_NewButton.setBounds(25, 560, 159, 36);
		frame.getContentPane().add(close_NewButton);
		
		course_teacher_comboBox = new JComboBox();
		course_teacher_comboBox.setBounds(174, 199, 161, 22);
		frame.getContentPane().add(course_teacher_comboBox);
		
		JLabel Course_name_label_1_1 = new JLabel("Select the teacher:");
		Course_name_label_1_1.setBounds(25, 202, 137, 17);
		frame.getContentPane().add(Course_name_label_1_1);
		
		JLabel Teachers_name_label_1_3 = new JLabel("Teacher's remuneration:");
		Teachers_name_label_1_3.setBounds(386, 202, 166, 17);
		frame.getContentPane().add(Teachers_name_label_1_3);
		
		teachers_remuneration_formattedTextField_2 = new JFormattedTextField();
		teachers_remuneration_formattedTextField_2.setBounds(587, 200, 95, 20);
		frame.getContentPane().add(teachers_remuneration_formattedTextField_2);
		
		JLabel course_number_of_hours_label_1_1_1 = new JLabel("???");
		course_number_of_hours_label_1_1_1.setBounds(692, 202, 42, 17);
		frame.getContentPane().add(course_number_of_hours_label_1_1_1);
		
		accept_NewButton = new JButton("Check and accept");
		
		
		accept_NewButton.setBounds(587, 560, 159, 36);
		frame.getContentPane().add(accept_NewButton);
	}
	
	
	//Getters and setters used in the controller
		public JFrame getFrame() { return this.frame; }
		public JButton getAcceptButton() {return this.accept_NewButton; }
		public JButton getCloseButton () {return this.close_NewButton;}
		public JTextField getCourse_name_textfield() {return this.Course_name_textfield;};
		public JTextField getcourse_place_textField () {return this.course_place_textField ;}
		public JFormattedTextField getcourse_fee_formattedTextField() {return this.course_fee_formattedTextField;}
		public JDateChooser getcourse_start_date_dateChooser() {return this.course_start_date_dateChooser;}
		public JTextArea getcourse_description_textArea() {return this.course_description_textArea;}
		public JTextArea getcourse_main_contents_textArea() {return this.course_main_contents_textArea;}
		public JFormattedTextField getformattedTextField() {return this.formattedTextField;}
		public JDateChooser getDate_of_erollement_begining_dateChooser() {return this.Date_of_erollement_begining_dateChooser;}
		public JDateChooser getDate_of_enrollement_end_dateChooser() {return this.Date_of_enrollement_end_dateChooser;}
		@SuppressWarnings("rawtypes")
		public JComboBox getcourse_modality_comboBox() {return this.course_modality_comboBox;}
		@SuppressWarnings("rawtypes")
		public JComboBox getcourse_teacher_comboBox() {return this.course_teacher_comboBox;}
		public JFormattedTextField getteachers_remuneration_formattedTextField_2() {return this.teachers_remuneration_formattedTextField_2;}
		public JLabel getcourse_palce_label() { return this.course_palce_label;}

		public JFormattedTextField getCourse_fee_formattedTextField_1() {return course_fee_formattedTextField_1;}

		public void setCourse_fee_formattedTextField_1(JFormattedTextField course_fee_formattedTextField_1) {
			this.course_fee_formattedTextField_1 = course_fee_formattedTextField_1;}

	
}
