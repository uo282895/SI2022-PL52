package giis.demo.tkrun;

public class PaymentInputDTO {

	private int available_places;
	private int payment_id;
	private int course_id;
	
	//Constructors
	public PaymentInputDTO() {}
	public PaymentInputDTO(int available_places, int payment_id, int course_id) {
		this.available_places = available_places;
		this.payment_id = payment_id;
		this.course_id = course_id;
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
	
}
