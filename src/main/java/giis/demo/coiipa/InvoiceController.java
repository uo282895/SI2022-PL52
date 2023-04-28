package giis.demo.coiipa;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


import giis.demo.dto.InvoiceDisplayDTO;
import giis.demo.dto.TeacherInvoiceDisplayDTO;
import giis.demo.util.ApplicationException;
import giis.demo.util.SwingUtil;
import giis.demo.util.Util;

public class InvoiceController {
	
	private InvoiceModel model;
	private InvoiceView view;
	private String lastSelectedKey=""; //remembers the last selected row to show info about it
	private String lastSelectedKey2=""; //remembers the last selected row to show info about it
	
	private Date today;	
	
	//Constructor
	public InvoiceController(InvoiceModel m, InvoiceView v, Date sysDate) {
		this.model = m;
		this.view = v;
		this.today = sysDate;
		//no model initialization but the view
		this.initView();
	}
	
	//init methods (one for each view)
	public void initView() {
		//Sets today's date to the current value (TODAY)
		view.setTodayDate(Util.dateToIsoString(today));
		
		this.getListTeachers();
		
		//Opens the window (instead of the main() generated by WindowBuilder)
		view.getFrame().setVisible(true); 
	}
	
	//Controller initialization (courses)
	public void initController() {
		view.getTableTeachers().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				SwingUtil.exceptionWrapper(() -> getInvoice());
				
				view.getTFAmount().setEnabled(false);
			    view.getTFDate().setEnabled(false);
			    
			    String lsk =SwingUtil.getSelectedKey(view.getTableTeachers());
				int courseId = Integer.parseInt(lsk);
			    
				if(model.hasInvoice(courseId).compareTo("Yes") == 0) {
					view.getTFAmount().setEnabled(true);
				    view.getTFDate().setEnabled(true);
				}
			}
		});
				
		JButton btnCancel = view.getBtnCancel();
		btnCancel.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        view.getFrame().dispose();//Close the window
		    }
		});
		
		JButton btnInvoice = view.getBtnRegister();
		btnInvoice.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        SwingUtil.exceptionWrapper(() -> insertInvoice());
		        SwingUtil.exceptionWrapper(() -> getInvoice());//for automatic refresh
		    }
		});
		
		JButton btnConfirm = view.getBtnConfirm();
		btnConfirm.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        //A dialog appears, it can be a right or a wrong registration
		    	SwingUtil.exceptionWrapper(() -> manageConfirm());
		    	view.getTFAmount().setText("");
		    	view.getTFDate().setText("");
		    }
		});
			
	}
	

	//Method listing all the pending payments coming from the POJO object
	public void getListTeachers() {
		List<TeacherInvoiceDisplayDTO> teachers = model.getListTeachers();
		DefaultTableModel tmodel = SwingUtil.getTableModelFromPojos(teachers, new String[] {"course_id", "teacher_id", "teacher_name", "teacher_surnames", "course_name"});
		Object[] newHeaders = {"Course id", "Teacher id", "Teacher name", "Teacher surnames", "Course name"};
		tmodel.setColumnIdentifiers(newHeaders);
		view.getTableTeachers().setModel(tmodel);
		
		SwingUtil.autoAdjustColumns(view.getTableTeachers());
		
		//Remove the 2 unwanted columns
		TableColumnModel columnModel = view.getTableTeachers().getColumnModel();
		TableColumn column = columnModel.getColumn(0);
		columnModel.removeColumn(column);
		TableColumn other = columnModel.getColumn(0);
		columnModel.removeColumn(other);
	}
	
	
	//Method showing the desired invoice
	public void getInvoice() {
		if (view.getTableTeachers().getSelectedRow() >= 0) {
			//Obtains the selected key (the id of the selected row in this case)
			this.lastSelectedKey=SwingUtil.getSelectedKey(view.getTableTeachers());
			System.out.println(lastSelectedKey);
			int courseId = Integer.parseInt(this.lastSelectedKey);
			
			//Invoice details
			InvoiceDisplayDTO invoice = model.getInvoice(courseId);
			DefaultTableModel tmodel=SwingUtil.getRecordModelFromPojo(invoice, new String[] {"invoice_id", "teacher_name", "teacher_surnames", "fiscal_number","teacher_address", "invoice_quantity"});
			Object[] newHeaders = {"Invoice ID", "Teacher name", "Teacher surnames", "Fiscal number", "Teacher's address", "To pay"};
			for(int i = 0; i < 6; i++) {
				tmodel.setValueAt(newHeaders[i],i,0);
			}
			view.getTableInvoice().setModel(tmodel);
			SwingUtil.autoAdjustColumns(view.getTableInvoice());
		}
	}
	
	//Method listing all the payments done for a specific registration
	public void insertInvoice() {
		if (view.getTableTeachers().getSelectedRow() >= 0) {
			//Obtains the selected key (the id of the selected row in this case)
			this.lastSelectedKey=SwingUtil.getSelectedKey(view.getTableTeachers());
			System.out.println(lastSelectedKey);
			int courseId = Integer.parseInt(this.lastSelectedKey);
			
			this.lastSelectedKey2 = SwingUtil.getSelectedKeySecond(view.getTableTeachers());
			System.out.println(lastSelectedKey2);
			int teacherId = Integer.parseInt(this.lastSelectedKey2);
			
			//Get a correct id (the last one + 1)
			int invid = model.getLastInvoiceId();
			invid++;
			
			//Get the teacher's remuneration
			int rem = model.getRemuneration(courseId);
			
			//INSERT THE INVOICE
			model.InsertInvoice(invid, rem, teacherId, courseId);
			model.updateFinished(courseId);
		}
		else {
			SwingUtil.showMessage("You must select a row of the table.", "Error", 0);
		}
	}

	/*
	* Method encharged of all the database insertions and 
	* messages when registering a new invoice payment
	*/ 
	public void manageConfirm() {
		//Initializations
		String strquant = "";
		String date = "";
		
		int quant = 0;
		
		//Initializations preventing exceptions
		if (view.getTFAmount().getText().isEmpty()) {
			throw new ApplicationException("Be careful, you must fill the amount gap");
		} else strquant = view.getTFAmount().getText();
		boolean containsDot = strquant.contains(".");
		if (containsDot) {
		    String cleanedString = strquant.replace(".", "");
		    quant = Integer.parseInt(cleanedString);
		}else {
			quant = Integer.parseInt(strquant);
		}
		
		if (view.getTFDate().getText().isEmpty()) {
			throw new ApplicationException("Be careful, you must fill the date gap");
		} else date = view.getTFDate().getText();

		
		if (view.getTableTeachers().getSelectedRow() >= 0) { //Valid index
			this.lastSelectedKey = SwingUtil.getSelectedKey(view.getTableTeachers());
			int courseId = Integer.parseInt(this.lastSelectedKey);

			int rem = model.getRemuneration(courseId);
			
			//Get a correct id (the last one + 1)
			int payid = model.getLastPaymentId();
			payid++;
			
			int invid = model.getInvoice(courseId).getInvoice_id();
			
			model.validateDate(Util.isoStringToDate(date));
			
			if (quant == rem) {//The payment of the invoice must be exact
				model.insertPayment(payid, - quant, Util.isoStringToDate(date), invid);
				SwingUtil.showMessage("The invoice has been correctly paid.", "Successful payment of an invoice", 1);
				model.updateState(invid);
				view.getTFAmount().setEnabled(false);
			    view.getTFDate().setEnabled(false);
			}else {
				SwingUtil.showMessage("Please, introduce the correct amount to be paid to the teacher.", "Wrong payment", 0);
			}
		}
	}

	public void updateSystemDate(Date system_date) {
		this.today = system_date;
		
	}
}
