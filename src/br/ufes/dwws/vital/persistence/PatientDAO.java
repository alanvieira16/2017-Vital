package br.ufes.dwws.vital.persistence;


import javax.ejb.Local;

import br.ufes.dwws.vital.domain.Patient;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Local
public interface PatientDAO extends BaseDAO<Patient>{

}
