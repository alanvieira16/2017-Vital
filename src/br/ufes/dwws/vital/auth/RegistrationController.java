package br.ufes.dwws.vital.auth;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.ufes.dwws.vital.domain.User;

@Named @RequestScoped
public class RegistrationController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private RegistrationService registrationService;
	
	private User user = new User();
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String register() {
		registrationService.register(user);
		return "/registration/success.xhtml";
	}
}