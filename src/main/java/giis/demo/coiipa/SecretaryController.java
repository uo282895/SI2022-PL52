package giis.demo.coiipa;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import giis.demo.dto.CourseDisplayDTO;
import giis.demo.dto.CourseInfoDisplayDTO;
import giis.demo.dto.PaymentAdditionalDisplayDTO;
import giis.demo.dto.PaymentDisplayDTO;
import giis.demo.util.ApplicationException;
import giis.demo.util.SwingUtil;
import giis.demo.util.Util;

public class SecretaryController{
	
	private SecretaryModel model;
	private PaymentsView viewPayments;
	private CoursesView viewCourses;
	private String lastSelectedKey=""; //remembers the last selected row to show info about it

	private Date today;
	
	//Constructors (one for each view)
	public SecretaryController(SecretaryModel m, PaymentsView v, Date sysDate) {
		this.model = m;
		this.viewPayments = v;
		this.today = sysDate;
		//no model initialization but the view
		this.initViewPayments();
	}
	
	public SecretaryController(SecretaryModel m, CoursesView v, Date sysDate) {
		this.model = m;
		this.viewCourses = v;
		this.today = sysDate;
		//no model initialization but the view
		this.initViewCourses();
	}
	
	//init methods (one for each view)
	public void initViewPayments() {
		viewPayments.setTodayDate(Util.dateToIsoString(today));
		
		this.getListPayments();
		
		//Opens the window (instead of the main() generated by WindowBuilder)
		viewPayments.getFrame().setVisible(true); 
	}
	
