package giis.demo.coiipa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
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
import giis.demo.util.ApplicationException;
import giis.demo.util.SwingUtil;
import giis.demo.util.Util;

public class InscriptionController {
	
	private InscriptionModel insmodel;
	private InscriptionsView insview;
	private String lastSelectedKey="";
	ArrayList<Integer> ids = new ArrayList<Integer>();
	
	private Date today;
	
	public InscriptionController (InscriptionModel m, InscriptionsView v, Date sysDate) {
		this.insmodel = m;
		this.insview = v;
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
		insview.getConfirm().addActionListener(new ActionListener() {
			Object[] options = {"Yes", "No"};
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "Are you sure you want to formalize the inscription?"
						+ "\nThis is not reversible and will generate a debt according to the price of the selected formative action.", "Confirm?", 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (confirm == JOptionPane.YES_OPTION) {
					SwingUtil.exceptionWrapper(()-> insertNewProffessional());
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
		DefaultTableModel tmodel= (DefaultTableModel) SwingUtil.getTableModelFromPojos(courses, new String[] {"course_id", "course_name", "course_start_date", "course_end_date", "course_fee"});
		Object[] newHeader = {"ID", "Name", "Starts", "Ends", "Fee"};
		tmodel.setColumnIdentifiers(newHeader);	
		insview.getTableCourses().setModel(tmodel);
		SwingUtil.autoAdjustColumns(insview.getTableCourses());

		TableColumnModel columnModel = insview.getTableCourses().getColumnModel();
		
		for(int i=0; i<insview.getTableCourses().getRowCount(); i++) {
			ids.add(Integer.valueOf(insview.getTableCourses().getValueAt(i, 0).toString()));
		}
		TableColumn column = columnModel.getColumn(0);
		columnModel.removeColumn(column);
		
	}
	
	public void insertNewProffessional() {
		String name = insview.getnameField().getText();
		String surnames = insview.getsurnamesField().getText();
		String phone = insview.getphoneField().getText();
		String email = insview.getemailField().getText();
		
		// Formatting today's day string		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Create a SimpleDateFormat object with the desired pattern
        String date = formatter.format(today);
        System.out.println(date);
        
		String state = "Received";
		//int course_id = insview.getTableCourses().getSelectedRow() + 1;
		if (insview.getnameField().getText().isBlank() || insview.getsurnamesField().getText().isBlank() 
				|| insview.getphoneField().getText().isBlank() || insview.getemailField().getText().isBlank()) {
			throw new ApplicationException("Be careful, you must fill all the gaps");
		}	
		
		int index = ids.get(insview.getTableCourses().getSelectedRow()); // Course_id
		int newid = insmodel.getLastID() + 1; // Registration_id
		
		int places = insmodel.getPlacesCourse(index);
		if (places > 0) {
			 insmodel.insertNewProffessional(newid, name, surnames, phone, email, date, state, index);
			 JOptionPane.showOptionDialog(null, "You have been registered successfully", "Everything seems correct", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {"OK"}, "OK");
			 System.out.println("Inscription success");
			 System.out.println(newid + " " + name + " " + surnames + " " + phone + " " + email + " " + date + " " + state + " " + index);
			 // Sending the .txt mail to the professional registered
			 String course_name = insmodel.getCurseName(index);
			 int course_fee = insmodel.getCurseFee(index);
			 insmodel.sendRegistrationReceivedMail(newid, name, surnames, course_name, email, course_fee, date);
		} else {
			 JOptionPane.showOptionDialog(null, "There are no places left for the selected formative action", "Sorry, no places left", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, new Object[] {"OK"}, "OK");
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
		TableModel tmodel=SwingUtil.getRecordModelFromPojo(course, new String[] {"course_name", "description", "course_start_period", "course_end_period", "course_start_date", "course_end_date", "total_places", "available_places"});
		insview.getDetalleCourses().setModel(tmodel);
		SwingUtil.autoAdjustColumns(insview.getDetalleCourses());
	}
	
	public void updateSystemDate(Date system_date) {
		this.today = system_date;
	}

}
