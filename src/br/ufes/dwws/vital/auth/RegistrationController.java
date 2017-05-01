package br.ufes.dwws.vital.auth;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.ufes.dwws.vital.domain.Doctor;
import br.ufes.inf.nemo.jbutler.TextUtils;

@Named @RequestScoped
public class RegistrationController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private RegistrationService registrationService;
	
	@Inject
	private HttpServletRequest request;
	
	private Doctor doctor = new Doctor();
	private String role;

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
		request.setAttribute("alertType", "success");
		request.setAttribute("message", "Your account has been created. Welcome to Vital!");
		return "/signup/index.xhtml";
	}



	
}