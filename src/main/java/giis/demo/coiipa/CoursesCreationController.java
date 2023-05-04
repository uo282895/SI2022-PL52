package giis.demo.coiipa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import giis.demo.dto.SessionDisplayDTO;
import giis.demo.dto.TeacherDisplayDTO;
import giis.demo.util.SwingUtil;

/**
 * Controlador para la funcionalidad de visualizacion de carreras para la inscripcion.
 * Es el punto de entrada de esta pantalla que se invocará:
 * -instanciando el controlador con la vista y el modelo
 * -ejecutando initController que instalara los manejadores de eventos
 */
public class CoursesCreationController {
	private CoursesCreationModel model;
	private CoursesCreationView view;
	private String lastSelectedKey=""; //recuerda la ultima fila seleccionada para restaurarla cuando cambie la tabla de carreras
	Random rand = new Random();
	
    private String error_message = "There were some errors introduced:\n\n";
    private String add_session_error_message = "There were some errors introduced:\n\n";

    private boolean error_adding_session = false;
    private boolean error = false;
    public boolean float_error = false;
    private boolean week3_warning = false;
    private boolean date_before_today = false;
    
    private Date today;
    
    private List<SessionDisplayDTO> sessions = new ArrayList<>();
;
 	
	public CoursesCreationController(CoursesCreationModel m, CoursesCreationView v, Date sysDate) {
		this.model = m;
		this.view = v;
		this.today = sysDate;
		//no hay inicializacion especifica del modelo, solo de la vista
		this.initView();
	}
	
	public float getSessionFloatValue(Object value) {
		if (value != null) {
		    // Covert the value to type float
		    try {
		        float floatValue = Float.parseFloat((String) value);
		        
		        // Verify if the value is not null
		        if (floatValue > 0) {
		            if(floatValue <= 12) {
			        	// Valid input
			        	return floatValue;
		            }else {
		            	error_adding_session = true;
			        	if(!float_error){
			        		add_session_error_message += "- Is not possible that a session has "
			        				+ "more than 12 hours of duration in a single day.\n";		
			        	}
			        	float_error = true;
		            }
		        } else {
		        	error_adding_session = true;
		        	if(!float_error){
		        		add_session_error_message += "- The hours value must be positive.\n";		
		        	}
		        	float_error = true;
		        }
		    } catch (NumberFormatException e) {
		        // JOptionPane.showMessageDialog(null, "The fee, remuneration and hours value must be positive and not null");
		        error = true;
		        if(!float_error){
		        	add_session_error_message += "- The hours value must be positive and not null.\n";
		        }
	        	float_error = true;
		        e.printStackTrace();
		    }
		} else {
		    // Show an error message if the fee is null
		    JOptionPane.showMessageDialog(null, "The hours quantity must be positive and not null");
		    error = true;
		    add_session_error_message += "- The hours quantity must be positive and not null.\n";
		}
		return (float) -1.0;
	}
	
