package giis.demo.dto;

public class TeacherDisplayDTO {
	
	private int teacher_id; 
	private String teacher_name;
	private String teacher_surnames;
	private String teacher_phone; 
	private String teacher_email;	
	
	//Constructors
	public TeacherDisplayDTO(){}
	
	public TeacherDisplayDTO(int teacher_id, String teacher_name, String teacher_surname, String teacher_phone, String teacher_email, int fee, String datehour) {
		this.teacher_id = teacher_id;
		this.teacher_name = teacher_name;
		this.teacher_surnames = teacher_surname;
		this.teacher_phone = teacher_phone;
		this.teacher_email = teacher_email;
	}
	
	public String getTeacher_surnames() {
		return teacher_surnames;
	}

	public void setTeacher_surnames(String teacher_surnames) {
		this.teacher_surnames = teacher_surnames;
	}

	public int getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	
	public String getTeacher_phone() {
		return teacher_phone;
	}
	public void setTeacher_phone(String teacher_phone) {
		this.teacher_phone = teacher_phone;
	}
	public String getTeacher_email() {
		return teacher_email;
	}
	public void setTeacher_email(String teacher_email) {
		this.teacher_email = teacher_email;
	}
		
}