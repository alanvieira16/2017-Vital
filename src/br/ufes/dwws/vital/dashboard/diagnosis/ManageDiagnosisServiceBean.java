package br.ufes.dwws.vital.dashboard.diagnosis;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.dwws.vital.domain.Appointment;
import br.ufes.dwws.vital.domain.Diagnosis;
import br.ufes.dwws.vital.persistence.DiagnosisDAO;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Stateless
@PermitAll
public class ManageDiagnosisServiceBean extends CrudServiceBean<Diagnosis> implements ManageDiagnosisService {
	private static final long serialVersionUID = 1L;

	@EJB
	private DiagnosisDAO diagnosisDAO;

	@Override
	public BaseDAO<Diagnosis> getDAO() {
		return diagnosisDAO;
	}

	@Override
	public List<Diagnosis> list(Appointment appointment) {
		return diagnosisDAO.retrieveByAppointment(appointment);
	}
}
