package giis.demo.tkrun;

import java.util.*;
import giis.demo.util.Util;
import giis.demo.util.ApplicationException;
import giis.demo.util.Database;

public class InscriptionModel {
		private Database db = new Database();
		
		public static final String SQL_List_Courses=
				"SELECT course_name, course_date, place, course_fee from Course";
		
		public static final String SQL_Insert_Proffessional=
				"INSERT into Registration (reg_id, reg_name, reg_surnames, reg_phone, reg_email, reg_date, reg_time, reg_state, course_id)\r\n"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, 'Received', ?)";
		
		public static final String SQL_Proff_Registered=
				"UPDATE Course SET available_places = available_places - 1 WHERE course_id = <curso_id>;\r\n";
				
		
		public List<Object[]> getListCoursesArray(Date fechaInsc){
			String sql = SQL_List_Courses;
			String d = Util.dateToIsoString(fechaInsc);
			return db.executeQueryArray(sql, d);
		}
		
		public List<CourseDisplayDTO> getListCourses(Date fechaInsc){
			String sql = "SELECT course_name, course_date, place, course_fee from Course";
			String d = Util.dateToIsoString(fechaInsc);
			return db.executeQueryPojo(CourseDisplayDTO.class, sql, d, d, d, d, d);
		}
		
		public void insertNewProffessional(String name, String surnames, String phone, String email) {
			String sql = SQL_Insert_Proffessional;	
			String sql_updateplaces = SQL_Proff_Registered;
			
			db.executeUpdate(sql, name, surnames, phone, email);
			db.executeUpdate(sql_updateplaces, -1);
		}
		
		public CourseEntity getCourse(int id) {
			String sql="SELECT id,course_name, course_date, place, course_fee from Course where id=?";
			List<CourseEntity> courses=db.executeQueryPojo(CourseEntity.class, sql, id);
			validateCondition(!courses.isEmpty(),"Id of course not found: "+id);
			return courses.get(0);
		}
		
		private void validateCondition(boolean condition, String message) {
			if (!condition)
				throw new ApplicationException(message);
		}
		
		public int getPlacesCourse(int id) {
			String sql = "SELECT available_places from Course";
			return db.executeQueryPojo(CourseDisplayDTO.class, sql, id).get(0).getAvailable_places();
		}
}
