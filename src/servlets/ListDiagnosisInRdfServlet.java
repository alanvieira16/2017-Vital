package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import br.ufes.dwws.vital.domain.Diagnosis;
import br.ufes.dwws.vital.persistence.DiagnosisDAO;

@WebServlet(urlPatterns = "/data/diagnosis")
public class ListDiagnosisInRdfServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(ListDiagnosisInRdfServlet.class.getCanonicalName());

	@EJB
	private DiagnosisDAO diagnosisDAO;

	public ListDiagnosisInRdfServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/xml");

		List<Diagnosis> diagnosisList = diagnosisDAO.retrieveAll();

		Model model = ModelFactory.createDefaultModel();

		String myNS = "http://localhost:8080/Vital-1/data/diagnosis/";
		String ogmsNS = "http://purl.obolibrary.org/obo/";

		model.setNsPrefix("ogms", ogmsNS);
		
		Resource rDiagnosis = ResourceFactory.createResource(ogmsNS + "OGMS_0000073");
		Resource rTreatment = ResourceFactory.createResource(ogmsNS + "OGMS_0000090");
		Resource rPathology = ResourceFactory.createResource(ogmsNS + "OGMS_0000031"); //disease

		Property hasTreatment = ResourceFactory.createProperty(myNS + "hasTreatment");
		Property hasPathology = ResourceFactory.createProperty(myNS + "hasPathology");

		for (Diagnosis diagnosis : diagnosisList) {

			model.createResource(myNS + diagnosis.getId())
					.addProperty(RDF.type, rDiagnosis)
					.addProperty(hasPathology,
							model.createResource()
									.addProperty(RDF.type, rPathology)
									.addProperty(RDFS.label, diagnosis.getPathology().getName())
									.addProperty(RDFS.comment, diagnosis.getPathology().getDescription()))
					.addProperty(hasTreatment, 
							model.createResource()
								.addProperty(RDF.type, rTreatment)
								.addProperty(RDFS.comment, diagnosis.getTreatment().getDescription()));

			logger.log(Level.INFO, "Added Diagnosis/" + diagnosis.getId() + " to the RDF model");
		}

		try (PrintWriter out = response.getWriter()) {
			model.write(out, "RDF/XML");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
