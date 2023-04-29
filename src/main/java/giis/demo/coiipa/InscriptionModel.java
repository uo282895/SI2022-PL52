package giis.demo.coiipa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import giis.demo.util.Util;
import giis.demo.dto.CourseDisplayDTO;
import giis.demo.dto.RegisterMaxDisplayDTO;
import giis.demo.util.ApplicationException;
import giis.demo.util.Database;

public class InscriptionModel {
		private Database db = new Database();
		
		public static final String SQL_List_Courses=
				"SELECT course_id, course_name, course_start_date, course_end_date, course_fee from Course";
		
		public static final String SQL_Insert_Proffessional=
				"INSERT into Registration (reg_id, reg_name, reg_surnames, reg_phone, reg_email, reg_date, reg_state, course_id)"
				+ "VALUES (?, ?, ?, ?, ?, ?, 'Received', ?)";
		
		public static final String SQL_Last_ID=
				"SELECT reg_id FROM Registration WHERE reg_id = (SELECT MAX(reg_id) FROM Registration);";

		
		
		public List<CourseDisplayDTO> getListCourses(Date fechaInsc){
			String sql = SQL_List_Courses;
			return db.executeQueryPojo(CourseDisplayDTO.class, sql);
		}
		
		public void insertNewProffessional(int regid, String name, String surnames, String phone, String email, String date, String state, int course_id) {
			String sql = SQL_Insert_Proffessional;	
			
			int regid2 = regid++;
			try {
				regid2 = getLastID() + 1;
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		        throw e;
		    }
			
			db.executeUpdate(sql, regid2, name, surnames, phone, email, date, course_id);
			//db.executeUpdate(sql_updateplaces, course_id);
		}
		
		public CourseEntity getCourse(int id) {
			String sql="SELECT c.course_id, course_name, description, course_start_date, course_end_date, course_start_period, course_end_period, total_places, "
					+ "(c.total_places - COUNT(CASE WHEN r.reg_state = 'Confirmed' "
					+ "OR r.reg_state = 'Compensate' THEN 1 END)) AS available_places,"
					+ "course_fee from Course c left join Registration r on c.course_id = r.course_id "
					+ "where c.course_id=? group by c.course_id";
			List<CourseEntity> courses=db.executeQueryPojo(CourseEntity.class, sql, id);
			validateCondition(!courses.isEmpty(),"Id of course not found: "+id);
			return courses.get(0);
		}
		
		private void validateCondition(boolean condition, String message) {
			if (!condition)
				throw new ApplicationException(message);
		}
		
		public int getPlacesCourse(int courseid) {
			String sql = "SELECT (C.total_places - COALESCE(SUM(CASE WHEN R.reg_state IN ('Compensate', 'Confirmed') THEN 1 ELSE 0 END), 0)) "
					+ "AS available_places FROM Course C "
					+ "LEFT JOIN Registration R ON C.course_id = R.course_id "
					+ "WHERE C.course_id = ?"
					+ "GROUP BY C.course_id";
			return db.executeQueryPojo(CourseDisplayDTO.class, sql, courseid).get(0).getAvailable_places();
		}
		
		public int getLastID() {
			String sql = SQL_Last_ID;
			return db.executeQueryPojo(RegisterMaxDisplayDTO.class, sql).get(0).getReg_id();
		}
		
		public String getFechaHoy()  { 
			LocalDate currentDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String date = currentDate.format(formatter);
			return date;			
		}
		
}
