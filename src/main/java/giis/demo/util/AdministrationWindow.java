package giis.demo.util;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

import javax.swing.JSeparator;

import giis.demo.coiipa.*;

/**
 * Punto de entrada principal que incluye botones para la ejecucion de las pantallas 
 * de las aplicaciones de ejemplo
 * y acciones de inicializacion de la base de datos.
 * No sigue MVC pues es solamente temporal para que durante el desarrollo se tenga posibilidad
 * de realizar acciones de inicializacion
 */
public class AdministrationWindow {

	private JFrame frame;

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
		frame.setBounds(0, 0, 613, 378);
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
			
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
				SecretaryController controller=new SecretaryController(new SecretaryModel(), new PaymentsView());
				controller.initControllerPayments();
			}
		});
		btnPayments.setBounds(334, 100, 237, 41);
		frame.getContentPane().add(btnPayments);
		btnInicializarBaseDeDatos.setBounds(20, 11, 244, 41);
		frame.getContentPane().add(btnInicializarBaseDeDatos);
		
		JButton btnInscr = new JButton("Inscribe proffessional");
		btnInscr.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				InscriptionController controller=new InscriptionController(new InscriptionModel(), new InscriptionsView());
				controller.initController();
			}
		});
		btnInscr.setBounds(10, 10, 10, 10);
		frame.getContentPane().add(btnInscr);
			
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
				SecretaryController controller = new SecretaryController(new SecretaryModel(), new CoursesView());
				controller.initControllerCourses();
			}
		});
		btnCourses.setBounds(24, 168, 240, 41);
		frame.getContentPane().add(btnCourses);
		
		JButton btnNewButton = new JButton("Create a new formative action");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CoursesCreationController controller=new CoursesCreationController(new CoursesCreationModel(), new CoursesCreationView());
				controller.initController();
			}
		});
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setBounds(24, 100, 240, 41);
		
		JButton btnEjecutarTkrun = new JButton("Register cancellations to courses");
		btnEjecutarTkrun.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				RegistrationCancellationController controller=
						new RegistrationCancellationController(new RegistrationCancellationModel(), 
								new RegistrationCancellationView());
			}
		});
		btnEjecutarTkrun.setBounds(334, 168, 237, 41);
		frame.getContentPane().add(btnEjecutarTkrun);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 71, 551, 18);
		frame.getContentPane().add(separator);
		

		JButton btnManageInvoices = new JButton("Manage invoices");
		btnManageInvoices.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				InvoiceController controller = new InvoiceController(new InvoiceModel(), new InvoiceView());
				controller.initController();
			}
		});
		btnManageInvoices.setBounds(334, 242, 237, 41);
		frame.getContentPane().add(btnManageInvoices);
		
		JButton ReportOfExpensesButton = new JButton("Show a report of expenses");
		ReportOfExpensesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReportOfExpensesController controller = new ReportOfExpensesController(new ReportOfExpensesView(),
						new ReportOfExpensesModel());
				controller.initController();
			}
		});
		ReportOfExpensesButton.setBounds(27, 236, 237, 41);
		frame.getContentPane().add(ReportOfExpensesButton);
		
	}

	public JFrame getFrame() { return this.frame; }
}
