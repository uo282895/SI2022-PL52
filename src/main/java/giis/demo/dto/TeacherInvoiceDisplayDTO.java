package giis.demo.dto;

public class TeacherInvoiceDisplayDTO {
	
	private String course_id;
	private String teacher_id;
	private String teacher_name;
	private String teacher_surnames;
	private String course_name;
	
	//Constructors
	public TeacherInvoiceDisplayDTO() {}
	public TeacherInvoiceDisplayDTO(String course_id, String teacher_id, String teacher_name, String teacher_surnames,
			String course_name) {
		this.course_id = course_id;
		this.teacher_id = teacher_id;
		this.teacher_name = teacher_name;
		this.teacher_surnames = teacher_surnames;
		this.course_name = course_name;
	}

	//Getters and setters
	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

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
