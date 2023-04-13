package giis.demo.coiipa;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import giis.demo.dto.CourseDisplayDTO;
import giis.demo.dto.RegistrationDisplayDTO;
import giis.demo.util.ApplicationException;
import giis.demo.util.SwingUtil;
import giis.demo.util.Util;

public class ConsultController {
	
	private ConsultModel cmodel;
	private ConsultView cview;
	private String lastSelectedKey="";
	
	public ConsultController (ConsultModel m, ConsultView v) {
		this.cmodel = m;
		this.cview = v;
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
		cview.getListCourses().addMouseListener(new MouseAdapter() {
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
		cview.getFrame().setVisible(true);
	}
	
	public void getListCourses() {
		List<CourseDisplayDTO> courses=cmodel.getListCourses(Util.isoStringToDate(cview.getFechaHoy()));
		DefaultTableModel tmodel= (DefaultTableModel) SwingUtil.getTableModelFromPojos(courses, new String[] {"course_id", "course_name"});
		Object[] newHeader = {"ID", "Name"};
		tmodel.setColumnIdentifiers(newHeader);	
		cview.getTableCourses().setModel(tmodel);
		SwingUtil.autoAdjustColumns(cview.getTableCourses());
		
	}
	
	public void getListRegistrations() {
		List<RegistrationDisplayDTO> courses=cmodel.getListRegistrations(1);
		DefaultTableModel tmodel= (DefaultTableModel) SwingUtil.getTableModelFromPojos(courses, new String[] {"course_id", "course_name"});
		Object[] newHeader = {"ID", "Name"};
		tmodel.setColumnIdentifiers(newHeader);	
		cview.getTableCourses().setModel(tmodel);
		SwingUtil.autoAdjustColumns(cview.getTableCourses());
		
	}
	
	public void restoreDetail() {
		//Utiliza la ultimo valor de la clave (que se reiniciara si ya no existe en la tabla)
		this.lastSelectedKey=SwingUtil.selectAndGetSelectedKey(cview.getTableCourses(), this.lastSelectedKey);
		//Si hay clave para seleccionar en la tabla muestra el detalle, si no, lo reinicia
		this.updateDetail();
	}
	
	public void updateDetail() {
		//Obtiene la clave seleccinada y la guarda para recordar la seleccion en futuras interacciones
		this.lastSelectedKey=SwingUtil.getSelectedKey(cview.getTableCourses());
		int idCourse=Integer.parseInt(this.lastSelectedKey);
		
		//Detalles de la carrera seleccionada
		CourseEntity course=cmodel.getCourse(idCourse);
		TableModel tmodel=SwingUtil.getRecordModelFromPojo(course, new String[] {"course_name", "description", "course_start_period", "course_end_period", "course_start_date", "course_end_date", "total_places", "available_places"});
		cview.getDetalleCourses().setModel(tmodel);
		String today = cview.getFechaHoy();
		/*
		if((LocalDate.parse("course_start_period").isBefore(LocalDate.parse(today))) && 
				(LocalDate.parse("course_end_period").isAfter(LocalDate.parse(today)))) {
			cview.getlabel().setText("Open");
			cview.getlabel().setBackground(Color.green);
		} else {
			cview.getlabel().setText("Closed");
			cview.getlabel().setBackground(Color.red);
		}
		*/
		SwingUtil.autoAdjustColumns(cview.getDetalleCourses());
	}

}
