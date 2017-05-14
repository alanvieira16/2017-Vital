package br.ufes.dwws.vital.dashboard.diagnosis;

import java.util.List;

import javax.ejb.Local;

import br.ufes.dwws.vital.domain.Appointment;
import br.ufes.dwws.vital.domain.Diagnosis;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;

@Local
public interface ManageDiagnosisService extends CrudService<Diagnosis>{
	
	public List<Diagnosis> list(Appointment appointment);
}
