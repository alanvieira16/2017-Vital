package br.ufes.dwws.vital.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Diagnosis extends PersistentObjectSupport {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Appointment appointment;
	
	@ManyToMany(mappedBy="diagnostics")
	private Set<Pathology> pathologies;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "diagnosis")
	private Set<Treatment> treatments;

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Set<Pathology> getPathologies() {
		return pathologies;
	}

	public void setPathologies(Set<Pathology> pathologies) {
		this.pathologies = pathologies;
	}

	public Set<Treatment> getTreatments() {
		return treatments;
	}

	public void setTreatments(Set<Treatment> treatments) {
		this.treatments = treatments;
	}
	
	
}
