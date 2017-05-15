package br.ufes.dwws.vital.dashboard.pathology;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.dwws.vital.domain.Pathology;
import br.ufes.dwws.vital.persistence.PathologyDAO;

@Stateless
@LocalBean
public class ListPathologiesService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private PathologyDAO pathologyDAO;

	@PersistenceContext
	private EntityManager entityManager;

	public List<Pathology> listPathologies() {
		List<Pathology> pathologies = pathologyDAO.retrieveAll();
		return pathologies;
	}
}
