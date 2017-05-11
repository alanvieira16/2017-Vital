package br.ufes.dwws.vital.dashboard.appointment;

import javax.ejb.Local;

import br.ufes.dwws.vital.domain.Appointment;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;

@Local
public interface ManageAppointmentsService extends CrudService<Appointment> {

}
