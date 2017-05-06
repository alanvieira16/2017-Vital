package br.ufes.dwws.vital.dashboard.appointment.control;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.ufes.dwws.vital.dashboard.appointment.application.CreateAppointmentService;
import br.ufes.dwws.vital.domain.Appointment;
import br.ufes.dwws.vital.persistence.AppointmentDAO;

@Named
@RequestScoped
public class AppointmentsController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private CreateAppointmentService createAppointmentService;
	
	@Inject
	private HttpServletRequest request;
	
	private List<Appointment> appointments;
	
	@Inject
	public void init(AppointmentDAO appointmentDAO) {
		appointments = createAppointmentService.listAppointments();
	}

	public CreateAppointmentService getAppointmentsService() {
		return createAppointmentService;
	}

	public void setAppointmentsService(CreateAppointmentService createAppointmentService) {
		this.createAppointmentService = createAppointmentService;
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
