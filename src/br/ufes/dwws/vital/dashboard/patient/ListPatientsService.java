package br.ufes.dwws.vital.dashboard.patient;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.dwws.vital.domain.Patient;
import br.ufes.dwws.vital.persistence.PatientDAO;

@Stateless
@LocalBean
public class ListPatientsService implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private PatientDAO patientDAO;

	@PersistenceContext
	private EntityManager entityManager;

	public List<Patient> listAppointments() {
		List<Patient> patients = patientDAO.retrieveAll();
		return patients;
	}

}
