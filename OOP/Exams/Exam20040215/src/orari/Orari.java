package orari;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Orari {

	private Map<String, Percorso> percorsi = new HashMap<>();
	private Map<String, Treno> treni = new HashMap<>();
	
	public Percorso creaPercorso(String codice, String categoria) {
		
		Percorso elemento = new Percorso (codice, categoria);
		percorsi.put(codice, elemento);
		
		return elemento;
	}

	public Collection<Percorso> getPercorsi() {
		return percorsi.values();
	}

	public Percorso getPercorso(String codice) {
		
		return percorsi.get(codice);
	}

	public Treno nuovoTreno(String codice, int giorno, int mese, int anno) throws PercorsoNonValido {
	
		Percorso percorso;
		Treno elemento = null;
		
		try {
			
			
		percorso = percorsi.get(codice);
			if(percorso == null) {
				throw new PercorsoNonValido();
			}
		 elemento = new Treno (percorso, giorno, mese, anno);
		 percorsi.get(codice).addTreno(elemento);
			
		} catch (PercorsoNonValido nonValido)
			 {
			
			 }
				
				
		return elemento;
	}

	public List<String> classificaRitardi() {
		return null;
	}

}
