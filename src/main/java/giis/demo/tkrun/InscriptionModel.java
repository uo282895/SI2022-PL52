package giis.demo.tkrun;

import java.util.*;
import giis.demo.util.Util;
import giis.demo.util.ApplicationException;
import giis.demo.util.Database;

public class InscriptionModel {
		private Database db = new Database();
		
		public static final String SQL_List_Courses=
				"SELECT course_id, course_name, course_start_date, course_end_date, course_fee from Course";
		
		public static final String SQL_Insert_Proffessional=
				"INSERT into Registration (reg_id, reg_name, reg_surnames, reg_phone, reg_email, reg_date, reg_time, reg_state, course_id)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, 'Received', ?)";
		
		public static final String SQL_Proff_Registered=
				"UPDATE Course SET available_places = available_places - 1 WHERE course_id = ?;";
		
		public static final String SQL_Last_ID=
				"SELECT reg_id FROM Registration WHERE reg_id = (SELECT MAX(reg_id) FROM Registration);";

		
		
		public List<CourseDisplayDTO> getListCourses(Date fechaInsc){
			String sql = SQL_List_Courses;
			return db.executeQueryPojo(CourseDisplayDTO.class, sql);
		}
		
		public void insertNewProffessional(int regid, String name, String surnames, String phone, String email, String date, String time, String state, int course_id) {
			String sql = SQL_Insert_Proffessional;	
			String sql_updateplaces = SQL_Proff_Registered;
			
			int regid2 = regid++;
			try {
				regid2 = getLastID() + 1;
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		        throw e;
		    }
			
			db.executeUpdate(sql, regid2, name, surnames, phone, email, "2023-04-03","12:00:00",1);
			db.executeUpdate(sql_updateplaces, course_id);
		}
		
		public CourseEntity getCourse(int id) {
			String sql="SELECT course_id, course_name, description, course_start_date, course_end_date, course_start_period, course_end_period, total_places, available_places, course_fee from Course where course_id=?";
			List<CourseEntity> courses=db.executeQueryPojo(CourseEntity.class, sql, id);
			validateCondition(!courses.isEmpty(),"Id of course not found: "+id);
			return courses.get(0);
		}
		
		private void validateCondition(boolean condition, String message) {
			if (!condition)
				throw new ApplicationException(message);
		}
		
		public int getPlacesCourse(int id) {
			String sql = "SELECT available_places from Course WHERE course_id = ?;";
			return db.executeQueryPojo(CourseDisplayDTO.class, sql, id).get(0).getAvailable_places();
		}
		
		public int getLastID() {
			String sql = SQL_Last_ID;
			return db.executeQueryPojo(RegisterMaxDisplayDTO.class, sql).get(0).getReg_id();
		}
		
}
