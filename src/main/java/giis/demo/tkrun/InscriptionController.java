package giis.demo.tkrun;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;

import giis.demo.util.ApplicationException;
import giis.demo.util.SwingUtil;
import giis.demo.util.Util;

public class InscriptionController {
	
	private InscriptionModel insmodel;
	private InscriptionsView insview;
	private String lastSelectedKey="";
	
	public InscriptionController (InscriptionModel m, InscriptionsView v) {
		this.insmodel = m;
		this.insview = v;
		this.initview();
	}
	
	public void initController() {
		//ActionListener define solo un metodo actionPerformed(), es un interfaz funcional que se puede invocar de la siguiente forma:
		//view.getBtnTablaCarreras().addActionListener(e -> getListaCarreras());
		//ademas invoco el metodo que responde al listener en el exceptionWrapper para que se encargue de las excepciones
		//insview.getViewCourses().addMouseListener(new MouseAdapater() {
		//	public void mouseClicked(MouseEvent e) {
				
		//	}
		//});
	
		//En el caso del mouse listener (para detectar seleccion de una fila) no es un interfaz funcional puesto que tiene varios metodos
		//ver discusion: https://stackoverflow.com/questions/21833537/java-8-lambda-expressions-what-about-multiple-methods-in-nested-class
		insview.getListCourses().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//no usa mouseClicked porque al establecer seleccion simple en la tabla de carreras
				//el usuario podria arrastrar el raton por varias filas e interesa solo la ultima
				SwingUtil.exceptionWrapper(() -> updateDetail());
			}
		});
	}
	
	public void initview() {
		this.getListCourses();
		insview.getFrame().setVisible(true); 
	}
	
	public void getListCourses() {
		List<CourseDisplayDTO> courses=insmodel.getListCourses(Util.isoStringToDate(insview.getFechaHoy()));
		DefaultTableModel tmodel= (DefaultTableModel) SwingUtil.getTableModelFromPojos(courses, new String[] {"course_name", "course_date", "place", "course_fee"});
		Object[] newHeader = {"Name", "Date", "Place", "Tax"};
		tmodel.setColumnIdentifiers(newHeader);
		insview.getTableCourses().setModel(tmodel);
		SwingUtil.autoAdjustColumns(insview.getTableCourses());
		
		//Como se guarda la clave del ultimo elemento seleccionado, restaura la seleccion de los detalles
		//this.restoreDetail();
	}
	
	public void insertNewProffessional() {
		String name = insview.getnameField().getText();
		String surnames = insview.getsurnamesField().getText();
		String phone = insview.getphoneField().getText();
		String email = insview.getemailField().getText();
		if (insview.getnameField().getText().isBlank() || insview.getsurnamesField().getText().isBlank() 
				|| insview.getphoneField().getText().isBlank() || insview.getemailField().getText().isBlank()) {
			throw new ApplicationException("Be careful, you must fill all the gaps");
		}
		 int index = insview.getListCourses().getSelectedRow();
		 
		 int places = insmodel.getPlacesCourse(index);
		 if (places > 0) {
			 insmodel.insertNewProffessional(name, surnames, phone, email);
		 } else {
			 throw new ApplicationException("The formative action chosen has no places left");
		 }
	}
	
	public void restoreDetail() {
		//Utiliza la ultimo valor de la clave (que se reiniciara si ya no existe en la tabla)
		this.lastSelectedKey=SwingUtil.selectAndGetSelectedKey(insview.getTableCourses(), this.lastSelectedKey);
		//Si hay clave para seleccionar en la tabla muestra el detalle, si no, lo reinicia
		this.updateDetail();
	}
	
	public void updateDetail() {
		//Obtiene la clave seleccinada y la guarda para recordar la seleccion en futuras interacciones
		this.lastSelectedKey=SwingUtil.getSelectedKey(insview.getTableCourses());
		int idCourse=Integer.parseInt(this.lastSelectedKey);
		;
		
		//Detalles de la carrera seleccionada
		CourseEntity course=insmodel.getCourse(idCourse);
		TableModel tmodel=SwingUtil.getRecordModelFromPojo(course, new String[] {"course_name", "course_date","place", "course_fee"});
		insview.getDetalleCourses().setModel(tmodel);
		SwingUtil.autoAdjustColumns(insview.getDetalleCourses());
	}

}
