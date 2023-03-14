package giis.demo.tkrun;

public class CourseDisplayDTO {
	
	private String course_name;
	private String course_date;
	private String place;
	private int course_fee;
	private int available_places;
	private int total_places;
	
	
	public CourseDisplayDTO() {}


	public String getCourse_name() {
		return course_name;
	}


	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}


	public String getCourse_date() {
		return course_date;
	}


	public void setCourse_date(String course_date) {
		this.course_date = course_date;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
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


	public CourseDisplayDTO(String course_name, String course_date, String place, int course_fee, int available_places,
			int total_places) {
		super();
		this.course_name = course_name;
		this.course_date = course_date;
		this.place = place;
		this.course_fee = course_fee;
		this.available_places = available_places;
		this.total_places = total_places;
	}
}
