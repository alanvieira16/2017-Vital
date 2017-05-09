package br.ufes.dwws.vital.signup;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.dwws.util.Role;
import br.ufes.dwws.vital.domain.Doctor;
import br.ufes.dwws.vital.domain.Hospital;
import br.ufes.dwws.vital.persistence.HospitalDAO;

@Stateless @LocalBean
public class RegistrationService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private HospitalDAO hospitalDAO;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void register(Doctor doctor) {
		doctor.setRole(Role.DOCTOR);
		entityManager.persist(doctor);
	}

	public List<Hospital> listHospitals() {
		List<Hospital> hospitals = hospitalDAO.retrieveAll();
		return hospitals;
	}
}
