package br.ufes.dwws.vital.login;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.ufes.dwws.vital.domain.User;
import br.ufes.dwws.vital.login.LoginFailedException.LoginFailedReason;

@Named
@SessionScoped
public class SessionController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(SessionController.class.getCanonicalName());

	@EJB
	private LoginService loginService;
	
	@Inject
	@Any
	private Event<LoginEvent> loginEvent;

	private User currentUser;
	private String email;
	private String password;
	private Boolean hasLoggedUser;

	

	public String login() {

		try {
			
			loginService.login(email, password);
			
			try {
				HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
				request.login(email, password);
				logger.log(Level.INFO, "login done by JAAS");							
			}
			catch (Exception e) {
				e.printStackTrace();
				logger.log(Level.INFO, "http error when login by JAAS");
				throw new LoginFailedException(e, LoginFailedReason.NO_HTTP_REQUEST);
			}

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

		currentUser = loginService.getCurrentUser();
		
		if(currentUser != null)
			loginEvent.fire(new LoginEvent(currentUser));
		
		logger.log(Level.INFO, "login current user: " + currentUser);

		return "/index.xhtml?faces-redirect=true";
	}

	
	public String logout() {
		getRequest().getSession().invalidate();
		return "index?faces-redirect=true";
	}
	
	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public Boolean getHasLoggedUser() {
		return hasLoggedUser;
	}

	public void setHasLoggedUser(Boolean hasLoggedUser) {
		this.hasLoggedUser = hasLoggedUser;
	}

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
}
