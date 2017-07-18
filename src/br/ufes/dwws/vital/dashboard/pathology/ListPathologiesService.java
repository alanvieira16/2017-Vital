package br.ufes.dwws.vital.dashboard.pathology;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import br.ufes.dwws.vital.domain.Pathology;
import br.ufes.dwws.vital.persistence.PathologyDAO;

@Stateless
@LocalBean
public class ListPathologiesService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private PathologyDAO pathologyDAO;

	@PersistenceContext
	private EntityManager entityManager;

	public List<Pathology> listPathologies() {
		List<Pathology> pathologies = pathologyDAO.retrieveAll();
		return pathologies;
	}
	
	public List<Pathology> fetchDisease() {
		
		List<Pathology> pathologiesList = new ArrayList<Pathology>();
		
		String query = "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>"
				+ "PREFIX dbpprop: <http://dbpedia.org/property/>"
				+ "PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#>" + "SELECT ?name ?desc WHERE {"
				+ "?x a dbpedia-owl:Disease;" + "rdfs:label ?name;" + "rdfs:comment ?desc."
				+ "FILTER(LANG(?name) = 'pt' && LANG(?desc) = 'pt')" + "} LIMIT 10";

		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);

		ResultSet results = queryExecution.execSelect();

		while (results.hasNext()) {
			QuerySolution querySolution = results.next();
			Pathology pathology = new Pathology();
			pathology.setName(querySolution.get("name").toString().replaceAll("@pt", ""));
			String desc = querySolution.get("desc").toString();
			pathology.setDescription(desc.substring(0, desc.length() > 254 ? 254 : desc.length()));
			//pathology.setId((long) pathology.getName().hashCode());
			pathologiesList.add(pathology);
		}

		queryExecution.close();
		
		return pathologiesList;

	}
}
