package giis.demo.util;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.JSeparator;

import com.toedter.calendar.JDateChooser;

import giis.demo.coiipa.*;
import giis.demo.dto.CourseDisplayDTO;
import giis.demo.dto.CourseEntity;

import javax.swing.JLabel;

/**
 * Punto de entrada principal que incluye botones para la ejecucion de las pantallas 
 * de las aplicaciones de ejemplo
 * y acciones de inicializacion de la base de datos.
 * No sigue MVC pues es solamente temporal para que durante el desarrollo se tenga posibilidad
 * de realizar acciones de inicializacion
 */
public class AdministrationWindow{

	private JFrame frame;
	
	private SecretaryController secreatarycontrollerConsultFormativeActions;
	private SecretaryController SecretaryControllerRegisterPendingPayments;

	private CoursesCreationController coursescreationcontroller;
	private RegistrationCancellationController registrationcancellationcontroller;
	private InvoiceController invoicecontroller;
	private ReportOfExpensesController reportofexpensescontroller;
	private InscriptionController inscriptioncontroller;
	
	private Date system_date;
	private JDateChooser System_timeDateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { //NOSONAR codigo autogenerado
			public void run() {
				try {
					AdministrationWindow window = new AdministrationWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); //NOSONAR codigo autogenerado
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdministrationWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Administration Window");
		frame.setBounds(0, 0, 613, 430);
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		system_date = new Date();
		
		JButton btnInicializarBaseDeDatos = new JButton("Initialize in blank the Data Base");
		btnInicializarBaseDeDatos.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				Database db=new Database();
				db.createDatabase(false);
			}
		});
		
		JButton btnPayments = new JButton("Register pending payments");
		btnPayments.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				SecretaryControllerRegisterPendingPayments = new SecretaryController(new SecretaryModel(), new PaymentsView(), system_date);
				SecretaryControllerRegisterPendingPayments.initControllerPayments();
			}
		});
		btnPayments.setBounds(334, 150, 237, 41);
		frame.getContentPane().add(btnPayments);
		btnInicializarBaseDeDatos.setBounds(20, 11, 244, 41);
		frame.getContentPane().add(btnInicializarBaseDeDatos);
		
		
		System_timeDateChooser = new JDateChooser();
		System_timeDateChooser.setBounds(201, 94, 161, 20);
		System_timeDateChooser.setDateFormatString("dd/MM/yy HH:mm");
		system_date = new Date(); // Today's date by default
		System_timeDateChooser.setDate(system_date);
		frame.getContentPane().add(System_timeDateChooser);
		
		JButton btnCargarDatosIniciales = new JButton("Load initial data for testing");
		btnCargarDatosIniciales.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				Database db=new Database();
				db.createDatabase(false);
				db.loadDatabase();
			}
		});
		btnCargarDatosIniciales.setBounds(334, 11, 237, 41);
		frame.getContentPane().add(btnCargarDatosIniciales);
		
		JButton btnCourses = new JButton("Consult formative actions");
		btnCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secreatarycontrollerConsultFormativeActions = new SecretaryController(new SecretaryModel(), new CoursesView(), system_date);
				secreatarycontrollerConsultFormativeActions.initControllerCourses();
			}
		});
		btnCourses.setBounds(20, 202, 240, 41);
		frame.getContentPane().add(btnCourses);
		
		JButton btnNewButton = new JButton("Create a new formative action");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				coursescreationcontroller=new CoursesCreationController(new CoursesCreationModel(), new CoursesCreationView(), system_date);
				coursescreationcontroller.initController();
			}
		});
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setBounds(20, 150, 240, 41);
		
		JButton btnEjecutarTkrun = new JButton("Register cancellations to courses");
		btnEjecutarTkrun.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				registrationcancellationcontroller=
						new RegistrationCancellationController(new RegistrationCancellationModel(), 
								new RegistrationCancellationView(), system_date);
			}
		});
		btnEjecutarTkrun.setBounds(334, 202, 237, 41);
		frame.getContentPane().add(btnEjecutarTkrun);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 71, 551, 18);
		frame.getContentPane().add(separator);
		
		JButton btnManageInvoices = new JButton("Manage invoices");
		btnManageInvoices.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				invoicecontroller = new InvoiceController(new InvoiceModel(), new InvoiceView(), system_date);
				invoicecontroller.initController();
			}
		});
		btnManageInvoices.setBounds(334, 254, 237, 41);
		frame.getContentPane().add(btnManageInvoices);
		
		JButton ReportOfExpensesButton = new JButton("Show a report of expenses");
		ReportOfExpensesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reportofexpensescontroller = new ReportOfExpensesController(new ReportOfExpensesView(),
						new ReportOfExpensesModel(), system_date);
				reportofexpensescontroller.initController();
			}
		});
		ReportOfExpensesButton.setBounds(20, 254, 244, 41);
		frame.getContentPane().add(ReportOfExpensesButton);
		
		JButton btnInscribeIntoA = new JButton("Inscribe into a formative action");
		btnInscribeIntoA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InscriptionController controller = new InscriptionController(new InscriptionModel(), new InscriptionsView(), system_date);
				controller.initController();
			}
		});
		btnInscribeIntoA.setBounds(24, 307, 237, 41);
		frame.getContentPane().add(btnInscribeIntoA);
		
		JButton btnConsultRegistrationsOf = new JButton("Consult status and registrations of a formative action");
		btnConsultRegistrationsOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultController controller = new ConsultController(new ConsultModel(), new ConsultView(), system_date);
				controller.initController();
			}
		});
		btnConsultRegistrationsOf.setBounds(334, 305, 237, 41);
		frame.getContentPane().add(btnConsultRegistrationsOf);

		JLabel lblNewLabel = new JLabel("Set the System Time:");
		lblNewLabel.setBounds(65, 94, 131, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnUpdateSystemDate = new JButton("Update system date");
		btnUpdateSystemDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// First store the system date of the DateChoser
				system_date = System_timeDateChooser.getDate();
				
				SetSystemDateModel systemDateModel = new SetSystemDateModel();
				List<CourseEntity> list_courses = systemDateModel.getAllCourses();
				for(CourseEntity course: list_courses) {
					
					String courseend = course.getCourse_end_date();//course end date
					String regstart = course.getCourse_start_period();//reg start date
					
					Date course_end_date = Util.isoStringToDate(courseend);
					Date reg_start_date = Util.isoStringToDate(regstart);
					
					if(system_date.before(reg_start_date)) // The state of this condition is 'Registered'
						systemDateModel.updateCourseState(Integer.parseInt(course.getCourse_id()), "Registered");
					else if(system_date.compareTo(reg_start_date) >= 0 && system_date.compareTo(course_end_date) <= 0) // The state of this condition is 'Active'
						systemDateModel.updateCourseState(Integer.parseInt(course.getCourse_id()), "Active");
					else if(system_date.after(course_end_date)) // The state of this condition is 'Finished'
						systemDateModel.updateCourseState(Integer.parseInt(course.getCourse_id()), "Finished");
					else {
						systemDateModel.updateCourseState(Integer.parseInt(course.getCourse_id()), "Cancelled");
					}
				}
				
				System.out.println(system_date);
				if (secreatarycontrollerConsultFormativeActions != null)
					secreatarycontrollerConsultFormativeActions.updateSystemDate(system_date);
				if(SecretaryControllerRegisterPendingPayments != null)
					SecretaryControllerRegisterPendingPayments.updateSystemDate(system_date);
				if(coursescreationcontroller != null)
					coursescreationcontroller.updateSystemDate(system_date);
				if(registrationcancellationcontroller != null)
					registrationcancellationcontroller.updateSystemDate(system_date);
				if(invoicecontroller != null)
					invoicecontroller.updateSystemDate(system_date);
				if(reportofexpensescontroller != null)
					reportofexpensescontroller.updateSystemDate(system_date);
				if(inscriptioncontroller != null)
					inscriptioncontroller.updateSystemDate(system_date);
			}
		});
		btnUpdateSystemDate.setBounds(382, 87, 162, 35);
		frame.getContentPane().add(btnUpdateSystemDate);
		
	}

	public JFrame getFrame() { return this.frame; }

	public SecretaryController getSecreatarycontrollerConsultFormativeActions() {
		return secreatarycontrollerConsultFormativeActions;
	}

	public void setSecreatarycontrollerConsultFormativeActions(
			SecretaryController secreatarycontrollerConsultFormativeActions) {
		this.secreatarycontrollerConsultFormativeActions = secreatarycontrollerConsultFormativeActions;
	}

	public SecretaryController getSecretaryControllerRegisterPendingPayments() {
		return SecretaryControllerRegisterPendingPayments;
	}

	public void setSecretaryControllerRegisterPendingPayments(
			SecretaryController secretaryControllerRegisterPendingPayments) {
		SecretaryControllerRegisterPendingPayments = secretaryControllerRegisterPendingPayments;
	}

	public CoursesCreationController getCoursescreationcontroller() {
		return coursescreationcontroller;
	}

	public void setCoursescreationcontroller(CoursesCreationController coursescreationcontroller) {
		this.coursescreationcontroller = coursescreationcontroller;
	}

	public RegistrationCancellationController getRegistrationcancellationcontroller() {
		return registrationcancellationcontroller;
	}

	public void setRegistrationcancellationcontroller(
			RegistrationCancellationController registrationcancellationcontroller) {
		this.registrationcancellationcontroller = registrationcancellationcontroller;
	}

	public InvoiceController getInvoicecontroller() {
		return invoicecontroller;
	}

	public void setInvoicecontroller(InvoiceController invoicecontroller) {
		this.invoicecontroller = invoicecontroller;
	}

	public ReportOfExpensesController getReportofexpensescontroller() {
		return reportofexpensescontroller;
	}

	public void setReportofexpensescontroller(ReportOfExpensesController reportofexpensescontroller) {
		this.reportofexpensescontroller = reportofexpensescontroller;
	}

	public InscriptionController getInscriptioncontroller() {
		return inscriptioncontroller;
	}

	public void setInscriptioncontroller(InscriptionController inscriptioncontroller) {
		this.inscriptioncontroller = inscriptioncontroller;
	}
	
}
