package br.ufes.dwws.vital.domain;


import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Medicine extends PersistentObjectSupport{

	private static final long serialVersionUID = 1L;

	@NotNull
	private String name;
	@NotNull
	private String activeCompound;
	@NotNull
	private String utilization;
	
	@OneToOne
	private Prescription prescription;

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

	public void setUtilization(String utilization) {
		this.utilization = utilization;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}


	
	
}
