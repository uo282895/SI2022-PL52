package giis.demo.coiipa;

import java.util.List;

import giis.demo.dto.ReportOfExpensesDisplayDTO;
import giis.demo.util.Database;

public class ReportOfExpensesModel {

	private Database db = new Database();

	public static final String SQL_LIST_OF_EXPENSES=
			"SELECT c.course_id, c.course_name, "
			        + "COALESCE(tpis.teacher_paid_invoices_sum, 0) AS teacher_paid_invoices_sum, "
			        + "c.teacher_remuneration AS teacher_invoices_sum, "
			        + "COALESCE(rpis.registration_paid_invoices_sum, 0) AS registration_paid_invoices_sum, "
			        + "COALESCE(ris.registration_invoices_sum, 0) AS registration_invoices_sum "
			        + "FROM Course c "
			        + "LEFT JOIN ( "
			        + "  SELECT i.course_id, SUM(i.invoice_quantity) AS teacher_paid_invoices_sum "
			        + "  FROM Invoice i "
			        + "  WHERE i.invoice_state = 'Paid' "
			        + "  GROUP BY i.course_id "
			        + ") AS tpis ON c.course_id = tpis.course_id "
			        + "LEFT JOIN ( "
			        + "  SELECT i.course_id, SUM(i.invoice_quantity) AS registration_paid_invoices_sum "
			        + "  FROM Invoice i "
			        + "  INNER JOIN Payment p ON i.invoice_id = p.invoice_id "
			        + "  WHERE i.invoice_state = 'Paid' AND p.payment_type = 'Registration' "
			        + "  GROUP BY i.course_id "
			        + ") AS rpis ON c.course_id = rpis.course_id "
			        + "LEFT JOIN ( "
			        + "  SELECT r.course_id, COUNT(r.reg_id) * c.course_fee AS registration_invoices_sum "
			        + "  FROM Registration r "
			        + "  INNER JOIN Course c ON r.course_id = c.course_id "
			        + "  WHERE r.reg_state != 'Cancelled' "
			        + "  GROUP BY r.course_id "
			        + ") AS ris ON c.course_id = ris.course_id "
			        + "WHERE ((? BETWEEN c.course_start_date AND c.course_end_date) OR (? BETWEEN c.course_start_date AND c.course_end_date) "
			        + "OR (? < c.course_start_date AND ? > c.course_end_date));";
	
	
	public List<ReportOfExpensesDisplayDTO> getListExpenses(String date_start, String date_end) {
		String sql= SQL_LIST_OF_EXPENSES;
		return db.executeQueryPojo(ReportOfExpensesDisplayDTO.class, sql, date_start, date_end, date_start, date_end);
	}
	
	
}