	public void initViewCourses() {
		viewCourses.setTodayDate(Util.dateToIsoString(today));
		
		this.getListCourses();
		
		//Opens the window (instead of the main() generated by WindowBuilder)
		viewCourses.getFrame().setVisible(true); 
	}
	
	
	//Controller initialization (payments)
	public void initControllerPayments() {
		//recharge the data of the table after changing the value of the combo box
		viewPayments.getcbType().addActionListener(e -> SwingUtil.exceptionWrapper(() -> getListPayments()));
				
		viewPayments.getTablePayments().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//Default initialization of the buttons (disabled)
				viewPayments.getTFAmount().setEnabled(false);
			    viewPayments.getTFDate().setEnabled(false);
			    
				int sel = viewPayments.getTablePayments().getSelectedRow();//index of the table selected
				int regid = getRegIdUtil();//get the ID of a registration
				String state = model.getRegistration(regid).getReg_state();//state of a registration
				
				if (viewPayments.getTablePayments().isRowSelected(sel) && (state.compareTo("Received")==0 || 
						state.compareTo("Incomplete")==0 || state.compareTo("Compensate")==0 || 
						state.compareTo("Cancelled - Compensate")==0 || state.compareTo("Confirmed - Profpay")==0
						|| state.compareTo("Cancelled - Profpay")==0)) {
					if(viewPayments.getcbType().getSelectedIndex()!=0) {
					    viewPayments.getTFAmount().setEnabled(true);
					    viewPayments.getTFDate().setEnabled(true);
					}
				}
				
				SwingUtil.exceptionWrapper(()-> getListPaymentsAdditional());
			}
		});
		
		JButton btnCancel = viewPayments.getBtnCancel();
		btnCancel.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        viewPayments.getFrame().dispose();//Close the window
		    }
		});
		
		JButton btnConfirm = viewPayments.getBtnConfirm();
		btnConfirm.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        //A dialog appears, it can be a right or a wrong registration
		    	SwingUtil.exceptionWrapper(() -> manageConfirm());
		    	SwingUtil.exceptionWrapper(() -> getListPayments());
		    	viewPayments.getTFAmount().setText("");
		    	viewPayments.getTFDate().setText("");
		    }
		});
	}
	
	//Controller initialization (courses)
	public void initControllerCourses() {
		viewCourses.getTableCourses().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				SwingUtil.exceptionWrapper(() -> updateDetailCourses());
			}
		});
		
		JButton btnCancel = viewCourses.getBtnOK();
		btnCancel.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        viewCourses.getFrame().dispose();//Close the window
		    }
		});
	}
	
	
	//Method listing all the pending payments coming from the POJO object
	public void getListPayments() {
		String state = (String) viewPayments.getcbType().getSelectedItem();
		List<PaymentDisplayDTO> payments = model.getListPayments(state, today);
		DefaultTableModel tmodel = SwingUtil.getTableModelFromPojos(payments, new String[] {"course_name", "reg_name", "reg_surnames", "reg_email", "course_fee", "reg_date"});
		Object[] newHeaders = {"Course name", "Professional name", "Professional surnames", "email", "Course fee", "Date of registration"};
		tmodel.setColumnIdentifiers(newHeaders);
		viewPayments.getTablePayments().setModel(tmodel);
		
		SwingUtil.autoAdjustColumns(viewPayments.getTablePayments());
	}
	
	//Method listing all the payments done for a specific registration
	public void getListPaymentsAdditional() {
		List <PaymentAdditionalDisplayDTO> paymentsadd = model.getListPaymentsAdditional(model.getCourseId(getRegIdUtil()), getRegIdUtil());
		DefaultTableModel tmodel = SwingUtil.getTableModelFromPojos(paymentsadd, new String[] {"amount","payment_date","payment_type"});
		Object[] newHeaders = {"Amount","Payment date","Payment type"};
		tmodel.setColumnIdentifiers(newHeaders);
		viewPayments.getTableAdditionalPayments().setModel(tmodel);
		
		SwingUtil.autoAdjustColumns(viewPayments.getTableAdditionalPayments());
		
		//Hide the 3rd column, not necessary
		TableColumnModel columnModel = viewPayments.getTableAdditionalPayments().getColumnModel();
		TableColumn column = columnModel.getColumn(2);
		columnModel.removeColumn(column);
	}
	
	//Method listing all the courses coming from the POJO object
	public void getListCourses() {
		List<CourseDisplayDTO> courses = model.getListCourses(today);
		DefaultTableModel tmodel = SwingUtil.getTableModelFromPojos(courses, new String[] {"course_id","course_name", "course_state", "course_start_period", "course_end_period", "total_places", "available_places","course_start_date"});
		Object[] newHeaders = {"Course id", "Course name", "Status", "Start of enrollement period", "End of enrollement period", "Total places", "Places left", "Course start date"};
		tmodel.setColumnIdentifiers(newHeaders);
		viewCourses.getTableCourses().setModel(tmodel);
		//Hide the column of the course id (the user doesn't want to see it)
		TableColumnModel columnModel = viewCourses.getTableCourses().getColumnModel();
		TableColumn column = columnModel.getColumn(0);
		columnModel.removeColumn(column);
		
		SwingUtil.autoAdjustColumns(viewCourses.getTableCourses());
	}
	
	
	//Updates the additional info grid depending on the selection of the course (upper table)
	public void updateDetailCourses() {
		//Obtains the selected key (the id of the selected row in this case)
		this.lastSelectedKey=SwingUtil.getSelectedKey(viewCourses.getTableCourses());
		int courseId = Integer.parseInt(this.lastSelectedKey);
		
		//Course details
		CourseInfoDisplayDTO course = model.getCourse(courseId);
		DefaultTableModel tmodel=SwingUtil.getRecordModelFromPojo(course, new String[] {"objectives", "description", "teacher_name","teacher_surnames"});
		Object[] newHeaders = {"Objectives", "Contents", "Teacher name", "Teacher surnames"};
		for(int i = 0; i < 4; i++) {
			tmodel.setValueAt(newHeaders[i],i,0);
		}
		viewCourses.getTableMore().setModel(tmodel);
		SwingUtil.autoAdjustColumns(viewCourses.getTableMore());
	}
	
	
	/*
	* Method encharged of all the database insertions and 
	* messages when registering a new payment
	*/ 
	public void manageConfirm() {
		//Initializations
		String strquant = "";
		String date = "";
		
		int quant = 0;
		
		//Initializations preventing exceptions
		if (viewPayments.getTFAmount().getText().isEmpty()) {
			throw new ApplicationException("Be careful, you must fill the amount gap");
		} else strquant = viewPayments.getTFAmount().getText();
		boolean containsDot = strquant.contains(".");
		if (containsDot) {
		    String cleanedString = strquant.replace(".", "");
		    quant = Integer.parseInt(cleanedString);
		}else {
			quant = Integer.parseInt(strquant);
		}
		
		if (quant <= 0) {
			throw new ApplicationException("Please, input a positive amount.");
		}
		
		if (viewPayments.getTFDate().getText().isEmpty()) {
			throw new ApplicationException("Be careful, you must fill the date gap");
		} else date = viewPayments.getTFDate().getText();
		
		
		String state = (String) viewPayments.getcbType().getSelectedItem();
		
		//Get the fee of the selected course
		int index = viewPayments.getTablePayments().getSelectedRow();//index of the selected row
		int fee = -1;
		String courseName = "";
		String regDate = "";
		String regName = "";
		String regSurnames = "";
		if (index >=0) {//No errors if the fields are not selected
			fee = model.getListPayments(state,today).get(index).getCourse_fee();
			courseName = model.getListPayments(state,today).get(index).getCourse_name();
			regDate = model.getListPayments(state,today).get(index).getReg_date();
			regName = model.getListPayments(state,today).get(index).getReg_name();
			regSurnames = model.getListPayments(state,today).get(index).getReg_surnames();
		}
		
		//Get the course places, depending on which course the registration is associated
		int regid = model.getRegId(courseName, Util.isoStringToDate(regDate), regName).getReg_id();
		int courseid = model.getCourseId(regid);
		int places = model.getPlacesCourse(today, courseid);
		System.out.println(today);
		
		//Get a correct id (the last one + 1)
		int payid = model.getLastPaymentId();
		payid++;
		
		//True if the difference is smaller or equal than 48 hours
		boolean days = model.compareDates(Util.isoStringToDate(regDate), Util.isoStringToDate(date));
		
		int totalamount = model.getAmountPaid(regid);
		
		
/***********************************************Process of inserting all the payments**********************************/
		
		if (state.compareTo("Confirmed") == 0) { //Only for compensations
			model.validateDate(Util.isoStringToDate(date), regid, today);
			
			if (model.getRegistration(regid).getReg_state().compareTo("Confirmed - Profpay")==0){
				model.insertPaymentReg(payid, quant, Util.isoStringToDate(date), regid);
			}else {
				model.insertPaymentDev(payid, quant, Util.isoStringToDate(date), regid);
			}
			
		}else if (state.compareTo("Cancelled") == 0){ //Cancellations
			model.validateDate(Util.isoStringToDate(date), regid, today);
			
			if (model.getRegistration(regid).getReg_state().compareTo("Cancelled - Profpay")==0){
				model.insertPaymentCancDev(payid, quant, Util.isoStringToDate(date), regid);
			}else {
				model.insertPaymentCanc(payid, quant, Util.isoStringToDate(date), regid);
			}
			
		}
		else if (places > 0){ //Normal payment
			model.validateDate(Util.isoStringToDate(date), regid, today);
			model.insertPaymentReg(payid, quant, Util.isoStringToDate(date), regid);
		}
		else {
			model.validateDate(Util.isoStringToDate(date), regid, today);
			SwingUtil.showMessage("The professional cannot be assigned a place for the course because there are no places left.\n"
					+ "The money paid will be given back by cash.", "Course full", 0);//WRONG
			model.updateFull(regid); 
		}
		
		totalamount = model.getAmountPaid(regid);
		
		//All the different possibilities according to courses
		if (state.compareTo("Confirmed")==0) {//CORRECT
			if (totalamount  == fee) {
				SwingUtil.showMessage("The money has been correctly compensated.", 
						"Correct compensation of the payment", 1);
				model.updateCompToCorrect(regid);
			}else if (totalamount > fee) {
				SwingUtil.showMessage("The college must compensate the professional again.\n"
						+ "COIIPA must give back " + Integer.toString(totalamount - fee) + " €.\n"
								+ "This is due to an incomplete compensation.", 
						"Incomplete compensation of the payment", 0);
				model.updateComp(regid);
			}else {//The college pays more than the expected, so the prof must pay back again
				SwingUtil.showMessage("The college has compensated more money than expected.\n"
						+ "You must tell the professional to pay back " + Integer.toString(fee - totalamount) + " €.\n"
								+ "This is due to a wrong compensation (more money paid).", 
								"Wrong compensation of the payment", 0);
				model.updateCompToProfPay(regid);
			}
		}
		else if (state.compareTo("Cancelled") == 0) {
			int togive = (int) RegistrationCancellationController.getAmountPaid();
			int totalcanc = model.getCancAmount(regid);
			System.out.println(togive);
			System.out.println(totalcanc);
			
			if (-totalcanc == togive) {
				SwingUtil.showMessage("The professional has received the money after cancelling its registration to a course.\n"
						+ "The money paid will be given back, according to the criteria.", "Cancellation paid", 1);
				model.updateCompToCancelled(regid);
			}
			else if (-totalcanc < togive) {
				SwingUtil.showMessage("The college must compensate the cancellation again.\n"
						+ "COIIPA must give back now " + Integer.toString(togive + totalcanc) + " €.\n"
								+ "This is due to an incomplete compensation.", 
						"Incomplete compensation of the payment", 0);
				model.updateCancComp(regid);
			}else {//The college pays more than the expected, so the prof must pay back again
				SwingUtil.showMessage("The college has compensated more money than expected.\n"
						+ "You must tell the professional to pay back " + Integer.toString(-totalcanc - togive) + " €.\n"
								+ "This is due to a wrong compensation (more money paid).", 
								"Wrong compensation of the payment", 0);
				model.updateCancToProfPay(regid);
			}
		}else if(model.getRegistration(regid).getReg_state().compareTo("Full")==0){
			return;
		}
		else if (totalamount == fee && days) {//CORRECT
			SwingUtil.showMessage("The professional has been correctly registered to the course and he has been assigned a place.", 
					"Correct registration of the payment", 1);
			model.updateTable(regid);
			model.sendMail(regName, regSurnames, courseName);
		} else if (totalamount == fee && !days) {//CORRECT
			SwingUtil.showMessage("The professional has been correctly registered to the course and he has been assigned a place.\n"
					+ "The payment has been done more than 48 hours after the registration but there where available places in the course.", 
					"Correct registration of the payment", 1);
			model.updateTable(regid);
			model.sendMail(regName, regSurnames, courseName);
		} else if (totalamount > fee && days) {//CORRECT
			SwingUtil.showMessage("The professional has been correctly registered to the course and he has been assigned a place.\n"
					+ "In addition, COIIPA must give back the professional the additional money he has paid: " + Integer.toString(totalamount - fee) + " €", 
					"Correct registration of the payment", 1);
			model.updateComp(regid);
			model.sendMail(regName, regSurnames, courseName);
		} else if (totalamount > fee && !days) {//CORRECT
			SwingUtil.showMessage("The professional has been correctly registered to the course and he has been assigned a place.\n"
					+ "The payment has been done more than 48 hours after the registration but there were available places."
					+ "In addition, COIIPA must give back the professional the additional money he has paid: " + Integer.toString(totalamount - fee) + " €", 
					"Correct registration of the payment", 1);
			model.updateComp(regid);
			model.sendMail(regName, regSurnames, courseName);
		}
		else if (totalamount < fee) { //Paying less
			int option = JOptionPane.showConfirmDialog(null, "The whole fee is not being paid. Are you sure that this is the correct amount?", "Confirmation", JOptionPane.YES_NO_OPTION);

			if (option == JOptionPane.YES_OPTION) {
			    // perform the operation
				model.updateWrong(regid);
				SwingUtil.showMessage("The professional cannot be assigned a place for the course. Please, warn her/him to pay the whole fee.", "Wrong data", 0);//WRONG
			}
		}
	}
	
	//method to get the registration id from some values selected on the table
	public int getRegIdUtil() {
		int sel = viewPayments.getTablePayments().getSelectedRow();//index of the table selected
		String state = (String) viewPayments.getcbType().getSelectedItem();//state selected on the table
		
		String courseName = "";
		String regDate = "";
		String regName = "";
		if (sel >=0) {//No errors if the fields are not selected
			courseName = model.getListPayments(state,today).get(sel).getCourse_name();
			regDate = model.getListPayments(state,today).get(sel).getReg_date();
			regName = model.getListPayments(state, today).get(sel).getReg_name();
		}
		return model.getRegId(courseName, Util.isoStringToDate(regDate), regName).getReg_id();
	}
	
	public void updateSystemDate(Date system_date) {
		this.today = system_date;
		
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String isoString = outputFormat.format(today);
	    Date isoDate = Util.isoStringToDate(isoString);
		this.today = isoDate;
	}
}
	
