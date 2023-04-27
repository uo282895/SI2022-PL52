package giis.demo.coiipa;

import java.util.List;

import giis.demo.dto.CourseCreationDisplayDTO;
import giis.demo.dto.SessionDisplayDTO;
import giis.demo.dto.TeacherDisplayDTO;
import giis.demo.util.Database;

public class CoursesCreationModel {

	private Database db=new Database();
		
	public static final String SQL_LIST_TEACHERS=
			"SELECT teacher_id, teacher_name, teacher_surnames, teacher_phone, teacher_email "
			+ "FROM Teacher" ;
	
	public static final String SQL_INSERT_COURSE=
			"INSERT INTO Course (course_id, course_name, description, objectives, course_hours, course_start_date, "
			+ "course_end_date, course_start_period, course_end_period, total_places, course_fee, "
			+ "course_state, teacher_id, teacher_remuneration) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String SQL_INSERT_SESSION=
			"INSERT INTO Session (session_id, session_location, session_date, session_hour, session_hours, course_id) VALUES "
			+ "(?, ?, ?, ?, ?, ?)";
	
	public static final String SQL_NUMBER_OF_SESSIONS_OF_A_COURSE=
			"SELECT COUNT(*) FROM Session WHERE course_id = ?;";

	
	/**
	 * Obtain every of the parameters of the teachers
	 */
	
	public List<TeacherDisplayDTO> getListTeachers(){
		String sql= SQL_LIST_TEACHERS;
		return db.executeQueryPojo(TeacherDisplayDTO.class, sql);
	}
	
	public void insertNewSession(int session_id, String session_location, String session_date, String session_hour, 
		float session_hours, int course_id) {
		db.executeUpdate(SQL_INSERT_SESSION, session_id, session_location, session_date, session_hour, session_hours,
			course_id);
	}
	
	public int getNumberOfSessionsOfACourse(int course_id) {
		return db.getSessionCount(course_id);
	}
	
	public int getMaxCourseID() {
		String sql = "SELECT course_id FROM Course WHERE course_id = (SELECT MAX(course_id) FROM Course);";
		return(db.executeQueryPojo(CourseCreationDisplayDTO.class, sql).get(0).getCourse_id());
	}
	
	public int getMaxSessionID() {
		String sql = "SELECT session_id FROM Session WHERE session_id = (SELECT MAX(session_id) FROM Session);";
		return(db.executeQueryPojo(SessionDisplayDTO.class, sql).get(0).getSession_id());
	}
	
	@SuppressWarnings("null")
	public void insertNewCourse(String course_name, String course_start_date, String course_end_date,
			String course_enrollement_start_date, String course_enrollement_end_date, float fee, 
			float n_hours, float teacher_remuneration, int n_places, int course_teacher_id, 
			String course_objectives, String course_main_contents) {
		
		int course_id;
		try {
			course_id = getMaxCourseID() + 1; // +1 is for differentiating the other Course ID	
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    }
				
		db.executeUpdate(SQL_INSERT_COURSE, course_id, course_name, course_main_contents, course_objectives, n_hours, 
				course_start_date, course_end_date, course_enrollement_start_date, course_enrollement_end_date,
				n_places, fee, "Active", course_teacher_id, teacher_remuneration);
		
	}
	
}
