package giis.demo.tkrun;

import java.util.Date;
import java.util.List;

import giis.demo.util.ApplicationException;
import giis.demo.util.Database;
import giis.demo.util.Util;

public class SecretaryModel {
	//private static final String MSG_FECHA_INSCRIPCION_NO_NULA = "La fecha de inscripcion no puede ser nula";

	private Database db=new Database();
	
	public static final String SQL_LIST_PAYMENTS=
			"SELECT course_name, reg_name, reg_surnames, reg_email, "
			+ "course_fee, reg_date, reg_time "
			+ "FROM Course c INNER JOIN Registration r ON c.course_id = r.course_id "
			+ "WHERE course_state = 'Active' AND reg_state = 'Received'";
	
	public static final String SQL_LIST_COURSES=
			"SELECT course_id, course_name, course_state, course_start_period, "
			+ "course_end_period, total_places, available_places,"
			+ "course_date, course_time "
			+ "FROM Course c";
	
	public static final String SQL_INSERT_AMOUNTDATEHOUR=
			"INSERT into Payment(payment_id, amount, payment_date, payment_time, payment_type, "
			+ "invoice_id, reg_id) values(?, ?, ?, ?,'Good',null, ?)";
	
	/**
	 * Obtiene la lista de carreras activas en forma objetos para una fecha de inscripcion dada
	 */
	public List<PaymentDisplayDTO> getListPayments() {
		//validateNotNull(fechaInscripcion,MSG_FECHA_INSCRIPCION_NO_NULA);
		String sql= SQL_LIST_PAYMENTS;
		//String d=Util.dateToIsoString(fechaInscripcion);
		return db.executeQueryPojo(PaymentDisplayDTO.class, sql);
	}
	
	/**
	 * Obtiene la lista de carreras activas en forma objetos para una fecha de inscripcion dada
	 */
	public List<CourseDisplayDTO> getListCourses() {
		//validateNotNull(fechaInscripcion,MSG_FECHA_INSCRIPCION_NO_NULA);
		String sql= SQL_LIST_COURSES;
		//String d=Util.dateToIsoString(fechaInscripcion);
		return db.executeQueryPojo(CourseDisplayDTO.class, sql);
	}
	
	public void insertAmountDate(int payid, int amount, Date paydate, Date payhour, int regid) {
		String sql = SQL_INSERT_AMOUNTDATEHOUR;
		String sql_updatestate = "UPDATE Registration " //Update the state (it is now correctly registered)
				+ "set reg_state = 'Confirmed' where reg_id = ?";
		String sql_updateplaces = "UPDATE Course" //Update the available places (-1 because someone has been registered9
				+ "set available_places = ? where course_id = ";
		
		int places = getPlacesCourse(regid);//places of the course associated to the registration
		int courseid = getCourseId(regid);//id of the course 
		RegistrationEntity registration = this.getRegistration(regid);
		Date regdate = Util.isoStringToDate(registration.getReg_date());
		validateCondition(regdate.compareTo(paydate) < 0, "The payment date must be after the registration date");
		
		String d = Util.dateToIsoString(paydate);
		String h = Util.hourToIsoString(payhour);
		db.executeUpdate(sql, payid, amount, d, h, regid);
		db.executeUpdate(sql_updatestate, regid);
		db.executeUpdate(sql_updateplaces, places - 1, courseid);
	}
	
	//Get places from a course associated to a registration
	public int getPlacesCourse(int regid) {
		String sql = "SELECT available_places from Course c inner join Registration r on "
				+ "c.course_id = r.course_id where r.reg_id = ?";
		return db.executeQueryPojo(PaymentInputDTO.class, sql, regid).get(0).getAvailable_places();
	}
	
	//Get the id from a course associated to a registration
	public int getCourseId(int regid) {
	String sql = "SELECT c.course_id from Course c inner join Registration r on "
			+ "c.course_id = r.course_id where r.reg_id = ?";
	return db.executeQueryPojo(PaymentInputDTO.class, sql, regid).get(0).getCourse_id();
	}
	
	//Get the last id recorded on the table payments
	public int getLastPaymentId () {
		String sql = "select max(payment_id) from Payment";
		return db.executeQueryPojo(PaymentInputDTO.class, sql).get(0).getPayment_id(); 
	}
	
	//Obtains the id of the registration of the selected values on the table
	public RegistrationEntity getRegId (String name, Date regdate, String regname){
		String sql = "select r.reg_id, reg_name, reg_surnames, reg_phone, reg_email,"
				+ " reg_date, reg_time, reg_state from Registration r inner join "
				+ "Course c on r.course_id = c.course_id where "
				+ "c.course_name = ? and r.reg_date = ? and r.reg_name = ?";
		String d = Util.dateToIsoString(regdate);
		List<RegistrationEntity> registrations = db.executeQueryPojo(RegistrationEntity.class, sql, name,d,regname);
		validateCondition(!registrations.isEmpty(),"This selection has not been found.");
		return registrations.get(0);
	}
	
	
	/**
	 * Obtiene todos los datos de la carrera con el id indicado
	 */
	public RegistrationEntity getRegistration(int id) {
		String sql="SELECT reg_id,reg_name,reg_surnames,reg_phone,reg_email, "
				+ "reg_date, reg_time, reg_state from Registration where reg_id=?";
		List<RegistrationEntity> registrations = db.executeQueryPojo(RegistrationEntity.class, sql, id);
		validateCondition(!registrations.isEmpty(),"Registration ID not found: "+id);
		return registrations.get(0);
	}
	
	//Obtains the necessary data to show of the selected course
	public CourseInfoDisplayDTO getCourse(int courseid) {
		String sql="SELECT c.objectives, c.description, c.place, t.teacher_name, t.teacher_surnames "
				+ "from Course c INNER JOIN Teacher t on c.teacher_id = t.teacher_id "
				+ "where c.course_id = ?";
		List<CourseInfoDisplayDTO> courses = db.executeQueryPojo(CourseInfoDisplayDTO.class, sql, courseid);
		validateCondition(!courses.isEmpty(),"Registration ID not found: "+courseid);
		return courses.get(0);
	}
	
	/* General use for object validation */
	public void validateNotNull(Object obj, String message) {
		if (obj==null)
			throw new ApplicationException(message);
	}
	private void validateCondition(boolean condition, String message) {
		if (!condition)
			throw new ApplicationException(message);
	}
	
	public double differenceDates(Date regdate, Date paydate) {
		long fechaInicialMs = regdate.getTime();
		long fechaFinalMs = paydate.getTime();
		long diff = fechaFinalMs - fechaInicialMs;
		double days = Math.floor(diff / (1000 * 60 * 60 * 24));
		return days;
	}
}