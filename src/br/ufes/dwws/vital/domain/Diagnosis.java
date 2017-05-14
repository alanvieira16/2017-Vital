package br.ufes.dwws.vital.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Diagnosis extends PersistentObjectSupport {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Appointment appointment;
	
	@ManyToOne
	private Pathology pathology;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "diagnosis")
	private Treatment treatment;

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Pathology getPathology() {
		return pathology;
	}

	public void setPathology(Pathology pathology) {
		this.pathology = pathology;
	}

	public Treatment getTreatment() {
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}


	
	
}
