package br.ufes.dwws.vital.dashboard.doctor;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.dwws.vital.domain.Doctor;
import br.ufes.dwws.vital.persistence.DoctorDAO;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Stateless
@PermitAll
public class ManageDoctorsServiceBean extends CrudServiceBean<Doctor> implements ManageDoctorsService {

	private static final long serialVersionUID = 1L;

	@EJB
	private DoctorDAO doctorDAO;

	@Override
	public BaseDAO<Doctor> getDAO() {
		return doctorDAO;
	}

	@Override
	public List<Doctor> list() {
		return doctorDAO.retrieveAll();
	}
}
