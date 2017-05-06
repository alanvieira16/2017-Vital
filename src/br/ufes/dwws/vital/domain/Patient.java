package br.ufes.dwws.vital.domain;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
public class Patient extends User{
	
	private static final long serialVersionUID = 1L;
	
	private String bloodType;
	
	private String notes;
	
	private String healthInsurance;
	
	private String healthInsuranceNumber;

	@ElementCollection
	@CollectionTable(name = "allergies")
	private Set<String> allergies;
	
	@ElementCollection
	@CollectionTable(name = "specialNeeds")
	private Set<String> specialNeeds;
	
	public Set<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(Set<String> allergies) {
		this.allergies = allergies;
	}

	public Set<String> getSpecialNeeds() {
		return specialNeeds;
	}

	public void setSpecialNeeds(Set<String> specialNeeds) {
		this.specialNeeds = specialNeeds;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getHealthInsurance() {
		return healthInsurance;
	}

	public void setHealthInsurance(String healthInsurance) {
		this.healthInsurance = healthInsurance;
	}

	public String getHealthInsuranceNumber() {
		return healthInsuranceNumber;
	}

	public void setHealthInsuranceNumber(String healthInsuranceNumber) {
		this.healthInsuranceNumber = healthInsuranceNumber;
	}
	
	


}
