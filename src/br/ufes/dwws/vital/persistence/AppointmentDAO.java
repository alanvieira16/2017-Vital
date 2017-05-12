package br.ufes.dwws.vital.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.dwws.vital.domain.Appointment;
import br.ufes.dwws.vital.domain.User;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;

@Local
public interface AppointmentDAO extends BaseDAO<Appointment> {
	
	public List<Appointment> retrieveByCurrentUser(User currentUser);
}
