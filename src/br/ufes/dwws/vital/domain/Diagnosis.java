package br.ufes.dwws.vital.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Diagnosis {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Appointment appointment;
	
	@ManyToMany(mappedBy="diagnostics")
	private Set<Pathology> pathologies;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "diagnosis")
	private Set<Treatment> treatments;
	
	
	
}
