package br.ufes.dwws.vital.dashboard.appointment.control;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.ufes.dwws.vital.dashboard.appointment.application.AppointmentsService;
import br.ufes.dwws.vital.domain.Appointment;
import br.ufes.dwws.vital.persistence.AppointmentDAO;

@Named
@RequestScoped
public class AppointmentsController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private AppointmentsService appointmentsService;
	
	@Inject
	private HttpServletRequest request;
	
	private List<Appointment> appointments;
	
	@Inject
	public void init(AppointmentDAO appointmentDAO) {
		appointments = appointmentsService.listAppointments();
	}

	public AppointmentsService getAppointmentsService() {
		return appointmentsService;
	}

	public void setAppointmentsService(AppointmentsService appointmentsService) {
		this.appointmentsService = appointmentsService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
}
