package giis.demo.dto;

public class RegistrationDisplayDTO {

	private int reg_id;
	private String course_name;
	private String reg_name;
	private String reg_surnames;
	private String reg_email;
	private String reg_phone;
	private String reg_date;
	private String course_start_date;
	private String reg_state;
	private int course_id;
	private double total_payment;
	
	public RegistrationDisplayDTO(){}
	public RegistrationDisplayDTO(int reg_id, String course_name, String reg_name, String reg_surnames, String reg_email, String reg_phone,
			String reg_date, String course_start_date, String reg_state, int course_id, double total_payment) {

		super();
		this.reg_id = reg_id;
		this.course_name = course_name;
		this.reg_name = reg_name;
		this.reg_surnames = reg_surnames;
		this.reg_email = reg_email;
		this.reg_phone = reg_phone;
		this.reg_date = reg_date;
		this.course_start_date = course_start_date;
		this.course_id = course_id;
		this.total_payment = total_payment;
	}
	
	public int getReg_id() {
		return reg_id;
	}
	public void setReg_id(int reg_id) {
		this.reg_id = reg_id;
	}
	public double getTotal_payment() {
		return total_payment;
	}
	public void setTotal_payment(double total_payment) {
		this.total_payment = total_payment;
	}
	public String getCourse_start_date() {
		return course_start_date;
	}
	public void setCourse_start_date(String course_start_date) {
		this.course_start_date = course_start_date;
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
	public String getReg_state() {
		return reg_state;
	}
	public void setReg_state(String reg_state) {
		this.reg_state = reg_state;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	
}
