package br.ufes.dwws.vital.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
}
