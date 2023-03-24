package giis.demo.tkrun;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

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
		insview.getConfirm().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to formalize the inscription?"
						+ "\nThis is not reversible and will generate a debt according to the price of the selected formative action.");
				if (confirm == JOptionPane.YES_OPTION) {
					insertNewProffessional();
					insview.getFrame().dispose();
				};
			}
		});
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
		DefaultTableModel tmodel= (DefaultTableModel) SwingUtil.getTableModelFromPojos(courses, new String[] {"course_id", "course_name", "course_date", "place", "course_fee"});
		Object[] newHeader = {"ID", "Name", "Date", "Place", "Tax"};
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
		String date = insview.getFechaHoy();
		String time = "12:00:00";
		String state = "Received";
		int course_id = insview.getTableCourses().getSelectedRow();
		if (insview.getnameField().getText().isBlank() || insview.getsurnamesField().getText().isBlank() 
				|| insview.getphoneField().getText().isBlank() || insview.getemailField().getText().isBlank()) {
			throw new ApplicationException("Be careful, you must fill all the gaps");
		}
		 int index = insview.getListCourses().getSelectedRow();
		 
		 int places = insmodel.getPlacesCourse(index);
		 if (places > 0) {
			 insmodel.insertNewProffessional(1, name, surnames, phone, email, date, time, state, course_id);
			 System.out.println("Inscription success");
			 System.out.println(1 + " " + name + " " + surnames + " " + phone + " " + email + " " + date + " " + time + " " + state + " " + course_id);
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
		
		//Detalles de la carrera seleccionada
		CourseEntity course=insmodel.getCourse(idCourse);
		TableModel tmodel=SwingUtil.getRecordModelFromPojo(course, new String[] {"course_name", "description", "course_start_period", "course_end_period", "total_places", "available_places"});
		insview.getDetalleCourses().setModel(tmodel);
		SwingUtil.autoAdjustColumns(insview.getDetalleCourses());
	}

}
