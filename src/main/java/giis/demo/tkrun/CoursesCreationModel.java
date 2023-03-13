package giis.demo.tkrun;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import giis.demo.util.ApplicationException;
import giis.demo.util.Database;
import giis.demo.util.Util;

public class CoursesCreationModel {

	private Database db=new Database();
		
	public static final String SQL_LIST_TEACHERS=
			"SELECT teacher_id, teacher_name, teacher_surnames, teacher_phone, teacher_email "
			+ "FROM Teacher" ;
	
	public static final String SQL_INSERT_COURSE=
			"INSERT INTO Course (course_id, course_name, description, objectives, course_hours, course_date, course_time, "
			+ "course_start_period, course_end_period, total_places, available_places, course_fee, place, "
			+ "course_state, teacher_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	
	/**
	 * Obtain every of the parameters of the teachers
	 */
	
	public List<TeacherDisplayDTO> getListTeachers(){
		String sql= SQL_LIST_TEACHERS;
		return db.executeQueryPojo(TeacherDisplayDTO.class, sql);
	}
	
	@SuppressWarnings("null")
	public void insertNewCourse(int c_id, String course_name, String course_start_date, String course_start_hour,
			String course_enrollement_start_date, String course_enrollement_end_date, float fee, 
			String place, float n_hours, float teacher_remuneration, int n_places, int course_teacher_id, 
			String course_objectives, String course_main_contents) {
		
		int course_id = c_id++; // This needs to be fixed
		/*try {
			
			String sql = "SELECT course_id FROM Course WHERE course_id = (SELECT MAX(course_id) FROM Course);";
			course_id = db.executeQueryPojo(CourseMaxIdDisplayDTO.class, sql).get(0).getCourse_id() + 1;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    }*/
				
		db.executeUpdate(SQL_INSERT_COURSE, course_id, course_name, course_main_contents, course_objectives, n_hours, 
				course_start_date, course_start_hour, course_enrollement_start_date, course_enrollement_end_date,
				n_places, n_places, fee, place, "Active", course_teacher_id);
		
	}
	
}
