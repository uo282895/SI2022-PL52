package giis.demo.coiipa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import giis.demo.dto.CourseDisplayDTO;
import giis.demo.dto.CourseInfoDisplayDTO;
import giis.demo.dto.PaymentDisplayDTO;
import giis.demo.dto.PaymentInputDTO;
import giis.demo.dto.RegistrationEntity;
import giis.demo.util.ApplicationException;
import giis.demo.util.Database;
import giis.demo.util.Util;

public class SecretaryModel {
	
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
			+ "invoice_id, reg_id) values(?, ?, ?, ?,'Professional registration',null, ?)";
	
	/**
	 * Obtains the list of pending payments
	 */
	public List<PaymentDisplayDTO> getListPayments() {
		String sql= SQL_LIST_PAYMENTS;
		return db.executeQueryPojo(PaymentDisplayDTO.class, sql);
	}
	
	/**
	 * Obtains the list of courses (formative actions)
	 */
	public List<CourseDisplayDTO> getListCourses() {
		String sql= SQL_LIST_COURSES;
		return db.executeQueryPojo(CourseDisplayDTO.class, sql);
	}
	
	//Method encharged of the validation of dates (both registration and payment)
	public void validateDate(Date paydate, Date payhour, int regid) {
		RegistrationEntity registration = this.getRegistration(regid);
		LocalDate localdate = LocalDate.now();
		
		//Actual date
		Date today = Date.from(localdate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date regdate = Util.isoStringToDate(registration.getReg_date());
		
		validateCondition(paydate.compareTo(today) <= 0, "You cannot input future dates. Please, enter a valid date.");
		validateCondition(regdate.compareTo(paydate) <= 0, "The payment date must be after the registration date");
	}
	
	//Updates the database with the changes performed on the a
	public void updateTable(int payid, int amount, Date paydate, Date payhour, int regid) {
		String sql = SQL_INSERT_AMOUNTDATEHOUR;
		
		String sql_updatestate = "UPDATE Registration " //Update the state (it is now correctly registered)
				+ "set reg_state = 'Confirmed' where reg_id = ?";
		String sql_updateplaces = "UPDATE Course " //Update the available places (-1 because someone has been registered9
				+ "set available_places = ? where course_id = ?";
		
		int places = getPlacesCourse(regid);//places of the course associated to the registration
		int courseid = getCourseId(regid);//id of the course 
		
		
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
		String sql = "select * from Payment order by payment_id";
		
		List<PaymentInputDTO> list = db.executeQueryPojo(PaymentInputDTO.class, sql);
		int size = list.size();//size of the list (each element is a PaymentInputDTO)
		if (size == 0) {
			return 0;
		}
		else return list.get(size -1).getPayment_id();
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
	 * Obtains all the data of the registration with the selected id
	 */
	public RegistrationEntity getRegistration(int id) {
		String sql="SELECT reg_id,reg_name,reg_surnames,reg_phone,reg_email, "
				+ "reg_date, reg_time, reg_state from Registration where reg_id=?";
		List<RegistrationEntity> registrations = db.executeQueryPojo(RegistrationEntity.class, sql, id);
		validateCondition(!registrations.isEmpty(),"Registration ID not found: "+id);
		return registrations.get(0);
	}
	
	//Obtains the necessary ADDITIONAL data to show of the selected course
	public CourseInfoDisplayDTO getCourse(int courseid) {
		String sql="SELECT c.objectives, c.description, c.place, t.teacher_name, t.teacher_surnames "
				+ "from Course c INNER JOIN Teacher t on c.teacher_id = t.teacher_id "
				+ "where c.course_id = ?";
		List<CourseInfoDisplayDTO> courses = db.executeQueryPojo(CourseInfoDisplayDTO.class, sql, courseid);
		validateCondition(!courses.isEmpty(),"Registration ID not found: " + courseid);
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
	
	//Method calculating the difference between dates
	public boolean differenceDatesHour(Date regdate, Date paydate, Date reghour, Date payhour) {
		// Convert input dates to LocalDateTime objects
		LocalDateTime regDateTime = LocalDateTime.ofInstant(regdate.toInstant(), ZoneId.systemDefault());
		LocalDateTime payDateTime = LocalDateTime.ofInstant(paydate.toInstant(), ZoneId.systemDefault());
		LocalDateTime regHourDateTime = LocalDateTime.ofInstant(reghour.toInstant(), ZoneId.systemDefault());
		LocalDateTime payHourDateTime = LocalDateTime.ofInstant(payhour.toInstant(), ZoneId.systemDefault());
		
		// Combine date and hour into a single LocalDateTime object
		LocalDateTime regDateTimeWithHour = regDateTime.withHour(regHourDateTime.getHour()).withSecond(regHourDateTime.getSecond());
		LocalDateTime payDateTimeWithHour = payDateTime.withHour(payHourDateTime.getHour()).withSecond(payHourDateTime.getSecond());
		
		// Calculate the duration between the two date-times
		Duration duration = Duration.between(regDateTimeWithHour, payDateTimeWithHour);
		
		// Check if the duration is less than or equal to 48 hours
		return duration.compareTo(Duration.ofHours(48)) <= 0;
	}
	
	//Method to send a fictitious mail to the person which has correctly paid (by creating a .txt)
	public void sendMail(String name, String surnames, String coursename) {
		try {
			String str = name+"_"+surnames+"_"+"_"+coursename+".txt";
            File file = new File(str);
            FileWriter writer = new FileWriter(file);
            writer.write("To: "+ name + " "+ surnames +"\n\n"
            		+ "Concept: Correct inscription to a course\n\n"
            		+"Congratulations! The College has received your payment to the course " + coursename + "\n" +
            		"and you have now a place assigned.");
            writer.close();

            System.out.println("File created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
