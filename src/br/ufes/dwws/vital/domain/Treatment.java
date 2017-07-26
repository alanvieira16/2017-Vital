package br.ufes.dwws.vital.domain;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Treatment extends PersistentObjectSupport{

	private static final long serialVersionUID = 1L;


	@NotNull
	private String description;

	@OneToOne
	private Diagnosis diagnosis;
	
	@OneToOne(mappedBy = "treatment", orphanRemoval = true, cascade = CascadeType.ALL)
	private Prescription prescription;

	public Diagnosis getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	
	
	
}
