package br.ufes.dwws.vital.dashboard.patient;

import java.util.List;

import javax.ejb.Local;

import br.ufes.dwws.vital.domain.Patient;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;

@Local
public interface ManagePatientsService extends CrudService<Patient> {

	public List<Patient> list(long doctor_id);
	
}
