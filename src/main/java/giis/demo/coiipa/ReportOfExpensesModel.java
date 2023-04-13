package giis.demo.coiipa;

import java.util.List;

import giis.demo.dto.ReportOfExpensesDisplayDTO;
import giis.demo.util.Database;

public class ReportOfExpensesModel {

	private Database db = new Database();

	public static final String SQL_LIST_OF_EXPENSES=
			"SELECT c.course_id, c.course_name, "
			+ "SUM(CASE WHEN i.invoice_state = 'paid' THEN i.invoice_quantity ELSE 0 END) AS teacher_paid_invoices_sum, "
			+ "c.teacher_remuneration AS teacher_invoices_sum, "
			+ "SUM(CASE WHEN p.payment_type = 'Registration' AND i.invoice_state = 'paid' THEN i.invoice_quantity ELSE 0 END) "
			+ "AS registration_paid_invoices_sum, "
			+ "(SELECT SUM(i2.invoice_quantity) FROM Invoice i2 INNER JOIN Registration r ON i2.course_id = r.course_id WHERE "
			+ "r.course_id = c.course_id) AS registration_invoices_sum "
			+ "FROM Course c "
			+ "LEFT JOIN Invoice i ON c.teacher_id = i.teacher_id AND c.course_id = i.course_id "
			+ "LEFT JOIN Payment p ON i.invoice_id = p.invoice_id "
			+ "WHERE ((? BETWEEN c.course_start_date AND c.course_end_date) OR (? BETWEEN c.course_start_date AND c.course_end_date)"
			+ " OR (? < c.course_start_date AND ? > c.course_end_date)) "
			+ "GROUP BY c.course_id;";
	
	
	public List<ReportOfExpensesDisplayDTO> getListExpenses(String date_start, String date_end) {
		String sql= SQL_LIST_OF_EXPENSES;
		return db.executeQueryPojo(ReportOfExpensesDisplayDTO.class, sql, date_start, date_end, date_start, date_end);
	}
	
	
}
