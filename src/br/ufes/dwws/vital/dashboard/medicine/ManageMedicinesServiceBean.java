package br.ufes.dwws.vital.dashboard.medicine;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.dwws.vital.domain.Medicine;
import br.ufes.dwws.vital.persistence.MedicineDAO;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Stateless
@PermitAll
public class ManageMedicinesServiceBean extends CrudServiceBean<Medicine> implements ManageMedicinesService {

	private static final long serialVersionUID = 1L;

	@EJB
	private MedicineDAO medicineDAO;
	
	@Override
	public BaseDAO<Medicine> getDAO() {
		return medicineDAO;
	}

}
