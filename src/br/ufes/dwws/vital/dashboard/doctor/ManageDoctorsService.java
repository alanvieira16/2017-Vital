package br.ufes.dwws.vital.dashboard.doctor;

import java.util.List;

import javax.ejb.Local;

import br.ufes.dwws.vital.domain.Doctor;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;

@Local
public interface ManageDoctorsService extends CrudService<Doctor> {
	
	public List<Doctor> list();

}
