package br.ufes.dwws.vital.persistence;

import javax.ejb.Local;

import br.ufes.dwws.vital.domain.Doctor;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Local
public interface DoctorDAO extends BaseDAO<Doctor>{}
