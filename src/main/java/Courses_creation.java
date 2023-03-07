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
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	
	private float getFloatValue(Object value) {
		if (value != null) {
		    // Covert the value to type float
		    try {
		        float floatValue = Float.parseFloat((String) value);

		        // Verify if the value is not null
		        if (floatValue > 0) {
		            // Valid input
		        	return floatValue;
		        } else {
		            // Show error message if the value is not positive and not null
		        	JOptionPane.showMessageDialog(null, "Introduce a positive fee value");
		            throw new NumberFormatException("The fee must be positive and not 0.");
		        }
		    } catch (NumberFormatException e) {
		        JOptionPane.showMessageDialog(null, "Introduce a fee value positive and not null");
		        e.printStackTrace();
		    }
		} else {
		    // Show an error message if the fee is null
		    JOptionPane.showMessageDialog(null, "Introduce a positive not null value for the fee quantity");
		}
		return (float) -1.0;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 797, 646);
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
		
		JDateChooser course_start_date_dateChooser = new JDateChooser();
		course_start_date_dateChooser.setBounds(587, 65, 159, 20);
        course_start_date_dateChooser.setDateFormatString("dd/MM/yy HH:mm");
        frame.getContentPane().add(course_start_date_dateChooser);
		
		JTextArea course_description_textArea = new JTextArea();
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
		
		JTextArea course_main_contents_textArea = new JTextArea();
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
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(587, 96, 62, 20);
		formattedTextField.setText("2");
		frame.getContentPane().add(formattedTextField);
		
		JLabel course_number_of_hours_label_1 = new JLabel("hours");
		course_number_of_hours_label_1.setBounds(669, 98, 42, 17);
		frame.getContentPane().add(course_number_of_hours_label_1);
		
		JLabel Teachers_name_label_1_1 = new JLabel("Enrollment start date:");
		Teachers_name_label_1_1.setBounds(25, 126, 171, 17);
		frame.getContentPane().add(Teachers_name_label_1_1);
		
		JLabel Teachers_name_label_1_1_1 = new JLabel("Enrollment end date:");
		Teachers_name_label_1_1_1.setBounds(386, 130, 166, 17);
		frame.getContentPane().add(Teachers_name_label_1_1_1);
		
		JDateChooser Date_of_erollement_begining_dateChooser = new JDateChooser();
		Date_of_erollement_begining_dateChooser.setBounds(174, 126, 161, 20);
		Date_of_erollement_begining_dateChooser.setDateFormatString("dd/MM/yy HH:mm");
		frame.getContentPane().add(Date_of_erollement_begining_dateChooser);
		
		JDateChooser Date_of_enrollement_end_dateChooser = new JDateChooser();
		Date_of_enrollement_end_dateChooser.setBounds(587, 127, 159, 20);
		Date_of_enrollement_end_dateChooser.setDateFormatString("dd/MM/yy HH:mm");
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
		
		JLabel course_palce_label = new JLabel("Course place:");
		course_palce_label.setBounds(386, 164, 145, 17);
		frame.getContentPane().add(course_palce_label);
		
		course_place_textField = new JTextField();
		course_place_textField.setBounds(587, 161, 159, 20);
		course_place_textField.setColumns(10);
		frame.getContentPane().add(course_place_textField);
		
		JComboBox course_modality_comboBox = new JComboBox();
		course_modality_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(course_modality_comboBox.getSelectedItem().equals("Online")){
					course_palce_label.setVisible(false);
					course_place_textField.setVisible(false);
				}else {
					course_palce_label.setVisible(true);
					course_place_textField.setVisible(true);
				}
			}
		});
		
		course_modality_comboBox.setBounds(174, 160, 161, 22);
		course_modality_comboBox.setModel(new DefaultComboBoxModel(new String[] {"In person", "Online"}));
		frame.getContentPane().add(course_modality_comboBox);
		
		JLabel Teachers_name_label_1_2 = new JLabel("Total number of places available:");
		Teachers_name_label_1_2.setBounds(216, 243, 227, 17);
		frame.getContentPane().add(Teachers_name_label_1_2);
		
		JFormattedTextField course_fee_formattedTextField_1 = new JFormattedTextField();
		course_fee_formattedTextField_1.setBounds(440, 241, 62, 20);
		frame.getContentPane().add(course_fee_formattedTextField_1);
		
		JButton close_NewButton = new JButton("Close");
		close_NewButton.setBounds(25, 560, 159, 36);
		frame.getContentPane().add(close_NewButton);
		
		JComboBox course_modality_comboBox_1 = new JComboBox();
		course_modality_comboBox_1.setBounds(174, 199, 161, 22);
		frame.getContentPane().add(course_modality_comboBox_1);

		JLabel date_label = new JLabel("Date");
		date_label.setBounds(545, 244, 166, 17);
		frame.getContentPane().add(date_label);
		
		JLabel Course_name_label_1_1 = new JLabel("Select the teacher:");
		Course_name_label_1_1.setBounds(25, 202, 137, 17);
		frame.getContentPane().add(Course_name_label_1_1);
		
		JLabel Teachers_name_label_1_3 = new JLabel("Teacher's remuneration:");
		Teachers_name_label_1_3.setBounds(386, 202, 166, 17);
		frame.getContentPane().add(Teachers_name_label_1_3);
		
		JFormattedTextField teachers_remuneration_formattedTextField_2 = new JFormattedTextField();
		teachers_remuneration_formattedTextField_2.setBounds(587, 200, 95, 20);
		frame.getContentPane().add(teachers_remuneration_formattedTextField_2);
		
		JLabel course_number_of_hours_label_1_1_1 = new JLabel("€");
		course_number_of_hours_label_1_1_1.setBounds(692, 202, 42, 17);
		frame.getContentPane().add(course_number_of_hours_label_1_1_1);
		
		JButton accept_NewButton = new JButton("Check and accept");
		accept_NewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String course_name = Course_name_textfield.getText(); // Name of the course to be created
				
				// Storing the course start date and checking null issue
				Date course_start_date = course_start_date_dateChooser.getDate();
				if(course_start_date == null) {
					JOptionPane.showMessageDialog(null, "The course start date must not be null");
				}
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");
				String course_start_date_string = format.format(course_start_date);
				
				// Storing the course enrollment start date and checking null issue
				Date course_enrollement_start_date = Date_of_erollement_begining_dateChooser.getDate();
				if(course_enrollement_start_date == null) {
					JOptionPane.showMessageDialog(null, "The course enrollment start date must not be null");
				}
				String course_enrollement_start_date_string = format.format(course_enrollement_start_date);
				
				// Storing the course enrollment end date and checking null issue
				Date course_enrollement_end_date = Date_of_enrollement_end_dateChooser.getDate();
				if(course_enrollement_end_date == null) {
					JOptionPane.showMessageDialog(null, "The course enrollment start date must not be null");
				}
				String course_enrollement_end_date_string = format.format(course_enrollement_end_date);
				
				float fee = getFloatValue(course_fee_formattedTextField.getText());
				
			}
		});
		accept_NewButton.setBounds(587, 560, 159, 36);
		frame.getContentPane().add(accept_NewButton);
		
		
	}
}
