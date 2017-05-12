package br.ufes.dwws.vital.dashboard.treatment;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.ufes.dwws.vital.domain.Treatment;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;

@Named
@RequestScoped
public class TreatmentController extends CrudController<Treatment> {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManageTreatmentsService manageTreatmentsService;
	
	@Override
	protected CrudService<Treatment> getCrudService() {
		return manageTreatmentsService;
	}

	@Override
	protected void initFilters() {}

}
