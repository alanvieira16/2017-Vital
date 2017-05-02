package br.ufes.dwws.vital.auth.application;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.dwws.vital.auth.persistence.HospitalDAO;
import br.ufes.dwws.vital.domain.Doctor;
import br.ufes.dwws.vital.domain.Hospital;
import br.ufes.dwws.vital.domain.User;

@Stateless @LocalBean
public class RegistrationService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private HospitalDAO hospitalDAO;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void register(User user, Doctor doctor) {
		entityManager.persist(user);
		entityManager.persist(doctor);
	}

	public List<Hospital> listHospitals() {
		List<Hospital> hospitals = hospitalDAO.retrieveAll();
		return hospitals;
	}
}
