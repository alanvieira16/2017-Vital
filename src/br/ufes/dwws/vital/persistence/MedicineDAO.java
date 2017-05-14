package br.ufes.dwws.vital.persistence;

import javax.ejb.Local;

import br.ufes.dwws.vital.domain.Medicine;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Local
public interface MedicineDAO extends BaseDAO<Medicine>{

}
