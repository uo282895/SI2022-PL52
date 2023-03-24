package giis.demo.dto;

public class CourseInfoDisplayDTO {
	
	//Private attributes
	private String objectives;
	private String description;
	private String place;
	private String teacher_name;
	private String teacher_surnames;
	
	//Constructors
	public CourseInfoDisplayDTO() {}
	public CourseInfoDisplayDTO(String objectives, String description, String place, String teacher_name, String teacher_surnames) {
		this.objectives = objectives;
		this.description = description;
		this.place = place;
		this.teacher_name = teacher_name;
		this.teacher_surnames = teacher_surnames;
	}

	
	//Getters and setters
	public String getObjectives() {
		return objectives;
	}
	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
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
	
	
	
}
