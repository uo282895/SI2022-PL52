package giis.demo.dto;

public class InvoiceDisplayDTO {
	
	private int invoice_id;
	private String teacher_name;
	private String teacher_surnames;
	private String fiscal_number;
	private String teacher_address;
	private int invoice_quantity;
	
	
	//Constructors
	public InvoiceDisplayDTO() {}
	public InvoiceDisplayDTO(int invoice_id, String teacher_name, String teacher_surnames, String fiscal_number,
			String teacher_address, int invoice_quantity) {
		this.invoice_id = invoice_id;
		this.teacher_name = teacher_name;
		this.teacher_surnames = teacher_surnames;
		this.fiscal_number = fiscal_number;
		this.teacher_address = teacher_address;
		this.invoice_quantity = invoice_quantity;
	}
	
	
	//Getters and setters
	public int getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
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
	public String getFiscal_number() {
		return fiscal_number;
	}
	public void setFiscal_number(String fiscal_number) {
		this.fiscal_number = fiscal_number;
	}
	public String getTeacher_address() {
		return teacher_address;
	}
	public void setTeacher_address(String teacher_address) {
		this.teacher_address = teacher_address;
	}
	public int getInvoice_quantity() {
		return invoice_quantity;
	}
	public void setInvoice_quantity(int invoice_quantity) {
		this.invoice_quantity = invoice_quantity;
	}
	
	
}
