package br.ufes.dwws.vital.domain;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@MappedSuperclass
public class User extends PersistentObjectSupport {
	
	private String name;
	
	private String phone;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	private String gender;
	
	private String email;
	
	private String password;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User convertTo(String role){
		User u;
		if(role.equals("Receptionist")){
			u = new Receptionist();
		} else if (role.equals("Doctor")){
			u = new Doctor();
		} else {
			u = new Patient();
		}
		return u;
	}
	
	
}
