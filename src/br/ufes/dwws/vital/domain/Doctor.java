package br.ufes.dwws.vital.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Doctor extends User {
	
	private static final long serialVersionUID = 1L;
	
	private String specialization;
	private String crm;

	@ManyToOne
	private Hospital hospital;

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
		
}
