package giis.demo.tkrun;

import java.util.List;

import javax.swing.table.DefaultTableModel;

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
				
		//Abre la ventana (sustituye al main generado por WindowBuilder)
		view.getFrame().setVisible(true); 
	}
	
	
	public void getListRegistrations() {
		List<RegistrationDisplayDTO> registrations = model.getListRegistrations();
		DefaultTableModel tmodel = (DefaultTableModel) SwingUtil.getTableModelFromPojos(registrations, 
				new String[] {"course_name", "reg_name", "reg_surnames", "reg_email", "reg_phone", "reg_date",
						"reg_time"});
		Object[] newHeaders = {"Course name", "Professional name", "Professional surnames", "email", "phone", "Date of registration", "Hour of registration"};
		tmodel.setColumnIdentifiers(newHeaders);
		view.getTable().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTable());
		
	}
	
}
