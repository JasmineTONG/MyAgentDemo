import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import jade.core.behaviours.OneShotBehaviour;

public class SemSearchBehaviour extends OneShotBehaviour {
	
	private String noun;
	private String attribute;
	private String queryengine = "http://dbpedia.org/sparql?format=text/rdf+n3&query=";
	
	public SemSearchBehaviour(String noun, String attribute) {
		this.attribute = attribute;
		this.noun = noun;
	}

	@Override
	public void action() {
		System.out.println("result: "+executeQuery(buildQuery(getURI(noun), getURI(attribute))));
	}
	
	public String getURI(String plaintext) {
		// e.g SELECT DISTINCT ?x FROM <http://dbpedia.org> WHERE { ?x rdfs:label ?y . ?x <http://www.w3.org/2004/02/skos/core#subject> ?z . FILTER bif:contains(?y, "Bob Marley") }
		String query = "SELECT DISTINCT ?uri FROM <http://dbpedia.org> WHERE { ?uri rdfs:label ?y . ?uri <http://www.w3.org/2004/02/skos/core#subject> ?z . FILTER bif:contains(?y, \"" + plaintext + "\") }";
		//System.out.println(query);
		return query;
	}
	
	public String buildQuery(String subject, String predicate) {
		String query = "SELECT DISTINCT ?value FROM <http://dbpedia.org> WHERE { " + subject + " " + predicate + " ?value }";
		//System.out.println(query);
		return query;
	}
	
	public String executeQuery(String query) {
		String result = "";
		try 
	    {
	        URL url = new URL(this.queryengine + query);
	    
	        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
	        String str;

	        while ((str = in.readLine()) != null) 
	        {
	        	System.out.println("result_str: "+str);
	          result += str;
	        }

	        in.close();
	    } 
	    catch (MalformedURLException e) {} 
	    catch (IOException e) {}

		return result.toString();
	}

}
