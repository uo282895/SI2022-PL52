package giis.demo.coiipa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import giis.demo.util.Util;
import giis.demo.dto.CourseDisplayDTO;
import giis.demo.dto.CourseEntity;
import giis.demo.dto.RegisterMaxDisplayDTO;
import giis.demo.dto.RegistrationDisplayDTO;
import giis.demo.dto.RegistrationEntity;
import giis.demo.util.ApplicationException;
import giis.demo.util.Database;

public class ConsultModel {
		private Database db = new Database();
		
		public static final String SQL_List_Courses=
				"SELECT c.course_id, c.course_name, c.course_state, c.course_start_period, c.course_end_period, c.total_places, "
				+ "(c.total_places - COUNT(CASE WHEN r.reg_state = 'Confirmed' OR r.reg_state = 'Compensate' THEN 1 END)) "
				+ "AS available_places, c.course_start_date "
				+ "FROM Course c LEFT JOIN Registration r ON c.course_id = r.course_id "
				+ "GROUP BY c.course_id";
		
		public static final String SQL_Last_ID=
				"SELECT reg_id FROM Registration WHERE reg_id = (SELECT MAX(reg_id) FROM Registration);";
		
		public static final String SQL_List_Registrations=
				"SELECT * FROM Registration WHERE course_id = ? AND reg_date <= ?";

		
		
		public List<CourseDisplayDTO> getListCourses(Date fechaInsc){
			String sql = SQL_List_Courses;
			return db.executeQueryPojo(CourseDisplayDTO.class, sql);
		}
		
		public List<RegistrationDisplayDTO> getListRegistrations(int id, Date today){
			String t = Util.dateToIsoString(today);
			
			String sql = SQL_List_Registrations;
			return db.executeQueryPojo(RegistrationDisplayDTO.class, sql, id, t);
		}
		
		public CourseEntity getCourse(int id) {
			String sql="SELECT course_id, course_name, description, course_start_date, course_end_date, course_start_period, course_end_period, total_places, course_fee from Course where course_id=?";
			List<CourseEntity> courses=db.executeQueryPojo(CourseEntity.class, sql, id);
			validateCondition(!courses.isEmpty(),"Id of course not found: "+id);
			return courses.get(0);
		}
		
		private void validateCondition(boolean condition, String message) {
			if (!condition)
				throw new ApplicationException(message);
		}
		
		//Get free places from a course 
		public int getPlacesCourse(int courseid, Date today) {
			String t = Util.dateToIsoString(today);
			
			String sql = "SELECT (C.total_places - COALESCE(SUM(CASE WHEN R.reg_state IN ('Compensate', 'Confirmed - Profpay', 'Confirmed') THEN 1 ELSE 0 END), 0)) AS available_places "
					+ "FROM Course C "
					+ "LEFT JOIN Registration R ON C.course_id = R.course_id "
					+ "WHERE C.course_id = ? "
					+ "AND (R.reg_date <= ? OR R.reg_date IS NULL) "
					+ "GROUP BY C.course_id";
			return db.executeQueryPojo(CourseDisplayDTO.class, sql, courseid, t).get(0).getAvailable_places();
		}
		
		public int getLastID() {
			String sql = SQL_Last_ID;
			return db.executeQueryPojo(RegisterMaxDisplayDTO.class, sql).get(0).getReg_id();
		}
		
		public String getStartPeriod(int id) {
			String sql = "SELECT course_start_period from Course WHERE course_id = ?;";
			return db.executeQueryPojo(CourseDisplayDTO.class, sql, id).get(0).getCourse_start_period();
		}
		
		public String getEndPeriod(int id) {
			String sql = "SELECT course_end_period from Course WHERE course_id = ?;";
			return db.executeQueryPojo(CourseDisplayDTO.class, sql, id).get(0).getCourse_end_period();
		}
		
}
