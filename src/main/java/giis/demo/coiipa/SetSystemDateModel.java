package giis.demo.coiipa;
import java.util.List;

import giis.demo.dto.CourseDisplayDTO;
import giis.demo.util.Database;

public class SetSystemDateModel {
	
	private Database db=new Database();
	
	public static final String SQL_LIST_COURSES=
			"SELECT * FROM Course";
	
	public static final String SQL_UPDATE_COURSE_COURSE_STATE=
			"UPDATE Course "
			+ "SET course_state = ? "
			+ "WHERE course_id = ?;";
	
	public List<CourseDisplayDTO> getAllCourses(){
		String sql= SQL_LIST_COURSES;
		return db.executeQueryPojo(CourseDisplayDTO.class, sql);
	}
	
	public void updateCourseState(int course_id, final String newState) {
		String sql = SQL_UPDATE_COURSE_COURSE_STATE;
		db.executeUpdate(sql, newState, course_id);
	}
	
}