	public float getFloatValue(Object value) {
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
		        	//JOptionPane.showMessageDialog(null, "The fee, remuneration and hours value must be positive");
		        	error = true;
		        	if(!float_error){
		        		error_message += "- The fee, remuneration and hours value must be positive.\n";		
		        	}
		        	float_error = true;
		        }
		    } catch (NumberFormatException e) {
		        // JOptionPane.showMessageDialog(null, "The fee, remuneration and hours value must be positive and not null");
		        error = true;
		        if(!float_error){
		        	error_message += "- The fee, remuneration and hours value must be positive and not null.\n";
		        }
	        	float_error = true;
		        e.printStackTrace();
		    }
		} else {
		    // Show an error message if the fee is null
		    JOptionPane.showMessageDialog(null, "The fee, remuneration and hours quantity must be positive and not null");
		    error = true;
		    error_message += "- The fee, remuneration and hours quantity must be positive and not null.\n";
		}
		return (float) -1.0;
	}
	
	public int getIntegerValue(Object value) {
		if (value != null) {
		    // Covert the value to type float
		    try {
		        float floatValue = Float.parseFloat((String) value);
		        int intValue = (int)floatValue;
		        
		        // Verify if the value is not null
		        if (floatValue > 0) {
		            // Valid input
		        	return intValue;
		        } else {
		            // Show error message if the value is not positive and not null
		        	// JOptionPane.showMessageDialog(null, "Introduce a positive number of places value");
		        	error = true;
		        	error_message += "- Introduce a positive number of places value.\n";
		        }
		    } catch (NumberFormatException e) {
		        // JOptionPane.showMessageDialog(null, "Introduce a number of places value positive and not null");
		        error = true;
	        	error_message += "- Introduce a number of places value positive and not null.\n";
			
		        e.printStackTrace();
		    }
		} else {
		    // Show an error message if the fee is null
		    //JOptionPane.showMessageDialog(null, "Introduce a positive not null value for the number of places value");
		    error = true;
        	error_message += "- Introduce a positive not null value for the number of places value.\n";
		}
		return (int) -1;
	}
	
	public void initView() {
		//Inicializa la fecha de hoy a un valor que permitira mostrar carreras en diferentes fases 
		//y actualiza los datos de la vista
		//view.setFechaHoy("2023-3-14");
		//this.getListaCarreras();
		
		//Abre la ventana (sustituye al main generado por WindowBuilder)
		today = new Date();
		view.getFrame().setVisible(true); 
	}
	
	@SuppressWarnings("unchecked")
	public void setCourseTheachersComboBox() {
		List<TeacherDisplayDTO> teachers = model.getListTeachers();
		ListIterator<TeacherDisplayDTO> teachers_list_iterator = teachers.listIterator();
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
		while(teachers_list_iterator.hasNext()) {
				TeacherDisplayDTO teacher = teachers_list_iterator.next();
				comboBoxModel.addElement(teacher.getTeacher_name() + " " + teacher.getTeacher_surnames());
		}
		view.getcourse_teacher_comboBox().setModel(comboBoxModel);
	}
	
	public int getSelectedCourseTheachersComboBox(int index) {
		return model.getListTeachers().get(index).getTeacher_id();
	}
	
	/**
	 * Inicializacion del controlador: anyade los manejadores de eventos a los objetos del UI.
	 * Cada manejador de eventos se instancia de la misma forma, para que invoque un metodo privado
	 * de este controlador, encerrado en un manejador de excepciones generico para mostrar ventanas
	 * emergentes cuando ocurra algun problema o excepcion controlada.
	 */
	@SuppressWarnings("unchecked")
	public void initController() {	
		view.getCloseButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				view.getFrame().setVisible(false);
			}
		});
		
		view.getDelete_session_NewButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = view.getTable().getSelectedRow();
				if (selectedRow != -1) {
		            // Remove the entry from the JTable
		            ((DefaultTableModel) view.getTable().getModel()).removeRow(selectedRow);
					sessions.remove(selectedRow); // Remove the entry from the List
		            // Remove the entry from the List
		        } else {
					JOptionPane.showMessageDialog(null, "No row selected");
		        }				
			}
		});
		
		view.getAdd_session_NewButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				/********************** Session Data Checkings ***********************/
				
				// Validate that the session place is correctly introduced
				String place = "Online";
				
				if(view.getcourse_modality_comboBox().getSelectedItem().toString().equals("In person")) {
					place = view.getcourse_place_textField().getText();
					if(place.isBlank()) {
						error_adding_session = true;
						// JOptionPane.showMessageDialog(null, "The place of the session needs to be specified");
						add_session_error_message += "- The place of the session needs to be specified.\n";
						try {
							throw new Exception("The place of the session needs to be specified");
						}catch (Exception exp){
							System.err.println(exp.getMessage());
						}
					}
				}
				
				// Storing the session number of hours value and checking for errors
				float n_hours = getSessionFloatValue(view.getformattedTextField().getText());
				
				boolean null_date = false;
				Date session_date = view.getSession_date_dateChooser().getDate();
				if(session_date == null) {
					error_adding_session = true;
					null_date = true;
					//JOptionPane.showMessageDialog(null, "The course enrolment start date must not be null");
					add_session_error_message += "- The session date must not be null.\n";
				}
				
				if(!null_date){ // Checking that the date is after today
					if(session_date.before(today)) {
						try {
							error_adding_session = true;
							date_before_today = true;
							add_session_error_message += "- The introduced session date must be for the future, after: " + today.toString() + ".\n";
							throw new Exception("The introduced session date must be for the future");
						}catch (Exception exp){
							System.err.println(exp.getMessage());
						}
					}
				}			
				
				// Printing all the messages of the current session adding if there is any
				if(error_adding_session) {
					JOptionPane.showMessageDialog(null, add_session_error_message);
					// Initialising again the error variables for other user tries 
					 add_session_error_message = "There were some errors introduced:\n\n";
					 error_adding_session = false;
				}else {
					/********************* Introduce the session data in the sessions List and table *******************************/
					
					// Create a SessionDisplayDTO object
					SessionDisplayDTO s = new SessionDisplayDTO();
					SimpleDateFormat formatter_date = new SimpleDateFormat("yyyy-MM-dd"); // Create a SimpleDateFormat object with the desired pattern
			        String session_date_string = formatter_date.format(session_date);
			        
					SimpleDateFormat formatter_hour = new SimpleDateFormat("HH:mm"); // Create a SimpleDateFormat object with the desired pattern
			        String session_hour_string = formatter_hour.format(session_date);

			        s.setSession_date(session_date_string);
			        s.setSession_hour(session_hour_string);
					s.setSession_hours(n_hours);
					s.setSession_location(place);
					
					// Add the created session to the 
					sessions.add(s);
			        DefaultTableModel model = view.getTable_model();
	                model.addRow(new Object[] { s.getSession_date(), s.getSession_hour(), s.getSession_hours(), s.getSession_location()});

					
				}
				
				
				
			}
		});
		
		view.getcourse_modality_comboBox().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(view.getcourse_modality_comboBox().getSelectedItem().equals("Online")){
					view.getcourse_palce_label().setVisible(false);
					view.getcourse_place_textField().setVisible(false);
				}else {
					view.getcourse_palce_label().setVisible(true);
					view.getcourse_place_textField().setVisible(true);
				}
			}
		});
		
		view.getcourse_modality_comboBox().setModel(new DefaultComboBoxModel(new String[] {"In person", "Online"}));
		
		this.setCourseTheachersComboBox();
		
		view.getAcceptButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
								
				String course_name = view.getCourse_name_textfield().getText(); // Name of the course to be created
				if(course_name.isBlank()){
					//JOptionPane.showMessageDialog(null, "The course must have a name");
					error_message += "- The course must have a name.\n";
					error = true;
					try {
						throw new Exception("The course must have a name");
					}catch (Exception exp){
						System.err.println(exp.getMessage());
					}
				}
				
				// Checking no session introduced
				if(sessions.isEmpty()) {
					error = true;
					error_message += "- The course must have at least one session.\n";
				}
				
				boolean null_date = false; // Variable to check if any date is null introduced
				
				// Storing the course start date and checking null issue
				Date course_start_date = null;
				if(!error) {
					course_start_date = SessionDisplayDTO.getFirstDate(sessions);	
				}
	
				if(course_start_date == null) {
					error = true;
					null_date = true;
					error_message += "- The course do not have a sessions have null dates for the start date.\n" ;
				}
				
				// Storing the course end date and checking null issue
				Date course_end_date = null;
				if(!error) {
					course_end_date = SessionDisplayDTO.getLastDate(sessions);	
				}
				
				if(course_end_date == null) {
					error = true;
					null_date = true;
					error_message += "The course do not have a sessions have null dates for the start date";
				}
				
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");
				
				// Storing the course enrolment start date and checking null issue
				Date course_enrollement_start_date = view.getDate_of_erollement_begining_dateChooser().getDate();
				if(course_enrollement_start_date == null) {
					error = true;
					null_date = true;
					//JOptionPane.showMessageDialog(null, "The course enrolment start date must not be null");
					error_message += "- The course enrolment start date must not be null.\n";
				}
				
				// Storing the course enrolment end date and checking null issue
				Date course_enrollement_end_date = view.getDate_of_enrollement_end_dateChooser().getDate();
				if(course_enrollement_end_date == null) {
					error = true;
					null_date = true;
					//JOptionPane.showMessageDialog(null, "The course enrolment start date must not be null");
					error_message += "- The course enrolment end date must not be null.\n";
				}
				
				/****************DATE CHECKINGS start****************/
				
				if(!null_date){
										
					if(course_enrollement_end_date.before(today)) {
						try {
							error = true;
							date_before_today = true;
							error_message += "- The introduced course enrolment end date must be for the future, after: " + today.toString() + ".\n";
							throw new Exception("The introduced course enrolment end date must be for the future");
						}catch (Exception exp){
							System.err.println(exp.getMessage());
						}
					}
					
					if(course_enrollement_start_date.before(today)) {
						try {
							error = true;
							date_before_today = true;
							error_message += "- The introduced course enrolment start date must be for the future, after: " + today.toString() + ".\n";
							throw new Exception("The introduced course enrolment start date must be for the future");
						}catch (Exception exp){
							System.err.println(exp.getMessage());
						}
					}
					
					if(course_start_date.before(today)) {
						try {
							error = true;
							date_before_today = true;
							error_message += "- The introduced course start date must be for the future, after: " + today.toString() + ".\n";
							throw new Exception("The introduced course start date must be for the future");
						}catch (Exception exp){
							System.err.println(exp.getMessage());
						}
					}
					
					if(course_end_date.before(today)) {
						try {
							error = true;
							date_before_today = true;
							error_message += "- The introduced course end date must be for the future, after: " + today.toString() + ".\n";
							throw new Exception("The introduced course end date must be for the future");
						}catch (Exception exp){
							System.err.println(exp.getMessage());
						}
					}
					
					if(!date_before_today){
					
						// Check that the enrolment period is in the future
						if(course_enrollement_start_date.after(course_start_date)) {
							try {
								error = true;
								// JOptionPane.showMessageDialog(null, "The course enrolment start date must be before the start of the course");
								error_message += "- The course enrolment start date must be before the start of the course.\n"; 
								throw new Exception("The date of the enrollment period must be before the start of the course");
							}catch (Exception exp){
								System.err.println(exp.getMessage());
							}
						}else {
					
							// Check that the enrolment period is before the start of the course
							if(course_enrollement_end_date.after(course_start_date)) {
								error = true;
								// JOptionPane.showMessageDialog(null, "The course enrolment end date must be before the start of the course");
								error_message += "- The course enrolment end date must be before the start of the course.\n";
								try {
									throw new Exception("The date of the enrolment period must be before the start of the course");
								}catch (Exception exp){
									System.err.println(exp.getMessage());
								}
							}else {
					
								// Check that the start enrolment date is before the end of the period
								if(course_enrollement_start_date.after(course_enrollement_end_date)) {
									error = true;
									// JOptionPane.showMessageDialog(null, "The course enrolment start date must be before the end of the enrolment period.");
									error_message += "- The course enrolment start date must be before the end of the enrolment period.\n";
									try {
										throw new Exception("the start enrolment date imust be before the end of the enrolment period");
									}catch (Exception exp){
										System.err.println(exp.getMessage());
									}
								}
							}
						}
					}
					// Show a message if the enrolment start date is after three weeks before the start of the course
					Calendar calendar = Calendar.getInstance(); // Use it to add days to the actual date
			        calendar.setTime(course_enrollement_start_date);
			        // Add 21 days (3 weeks) to the date 
			        calendar.add(Calendar.DAY_OF_YEAR, 21);
			        Date weeks3_later = calendar.getTime();
			        
					if(weeks3_later.after(course_start_date)){
						week3_warning = true;
					}
				}
				
				/****************DATE CHECKINGS end****************/
								
				// Storing the fee value and checking for errors
				float fee = getFloatValue(view.getcourse_fee_formattedTextField().getText());
				
				float n_hours = 0;
				if(!error) {
					// Storing the course number of hours value and checking for errors
					n_hours = SessionDisplayDTO.getTotalNumberOfHours(sessions);	
				}
				
				// Storing the teacher's remuneration value and checking for errors
				float teacher_remuneration = getFloatValue(view.getteachers_remuneration_formattedTextField_2().getText());
				
				// Store the number of available places 
				int n_places = getIntegerValue(view.getCourse_fee_formattedTextField_1().getText());
				
				// Store the id of the selected teacher
				int teacher_selected_index = view.getcourse_teacher_comboBox().getSelectedIndex();
				int course_teacher_id = getSelectedCourseTheachersComboBox(teacher_selected_index);
				
				// Storing the course objectives and checking for errors
				String course_objectives = view.getcourse_description_textArea().getText();
				if(course_objectives.isBlank()) {
					course_objectives = "-";
				}
								
				// Storing the course main contents and checking for errors
				String course_main_contents = view.getcourse_main_contents_textArea().getText(); 
				if(course_main_contents.isBlank()) {
					course_main_contents = "-";
				}
				
				/********************* DATA INTRODUCTION INTO THE DB *************************/
				
				if(!error) { // If there was no error in the introduced data, the course will be introduced in the DB
					if(week3_warning){
						JOptionPane.showMessageDialog(null, "WARNING: the start date of the enrolment period is not 3 weeks before the formative action");
					}
					// Formating to string the course dates 
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Create a SimpleDateFormat object with the desired pattern
			        String course_start_date_string = formatter.format(course_start_date);
			        String course_end_date_string = formatter.format(course_end_date);
			       
			        formatter = new SimpleDateFormat("yyyy-MM-dd"); // Create a SimpleDateFormat object with the desired pattern
			        String course_enrollement_start_date_string = formatter.format(course_enrollement_start_date);
			        
			        formatter = new SimpleDateFormat("yyyy-MM-dd"); // Create a SimpleDateFormat object with the desired pattern
			        String course_enrollement_end_date_string = formatter.format(course_enrollement_end_date);
			        
			        // Inserting the data into the DB
					model.insertNewCourse(course_name, course_start_date_string, course_end_date_string,
							course_enrollement_start_date_string, course_enrollement_end_date_string, fee, 
							n_hours, teacher_remuneration, n_places, course_teacher_id, 
							course_objectives, course_main_contents);
					
					// Inserting the sessions of the course in the DB
					int session_id;
					int course_id = model.getMaxCourseID();
					
					for(SessionDisplayDTO s: sessions) {
						session_id = model.getMaxSessionID() + 1;
						model.insertNewSession(session_id , s.getSession_location(), s.getSession_date(), 
							s.getSession_hour(), s.getSession_hours(), course_id);	
					}
					
					// Create a custom dialog box with an "OK" button
					JDialog dialog = new JDialog();
					JPanel panel = new JPanel();
					JButton okButton = new JButton("OK");

					okButton.addActionListener(new ActionListener() {
					    public void actionPerformed(ActionEvent e) {
					        // Perform some custom action here
					        view.getFrame().setVisible(false);
					        dialog.dispose();
					    }
					});
					
					int number_sessions = model.getNumberOfSessionsOfACourse(course_id);
					panel.add(new JLabel(course_name + " course, that has " + number_sessions + " sessions, has been "
							+ "created sucessfully."));
					panel.add(okButton);
					dialog.add(panel);
					dialog.pack();
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				}else {
					// Display the error message with all the errors that were checked 
					JOptionPane.showMessageDialog(null, error_message);
					error_message = "There were some errors introduced:\n\n";
					float_error = false;
					error = false;
					date_before_today = false;
				}
			}
		});
	}

	public void updateSystemDate(Date system_date) {
		this.today = system_date;
	}

}