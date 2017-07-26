package br.ufes.dwws.vital.dashboard.pathology;

import java.util.List;

import javax.ejb.Local;

import br.ufes.dwws.vital.domain.Pathology;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;

@Local
public interface ManagePathologiesService extends CrudService<Pathology> {
	
	public List<Pathology> list();
	public List<Pathology> fetchDisease();

}
