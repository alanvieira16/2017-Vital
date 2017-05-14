package br.ufes.dwws.vital.dashboard.patient;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.dwws.vital.domain.Patient;
import br.ufes.dwws.vital.persistence.PatientDAO;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Stateless
@PermitAll
public class ManagePatientsServiceBean extends CrudServiceBean<Patient> implements ManagePatientsService {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private PatientDAO patientDAO;
	
	@Override
	public BaseDAO<Patient> getDAO() {
		return patientDAO;
	}
	
	@Override
	public List<Patient> list(long doctor_id) {
		return patientDAO.retrieveByDoctor(doctor_id);
	}

}
