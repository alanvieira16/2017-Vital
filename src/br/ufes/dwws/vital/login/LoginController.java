package br.ufes.dwws.vital.login;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.ufes.dwws.vital.domain.User;

@Named
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private LoginService loginService;

	@Inject
	private HttpServletRequest request;

	private User currentUser;
	private String email;
	private String password;

	public User getCurrentUser() {
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

	public String login() {

		try {

			loginService.login(email, password);

		} catch (LoginFailedException e) {

			switch (e.getReason()) {

			case INCORRECT_PASSWORD:
			case UNKNOWN_USERNAME:
				return null;
			default:
				return null;

			}

		}

		currentUser = loginService.getCurrentUser();
		return "/index.xhtml";
	}

}
