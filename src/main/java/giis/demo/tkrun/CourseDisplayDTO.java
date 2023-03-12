package giis.demo.tkrun;

public class CourseDisplayDTO {
	
    private int course_id;
	private String course_name;
	private String description;
	private String objectives;
	private int course_hours;
	private String course_date;
	private String course_time;
	private String course_start_period;
	private String course_end_period;
	private int total_places;
	private int available_places;
	private float course_fee;
	private String place;
	private String course_state;
		
	public CourseDisplayDTO(int course_id, String course_name, String description, String objectives,
			int course_hours, String course_date, String course_time, String course_start_period,
			String course_end_period, int total_places, int available_places, float course_fee, String place,
			String course_state) {
		super();
		this.course_id = course_id;
		this.course_name = course_name;
		this.description = description;
		this.objectives = objectives;
		this.course_hours = course_hours;
		this.course_date = course_date;
		this.course_time = course_time;
		this.course_start_period = course_start_period;
		this.course_end_period = course_end_period;
		this.total_places = total_places;
		this.available_places = available_places;
		this.course_fee = course_fee;
		this.place = place;
		this.course_state = course_state;
	}
	
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getObjectives() {
		return objectives;
	}
	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}
	public int getCourse_hours() {
		return course_hours;
	}
	public void setCourse_hours(int course_hours) {
		this.course_hours = course_hours;
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
	public float getCourse_fee() {
		return course_fee;
	}
	public void setCourse_fee(float course_fee) {
		this.course_fee = course_fee;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getCourse_state() {
		return course_state;
	}
	public void setCourse_state(String course_state) {
		this.course_state = course_state;
	}
	
	
	

}
