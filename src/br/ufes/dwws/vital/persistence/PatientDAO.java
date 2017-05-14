package br.ufes.dwws.vital.persistence;


import java.util.List;

import javax.ejb.Local;

import br.ufes.dwws.vital.domain.Patient;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Local
public interface PatientDAO extends BaseDAO<Patient>{

	public List<Patient> retrieveByDoctor(long doctor_id);
}
