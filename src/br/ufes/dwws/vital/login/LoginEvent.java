package br.ufes.dwws.vital.login;

import br.ufes.dwws.vital.domain.User;

public class LoginEvent {
	
	private User user;


	public LoginEvent(User user) {
		super();
		this.user = user;
	}
	

	public User getUser() {
		return user;
	}
}
