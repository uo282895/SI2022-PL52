package app;


/*Each of the rows to show to the user*/

public class RegistrationDisplayDTO {
	//Private attributes
	private String id;
	private String name;
	private String surname;
	
	//Constructors
	public RegistrationDisplayDTO() {}
	public RegistrationDisplayDTO(String rowId, String rowName, String rowSurname) {
		this.id=rowId;
		this.name=rowName;
		this.surname=rowSurname;
	}
	
	//Getters and setters
	public String getId() { return this.id; }
	public String getName() { return this.name; }
	public String getSurname() { return this.surname; }
	public void setId(String value) { this.id=value; }
	public void setName(String value) { this.name=value; }
	public void setSurname(String value) { this.surname=value; }
		 
}
