package br.ufes.dwws.vital.dashboard.patient;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.ufes.dwws.util.Role;
import br.ufes.dwws.vital.converters.StringToListConverter;
import br.ufes.dwws.vital.domain.Patient;
import br.ufes.inf.nemo.jbutler.TextUtils;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;

@Named
@RequestScoped
public class PatientController extends CrudController<Patient> implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagePatientsService managePatientsService;

	@Inject
	private HttpServletRequest request;

	private List<Patient> patients;
	private Patient patient = new Patient();

	private StringToListConverter str2listConverter = new StringToListConverter();

	@Inject
	public void init() {
		patients = managePatientsService.getDAO().retrieveAll();
	}

	public String register() {
		try {
			String md5pwd = TextUtils.produceBase64EncodedMd5Hash(patient.getPassword());
			patient.setPassword(md5pwd);
			patient.setRole(Role.PATIENT);
			managePatientsService.create(patient);
		} catch (NoSuchAlgorithmException e) {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "danger");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage",
					"Something wrong happened. Try again.");
			return "/appointment/new";
		} catch (UnsupportedEncodingException e) {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "danger");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage",
					"Something wrong happened. Try again.");
			return "/appointment/new";
		}
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "success");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage",
				"The patient has been registered successfully");
		return "/index?faces-redirect=true";
	}

	public String details(String id) {
		patient = managePatientsService.retrieve(Long.parseLong(id));
		return "/patient/details?faces-redirect=true";
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

	@Override
	protected CrudService<Patient> getCrudService() {
		return managePatientsService;
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
	}

}
