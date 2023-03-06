import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class Courses_creation {

	private JFrame frame;
	private JTextField Course_name_textfield;
	private JTextField course_place_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Courses_creation window = new Courses_creation();
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
	public Courses_creation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 797, 621);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
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
		
		JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yy HH:mm");
        dateChooser.setBounds(587, 65, 159, 20);
        frame.getContentPane().add(dateChooser);
		
		JTextArea course_description_textArea = new JTextArea();
		course_description_textArea.setWrapStyleWord(true);
		course_description_textArea.setLineWrap(true);
		course_description_textArea.setBounds(25, 277, 721, 110);
		frame.getContentPane().add(course_description_textArea);
		
		JLabel course_description_label = new JLabel("Course objectives:");
		course_description_label.setBounds(25, 249, 166, 17);
		frame.getContentPane().add(course_description_label);
		
		JLabel course_main_contents_label = new JLabel("Course main contents:");
		course_main_contents_label.setBounds(25, 398, 166, 17);
		frame.getContentPane().add(course_main_contents_label);
		
		JTextArea course_main_contents_textArea = new JTextArea();
		course_main_contents_textArea.setLineWrap(true);
		course_main_contents_textArea.setBounds(25, 426, 721, 110);
		frame.getContentPane().add(course_main_contents_textArea);
		
		JLabel course_begining_date_begining_label = new JLabel("Date of the course begining:");
		course_begining_date_begining_label.setBounds(386, 67, 191, 17);
		frame.getContentPane().add(course_begining_date_begining_label);
		
		JLabel course_number_of_hours_label = new JLabel("Number of hours of the course:");
		course_number_of_hours_label.setBounds(386, 98, 191, 17);
		frame.getContentPane().add(course_number_of_hours_label);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setText("2");
		formattedTextField.setBounds(587, 96, 62, 20);
		frame.getContentPane().add(formattedTextField);
		
		JLabel course_number_of_hours_label_1 = new JLabel("hours");
		course_number_of_hours_label_1.setBounds(669, 98, 42, 17);
		frame.getContentPane().add(course_number_of_hours_label_1);
		
		JLabel Teachers_name_label_1_1 = new JLabel("Enrollement start date:");
		Teachers_name_label_1_1.setBounds(25, 126, 171, 17);
		frame.getContentPane().add(Teachers_name_label_1_1);
		
		JLabel Teachers_name_label_1_1_1 = new JLabel("Enrollement end date:");
		Teachers_name_label_1_1_1.setBounds(386, 130, 166, 17);
		frame.getContentPane().add(Teachers_name_label_1_1_1);
		
		JDateChooser Date_of_erollement_begining_dateChooser = new JDateChooser();
		Date_of_erollement_begining_dateChooser.setDateFormatString("dd/MM/yy");
		Date_of_erollement_begining_dateChooser.setBounds(174, 126, 161, 20);
		frame.getContentPane().add(Date_of_erollement_begining_dateChooser);
		
		JDateChooser Date_of_enrollement_end_dateChooser = new JDateChooser();
		Date_of_enrollement_end_dateChooser.setDateFormatString("dd/MM/yy");
		Date_of_enrollement_end_dateChooser.setBounds(587, 127, 159, 20);
		frame.getContentPane().add(Date_of_enrollement_end_dateChooser);
		
		JFormattedTextField course_fee_formattedTextField = new JFormattedTextField();
		course_fee_formattedTextField.setBounds(174, 96, 62, 20);
		frame.getContentPane().add(course_fee_formattedTextField);
		
		JLabel course_number_of_hours_label_1_1 = new JLabel("€");
		course_number_of_hours_label_1_1.setBounds(246, 98, 42, 17);
		frame.getContentPane().add(course_number_of_hours_label_1_1);
		
		JLabel Course_name_label_1 = new JLabel("Course modality:");
		Course_name_label_1.setBounds(25, 163, 137, 17);
		frame.getContentPane().add(Course_name_label_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"In person", "Online"}));
		comboBox.setBounds(174, 160, 93, 22);
		frame.getContentPane().add(comboBox);
		
		JLabel Teachers_name_label_1_1_1_1 = new JLabel("Course place:");
		Teachers_name_label_1_1_1_1.setBounds(386, 164, 145, 17);
		frame.getContentPane().add(Teachers_name_label_1_1_1_1);
		
		course_place_textField = new JTextField();
		course_place_textField.setColumns(10);
		course_place_textField.setBounds(587, 161, 159, 20);
		frame.getContentPane().add(course_place_textField);
	}
}
