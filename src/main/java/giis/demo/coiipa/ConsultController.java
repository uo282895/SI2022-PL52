package giis.demo.coiipa;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import giis.demo.dto.CourseDisplayDTO;
import giis.demo.dto.CourseEntity;
import giis.demo.dto.RegistrationDisplayDTO;
import giis.demo.dto.RegistrationEntity;
import giis.demo.util.ApplicationException;
import giis.demo.util.SwingUtil;
import giis.demo.util.Util;

public class ConsultController {
	
	private ConsultModel cmodel;
	private ConsultView cview;
	private String lastSelectedKey="";
	ArrayList<Integer> ids = new ArrayList<Integer>();
	
	private Date today;
	int correctDate = 0;
	
	public ConsultController (ConsultModel m, ConsultView v, Date sysDate) {
		this.cmodel = m;
		this.cview = v;
		this.today = sysDate;
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
				SwingUtil.exceptionWrapper(() -> {
					try {
						getListRegistrations();
						updateDetail();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
			}
		});
	}
	
	public void initview() {
		this.getListCourses();
		cview.getFrame().setVisible(true);
	}
	
	public void getListCourses() {
		List<CourseDisplayDTO> courses = cmodel.getListCourses(today);
		DefaultTableModel tmodel= (DefaultTableModel) SwingUtil.getTableModelFromPojos(courses, new String[] {"course_id","course_name", "course_state", "course_start_period", "course_end_period", "total_places", "course_start_date"});
		Object[] newHeader = {"Course id", "Course name", "Status", "Start of enrollement period", "End of enrollement period", "Total places", "Places left", "Course date"};
		tmodel.setColumnIdentifiers(newHeader);	
		cview.getTableCourses().setModel(tmodel);
		
		TableColumnModel columnModel = cview.getTableCourses().getColumnModel();
		
		for(int i=0; i<cview.getTableCourses().getRowCount(); i++) {
			ids.add(Integer.valueOf(cview.getTableCourses().getValueAt(i, 0).toString()));
		}
		TableColumn column = columnModel.getColumn(0);
		columnModel.removeColumn(column);
		
		SwingUtil.autoAdjustColumns(cview.getTableCourses());
		
	}
	
	public void getListRegistrations() {
		int index = ids.get(cview.getTableCourses().getSelectedRow());
		
		List<RegistrationDisplayDTO> reg = cmodel.getListRegistrations(index);
		DefaultTableModel tmodel2= (DefaultTableModel) SwingUtil.getTableModelFromPojos(reg, new String[] {"reg_id", "reg_name", "reg_surnames", "reg_phone", "reg_email", "reg_date", "reg_state", "course_id"});
		Object[] newHeader = {"ID", "Name", "Surnames", "Phone", "Email", "Date", "State", "Course ID"};
		tmodel2.setColumnIdentifiers(newHeader);	
		cview.getProffReg().setModel(tmodel2);
		
		TableColumnModel columnModel2 = cview.getProffReg().getColumnModel();
		TableColumn column2 = columnModel2.getColumn(0);
		columnModel2.removeColumn(column2);
		
		SwingUtil.autoAdjustColumns(cview.getTableCourses());
		
	}
	
	public void restoreDetail() {
		//Utiliza la ultimo valor de la clave (que se reiniciara si ya no existe en la tabla)
		this.lastSelectedKey=SwingUtil.selectAndGetSelectedKey(cview.getTableCourses(), this.lastSelectedKey);
		//Si hay clave para seleccionar en la tabla muestra el detalle, si no, lo reinicia
		try {
			this.updateDetail();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateDetail() throws ParseException {
		//Obtiene la clave seleccinada y la guarda para recordar la seleccion en futuras interacciones
		this.lastSelectedKey=SwingUtil.getSelectedKey(cview.getTableCourses());
		int idCourse=Integer.parseInt(this.lastSelectedKey);
		
		//Detalles de la carrera seleccionada
		CourseEntity course = cmodel.getCourse(idCourse);
		TableModel tmodel=SwingUtil.getRecordModelFromPojo(course, new String[] {"course_name", "description", "course_start_period", "course_end_period", "course_start_date", "course_end_date", "total_places"});
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(today);
		
		int index = ids.get(cview.getTableCourses().getSelectedRow());
		
		Date start = sdf.parse(cmodel.getStartPeriod(index));
		Date end = sdf.parse(cmodel.getEndPeriod(index));
		
		int comp1 = today.compareTo(start);
		int comp2 = today.compareTo(end);
		if (comp1 >= 0) {
			correctDate++;
		}
		if (comp2 <= 0) {
			correctDate++;
		}
		
		if(correctDate == 2) {
			cview.getstate().setText("Open");
			cview.getstate().setBackground(Color.green);
		} else {
			cview.getstate().setText("Closed");
			cview.getstate().setBackground(Color.red);
		}
		
		correctDate = 0;
		
		List<RegistrationDisplayDTO> reg = cmodel.getListRegistrations(idCourse);
		DefaultTableModel tmodel2 = (DefaultTableModel) SwingUtil.getTableModelFromPojos(reg, new String[] {"reg_id", "reg_name", "reg_surnames", "reg_phone", "reg_email", "reg_date", "reg_state", "course_id"});
		Object[] newHeader = {"ID", "Name", "Surnames", "Phone", "Email", "Date", "State", "Course ID"};
		tmodel2.setColumnIdentifiers(newHeader);
		cview.getProffReg().setModel(tmodel2);
		
		TableColumnModel columnModel2 = cview.getProffReg().getColumnModel();
		TableColumn column1 = columnModel2.getColumn(0);
		TableColumn column2 = columnModel2.getColumn(7);
		columnModel2.removeColumn(column1);
		columnModel2.removeColumn(column2);
		SwingUtil.autoAdjustColumns(cview.getProffReg());	
	}

}
