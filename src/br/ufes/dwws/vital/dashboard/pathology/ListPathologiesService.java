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
		
		String query = 	"PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>\n" +
						"PREFIX dbpprop: <http://dbpedia.org/property/>\n" +
						"PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#>\n" + 
						"SELECT ?name ?desc WHERE {\n" +
						"?x a dbpedia-owl:Disease;\n" + 
						"rdfs:label ?_name;\n" + 
						"rdfs:comment ?_desc.\n" +
						"FILTER(LANG(?_name) = 'pt' && LANG(?_desc) = 'pt')\n" + 
						"BIND (STR(?_desc)  AS ?desc)\n" + 
						"BIND (STR(?_name)  AS ?name)}\n" + 
						"ORDER BY ASC(?name)\n" +
						"LIMIT 1000";

		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);

		ResultSet results = queryExecution.execSelect();

		while (results.hasNext()) {
			QuerySolution querySolution = results.next();
			Pathology pathology = new Pathology();
			pathology.setName(querySolution.get("name").toString());
			String desc = querySolution.get("desc").toString();
			pathology.setDescription(desc);
			pathologiesList.add(pathology);
		}

		queryExecution.close();
		
		return pathologiesList;

	}
}
