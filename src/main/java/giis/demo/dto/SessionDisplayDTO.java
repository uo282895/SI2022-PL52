package giis.demo.dto;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SessionDisplayDTO {

	private int session_id;
	private String session_location;
	private String session_date;
	private String session_hour;
	private float session_hours;
	private int course_id;
	
	public SessionDisplayDTO() {}
	
	public static float getTotalNumberOfHours(List<SessionDisplayDTO> list) {
		float n = (float) 0.0;
		for(SessionDisplayDTO s: list) 
			n += s.getSession_hours();
		return n;
	}
	
	public static Date getFirstDate(List<SessionDisplayDTO> list) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = dateFormat.parse(list.get(0).getSession_date());
			for(SessionDisplayDTO s: list) {
				if(d.after(dateFormat.parse(s.getSession_date()))) {
					d = dateFormat.parse(s.getSession_date());
				}
			}
			return d;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Date getLastDate(List<SessionDisplayDTO> list) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = dateFormat.parse(list.get(0).getSession_date());
			for(SessionDisplayDTO s: list) {
				if(d.before(dateFormat.parse(s.getSession_date()))) {
					d = dateFormat.parse(s.getSession_date());
				}
			}
			return d;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	
	public String getSession_hour() {
		return session_hour;
	}


	public void setSession_hour(String session_hour) {
		this.session_hour = session_hour;
	}


	public int getSession_id() {
		return session_id;
	}


	public void setSession_id(int session_id) {
		this.session_id = session_id;
	}


	public String getSession_location() {
		return session_location;
	}


	public void setSession_location(String session_location) {
		this.session_location = session_location;
	}


	public String getSession_date() {
		return session_date;
	}


	public void setSession_date(String session_date) {
		this.session_date = session_date;
	}


	public float getSession_hours() {
		return session_hours;
	}


	public void setSession_hours(float session_hours) {
		this.session_hours = session_hours;
	}


	public int getCourse_id() {
		return course_id;
	}


	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	
	
	
}
