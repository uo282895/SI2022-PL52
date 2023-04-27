package giis.demo.coiipa;

import java.util.List;

import giis.demo.dto.RegistrationDisplayDTO;
import giis.demo.util.Database;

public class RegistrationCancellationModel {
	
	private Database db=new Database();
	
	public static final String SQL_LIST_REGISTRATIONS=
			  "SELECT Registration.reg_id, Course.course_name, Registration.reg_name, Registration.reg_surnames, Registration.reg_email, "
			  + "Registration.reg_phone, Registration.reg_date, Registration.reg_time, Course.course_start_date, "
			  + "COALESCE(SUM(Payment.amount), 0) AS total_payment "
			  + "FROM Course "
			  + "INNER JOIN Registration ON Course.course_id = Registration.course_id "
			  + "LEFT JOIN Payment ON Payment.reg_id = Registration.reg_id "
			  + "WHERE Registration.reg_state != 'Cancelled' "
			  + "GROUP BY Registration.reg_id, Course.course_name, Registration.reg_name, Registration.reg_surnames, Registration.reg_email, "
			  + "Registration.reg_phone, Registration.reg_date, Registration.reg_time, Course.course_start_date "
			  + "ORDER BY Registration.reg_id;";
	
	public static final String SQL_UPDATE_CANCELLATION=
			"UPDATE Registration "
			+ "SET reg_state = 'Cancelled' "
			+ "WHERE reg_id = ?;";
	
	public static final String SQL_UPDATE_CANCELLATION_COMPENSATE=
			"UPDATE Registration "
			+ "SET reg_state = 'Cancelled - Compensate' "
			+ "WHERE reg_id = ?;";
	
	public void insertCancellation(int id) {
		String sql = SQL_UPDATE_CANCELLATION;
		db.executeUpdate(sql, id);
	}
	
	public void insertCancellationCompensate(int id) {
		String sql = SQL_UPDATE_CANCELLATION_COMPENSATE;
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
