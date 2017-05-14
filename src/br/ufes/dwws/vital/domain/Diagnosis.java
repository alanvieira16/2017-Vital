package br.ufes.dwws.vital.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Diagnosis extends PersistentObjectSupport {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Appointment appointment;
	
	@ManyToOne
	private Pathology pathology;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "diagnosis")
	private Set<Treatment> treatments;

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

	public Set<Treatment> getTreatments() {
		return treatments;
	}

	public void setTreatments(Set<Treatment> treatments) {
		this.treatments = treatments;
	}
	
	
}
