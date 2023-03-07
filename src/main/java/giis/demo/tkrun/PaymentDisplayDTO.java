package giis.demo.tkrun;

public class PaymentDisplayDTO {
	
	private String course_name;
	private String reg_name;
	private String reg_surnames;
	private String reg_email;
	private int course_fee;
	private String reg_date;
	private String reg_time;
	
	
	//Constructors
	public PaymentDisplayDTO(){}
	public PaymentDisplayDTO(String coursename, String name, String surnames, String email, int fee, String date, String time) {
		this.course_name = coursename;
		this.reg_name = name;
		this.reg_surnames = surnames;
		this.reg_email = email;
		this.course_fee = fee;
		this.reg_date = date;
		this.reg_time = time;
	}
	
	
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getReg_name() {
		return reg_name;
	}
	public void setReg_name(String reg_name) {
		this.reg_name = reg_name;
	}
	public String getReg_surnames() {
		return reg_surnames;
	}
	public void setReg_surnames(String reg_surnames) {
		this.reg_surnames = reg_surnames;
	}
	public String getReg_email() {
		return reg_email;
	}
	public void setReg_email(String reg_email) {
		this.reg_email = reg_email;
	}
	public int getCourse_fee() {
		return course_fee;
	}
	public void setCourse_fee(int course_fee) {
		this.course_fee = course_fee;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getReg_time() {
		return reg_time;
	}
	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}
	
}
