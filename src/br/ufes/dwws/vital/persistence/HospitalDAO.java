package br.ufes.dwws.vital.persistence;

import javax.ejb.Local;

import br.ufes.dwws.vital.domain.Hospital;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Local
public interface HospitalDAO extends BaseDAO<Hospital> {}