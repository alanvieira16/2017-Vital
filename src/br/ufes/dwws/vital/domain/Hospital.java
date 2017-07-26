package br.ufes.dwws.vital.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Hospital extends PersistentObjectSupport{
	
	private static final long serialVersionUID = 1L;

	@NotNull
	private String name;
	@NotNull
	private String homepage;
	@NotNull
	private String state;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
