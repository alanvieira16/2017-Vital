package br.ufes.dwws.vital.persistence;

import javax.ejb.Local;

import br.ufes.dwws.vital.domain.Treatment;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Local
public interface TreatmentDAO extends BaseDAO<Treatment>{}
