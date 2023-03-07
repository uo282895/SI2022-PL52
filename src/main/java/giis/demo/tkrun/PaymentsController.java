package giis.demo.tkrun;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import giis.demo.util.ApplicationException;
import giis.demo.util.SwingMain;
import giis.demo.util.SwingUtil;
import giis.demo.util.Util;

public class PaymentsController {
	
	private PaymentsModel model;
	private PaymentsView view;
	private String lastSelectedKey=""; //recuerda la ultima fila seleccionada para restaurarla cuando cambie la tabla de carreras
	
	
	public PaymentsController(PaymentsModel m, PaymentsView v) {
		this.model = m;
		this.view = v;
		//no hay inicializacion especifica del modelo, solo de la vista
		this.initView();
	}
	
	public void initView() {
		//Inicializa la fecha de hoy a un valor que permitira mostrar carreras en diferentes fases 
		//y actualiza los datos de la vista
		//view.setFechaHoy("2016-11-10");
		this.getListPayments();
		
		//Abre la ventana (sustituye al main generado por WindowBuilder)
		view.getFrame().setVisible(true); 
	}
	
	/**
	 * Inicializacion del controlador: anyade los manejadores de eventos a los objetos del UI.
	 * Cada manejador de eventos se instancia de la misma forma, para que invoque un metodo privado
	 * de este controlador, encerrado en un manejador de excepciones generico para mostrar ventanas
	 * emergentes cuando ocurra algun problema o excepcion controlada.
	 */
	public void initController() {
		
		view.getTablePayments().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int sel = view.getTablePayments().getSelectedRow();
				if (view.getTablePayments().isRowSelected(sel)) {
				    view.getTFAmount().setEnabled(true);
				    view.getTFDate().setEnabled(true);
				    view.getTFHour().setEnabled(true);
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
		
		JButton btnConfirm = view.getBtnConfirm();
		btnConfirm.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        //A dialog appears, it can be a right or a wrong registration
		    	SwingUtil.exceptionWrapper(() -> manageConfirm());
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
		view.getTablePayments().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTablePayments());
		//Como se guarda la clave del ultimo elemento seleccionado, restaura la seleccion de los detalles
		//this.restoreDetail();
	}
	
	public void manageConfirm() {
		//Initializations
		String strquant = "";
		String date = "";
		String hour = "";
		
		//Initializations preventing exceptions
		if (view.getTFAmount().getText().isEmpty()) {
			throw new ApplicationException("Be careful, you must fill the amount gap");
		} else strquant = view.getTFAmount().getText();
		int quant = Integer.parseInt(strquant);
		
		if (view.getTFDate().getText().isEmpty()) {
			throw new ApplicationException("Be careful, you must fill the date gap");
		} else date = view.getTFDate().getText();
		
		if (view.getTFHour().getText().isEmpty()) {
			throw new ApplicationException("Be careful, you must fill the hour gap");
		} else hour = view.getTFHour().getText();
		
		
		//Get the fee of the selected course
		int index = view.getTablePayments().getSelectedRow();//index of the selected row
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
					"Correct registration of the payment", 1);
		} else if (quant == fee && places > 0 && days > 2) {//Wrong, but registered (
			model.insertAmountDate(payid, quant, Util.isoStringToDate(date), Util.isoStringToHour(hour), regid);
			SwingUtil.showMessage("The professional has been correctly registered to the course and he has been assigned a place.\n"
					+ "The payment has been done more than 48 hours after the registration but there where places available in the course", 
					"Correct registration of the payment", 1);
		} else if (quant > fee && days <=2) {//Wrong, but registered (MORE MONEY)
			model.insertAmountDate(payid, quant, Util.isoStringToDate(date), Util.isoStringToHour(hour), regid);
			SwingUtil.showMessage("The professional has been correctly registered to the course and he has been assigned a place."
					+ "In addition, COIIPA must give back the professional the additional money he has paid.", 
					"Correct registration of the payment", 1);
		} else if (quant > fee && places > 0 && days > 2) {//Wrong, but registered (MORE MONEY AND DAYS)
			model.insertAmountDate(payid, quant, Util.isoStringToDate(date), Util.isoStringToHour(hour), regid);
			SwingUtil.showMessage("The professional has been correctly registered to the course and he has been assigned a place."
					+ "The payment has been done more than 48 hours after the registration but there where places available."
					+ "In addition, COIIPA must give back the professional the additional money he has paid." , 
					"Correct registration of the payment", 1);
		} else if (quant < fee) {
			model.insertAmountDate(payid, quant, Util.isoStringToDate(date), Util.isoStringToHour(hour), regid);
			SwingUtil.showMessage("The professional cannot be assigned a place for the course. Please, warn her/him to pay the whole fee", "Wrong data", 0);//WRONG
		} else if (quant >= fee && days > 2 && places == 0) {
			model.insertAmountDate(payid, quant, Util.isoStringToDate(date), Util.isoStringToHour(hour), regid);
			SwingUtil.showMessage("The professional cannot be assigned a place for the course. He/she has paid more than 2 days after the registration date "
					+ "and at this moment there are no free places","Wrong data",0);
		}
	}
}
	
