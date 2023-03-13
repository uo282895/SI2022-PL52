package giis.demo.tkrun;

import java.util.Date;
import java.util.List;

import giis.demo.util.ApplicationException;
import giis.demo.util.Database;
import giis.demo.util.Util;

public class RegistrationCancellationModel {
	
	private Database db=new Database();
	
	public static final String SQL_LIST_REGISTRATIONS=
			"SELECT Course.course_name, Registration.reg_name, Registration.reg_surnames, Registration.reg_email, "
			+ "Registration.reg_phone, Registration.reg_date, Registration.reg_time "
			+ "FROM Registration "
			+ "JOIN Course ON Registration.course_id = Course.course_id;";
	
	/**
	 * Obtain the corresponding list with the required information of registrations
	 */
	public List<RegistrationDisplayDTO> getListRegistrations() {
		String sql= SQL_LIST_REGISTRATIONS;
		return db.executeQueryPojo(RegistrationDisplayDTO.class, sql);
	}
	
}
