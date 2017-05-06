package br.ufes.dwws.vital.signup.control;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.ufes.dwws.vital.domain.Doctor;
import br.ufes.dwws.vital.domain.Hospital;
import br.ufes.dwws.vital.login.LoginFailedException;
import br.ufes.dwws.vital.login.LoginService;
import br.ufes.dwws.vital.persistence.HospitalDAO;
import br.ufes.dwws.vital.signup.application.RegistrationService;
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
	
	@EJB
	private LoginService loginService;

	private Doctor doctor = new Doctor();

	private String role;

	private PersistentObjectConverterFromId<Hospital> hospitalConverter;
	private List<Hospital> hospitals;

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

	@Inject
	public void init(HospitalDAO hospitalDAO) {
		hospitals = registrationService.listHospitals();
		hospitalConverter = new PersistentObjectConverterFromId<>(hospitalDAO);
	}
	
	public String register() {
		try {
			String md5pwd = TextUtils.produceMd5Hash(doctor.getPassword());
			doctor.setPassword(md5pwd);
			registrationService.register(doctor);
		} catch (NoSuchAlgorithmException e) {
			request.setAttribute("alertType", "danger");
			request.setAttribute("message", "Oops! Something wrong happened. Try again.");
			return "/signup/index.xhtml";
		}
		try {
			loginService.login(doctor.getEmail(), doctor.getPassword());
		} catch (LoginFailedException e) {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "danger");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage", "Something wrong happened. Try again.");
			return "/signup/index.xhtml?faces-redirect=true";
		}
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "success");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage", "Your account has been created. Welcome to Vital!");
		return "index?faces-redirect=true";
	}

}