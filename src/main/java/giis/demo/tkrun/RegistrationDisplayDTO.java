package giis.demo.tkrun;

public class RegistrationDisplayDTO {

	private int reg_id;
	private String course_name;
	private String reg_name;
	private String reg_surnames;
	private String reg_email;
	private String reg_phone;
	private String reg_date;
	private String reg_time;
	private String course_date;
	private String course_time;
	private double amount;
	
	public RegistrationDisplayDTO(){}
	public RegistrationDisplayDTO(int reg_id, String course_name, String reg_name, String reg_surnames, String reg_email, String reg_phone,
			String reg_date, String reg_time, String course_date, String course_time, double amount) {
		super();
		this.reg_id = reg_id;
		this.course_name = course_name;
		this.reg_name = reg_name;
		this.reg_surnames = reg_surnames;
		this.reg_email = reg_email;
		this.reg_phone = reg_phone;
		this.reg_date = reg_date;
		this.reg_time = reg_time;
		this.course_date = course_date;
		this.course_time = course_time;
		this.amount = amount;
	}
	
	public int getReg_id() {
		return reg_id;
	}
	public void setReg_id(int reg_id) {
		this.reg_id = reg_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
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
	public String getReg_phone() {
		return reg_phone;
	}
	public void setReg_phone(String reg_phone) {
		this.reg_phone = reg_phone;
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
