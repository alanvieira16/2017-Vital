package br.ufes.dwws.vital.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Pathology {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String description;
	
	@ManyToMany
	private Set<Diagnosis> diagnostics;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Diagnosis> getDiagnostics() {
		return diagnostics;
	}

	public void setDiagnostics(Set<Diagnosis> diagnostics) {
		this.diagnostics = diagnostics;
	}
	
	
}
