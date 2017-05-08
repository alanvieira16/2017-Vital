package br.ufes.dwws.vital.dashboard.appointment;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.ufes.dwws.vital.dashboard.doctor.ListDoctorsService;
import br.ufes.dwws.vital.domain.Appointment;
import br.ufes.dwws.vital.domain.Doctor;
import br.ufes.dwws.vital.persistence.DoctorDAO;
import br.ufes.inf.nemo.jbutler.ejb.controller.PersistentObjectConverterFromId;

@Named
@RequestScoped
public class AppointmentController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private ScheduleAppointmentService scheduleAppointmentService;
	
	@EJB
	private ListDoctorsService listDoctorsService;
	
	@Inject
	private HttpServletRequest request;

	private PersistentObjectConverterFromId<Doctor> doctorConverter;
	
	private List<Appointment> appointments;
	private List<Doctor> doctors;
	private Appointment appointment = new Appointment();
	
	@Inject
	public void init(DoctorDAO doctorDAO) {
		doctorConverter = new PersistentObjectConverterFromId<>(doctorDAO);
		appointments = scheduleAppointmentService.listAppointments();
		doctors = listDoctorsService.listDoctors();
	}
	
	public String schedule() {
		try {
			scheduleAppointmentService.schedule(appointment);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "success");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage", "Your appointment has been scheduled successfully");
			return "/index?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "danger");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage", "Something wrong happened. Try again.");
			return "/appointment/new";
		}
				
	}

	public ScheduleAppointmentService getAppointmentsService() {
		return scheduleAppointmentService;
	}

	public void setAppointmentsService(ScheduleAppointmentService scheduleAppointmentService) {
		this.scheduleAppointmentService = scheduleAppointmentService;
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

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public PersistentObjectConverterFromId<Doctor> getDoctorConverter() {
		return doctorConverter;
	}

	public void setDoctorConverter(PersistentObjectConverterFromId<Doctor> doctorConverter) {
		this.doctorConverter = doctorConverter;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}
	
}
