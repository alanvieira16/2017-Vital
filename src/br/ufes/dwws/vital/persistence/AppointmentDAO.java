package br.ufes.dwws.vital.persistence;

import javax.ejb.Local;

import br.ufes.dwws.vital.domain.Appointment;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Local
public interface AppointmentDAO extends BaseDAO<Appointment> {}
