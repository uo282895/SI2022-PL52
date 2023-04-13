package giis.demo.dto;

public class TeacherInvoiceDisplayDTO {
	
	private String teacher_name;
	private String teacher_surnames;
	private String course_name;
	
	
	//Constructors
	public TeacherInvoiceDisplayDTO() {}
	public TeacherInvoiceDisplayDTO(String teacher_name, String teacher_surnames, String course_name) {
		this.teacher_name = teacher_name;
		this.teacher_surnames = teacher_surnames;
		this.course_name = course_name;
	}
	
	
	//Getters and setters
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getTeacher_surnames() {
		return teacher_surnames;
	}
	public void setTeacher_surnames(String teacher_surnames) {
		this.teacher_surnames = teacher_surnames;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	
}
