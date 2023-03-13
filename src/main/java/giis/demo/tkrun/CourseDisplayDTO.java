package giis.demo.tkrun;

public class CourseDisplayDTO {
	
	private String course_name;
	private String course_date;
	private String place;
	private int course_fee;
	private int available_places;
	private int total_places;
	
	
	public CourseDisplayDTO() {}
	public CourseDisplayDTO(String rowname, String rowdate, String rowplace, int rowfee) {
		this.course_name = rowname;
		this.course_date = rowdate;
		this.place = rowplace;
		this.course_fee = rowfee;
	}
	public String getName() { return this.course_name; }
	public String getDate() { return this.course_date; }
	public String getPlace() { return this.place; }
	public int getFee() { return this.course_fee; }
	public int getAvailable_places() { return this.available_places; }
	public int getTotal_places() { return this.total_places; }
	public void setName(String value) { this.course_name = value; }
	public void setDate(String value) { this.course_date = value; }
	public void setPlace(String value) { this.place = value; }
	public void setFee(int value) { this.course_fee = value; }
	public void setAvailablePlaces(int value) { this.available_places = value; }
	public void setTotalPlaces(int value) { this.total_places = value; }
}
