package giis.demo.tkrun;

public class CourseDisplayDTO {
	
	//Private attributes
	private String course_name;
	private String course_state;
	private String course_start_period;
	private String course_end_period;
	private int total_places;
	private int available_places;
	private String course_date;
	private String course_time;
	private String course_id;
	
	//Constructors
	public CourseDisplayDTO() {}
	public CourseDisplayDTO(String course_name, String course_state, String course_start_period,
			String course_end_period, int total_places, int available_places, String course_date, String course_time, String course_id) {
		super();
		this.course_name = course_name;
		this.course_state = course_state;
		this.course_start_period = course_start_period;
		this.course_end_period = course_end_period;
		this.total_places = total_places;
		this.available_places = available_places;
		this.course_date = course_date;
		this.course_time = course_time;
		this.course_id = course_id;
	}
	
	//Getters and setters
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_state() {
		return course_state;
	}
	public void setCourse_state(String course_state) {
		this.course_state = course_state;
	}
	public String getCourse_start_period() {
		return course_start_period;
	}
	public void setCourse_start_period(String course_start_period) {
		this.course_start_period = course_start_period;
	}
	public String getCourse_end_period() {
		return course_end_period;
	}
	public void setCourse_end_period(String course_end_period) {
		this.course_end_period = course_end_period;
	}
	public int getTotal_places() {
		return total_places;
	}
	public void setTotal_places(int total_places) {
		this.total_places = total_places;
	}
	public int getAvailable_places() {
		return available_places;
	}
	public void setAvailable_places(int available_places) {
		this.available_places = available_places;
	}
	public String getCourse_date() {
		return course_date;
	}
	public void setCourse_date(String course_date) {
		this.course_date = course_date;
	}
	public String getCourse_time() {
		return course_time;
	}
	public void setCourse_time(String course_time) {
		this.course_time = course_time;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	
	
	
}

