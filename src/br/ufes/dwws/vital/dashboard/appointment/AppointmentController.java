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
import br.ufes.dwws.vital.dashboard.patient.ManagePatientsService;
import br.ufes.dwws.vital.domain.Appointment;
import br.ufes.dwws.vital.domain.Doctor;
import br.ufes.dwws.vital.login.SessionController;
import br.ufes.dwws.vital.persistence.DoctorDAO;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.Criterion;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.CriterionType;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.Filter;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.jbutler.ejb.controller.PersistentObjectConverterFromId;

@Named
@RequestScoped
public class AppointmentController extends CrudController<Appointment> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManageAppointmentsService manageAppointmentsService;
	
	@EJB
	private ListDoctorsService listDoctorsService;
	
	@EJB
	private ManagePatientsService managePatientsService;
	
	@Inject
	private SessionController sessionController;
	
	@Inject
	private HttpServletRequest request;

	private PersistentObjectConverterFromId<Doctor> doctorConverter;
	
	private List<Appointment> appointments;
	private List<Doctor> doctors;
	private Appointment appointment = new Appointment();
	
	@Inject
	public void init(DoctorDAO doctorDAO) {
		doctorConverter = new PersistentObjectConverterFromId<>(doctorDAO);
		doctors = listDoctorsService.listDoctors();
		appointments = manageAppointmentsService.list(sessionController.getCurrentUser());
		
	}
	
	public String schedule() {
		try {
			appointment.setPatient(managePatientsService.getDAO().retrieveById(sessionController.getCurrentUser().getId()));
			manageAppointmentsService.create(appointment);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "success");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage", "Your appointment has been scheduled successfully");
			return "/index?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "danger");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage", "Something wrong happened. Try again.");
			return "/appointment/new";
		}
	}

	public ManageAppointmentsService getAppointmentsService() {
		return manageAppointmentsService;
	}

	public void setAppointmentsService(ManageAppointmentsService manageAppointmentsService) {
		this.manageAppointmentsService = manageAppointmentsService;
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

	@Override
	protected CrudService<Appointment> getCrudService() {
		return manageAppointmentsService;
	}

	@Override
	protected void initFilters() {}

	public SessionController getSessionController() {
		return sessionController;
	}

	public void setSessionController(SessionController sessionController) {
		this.sessionController = sessionController;
	}
	
}
