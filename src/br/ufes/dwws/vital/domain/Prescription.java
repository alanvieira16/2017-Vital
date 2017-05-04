package br.ufes.dwws.vital.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Prescription extends PersistentObjectSupport {

	private static final long serialVersionUID = 1L;
	
	private String dosage;
	private String duration;
	
	@ManyToOne
	private Treatment treatment;
	
	@ManyToOne
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
