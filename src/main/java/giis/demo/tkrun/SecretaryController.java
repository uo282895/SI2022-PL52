package giis.demo.tkrun;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;


import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import giis.demo.util.ApplicationException;
import giis.demo.util.SwingUtil;
import giis.demo.util.Util;

public class SecretaryController {
	
	private SecretaryModel model;
	private PaymentsView viewPayments;
	private CoursesView viewCourses;
	private String lastSelectedKey=""; //recuerda la ultima fila seleccionada para restaurarla cuando cambie la tabla de carreras
	
	//Constructors (one for each view)
	public SecretaryController(SecretaryModel m, PaymentsView v) {
		this.model = m;
		this.viewPayments = v;
		//no model initialization but the view
		this.initViewPayments();
	}
	
	public SecretaryController(SecretaryModel m, CoursesView v) {
		this.model = m;
		this.viewCourses = v;
		//no model initialization but the view
		this.initViewCourses();
	}
	
	//init methods (one for each view)
	public void initViewPayments() {
		//Inicializa la fecha de hoy a un valor que permitira mostrar carreras en diferentes fases 
		//y actualiza los datos de la vista
		LocalDate localdate = LocalDate.now();
		Date today = Date.from(localdate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		viewPayments.setTodayDate(Util.dateToIsoString(today));
		
		this.getListPayments();
		
		//Abre la ventana (sustituye al main generado por WindowBuilder)
		viewPayments.getFrame().setVisible(true); 
	}
	
	public void initViewCourses() {
		this.getListCourses();
		
		//Opens the window (instead of the main() generated by WindowBuilder)
		viewCourses.getFrame().setVisible(true); 
	}
	
	/**
	 * Inicializacion del controlador: anyade los manejadores de eventos a los objetos del UI.
	 * Cada manejador de eventos se instancia de la misma forma, para que invoque un metodo privado
	 * de este controlador, encerrado en un manejador de excepciones generico para mostrar ventanas
	 * emergentes cuando ocurra algun problema o excepcion controlada.
	 */
	public void initControllerPayments() {
		
		//recharge the data of the table after a correct inscription
		viewPayments.getBtnRefresh().addActionListener(e -> SwingUtil.exceptionWrapper(() -> getListPayments()));
		
		viewPayments.getTablePayments().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int sel = viewPayments.getTablePayments().getSelectedRow();
				if (viewPayments.getTablePayments().isRowSelected(sel)) {
				    viewPayments.getTFAmount().setEnabled(true);
				    viewPayments.getTFDate().setEnabled(true);
				    viewPayments.getTFHour().setEnabled(true);
				}
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
		    }
		});
	}
	
	public void initControllerCourses() {
		//En el caso del mouse listener (para detectar seleccion de una fila) no es un interfaz funcional puesto que tiene varios metodos
		//ver discusion: https://stackoverflow.com/questions/21833537/java-8-lambda-expressions-what-about-multiple-methods-in-nested-class
		viewCourses.getTableCourses().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//no usa mouseClicked porque al establecer seleccion simple en la tabla de carreras
				//el usuario podria arrastrar el raton por varias filas e interesa solo la ultima
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
	
	
	/**
	 * La obtencion de la lista de carreras solo necesita obtener la lista de objetos del modelo 
	 * y usar metodo de SwingUtil para crear un tablemodel que se asigna finalmente a la tabla.
	 */
	public void getListPayments() {
		List<PaymentDisplayDTO> payments = model.getListPayments();
		DefaultTableModel tmodel = SwingUtil.getTableModelFromPojos(payments, new String[] {"course_name", "reg_name", "reg_surnames", "reg_email", "course_fee", "reg_date","reg_time"});
		Object[] newHeaders = {"Course name", "Professional name", "Professional surnames", "email", "Course fee", "Date of registration", "Hour of registration"};
		tmodel.setColumnIdentifiers(newHeaders);
		viewPayments.getTablePayments().setModel(tmodel);
		SwingUtil.autoAdjustColumns(viewPayments.getTablePayments());
		//Como se guarda la clave del ultimo elemento seleccionado, restaura la seleccion de los detalles
		//this.restoreDetail();
	}
	
	public void getListCourses() {
		List<CourseDisplayDTO> courses = model.getListCourses();
		DefaultTableModel tmodel = SwingUtil.getTableModelFromPojos(courses, new String[] {"course_id","course_name", "course_state", "course_start_period", "course_end_period", "total_places", "available_places","course_date","course_time"});
		Object[] newHeaders = {"Course id", "Course name", "Status", "Start of enrollement period", "End of enrollement period", "Total places", "Places left", "Course date", "Course starting time"};
		tmodel.setColumnIdentifiers(newHeaders);
		viewCourses.getTableCourses().setModel(tmodel);
		SwingUtil.autoAdjustColumns(viewCourses.getTableCourses());
		//Como se guarda la clave del ultimo elemento seleccionado, restaura la seleccion de los detalles
		//this.restoreDetail();
	}
	
	public void updateDetailCourses() {
		//Obtiene la clave seleccinada y la guarda para recordar la seleccion en futuras interacciones
		this.lastSelectedKey=SwingUtil.getSelectedKey(viewCourses.getTableCourses());
		int courseId = Integer.parseInt(this.lastSelectedKey);
		
		//Course details
		CourseInfoDisplayDTO course = model.getCourse(courseId);
		DefaultTableModel tmodel=SwingUtil.getRecordModelFromPojo(course, new String[] {"objectives", "description", "place", "teacher_name","teacher_surnames"});
		Object[] newHeaders = {"Objectives", "Contents", "Place", "Teacher name", "Teacher surnames"};
		for(int i = 0; i < 5; i++) {
			tmodel.setValueAt(newHeaders[i],i,0);
		}
		viewCourses.getTableMore().setModel(tmodel);
		SwingUtil.autoAdjustColumns(viewCourses.getTableMore());
	}
	
	public void manageConfirm() {
		//Initializations
		String strquant = "";
		String date = "";
		String hour = "";
		
		//Initializations preventing exceptions
		if (viewPayments.getTFAmount().getText().isEmpty()) {
			throw new ApplicationException("Be careful, you must fill the amount gap");
		} else strquant = viewPayments.getTFAmount().getText();
		int quant = Integer.parseInt(strquant);
		
		if (viewPayments.getTFDate().getText().isEmpty()) {
			throw new ApplicationException("Be careful, you must fill the date gap");
		} else date = viewPayments.getTFDate().getText();
		
		if (viewPayments.getTFHour().getText().isEmpty()) {
			throw new ApplicationException("Be careful, you must fill the hour gap");
		} else hour = viewPayments.getTFHour().getText();
		
		
		//Get the fee of the selected course
		int index = viewPayments.getTablePayments().getSelectedRow();//index of the selected row
		int fee = -1;
		String courseName = "";
		String regDate = "";
		String regName = "";
		if (index >=0) {//No errors if the fields are not selected
			fee = model.getListPayments().get(index).getCourse_fee();
			courseName = model.getListPayments().get(index).getCourse_name();
			regDate = model.getListPayments().get(index).getReg_date();
			regName = model.getListPayments().get(index).getReg_name();
		}
		
		//Get the course places, depending on which course the registration is associated
		int regid = model.getRegId(courseName, Util.isoStringToDate(regDate), regName).getReg_id();
		int places = model.getPlacesCourse(regid);
		
		//Get a correct id (the last one + 1)
		int payid = model.getLastPaymentId();
		if (Objects.isNull(payid)) {
			payid = 1;
		}else payid++;
		
		double days = model.differenceDates(Util.isoStringToDate(regDate), Util.isoStringToDate(date));
		
		//All the different possibilities according to courses
		if (quant == fee && days <= 2) {//CORRECT, but there are more restrictions
			model.insertAmountDate(payid, quant, Util.isoStringToDate(date), Util.isoStringToHour(hour), regid);
			SwingUtil.showMessage("The professional has been correctly registered to the course and he has been assigned a place", 
					"Correct registration of the payment.", 1);
		} else if (quant == fee && places > 0 && days > 2) {//Wrong, but registered (
			model.insertAmountDate(payid, quant, Util.isoStringToDate(date), Util.isoStringToHour(hour), regid);
			SwingUtil.showMessage("The professional has been correctly registered to the course and he has been assigned a place.\n"
					+ "The payment has been done more than 48 hours after the registration but there where places available in the course", 
					"Correct registration of the payment.", 1);
		} else if (quant > fee && days <=2) {//Wrong, but registered (MORE MONEY)
			model.insertAmountDate(payid, quant, Util.isoStringToDate(date), Util.isoStringToHour(hour), regid);
			SwingUtil.showMessage("The professional has been correctly registered to the course and he has been assigned a place."
					+ "In addition, COIIPA must give back the professional the additional money he has paid.", 
					"Correct registration of the payment.", 1);
		} else if (quant > fee && places > 0 && days > 2) {//Wrong, but registered (MORE MONEY AND DAYS)
			model.insertAmountDate(payid, quant, Util.isoStringToDate(date), Util.isoStringToHour(hour), regid);
			SwingUtil.showMessage("The professional has been correctly registered to the course and he has been assigned a place."
					+ "The payment has been done more than 48 hours after the registration but there where places available."
					+ "In addition, COIIPA must give back the professional the additional money he has paid." , 
					"Correct registration of the payment.", 1);
		} else if (quant < fee) {
			model.insertAmountDate(payid, quant, Util.isoStringToDate(date), Util.isoStringToHour(hour), regid);
			SwingUtil.showMessage("The professional cannot be assigned a place for the course. Please, warn her/him to pay the whole fee", "Wrong data", 0);//WRONG
		} else if (quant >= fee && days > 2 && places == 0) {
			model.insertAmountDate(payid, quant, Util.isoStringToDate(date), Util.isoStringToHour(hour), regid);
			SwingUtil.showMessage("The professional cannot be assigned a place for the course. He/she has paid more than 2 days after the registration date "
					+ "and at this moment there are no free places.","Wrong data",0);
		}
	}
}
	
