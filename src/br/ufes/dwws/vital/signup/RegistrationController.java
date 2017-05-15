package br.ufes.dwws.vital.signup;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.ufes.dwws.util.Mail;
import br.ufes.dwws.vital.domain.Doctor;
import br.ufes.dwws.vital.domain.Hospital;
import br.ufes.dwws.vital.login.LoginService;
import br.ufes.dwws.vital.login.SessionController;
import br.ufes.dwws.vital.persistence.HospitalDAO;
import br.ufes.inf.nemo.jbutler.TextUtils;
import br.ufes.inf.nemo.jbutler.ejb.controller.PersistentObjectConverterFromId;

@Named
@RequestScoped
public class RegistrationController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private RegistrationService registrationService;

	@Inject
	private HttpServletRequest request;

	@Inject
	private SessionController sessionController;

	@EJB
	private LoginService loginService;

	private Doctor doctor = new Doctor();

	private String role;

	private PersistentObjectConverterFromId<Hospital> hospitalConverter;
	private List<Hospital> hospitals;

	@Inject
	public void init(HospitalDAO hospitalDAO) {
		hospitals = registrationService.listHospitals();
		hospitalConverter = new PersistentObjectConverterFromId<>(hospitalDAO);
	}

	public String register() {

		String rawPwd = doctor.getPassword();
		ResourceBundle bundle = FacesContext.getCurrentInstance() .getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msgs"); 
		
		try {
			
			String md5pwd = TextUtils.produceBase64EncodedMd5Hash(rawPwd);
			doctor.setPassword(md5pwd);
			registrationService.register(doctor);

			Mail mail = new Mail();
			String message = String.format(
					"<h2>%s</h2>"
					+ "<p>%s %s</p>"
					+ "<p>%s %s",
					bundle.getString("mail.created"), bundle.getString("mail.login"),
					doctor.getEmail(), bundle.getString("mail.password"), rawPwd);
			
			mail.send(doctor.getEmail(), bundle.getString("mail.subject"), message);
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			request.setAttribute("alertType", "danger");
			request.setAttribute("message", bundle.getString("alert.error"));
			return "/signup/index.xhtml";
		}

		sessionController.login(doctor.getEmail(), rawPwd);

		getFlash().put("alertType", "success");
		getFlash().put("alertMessage", bundle.getString("alert.doctorCreated"));

		return "/index.xhtml?faces-redirect=true";
		
	}

	private Flash getFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}

	public List<Hospital> getHospitals() {
		return hospitals;
	}

	public void setHospitals(List<Hospital> hospitals) {
		this.hospitals = hospitals;
	}

	public PersistentObjectConverterFromId<Hospital> getHospitalConverter() {
		return hospitalConverter;
	}

	public void setHospitalConverter(PersistentObjectConverterFromId<Hospital> hospitalConverter) {
		this.hospitalConverter = hospitalConverter;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}