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
	                + "  WHERE i.invoice_state = 'Paid' AND i.invoice_state = 'Profpay'"
	                + "  GROUP BY i.course_id "
	                + ") AS tpis ON c.course_id = tpis.course_id "
	                + "LEFT JOIN ( "
	                + "  SELECT c.course_id, SUM(c.course_fee * confirmed_registrations_count) AS registration_paid_invoices_sum "
	                + "  FROM Course c "
	                + "  INNER JOIN ( "
	                + "    SELECT r.course_id, COUNT(*) AS confirmed_registrations_count "
	                + "    FROM Registration r "
	                + "    WHERE (r.reg_state = 'Confirmed' AND r.reg_state = 'Confirmed - Profpay' AND r.reg_state = 'Compensate')  "
	                + "    GROUP BY r.course_id "
	                + "  ) AS confirmed_regs ON c.course_id = confirmed_regs.course_id "
	                + "  GROUP BY c.course_id "
	                + ") AS rpis ON c.course_id = rpis.course_id "
	                + "LEFT JOIN ( "
	                + "  SELECT r.course_id, COUNT(r.reg_id) * c.course_fee AS registration_invoices_sum "
	                + "  FROM Registration r "
	                + "  INNER JOIN Course c ON r.course_id = c.course_id "
	                + "  WHERE (r.reg_state != 'Cancelled' AND r.reg_state != 'Cancelled - Compensate' and r.reg_state != 'Cancelled - Profpay') "
	                + "  GROUP BY r.course_id "
	                + ") AS ris ON c.course_id = ris.course_id "
	                + "WHERE ((? BETWEEN c.course_start_date AND c.course_end_date) OR (? BETWEEN c.course_start_date AND c.course_end_date) "
	                + "OR (? <= c.course_start_date AND ? >= c.course_end_date));";
	
	
	public List<ReportOfExpensesDisplayDTO> getListExpenses(String date_start, String date_end) {
		String sql= SQL_LIST_OF_EXPENSES;
		return db.executeQueryPojo(ReportOfExpensesDisplayDTO.class, sql, date_start, date_end, date_start, date_end);
	}
	
	
}
