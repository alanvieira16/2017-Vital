package br.ufes.dwws.vital.dashboard.patient;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.ufes.dwws.vital.converters.StringToListConverter;
import br.ufes.dwws.vital.domain.Patient;
import br.ufes.dwws.vital.persistence.AppointmentDAO;
import br.ufes.inf.nemo.jbutler.TextUtils;

@Named
@RequestScoped
public class PatientController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private RegisterPatientService registerPatientService;

	@EJB
	private ListPatientsService listPatientsService;
	
	@Inject
	private HttpServletRequest request;
	
	private List<Patient> patients;
	private Patient patient = new Patient();
	
	private StringToListConverter str2listConverter = new StringToListConverter();
	
	@Inject
	public void init(AppointmentDAO appointmentDAO) {
		patients = listPatientsService.listAppointments();
	}
	
	public String register() {
		try {
			String md5pwd = TextUtils.produceMd5Hash(patient.getPassword());
			patient.setPassword(md5pwd);
			registerPatientService.register(patient);
		} catch (NoSuchAlgorithmException e) {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "danger");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage", "Something wrong happened. Try again.");
			return "/patient/new";
		}
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "success");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage", "The patient has been registered successfully");
		return "index?faces-redirect=true";
	}

	public RegisterPatientService getRegisterPatientService() {
		return registerPatientService;
	}

	public void setRegisterPatientService(RegisterPatientService registerPatientService) {
		this.registerPatientService = registerPatientService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public StringToListConverter getStr2listConverter() {
		return str2listConverter;
	}

	public void setStr2listConverter(StringToListConverter str2listConverter) {
		this.str2listConverter = str2listConverter;
	}
	
	
}
