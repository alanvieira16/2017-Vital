package br.ufes.dwws.vital.dashboard.appointment;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.dwws.vital.domain.Appointment;
import br.ufes.dwws.vital.persistence.AppointmentDAO;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Stateless
@PermitAll
public class ManageAppointmentsServiceBean extends CrudServiceBean<Appointment> implements ManageAppointmentsService {

	private static final long serialVersionUID = 1L;

	@EJB
	private AppointmentDAO appointmentDAO;

	@Override
	public BaseDAO<Appointment> getDAO() {
		return appointmentDAO;
	}
}
