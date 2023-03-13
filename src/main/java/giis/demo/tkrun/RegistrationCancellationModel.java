package giis.demo.tkrun;

import java.util.Date;
import java.util.List;

import giis.demo.util.ApplicationException;
import giis.demo.util.Database;
import giis.demo.util.Util;

public class RegistrationCancellationModel {
	
	private Database db=new Database();
	
	public static final String SQL_LIST_REGISTRATIONS=
			"SELECT Registration.reg_id, Course.course_name, Registration.reg_name, Registration.reg_surnames, Registration.reg_email, "
			+ "Registration.reg_phone, Registration.reg_date, Registration.reg_time, Course.course_date, Course.course_time, "
			+ "Payment.amount "
			+ "FROM Course "
			+ "INNER JOIN Registration ON Course.course_id = Registration.course_id "
			+ "INNER JOIN Invoice ON Course.course_id = Invoice.course_id "
			+ "INNER JOIN Payment ON Invoice.invoice_id = Payment.invoice_id "
			+ "WHERE Registration.reg_state != 'cancelled';";
	
	public static final String SQL_UPDATE_CANCELLATION=
			"UPDATE Registration "
			+ "SET reg_state = 'cancelled' "
			+ "WHERE reg_id = ?;";
	
	public void insertCancellation(int id) {
		String sql = SQL_UPDATE_CANCELLATION;
		db.executeUpdate(sql, id);
	}
	
	/**
	 * Obtain the corresponding list with the required information of registrations
	 */
	public List<RegistrationDisplayDTO> getListRegistrations() {
		String sql= SQL_LIST_REGISTRATIONS;
		return db.executeQueryPojo(RegistrationDisplayDTO.class, sql);
	}
	
}
