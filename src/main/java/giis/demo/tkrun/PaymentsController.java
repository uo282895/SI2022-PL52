package giis.demo.tkrun;

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
		//this.getListaCarreras();
		
		//Abre la ventana (sustituye al main generado por WindowBuilder)
		view.getFrame().setVisible(true); 
	}
}
