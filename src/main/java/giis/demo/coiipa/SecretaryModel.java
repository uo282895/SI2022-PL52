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
import giis.demo.dto.PaymentAdditionalDisplayDTO;
import giis.demo.dto.PaymentDisplayDTO;
import giis.demo.dto.PaymentInputDTO;
import giis.demo.dto.RegistrationEntity;
import giis.demo.util.ApplicationException;
import giis.demo.util.Database;
import giis.demo.util.Util;

public class SecretaryModel {
	
	private Database db=new Database();
	
	public static final String SQL_LIST_COURSES=
			"SELECT c.course_id, c.course_name, c.course_state, c.course_start_period, c.course_end_period, c.total_places, "
			+ "(c.total_places - COUNT(CASE WHEN r.reg_state = 'Confirmed' OR r.reg_state = 'Compensate' THEN 1 END)) "
			+ "AS available_places, c.course_start_date "
			+ "FROM Course c LEFT JOIN Registration r ON c.course_id = r.course_id "
			+ "GROUP BY c.course_id";
	
	public static final String SQL_INSERT_AMOUNTDATEHOUR=
			"INSERT into Payment(payment_id, amount, payment_date, payment_type, "
			+ "invoice_id, reg_id) values(?, ?, ?,'Professional registration',null, ?)";
	
	public static final String SQL_LIST_PAYMENTS_ADDITIONAL=
			"select p.amount, p.payment_date, p.payment_type from Payment p "
			+ "INNER JOIN Registration r on p.reg_id = r.reg_id "
			+ "INNER JOIN Course c on r.course_id = c.course_id "
			+ "where c.course_id = ? AND r.reg_id = ?";
	
	public static final String SQL_INSERT_AMOUNTDATEHOUR_DEVOLUTION = 
			"INSERT into Payment(payment_id, amount, payment_date, payment_type, "
			+ "invoice_id, reg_id) values(?, ?, ?,'Devolution',null, ?)";
	
	/**
	 * Obtains the list of payments of the desired type
	 */
	public List<PaymentDisplayDTO> getListPayments(String state) {
		
		if (state.compareTo("--All--") == 0) {//all the payments must be shown
			String sql = "SELECT course_name, reg_name, reg_surnames, reg_email, "
					+ "course_fee, reg_date, reg_time "
					+ "FROM Course c INNER JOIN Registration r ON c.course_id = r.course_id "
					+ "WHERE course_state = 'Active'";
			return db.executeQueryPojo(PaymentDisplayDTO.class, sql);
		}else if (state.compareTo("Wrong") == 0){//Only wrong payments shown
			String sql = "SELECT course_name, reg_name, reg_surnames, reg_email, "
					+ "course_fee, reg_date, reg_time "
					+ "FROM Course c INNER JOIN Registration r ON c.course_id = r.course_id "
					+ "WHERE course_state = 'Active' "
					+ "AND reg_state = 'Incomplete' OR reg_state = 'Full'";
			return db.executeQueryPojo(PaymentDisplayDTO.class, sql);
		}else if (state.compareTo("Pending")==0){
			String sql = "SELECT course_name, reg_name, reg_surnames, reg_email, "
					+ "course_fee, reg_date, reg_time "
					+ "FROM Course c INNER JOIN Registration r ON c.course_id = r.course_id "
					+ "WHERE course_state = 'Active' "
					+ "AND reg_state = 'Received'";
			return db.executeQueryPojo(PaymentDisplayDTO.class, sql);
		}else if (state.compareTo("Confirmed")==0){
			String sql = "SELECT course_name, reg_name, reg_surnames, reg_email, "
					+ "course_fee, reg_date, reg_time "
					+ "FROM Course c INNER JOIN Registration r ON c.course_id = r.course_id "
					+ "WHERE course_state = 'Active' "
					+ "AND reg_state = 'Confirmed' OR reg_state = 'Compensate'";
			return db.executeQueryPojo(PaymentDisplayDTO.class, sql);
		}
		else { //Cancelled
			String sql = "SELECT course_name, reg_name, reg_surnames, reg_email, "
					+ "course_fee, reg_date, reg_time "
					+ "FROM Course c INNER JOIN Registration r ON c.course_id = r.course_id "
					+ "WHERE course_state = 'Active' "
					+ "AND reg_state = 'Cancelled' OR reg_state = 'Cancelled - Compensate'";
			return db.executeQueryPojo(PaymentDisplayDTO.class, sql);
		}
	}
	
	/**
	 * Obtains the list of all the payments done to complete (or not) a registration
	 */
	public List<PaymentAdditionalDisplayDTO> getListPaymentsAdditional(int courseid, int regid) {
		String sql= SQL_LIST_PAYMENTS_ADDITIONAL;
		return db.executeQueryPojo(PaymentAdditionalDisplayDTO.class, sql, courseid, regid);
	}
	
	/**
	 * Obtains the list of courses (formative actions)
	 */
	public List<CourseDisplayDTO> getListCourses() {
		String sql= SQL_LIST_COURSES;
		return db.executeQueryPojo(CourseDisplayDTO.class, sql);
	}
	
