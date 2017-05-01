package br.ufes.dwws.vital.login;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.dwws.vital.domain.User;

@Named
@SessionScoped
public class SessionController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(SessionController.class.getCanonicalName());

	@EJB
	private LoginService loginService;

	private User currentUser;
	private String email;
	private String password;

	public User getCurrentUser() {
		logger.log(Level.INFO, "getCurrentUser -> current user: " + currentUser);
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean hasLoggedUser() {
		return currentUser != null;
	}

	public String login() {

		try {

			currentUser = loginService.login(email, password);

		} catch (LoginFailedException e) {

			switch (e.getReason()) {

			case INCORRECT_PASSWORD:
				logger.log(Level.INFO, "Incorrect password");
			case UNKNOWN_USERNAME:
				logger.log(Level.INFO, "unknown username");
				return null;
			default:
				logger.log(Level.INFO, "unknown error");
				return null;

			}

		}

		logger.log(Level.INFO, "login current user: " + currentUser);

		return "/index.xhtml?faces-redirect=true";
	}

}
