package br.ufes.dwws.vital.dashboard.treatment;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.dwws.vital.domain.Treatment;
import br.ufes.dwws.vital.persistence.TreatmentDAO;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Stateless
@PermitAll
public class ManageTreatmentsServiceBean extends CrudServiceBean<Treatment> implements ManageTreatmentsService {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private TreatmentDAO treatmentDAO;

	@Override
	public BaseDAO<Treatment> getDAO() {
		return treatmentDAO;
	}

}
