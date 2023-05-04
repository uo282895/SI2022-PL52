package giis.demo.coiipa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import giis.demo.dto.CourseDisplayDTO;
import giis.demo.dto.CourseEntity;
import giis.demo.dto.RegisterMaxDisplayDTO;
import giis.demo.util.ApplicationException;
import giis.demo.util.Database;
import giis.demo.util.Util;

public class InscriptionModel {
		private Database db = new Database();
		
		public static final String SQL_List_Courses=
				"SELECT course_id, course_name, course_start_date, course_end_date, course_fee from Course";
		
		public static final String SQL_Insert_Proffessional=
				"INSERT into Registration (reg_id, reg_name, reg_surnames, reg_phone, reg_email, reg_date, reg_state, course_id)"
				+ "VALUES (?, ?, ?, ?, ?, ?, 'Received', ?)";
		
		public static final String SQL_Last_ID=
				"SELECT reg_id FROM Registration WHERE reg_id = (SELECT MAX(reg_id) FROM Registration);";

		public static final String SQL_Get_Course_Name=
				"SELECT course_id, course_name from Course where course_id = ?;";

		public List<CourseDisplayDTO> getListCourses(Date fechaInsc){
			String sql = SQL_List_Courses;
			return db.executeQueryPojo(CourseDisplayDTO.class, sql);
		}
		
		public void insertNewProffessional(int regid, String name, String surnames, String phone, String email, String today, String state, int course_id) {
			String sql = SQL_Insert_Proffessional;	
			
			int regid2 = regid++;
			try {
				regid2 = getLastID() + 1;
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		        throw e;
		    }
			
			db.executeUpdate(sql, regid2, name, surnames, phone, email, today ,course_id);
			//db.executeUpdate(sql_updateplaces, course_id);
		}
		
		public String getCurseName(int course_id) {
			String sql = SQL_Get_Course_Name;
			List<CourseDisplayDTO> course = db.executeQueryPojo(CourseDisplayDTO.class, sql, course_id);
			return course.get(0).getCourse_name();			
		}

		public int getCurseFee(int course_id) {
			String sql = SQL_Get_Course_Name;
			List<CourseDisplayDTO> course = db.executeQueryPojo(CourseDisplayDTO.class, sql, course_id);
			return course.get(0).getCourse_fee();			
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
		
		public String getStartPeriod(int id) {
			String sql = "SELECT course_start_period from Course WHERE course_id = ?;";
			return db.executeQueryPojo(CourseDisplayDTO.class, sql, id).get(0).getCourse_start_period();
		}
		
		public String getEndPeriod(int id) {
			String sql = "SELECT course_end_period from Course WHERE course_id = ?;";
			return db.executeQueryPojo(CourseDisplayDTO.class, sql, id).get(0).getCourse_end_period();
		}
		
		public int getLastID() {
			String sql = SQL_Last_ID;
			return db.executeQueryPojo(RegisterMaxDisplayDTO.class, sql).get(0).getReg_id();
		}
		
		//Method to send a fictitious mail (via .txt) to the person which has correctly register into a course
		public void sendRegistrationReceivedMail(int id, String name, String surnames, String coursename, 
				String mail, int fee, String reg_date) {
				try {
					String str = "Registration_" + id + "-" + name+"_"+surnames+"_"+"_"+coursename+".txt";
			        File file = new File(str);
			        FileWriter writer = new FileWriter(file);
			        writer.write("To: "+ name + " "+ surnames + " (" + mail + ")" + "\n\n"
			            + "Concept: Correct reception of your registration form on " + reg_date + " \n\n"
			            + "You have, from this moment, 24 hours to pay the course fee: " + fee + "€\n"
			            + " so that we are able to reserve you a place.\n");
			        writer.close();

			        System.out.println("Registration received mail sent successfully.\n");
			    } catch (IOException e) {
			        e.printStackTrace();
			      }
		}
}
