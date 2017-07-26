package br.ufes.dwws.vital.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Prescription extends PersistentObjectSupport {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String dosage;
	@NotNull
	private String duration;
	
	@OneToOne
	private Treatment treatment;
	
	@OneToOne(mappedBy = "prescription", cascade = CascadeType.ALL, orphanRemoval = true)
	private Medicine medicine;

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Treatment getTreatment() {
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
	
	

}
