package br.ufes.dwws.vital.dashboard.doctor;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.dwws.vital.domain.Doctor;
import br.ufes.dwws.vital.persistence.DoctorDAO;

@Stateless
@LocalBean
public class ListDoctorsService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private DoctorDAO doctorDAO;

	@PersistenceContext
	private EntityManager entityManager;

	public List<Doctor> listDoctors() {
		List<Doctor> doctors = doctorDAO.retrieveAll();
		return doctors;
	}
}
