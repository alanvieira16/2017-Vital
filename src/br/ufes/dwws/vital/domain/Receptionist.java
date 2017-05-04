package br.ufes.dwws.vital.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Receptionist extends User{
	
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	private Doctor doctor;
	

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	

}
