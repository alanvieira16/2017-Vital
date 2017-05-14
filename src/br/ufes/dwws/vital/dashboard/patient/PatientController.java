package br.ufes.dwws.vital.dashboard.patient;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.ufes.dwws.util.Mail;
import br.ufes.dwws.util.Role;
import br.ufes.dwws.vital.converters.StringToSetConverter;
import br.ufes.dwws.vital.domain.Patient;
import br.ufes.dwws.vital.login.SessionController;
import br.ufes.inf.nemo.jbutler.TextUtils;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;

@Named
@SessionScoped
public class PatientController extends CrudController<Patient> implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagePatientsService managePatientsService;

	@Inject
	private SessionController sessionController;
	
	@Inject
	private HttpServletRequest request;

	private List<Patient> patients;
	private Patient patient = new Patient();

	private StringToSetConverter str2listConverter = new StringToSetConverter();

	@Inject
	public void init() {
		if(sessionController.hasLoggedUser()){
			patients = managePatientsService.list(sessionController.getCurrentUser().getId());
		}
	}

	public String register() {			
		ResourceBundle bundle = FacesContext.getCurrentInstance() .getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msgs"); 
		try {
			String rawPwd = patient.getPassword();
			String md5pwd = TextUtils.produceBase64EncodedMd5Hash(rawPwd);
			patient.setPassword(md5pwd);
			patient.setRole(Role.PATIENT);
			managePatientsService.create(patient);
			Mail mail = new Mail();
			String message = String.format(
					"<h2>%s</h2>"
					+ "<p>%s %s</p>"
					+ "<p>%s %s",
					bundle.getString("mail.created"), bundle.getString("mail.login"),
					patient.getEmail(), bundle.getString("mail.password"), rawPwd);
			mail.send(patient.getEmail(), bundle.getString("mail.subject"), message);
		} catch (NoSuchAlgorithmException e) {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "danger");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage",
					bundle.getString("alert.error"));
			return "/appointment/new";
		} catch (UnsupportedEncodingException e) {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "danger");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage",
					bundle.getString("alert.error"));
			return "/appointment/new";
		}
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "success");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage",
				bundle.getString("alert.patientCreated"));
		return "/index?faces-redirect=true";
	}
	
	public String details(String id) {
		patient = managePatientsService.retrieve(Long.parseLong(id));
		return "/patient/details?faces-redirect=true";
	}
	
	public String update(){
		managePatientsService.getDAO().merge(patient);
		return "/patient/index?faces-redirect=true";
	}

	public String edit(String id){
		patient = managePatientsService.retrieve(Long.parseLong(id));
		return "/patient/edit?faces-redirect=true";
	}
	
	public String delete(String id){
		patient = managePatientsService.retrieve(Long.parseLong(id));
		managePatientsService.delete(patient);
		return "/patient/index?faces-redirect=true";
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

	public StringToSetConverter getStr2listConverter() {
		return str2listConverter;
	}

	public void setStr2listConverter(StringToSetConverter str2listConverter) {
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

	public SessionController getSessionController() {
		return sessionController;
	}

	public void setSessionController(SessionController sessionController) {
		this.sessionController = sessionController;
	}

}
