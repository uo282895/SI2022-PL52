package giis.demo.coiipa;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import giis.demo.dto.HelpInvoiceDTO;
import giis.demo.dto.InvoiceDisplayDTO;
import giis.demo.dto.PaymentInputDTO;
import giis.demo.dto.TeacherInvoiceDisplayDTO;
import giis.demo.dto.TeacherRemunerationDTO;
import giis.demo.util.ApplicationException;
import giis.demo.util.Database;
import giis.demo.util.Util;

public class InvoiceModel {
	
	private Database db=new Database();
	
	public static final String SQL_LIST_TEACHERS=
			"SELECT c.course_id, t.teacher_id, t.teacher_name, t.teacher_surnames, c.course_name "+
			"FROM Teacher t "+ 
			"INNER JOIN Course c ON t.teacher_id = c.teacher_id "+ 
			"WHERE c.course_end_date < CURRENT_DATE";
	
	public static final String SQL_LIST_INVOICES=
			"SELECT i.invoice_id, t.teacher_name, t.teacher_surnames, t.fiscal_number, t.teacher_address, i.invoice_quantity "
			+ "FROM Invoice i "
			+ "INNER JOIN Teacher t ON i.teacher_id = t.teacher_id "
			+ "INNER JOIN Course c ON t.teacher_id = c.teacher_id "
			+ "WHERE c.course_id = ?";
	
	public static final String SQL_INSERT_INVOICE = "INSERT into Invoice(invoice_id, invoice_quantity, "
			+ "invoice_state, teacher_id, course_id) values(?, ?,'Received', ?, ?)";
	
	public static final String SQL_REMUNERATION = "SELECT c.teacher_remuneration "+
			"FROM Course c "+  
			"WHERE c.course_id = ?";
	
	public static final String SQL_INSERT_AMOUNTDATEHOUR=
			"INSERT into Payment(payment_id, amount, payment_date, payment_time, payment_type, "
			+ "invoice_id, reg_id) values(?, ?, ?, ?,'Invoice payment',?, null)";
	
	/**
	 * Obtains the list of teachers that have taught the last session of a course
	 */
	public List<TeacherInvoiceDisplayDTO> getListTeachers() {
		String sql= SQL_LIST_TEACHERS;
		return db.executeQueryPojo(TeacherInvoiceDisplayDTO.class, sql);
	}
	
	//Obtains the data to show of the selected course (its invoice)
	public InvoiceDisplayDTO getInvoice(int courseid) {
		List<InvoiceDisplayDTO> invoices = db.executeQueryPojo(InvoiceDisplayDTO.class, SQL_LIST_INVOICES, courseid);
		validateCondition(!invoices.isEmpty(),"The teacher has not yet generated the invoice for this course.");
		return invoices.get(0);
	}
	
	//Inserts a new invoice generated
	public void InsertInvoice(int invid, int quant, int teacherid, int courseid) {
		db.executeUpdate(SQL_INSERT_INVOICE, invid, quant, teacherid, courseid);
	}
	
	//Get the last id recorded on the table Invoice
	public int getLastInvoiceId () {
		String sql = "select * from Invoice order by invoice_id";
		
		List<InvoiceDisplayDTO> list = db.executeQueryPojo(InvoiceDisplayDTO.class, sql);
		int size = list.size();//size of the list (each element is a InvoiceDisplayDTO)
		if (size == 0) {
			return 0;
		}
		else return list.get(size -1).getInvoice_id();
	}
	
	//Get the last id recorded on the table Invoice
	public int getLastPaymentId () {
		String sql = "select * from Payment order by payment_id";
		
		List<PaymentInputDTO> list = db.executeQueryPojo(PaymentInputDTO.class, sql);
		int size = list.size();//size of the list (each element is a InvoiceDisplayDTO)
		if (size == 0) {
			return 0;
		}
		else return list.get(size -1).getPayment_id();
	}
	
	//Get the teacher's remuneration for the selected course
	public int getRemuneration(int courseid) {
		List<TeacherRemunerationDTO> list= db.executeQueryPojo(TeacherRemunerationDTO.class, SQL_REMUNERATION, courseid);
		return list.get(0).getTeacher_remuneration();
	}
	
	public String hasInvoice(int courseid) {
		String sql = "SELECT c.course_name, " + 
                "CASE WHEN i.invoice_id IS NULL THEN 'No' ELSE 'Yes' END AS has_invoice " +
                "FROM Course c " + 
                "LEFT JOIN Invoice i ON c.course_id = i.course_id " +
                "WHERE c.course_id = ?";
		
		return db.executeQueryPojo(HelpInvoiceDTO.class, sql, courseid).get(0).getHas_invoice();
	}


	//Method encharged of the validation of dates (both registration and payment)
	public void validateDate(Date paydate) {
		LocalDate localdate = LocalDate.now();
		
		//Actual date
		Date today = Date.from(localdate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		
		validateCondition(!paydate.before(today), "You cannot input past dates. Please, enter a valid date.");
		validateCondition(!paydate.after(today), "You cannot input future dates. Please, enter a valid date.");
	}
	
	//Method to insert into the DB each payment of an invoice
	public void insertPayment(int payid, int amount, Date paydate, Date payhour, int invid) {
		String d = Util.dateToIsoString(paydate);
		String h = Util.hourToIsoString(payhour);
		
		db.executeUpdate(SQL_INSERT_AMOUNTDATEHOUR, payid, amount, d, h, invid);
	}
	
	public void updateState(int invid) {
		String sql_state = "UPDATE Invoice " //Update the state (it is now wrong)
				+ "set invoice_state = 'Paid' where invoice_id = ?";
		db.executeUpdate(sql_state, invid);
	}
	
	public void updateFinished(int courseid) {
		String sql = "UPDATE Course " //Update the state (it is now wrong)
				+ "set course_state = 'Finished' where course_id = ?";
		db.executeUpdate(sql, courseid);
	}
	
	/* General use for object validation */
	public void validateNotNull(Object obj, String message) {
		if (obj==null)
			throw new ApplicationException(message);
	}
	private void validateCondition(boolean condition, String message) {
		if (!condition)
			throw new ApplicationException(message);
	}
}
