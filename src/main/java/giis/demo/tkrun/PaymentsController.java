package giis.demo.tkrun;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
	 * La obtencion de la lista de carreras solo necesita obtener la lista de objetos del modelo 
	 * y usar metodo de SwingUtil para crear un tablemodel que se asigna finalmente a la tabla.
	 */
	public void getListPayments() {
		List<PaymentDisplayDTO> payments = model.getListPayments();
		TableModel tmodel = SwingUtil.getTableModelFromPojos(payments, new String[] {"Course name", "Name", "Surnames", "email", "Course fee", "Date and hour of registration"});
		view.getTablePayments().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTablePayments());
		
		//Como se guarda la clave del ultimo elemento seleccionado, restaura la seleccion de los detalles
		//this.restoreDetail();
	}
	
}
