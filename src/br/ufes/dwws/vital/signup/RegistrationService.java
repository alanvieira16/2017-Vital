package br.ufes.dwws.vital.signup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import br.ufes.dwws.util.Role;
import br.ufes.dwws.vital.domain.Doctor;
import br.ufes.dwws.vital.domain.Hospital;
import br.ufes.dwws.vital.domain.Pathology;
import br.ufes.dwws.vital.persistence.HospitalDAO;

@Stateless @LocalBean
public class RegistrationService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private HospitalDAO hospitalDAO;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void register(Doctor doctor) {
		doctor.setRole(Role.DOCTOR);
		entityManager.persist(doctor);
	}

	public List<Hospital> listHospitals() {
		List<Hospital> hospitals = hospitalDAO.retrieveAll();
		return hospitals;
	}
	
	public List<Hospital> fetchHospitals() {
		
		List<Hospital> hospitalsList = new ArrayList<Hospital>();
		
		String query = 	"PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
						"PREFIX dbr: <http://dbpedia.org/resource/>\n" +
						"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
						"SELECT ?name WHERE {\n" +
						"?x a dbo:Hospital;\n" +
						"rdfs:label ?_name;\n" +
						"dbo:country dbr:United_States.\n" +
						"FILTER (LANG(?_name) = 'en')\n" +
						"BIND (STR(?_name)  AS ?name)}\n" + 
						"ORDER BY ASC(?name)\n" +
						"LIMIT 500";

		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);

		ResultSet results = queryExecution.execSelect();

		while (results.hasNext()) {
			QuerySolution querySolution = results.next();
			Hospital hospital = new Hospital();
			hospital.setName(querySolution.get("name").toString());
			hospitalsList.add(hospital);
		}

		queryExecution.close();
		
		return hospitalsList;

	}
}
