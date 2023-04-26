package giis.demo.coiipa;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import giis.demo.dto.ReportOfExpensesDisplayDTO;
import giis.demo.util.SwingUtil;

public class ReportOfExpensesController {

	private ReportOfExpensesView view;
	private ReportOfExpensesModel model;
	
	private boolean null_date = false;
	private boolean error = false;
	
	private Date today;
	
	public ReportOfExpensesController(ReportOfExpensesView v, ReportOfExpensesModel m) {
		this.view = v;
		this.model = m;
		this.initView();
	}
	
	public void initView() {
		// Insert the courses data into the JTable
		
		view.getFrame().setVisible(true); 
	}
	
	public void getListExpenses(String date_start, String date_end) {
		List<ReportOfExpensesDisplayDTO> expenses = model.getListExpenses(date_start, date_end);
		DefaultTableModel tmodel = (DefaultTableModel) SwingUtil.getTableModelFromPojos(expenses, 
				new String[] {"course_id", "course_name", "teacher_paid_invoices_sum", "teacher_invoices_sum", 
						"registration_paid_invoices_sum", "registration_invoices_sum"});
		
		Object[] newHeaders = {"ID", "Course name", "Confirmed teachers' outcomes", "Estimated teacher's outcomes", 
				"Confirmed registrations' incomes", "Estimated registrations' incomes"};
		tmodel.setColumnIdentifiers(newHeaders);
		view.getExpenses_JTable().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getExpenses_JTable());
		
		/*Hiding the column of the ID*/
		TableColumnModel columnModel = view.getExpenses_JTable().getColumnModel();
		TableColumn column = columnModel.getColumn(0);
		columnModel.removeColumn(column);
	}
	
	public void initController() {
		view.getSearch_courses_button().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Storing the course enrolment start date and checking null issue
				String error_message = "ERROR in the dates\n";
				error = false;
				null_date = false;
				
				Date start_date = view.getStart_of_timeslot().getDate();
				if(start_date == null) {
					error = true;
					null_date = true;
					error_message += "- You must introduce a start date.\n";
				}
				
				Date end_date = view.getEnd_of_timeslot().getDate();
				if(end_date == null) {
					error = true;
					null_date = true;
					error_message += "- You must introduce a end date.\n";
					}
				
				if(!null_date) { // Checking that the start date is before the end date
					if(start_date.after(end_date)) {
						error = true;
						JOptionPane.showMessageDialog(null, "The start date must be before the end date");
					}
				}else {
					JOptionPane.showMessageDialog(null, error_message);
				}
				
				if(!error && !null_date) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Create a SimpleDateFormat object with the desired pattern
			        String start_date_string = formatter.format(start_date);
			        String end_date_string = formatter.format(end_date);
					getListExpenses(start_date_string, end_date_string);
				}
				
			}
		});
			
		
		view.getClose_button() .addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				view.getFrame().setVisible(false); 
			}
		});
	
	}

	public void updateSystemDate(Date system_date) {
		this.today = system_date;
	}
	
	
	
}
