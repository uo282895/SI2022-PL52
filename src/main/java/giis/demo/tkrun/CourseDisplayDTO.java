package giis.demo.tkrun;

public class CourseDisplayDTO {
	
	private String course_id;
	private String course_name;
	private String course_start_date;
	private String course_end_date;
	private String course_start_period;
	private String course_end_period;
	private String description;
	private int course_fee;
	private int available_places;
	private int total_places;
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCourse_fee() {
		return course_fee;
	}
	public void setCourse_fee(int course_fee) {
		this.course_fee = course_fee;
	}
	public int getAvailable_places() {
		return available_places;
	}
	public void setAvailable_places(int available_places) {
		this.available_places = available_places;
	}
	public int getTotal_places() {
		return total_places;
	}
	public void setTotal_places(int total_places) {
		this.total_places = total_places;
	}
	public String getCourse_start_date() {
		return course_start_date;
	}
	public void setCourse_start_date(String course_start_date) {
		this.course_start_date = course_start_date;
	}
	public String getCourse_end_date() {
		return course_end_date;
	}
	public void setCourse_end_date(String course_end_date) {
		this.course_end_date = course_end_date;
	}
	public CourseDisplayDTO(String course_id, String course_name, String course_start_date, String course_end_date,
			String course_start_period, String course_end_period, String description, int course_fee,
			int available_places, int total_places) {
		super();
		this.course_id = course_id;
		this.course_name = course_name;
		this.course_start_date = course_start_date;
		this.course_end_date = course_end_date;
		this.course_start_period = course_start_period;
		this.course_end_period = course_end_period;
		this.description = description;
		this.course_fee = course_fee;
		this.available_places = available_places;
		this.total_places = total_places;
	}
	public CourseDisplayDTO() {}
	
}
