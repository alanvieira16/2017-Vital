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
import br.ufes.dwws.vital.domain.User;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;

@Stateless
public class AppointmentJPADAO extends BaseJPADAO<Appointment> implements AppointmentDAO {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Override
	public List<Appointment> retrieveByCurrentUser(User currentUser){

		// Constructs the query over the Academic class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Appointment> cq = cb.createQuery(Appointment.class);
		Root<Appointment> root = cq.from(Appointment.class);

		// Filters the query with the email.
		if(currentUser.getRole().equals("doctor")){
			cq.where(cb.equal(root.get("doctor"), currentUser.getId()));
		} else {
			cq.where(cb.equal(root.get("patient"), currentUser.getId()));
		}
		TypedQuery<Appointment> q = entityManager.createQuery(cq);
		List<Appointment> result = q.getResultList();
		return result;
	}

}
