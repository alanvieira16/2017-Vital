package br.ufes.dwws.vital.auth;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.dwws.vital.domain.User;

@Stateless @LocalBean
public class RegistrationService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void register(User user) {
		entityManager.persist(user);
	}
}
