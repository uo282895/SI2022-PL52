package giis.demo.dto;

public class ReportOfExpensesDisplayDTO {

	private int course_id;
	private String course_name;
	private int teacher_paid_invoices_sum;
	private int teacher_invoices_sum;
	private int registration_paid_invoices_sum;
	private int registration_invoices_sum;
	
	public ReportOfExpensesDisplayDTO() {}
	
	public ReportOfExpensesDisplayDTO(int course_id, String course_name,
			int teacher_paid_invoices_sum, int teacher_invoices_sum, int registration_paid_invoices_sum,
			int registration_invoices_sum) {
		super();
		this.course_id = course_id;
		this.course_name = course_name;
		this.teacher_paid_invoices_sum = teacher_paid_invoices_sum;
		this.teacher_invoices_sum = teacher_invoices_sum;
		this.registration_paid_invoices_sum = registration_paid_invoices_sum;
		this.registration_invoices_sum = registration_invoices_sum;
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
	public int getTeacher_paid_invoices_sum() {
		return teacher_paid_invoices_sum;
	}
	public void setTeacher_paid_invoices_sum(int teacher_paid_invoices_sum) {
		this.teacher_paid_invoices_sum = teacher_paid_invoices_sum;
	}
	public int getTeacher_invoices_sum() {
		return teacher_invoices_sum;
	}
	public void setTeacher_invoices_sum(int teacher_invoices_sum) {
		this.teacher_invoices_sum = teacher_invoices_sum;
	}
	public int getRegistration_paid_invoices_sum() {
		return registration_paid_invoices_sum;
	}
	public void setRegistration_paid_invoices_sum(int registration_paid_invoices_sum) {
		this.registration_paid_invoices_sum = registration_paid_invoices_sum;
	}
	public int getRegistration_invoices_sum() {
		return registration_invoices_sum;
	}
	public void setRegistration_invoices_sum(int registration_invoices_sum) {
		this.registration_invoices_sum = registration_invoices_sum;
	}	
}
