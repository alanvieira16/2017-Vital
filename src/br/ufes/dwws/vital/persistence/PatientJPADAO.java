package br.ufes.dwws.vital.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import br.ufes.dwws.vital.domain.Appointment;
import br.ufes.dwws.vital.domain.Patient;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;

@Stateless
public class PatientJPADAO extends BaseJPADAO<Patient> implements PatientDAO {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<Patient> retrieveByDoctor(long doctor_id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Patient> c = cb.createQuery(Patient.class);
		Root<Patient> patient = c.from(Patient.class);

		Subquery<Long> sq = c.subquery(Long.class);
		Root<Appointment> appointment = sq.from(Appointment.class);

		sq.select(appointment.get("patient")).where(
		        cb.equal(appointment.get("doctor").get("id"), doctor_id));

		c.select(patient).where(
		        cb.in(patient.get("id")).value(sq));

		TypedQuery<Patient> q = entityManager.createQuery(c);
		List<Patient> patients = q.getResultList();
		return patients;

	}

}
