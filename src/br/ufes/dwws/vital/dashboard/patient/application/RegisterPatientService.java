package br.ufes.dwws.vital.dashboard.patient.application;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.dwws.vital.domain.Patient;
import br.ufes.dwws.vital.persistence.PatientDAO;

@Stateless
@LocalBean
public class RegisterPatientService implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private PatientDAO patientDAO;

	@PersistenceContext
	private EntityManager entityManager;

	public void register(Patient patient) {
		entityManager.persist(patient);
	}


}
