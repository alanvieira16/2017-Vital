package br.ufes.dwws.vital.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.dwws.vital.domain.Appointment;
import br.ufes.dwws.vital.domain.Diagnosis;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;

@Stateless
public class DiagnosisJPADAO extends BaseJPADAO<Diagnosis> implements DiagnosisDAO{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<Diagnosis> retrieveByAppointment(Appointment appointment) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Diagnosis> cq = cb.createQuery(Diagnosis.class);
		Root<Diagnosis> root = cq.from(Diagnosis.class);

		cq.where(cb.equal(root.get("appointment"), appointment));
	
		TypedQuery<Diagnosis> q = entityManager.createQuery(cq);
		List<Diagnosis> result = q.getResultList();
		return result;
	}
	
	
}
