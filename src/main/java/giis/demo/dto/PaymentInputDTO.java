package giis.demo.dto;

public class PaymentInputDTO {

	private int available_places;
	private int payment_id;
	private int course_id;
	private int net_amount;
	
	//Constructors
	public PaymentInputDTO() {}
	public PaymentInputDTO(int available_places, int payment_id, int course_id, int net_amount) {
		this.available_places = available_places;
		this.payment_id = payment_id;
		this.course_id = course_id;
		this.net_amount = net_amount;
	}

	
	//Getters and setters
	public int getAvailable_places() {
		return available_places;
	}

	public void setAvailable_places(int available_places) {
		this.available_places = available_places;
	}
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public int getNet_amount() {
		return net_amount;
	}
	public void setNet_amount(int net_amount) {
		this.net_amount = net_amount;
	}
	
}
