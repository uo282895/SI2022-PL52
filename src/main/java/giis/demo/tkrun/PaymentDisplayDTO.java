package giis.demo.tkrun;

public class PaymentDisplayDTO {
	
	private String coursename;
	private String name;
	private String surnames;
	private String email;
	private int fee;
	private String datehour;
	
	
	//Constructors
	public PaymentDisplayDTO(){}
	public PaymentDisplayDTO(String coursename, String name, String surnames, String email, int fee, String datehour) {
		this.coursename = coursename;
		this.name = name;
		this.surnames = surnames;
		this.email = email;
		this.fee = fee;
		this.datehour = datehour;
	}
	
	
	//Getters and setters
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurnames() {
		return surnames;
	}
	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public String getDatehour() {
		return datehour;
	}
	public void setDatehour(String datehour) {
		this.datehour = datehour;
	}
	
	
	
}
