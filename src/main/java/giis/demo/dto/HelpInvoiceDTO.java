package giis.demo.dto;

public class HelpInvoiceDTO {
	
	private String course_name;
	private String has_invoice;
	
	public HelpInvoiceDTO(){}
	public HelpInvoiceDTO(String course_name, String has_invoice) {
		this.course_name = course_name;
		this.has_invoice = has_invoice;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getHas_invoice() {
		return has_invoice;
	}
	public void setHas_invoice(String has_invoice) {
		this.has_invoice = has_invoice;
	}
	
}
