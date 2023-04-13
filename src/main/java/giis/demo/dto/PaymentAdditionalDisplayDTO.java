package giis.demo.dto;

public class PaymentAdditionalDisplayDTO {
	
    private int amount;
    private String payment_date;
    private String payment_type;
    
	public PaymentAdditionalDisplayDTO() {}
	public PaymentAdditionalDisplayDTO(int amount, String payment_date, String payment_type) {
		this.amount = amount;
		this.payment_date = payment_date;
		this.payment_type = payment_type;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	
}