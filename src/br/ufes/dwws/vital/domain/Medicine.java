package br.ufes.dwws.vital.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Medicine extends PersistentObjectSupport{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String activeCompound;
	private String utilization;
	
	@OneToMany(mappedBy = "medicine")
	private Set<Prescription> prescriptions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActiveCompound() {
		return activeCompound;
	}

	public void setActiveCompound(String activeCompound) {
		this.activeCompound = activeCompound;
	}

	public String getUtilization() {
		return utilization;
	}

	public void setUtuilization(String utilization) {
		this.utilization = utilization;
	}

	public Set<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(Set<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}
	
	
}
