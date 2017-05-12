package br.ufes.dwws.vital.dashboard.treatment;

import javax.ejb.Local;

import br.ufes.dwws.vital.domain.Treatment;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;

@Local
public interface ManageTreatmentsService extends CrudService<Treatment> {}
