package br.ufes.dwws.vital.dashboard.prescription;

import javax.ejb.Local;

import br.ufes.dwws.vital.domain.Prescription;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;

@Local
public interface ManagePrescriptionsService extends CrudService<Prescription>{}
