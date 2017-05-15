package br.ufes.dwws.vital.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.dwws.vital.domain.Appointment;
import br.ufes.dwws.vital.domain.Diagnosis;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Local
public interface DiagnosisDAO extends BaseDAO<Diagnosis> {
	
	public List<Diagnosis> retrieveByAppointment(Appointment appointment);
}
