package giis.demo.tkrun;

import java.util.Date;
import java.util.List;

import giis.demo.util.ApplicationException;
import giis.demo.util.Database;
import giis.demo.util.Util;

public class PaymentsModel {
	//private static final String MSG_FECHA_INSCRIPCION_NO_NULA = "La fecha de inscripcion no puede ser nula";

	private Database db=new Database();
	
	public static final String SQL_LIST_PAYMENTS=
			"SELECT c.course_name, r.reg_name, r.reg_surnames, "
			+ "r.reg_email, c.course_fee, r.reg_date_time"
			+"FROM Course c"
			+"INNER JOIN Registration r ON c.course_id = r.course_id"
			+"WHERE c.state = 'Active' AND r.reg_state = 'Received'";
	
	/**
	 * Obtiene la lista de carreras futuras (posteriores a una fecha dada) con el id, descripcion
	 * y la indicacion de si tienen inscripcion abierta.
	 * Implementacion usando la utilidad que obtiene una lista de arrays de objetos 
	 * resultado de la ejecucion de una query sql
	 */
	public List<Object[]> getListPaymentsArray() {
		//validateNotNull(fechaInscripcion,MSG_FECHA_INSCRIPCION_NO_NULA);
		//concatena los campos deseados en una unica columna pues el objetivo es devolver una lista de strings
		String sql="SELECT course_name || '-' || reg_name || '-' || reg_surnames "
				+ "|| '-' || reg_email || '-' || course_fee || '-' || reg_date_time "
				+ " as item from (" + SQL_LIST_PAYMENTS + ")";
		//String d=Util.dateToIsoString(fechaInscripcion);
		return db.executeQueryArray(sql);
	}
	
	/**
	 * Obtiene la lista de carreras activas en forma objetos para una fecha de inscripcion dada
	 */
	public List<PaymentDisplayDTO> getListPayments() {
		//validateNotNull(fechaInscripcion,MSG_FECHA_INSCRIPCION_NO_NULA);
		String sql= 
				"SELECT c.course_name, r.reg_name, r.reg_surnames, "
				+ "r.reg_email, c.course_fee, r.reg_date_time"
				+"FROM Course c"
				+"INNER JOIN Registration r ON c.course_id = r.course_id"
				+"WHERE c.state = 'Active' AND r.reg_state = 'Received'";
		//String d=Util.dateToIsoString(fechaInscripcion);
		return db.executeQueryPojo(PaymentDisplayDTO.class, sql);
	}
	
	/**
	 * Obtiene todos los datos de la carrera con el id indicado
	 */
	public RegistrationEntity getRegistration(int id) {
		String sql="SELECT reg_id,reg_name,reg_surnames,reg_phone,reg_email, "
				+ "reg_bank_account, reg_date_time, reg_state from Registration where id=?";
		List<RegistrationEntity> registrations=db.executeQueryPojo(RegistrationEntity.class, sql, id);
		validateCondition(!registrations.isEmpty(),"Registration ID not found: "+id);
		return registrations.get(0);
	}
	
	/* General use for object validation */
	private void validateNotNull(Object obj, String message) {
		if (obj==null)
			throw new ApplicationException(message);
	}
	private void validateCondition(boolean condition, String message) {
		if (!condition)
			throw new ApplicationException(message);
	}
}
