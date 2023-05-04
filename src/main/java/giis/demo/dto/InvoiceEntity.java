package giis.demo.dto;

public class InvoiceEntity {
	
	private int invoice_id;
	private int invoice_quantity;
	private String invoice_state;
	
	public int getInvoice_id() {
		return invoice_id;
	}
	public int getInvoice_quantity() {
		return invoice_quantity;
	}
	public String getInvoice_state() {
		return invoice_state;
	}
	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}
	public void setInvoice_quantity(int invoice_quantity) {
		this.invoice_quantity = invoice_quantity;
	}
	public void setInvoice_state(String invoice_state) {
		this.invoice_state = invoice_state;
	}
	

	
}
