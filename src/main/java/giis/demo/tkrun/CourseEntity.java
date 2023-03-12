package giis.demo.tkrun;

public class CourseEntity {
	private String course_id;
	private String course_name; //las fechas son string (vienen de sqlite)
	private String course_date;
	private String place;
	private String descr;
	private int course_fee;

	public String getId() { return this.course_id; }
	public String getName() { return this.course_name; }
	public String getFecha() { return this.course_date; }
	public String getDescr() { return this.descr; }
	public String getPlace() { return this.place; }
	public int getFee() { return this.course_fee; }
	public void setId(String value) { this.course_id=value; }
	public void setName(String value) { this.course_name=value; }
	public void setFecha(String value) { this.course_date=value; }
	public void setDescr(String value) { this.descr=value; }
	public void setPlace(String value) { this.place=value; }
	public void setFee(int value) { this.course_fee=value; }
}
