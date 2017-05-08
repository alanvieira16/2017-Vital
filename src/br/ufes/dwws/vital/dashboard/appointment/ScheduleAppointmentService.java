package br.ufes.dwws.vital.dashboard.appointment;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.dwws.vital.domain.Appointment;
import br.ufes.dwws.vital.persistence.AppointmentDAO;

@Stateless
@LocalBean
public class ScheduleAppointmentService implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private AppointmentDAO appointmentDAO;

	@PersistenceContext
	private EntityManager entityManager;

	public List<Appointment> listAppointments() {
		List<Appointment> appointments = appointmentDAO.retrieveAll();
		return appointments;
	}
	
	public void schedule(Appointment appointment) {
		entityManager.persist(appointment);
	}
}
