package giis.demo.coiipa;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import giis.demo.dto.RegistrationDisplayDTO;
import giis.demo.util.SwingUtil;

public class RegistrationCancellationController {

	private RegistrationCancellationModel model;
	private RegistrationCancellationView view;
	
	public RegistrationCancellationController(RegistrationCancellationModel model, RegistrationCancellationView view) {
		super();
		this.model = model;
		this.view = view;
		this.initView();
		
	}
		
	public void initView() {
		// Insert the registrations data into the JTable
		this.getListRegistrations();
		this.initController();
		/*
		TableModel selected_index = view.getTable().getModel();
		String course_selected = (String)selected_index.getValueAt(0, 0);
		view.getSelected_registration_label().setText(course_selected);
		*/
		//Abre la ventana (sustituye al main generado por WindowBuilder)
		view.getFrame().setVisible(true); 
	}
	
	
	public void getListRegistrations() {
		List<RegistrationDisplayDTO> registrations = model.getListRegistrations();
		DefaultTableModel tmodel = (DefaultTableModel) SwingUtil.getTableModelFromPojos(registrations, 
				new String[] {"reg_id", "course_name", "reg_name", "reg_surnames", "reg_email", "reg_phone", "reg_date",
						"reg_time", "course_date", "course_time", "amount"});
		Object[] newHeaders = {"ID", "Course name", "Professional name", "Professional surnames", "email", "phone", "Date of registration", "Hour of registration",
				"Course start date", "Course start hour", "amount paid"};
		tmodel.setColumnIdentifiers(newHeaders);
		view.getTable().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTable());
	}
	
	
	public void initController() {
		
		
		view.getClosebutton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				view.getFrame().setVisible(false);
			}
		});
		
		
		view.getAcept_cancellation_JButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				double refund = 1.0;
				int sel = view.getTable().getSelectedRow();
				
				// Check that the registration was selected in the JTable
				if(sel < 0) {
					try {
						JOptionPane.showMessageDialog(null, "A registration must be selected");
						throw new Exception("A registration must be selected");
					}catch (Exception exp){
						System.err.println(exp.getMessage());
					}
				}
				
				Date cancellation_date = view.getCancellation_dateJDateChooser().getDate();
				
				// Check that the introduced date is not null
				if(cancellation_date == null) {
					try {
						JOptionPane.showMessageDialog(null, "The cancellation date must be introduced");
						throw new Exception("The cancellation date must be introduced");
					}catch (Exception exp){
						System.err.println(exp.getMessage());
					}
				}
				
				DateTimeFormatter formatter_date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String course_start_date_string = (String)view.getTable().getValueAt(sel, 8); // Course start date in String
			    LocalDate course_start = LocalDate.parse(course_start_date_string, formatter_date); // Course end time in LocalTime
			    
			    Calendar course_s = Calendar.getInstance();
			    course_s.set(Calendar.YEAR, course_start.getYear());
			    course_s.set(Calendar.MONTH, course_start.getMonthValue() - 1); // Month value is zero-based in Calendar
			    course_s.set(Calendar.DAY_OF_MONTH, course_start.getDayOfMonth());
			    
			    Date course_star = course_s.getTime();
			    
				/************************ DATE CHECKINGS **********************************/
				Calendar seven_c = Calendar.getInstance();
				seven_c.setTime(cancellation_date);
				seven_c.add(Calendar.DAY_OF_YEAR, 7);
				Date seven_cancel = seven_c.getTime();
				
				Calendar three_c = Calendar.getInstance();
				three_c.setTime(cancellation_date);
				three_c.add(Calendar.DAY_OF_YEAR, 3);
				Date three_cancel = three_c.getTime();
							
			    if(seven_cancel.before(course_star)) {
					refund = 1.0; // cancellation before 7 days of the course start
				}else {
					if(!three_cancel.before(course_star)) {
						refund = 0.0; // cancellation between 6 and 3 calendar days of the course start
					}else {
						refund = 0.5; // cancellation after 3 days of the course start
					}
				}
				
			    int reg_id = (int)view.getTable().getValueAt(sel, 0);
			    model.insertCancellation(reg_id); // Updating the DB with the cancelled registration
			    
			    double amount_paid = (double)view.getTable().getValueAt(sel, 10); // Amount paid by the Professional for the course enrol	    
			    JOptionPane.showMessageDialog(null, "The refund amount must be " + refund*100 + "% of the payed fee, that is " + refund*amount_paid + "€");
				
				view.getFrame().setVisible(false);
			}
		});
		
		view.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				view.getCancellation_dateJDateChooser().setCalendar(null);
				int sel = view.getTable().getSelectedRow();
				int id = (int)view.getTable().getValueAt(sel, 0);
				String course_name = (String)view.getTable().getValueAt(sel, 1); // Get the course name from the table
				String reg_name = (String)view.getTable().getValueAt(sel, 2); // Get the Professional name from the table
				String reg_surname = (String)view.getTable().getValueAt(sel, 3); // Get the Professional surname from the table
				view.getSelected_registration_label().setText("ID " + id + " from "+ reg_name + " "
				+ reg_surname + " for " + course_name + " course");
			}
		});
	}
	
	
	
}
