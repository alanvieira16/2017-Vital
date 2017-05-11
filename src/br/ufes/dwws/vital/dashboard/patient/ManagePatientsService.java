package br.ufes.dwws.vital.dashboard.patient;

import javax.ejb.Local;

import br.ufes.dwws.vital.domain.Patient;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;

@Local
public interface ManagePatientsService extends CrudService<Patient> {

}
