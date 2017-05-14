package br.ufes.dwws.vital.dashboard.prescription;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.dwws.vital.domain.Prescription;
import br.ufes.dwws.vital.persistence.PrescriptionDAO;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Stateless
@PermitAll
public class ManagePrescriptionsServiceBean extends CrudServiceBean<Prescription>
		implements ManagePrescriptionsService {

	private static final long serialVersionUID = 1L;

	@EJB
	private PrescriptionDAO prescriptionDAO;

	@Override
	public BaseDAO<Prescription> getDAO() {
		return prescriptionDAO;
	}

}