	//Method encharged of the validation of dates (both registration and payment)
	public void validateDate(Date paydate, int regid) {
		RegistrationEntity registration = this.getRegistration(regid);
		LocalDate localdate = LocalDate.now();
		
		//Actual date
		Date today = Date.from(localdate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date regdate = Util.isoStringToDate(registration.getReg_date());
		
		validateCondition(paydate.compareTo(today) <= 0, "You cannot input future dates. Please, enter a valid date.");
		validateCondition(regdate.compareTo(paydate) < 0, "The payment date must be after the registration date");
	}
	
	//Method to insert into the DB each payment of type registration
	public void insertPaymentReg(int payid, int amount, Date paydate, int regid) {
		String d = Util.dateToIsoString(paydate);
		
		db.executeUpdate(SQL_INSERT_AMOUNTDATEHOUR, payid, amount, d, regid);
	}
	
	//Method to insert into the DB each payment of type devolution
	public void insertPaymentDev(int payid, int amount, Date paydate, int regid) {
		String d = Util.dateToIsoString(paydate);
		
		db.executeUpdate(SQL_INSERT_AMOUNTDATEHOUR_DEVOLUTION, payid, - amount, d, regid);//Negative amount because it is a devolution
	}
	
	
	//Updates the database with the changes performed on the input of data
	public void updateTable(int regid) {
		String sql_updatestate = "UPDATE Registration " //Update the state (it is now correctly registered)
				+ "set reg_state = 'Confirmed' where reg_id = ?";
		
		db.executeUpdate(sql_updatestate, regid);
	}
	
	//Method to change the state of a payment when it has to be compensated 
	public void updateWrong(int regid) {
		String sql_stateWrong = "UPDATE Registration " //Update the state (it is now wrong)
				+ "set reg_state = 'Incomplete' where reg_id = ?";
		
		db.executeUpdate(sql_stateWrong, regid);
	}
	
	//Method to change the state of a payment when there are no places left
	public void updateFull(int regid) {
		String sql_stateFull = "UPDATE Registration " //Update the state (it is now wrong)
				+ "set reg_state = 'Full' where reg_id = ?";
		
		db.executeUpdate(sql_stateFull, regid);
	}
	
	//Method to change the state of a payment when the college must compensate the prof. with money
	public void updateComp(int regid) {
		String sql_stateComp = "UPDATE Registration " //Update the state (it is now wrong)
				+ "set reg_state = 'Compensate' where reg_id = ?";
		
		db.executeUpdate(sql_stateComp, regid);
	}
	
	//Method to change the state of a payment when it becomes correct after compensations (places are not decremented
	//because they have already been)
	public void updateCompToCorrect(int regid) {
		String sql_stateComp = "UPDATE Registration " //Update the state (it is now wrong)
				+ "set reg_state = 'Confirmed' where reg_id = ?";
		db.executeUpdate(sql_stateComp, regid);
	}
	
	//Method to change the state of a cancellation (when it is compensated correctly)
	public void updateCompToCancelled(int regid) {
			String sql_stateComp = "UPDATE Registration " //Update the state (it is now wrong)
					+ "set reg_state = 'Cancelled' where reg_id = ?";
			db.executeUpdate(sql_stateComp, regid);
	}
	
	//Get free places from a course 
	public int getPlacesCourse(int courseid) {
		String sql = "SELECT (C.total_places - COALESCE(SUM(CASE WHEN R.reg_state IN ('Compensate', 'Confirmed') THEN 1 ELSE 0 END), 0)) "
				+ "AS available_places FROM Course C "
				+ "LEFT JOIN Registration R ON C.course_id = R.course_id "
				+ "WHERE C.course_id = ?"
				+ "GROUP BY C.course_id";
		return db.executeQueryPojo(CourseDisplayDTO.class, sql, courseid).get(0).getAvailable_places();
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
		String sql="SELECT c.objectives, c.description, t.teacher_name, t.teacher_surnames "
				+ "from Course c INNER JOIN Teacher t on c.teacher_id = t.teacher_id "
				+ "where c.course_id = ?";
		List<CourseInfoDisplayDTO> courses = db.executeQueryPojo(CourseInfoDisplayDTO.class, sql, courseid);
		validateCondition(!courses.isEmpty(),"Registration ID not found: " + courseid);
		return courses.get(0);
	}
	
	//Method to get the amount paid of a given registration (to handle wrong payments)
	public Integer getAmountPaid(int regid) {
		String sql = "select sum(p.amount) from Payment p inner join "
				+ "Registration r on p.reg_id = r.reg_id where r.reg_id = ?";
		Integer res = db.executeScalarQuery(Integer.class, sql, regid);
		if (res == null) {
			return 0;
		}else return res;
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
	/*public boolean differenceDatesHour(Date regdate, Date reghour, Date payhour) {
		// Convert input dates to LocalDateTime objects
		LocalDateTime regDateTime = LocalDateTime.ofInstant(regdate.toInstant(), ZoneId.systemDefault());
		LocalDateTime regHourDateTime = LocalDateTime.ofInstant(reghour.toInstant(), ZoneId.systemDefault());
		LocalDateTime payHourDateTime = LocalDateTime.ofInstant(payhour.toInstant(), ZoneId.systemDefault());
		
		// Combine date and hour into a single LocalDateTime object
		LocalDateTime regDateTimeWithHour = regDateTime.withHour(regHourDateTime.getHour()).withSecond(regHourDateTime.getSecond());
		LocalDateTime payDateTimeWithHour = payDateTime.withHour(payHourDateTime.getHour()).withSecond(payHourDateTime.getSecond());
		
		// Calculate the duration between the two date-times
		Duration duration = Duration.between(regDateTimeWithHour, payDateTimeWithHour);
		
		// Check if the duration is less than or equal to 48 hours
		return duration.compareTo(Duration.ofHours(48)) <= 0;
	}*/
	
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
