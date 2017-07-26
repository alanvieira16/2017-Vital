package br.ufes.dwws.vital.dashboard.diagnosis;

import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufes.dwws.vital.dashboard.medicine.ManageMedicinesService;
import br.ufes.dwws.vital.dashboard.pathology.ManagePathologiesService;
import br.ufes.dwws.vital.dashboard.prescription.ManagePrescriptionsService;
import br.ufes.dwws.vital.dashboard.treatment.ManageTreatmentsService;
import br.ufes.dwws.vital.domain.Appointment;
import br.ufes.dwws.vital.domain.Diagnosis;
import br.ufes.dwws.vital.domain.Medicine;
import br.ufes.dwws.vital.domain.Pathology;
import br.ufes.dwws.vital.domain.Prescription;
import br.ufes.dwws.vital.domain.Treatment;
import br.ufes.dwws.vital.persistence.PathologyDAO;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.jbutler.ejb.controller.PersistentObjectConverterFromId;

@Named
@SessionScoped
public class DiagnosisController extends CrudController<Diagnosis> {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManageDiagnosisService manageDiagnosisService;
	@EJB
	private ManageTreatmentsService manageTreatmentsService;
	@EJB
	private ManagePrescriptionsService managePrescriptionsService;
	@EJB
	private ManageMedicinesService manageMedicinesService;
	@EJB
	private ManagePathologiesService managePathologiesService;

	private Appointment appointment;
	private Diagnosis diagnosis = new Diagnosis();
	private Treatment treatment = new Treatment();
	private Prescription prescription = new Prescription();
	private Medicine medicine = new Medicine();
	private List<Pathology> pathologies;

	private PersistentObjectConverterFromId<Pathology> pathologyConverter;

	@Inject
	public void init(PathologyDAO pathologyDAO) {

		//get pathologies from network
		if (pathologyDAO.retrieveCount() == 0) {
			
			for (Pathology p : managePathologiesService.fetchDisease()) 
				pathologyDAO.save(p);
			
		} 
		
		pathologies = managePathologiesService.list();
		pathologyConverter = new PersistentObjectConverterFromId<Pathology>(pathologyDAO);
	}

	@Override
	protected CrudService<Diagnosis> getCrudService() {
		return manageDiagnosisService;
	}

	@Override
	protected void initFilters() {
	}

	public String create(Appointment appointment) {
		this.appointment = appointment;
		return "/diagnosis/new?faces-redirect=true";
	}

	public String diagnose() {
		ResourceBundle bundle = FacesContext.getCurrentInstance().getApplication()
				.getResourceBundle(FacesContext.getCurrentInstance(), "msgs");
		try {
			
			medicine.setPrescription(prescription);
			prescription.setTreatment(treatment);
			treatment.setDiagnosis(diagnosis);
			diagnosis.setAppointment(appointment);
			manageDiagnosisService.create(diagnosis);
			manageTreatmentsService.create(treatment);
			managePrescriptionsService.create(prescription);
			manageMedicinesService.create(medicine);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "success");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage",
					bundle.getString("alert.diagnosisCreated"));
			

			/* created new instances for news diagnosis */
			diagnosis = new Diagnosis();
			treatment = new Treatment();
			prescription = new Prescription();
			medicine = new Medicine();
		
			return "/appointment/index?faces-redirect=true";
			
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "danger");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage",
					bundle.getString("alert.error"));
			return "/appointment/details?faces-redirect=true";
		}
	}

	public ManageDiagnosisService getManageDiagnosisService() {
		return manageDiagnosisService;
	}

	public void setManageDiagnosisService(ManageDiagnosisService manageDiagnosisService) {
		this.manageDiagnosisService = manageDiagnosisService;
	}

	public Diagnosis getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Treatment getTreatment() {
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	public PersistentObjectConverterFromId<Pathology> getPathologyConverter() {
		return pathologyConverter;
	}

	public void setPathologyConverter(PersistentObjectConverterFromId<Pathology> pathologyConverter) {
		this.pathologyConverter = pathologyConverter;
	}

	public List<Pathology> getPathologies() {
		return pathologies;
	}

	public void setPathologies(List<Pathology> pathologies) {
		this.pathologies = pathologies;
	}

	public ManageTreatmentsService getManageTreatmentsService() {
		return manageTreatmentsService;
	}

	public void setManageTreatmentsService(ManageTreatmentsService manageTreatmentsService) {
		this.manageTreatmentsService = manageTreatmentsService;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

}
