package orari;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;

public class Percorso {
	
	
	private String codice;
	private Categoria categoria;
	private boolean straordinario;
	private Map<String,Fermata> fermate = new HashMap<>();
	private List<Treno> treni = new ArrayList<>();
	
	public enum Categoria {
		Intercity,
		Eurostar,
		Interregionale,
		Regionale
	}
	
	public String getCodice() {
		return codice;
	}

	public Percorso(String codice, String categoria) {
		
		this.codice = codice;
		this.categoria = Categoria.valueOf(categoria);
		this.straordinario= false;

	}
	
	public void addTreno(Treno treno) {
		
		if(treni.contains(treno)== false)
		treni.add(treno);
	}

	public String getCategoria() {
		return categoria.toString();
	}

	public boolean isStraordinario() {
		return straordinario;
	}

	public void setStraordinario(boolean newValue) {
		straordinario = newValue;
		
	}

	public Fermata aggiungiFermata(String nomeStazione, int ore, int minuti) {
		
		Fermata element = new Fermata(nomeStazione, ore, minuti);
		fermate.put(nomeStazione, element);
		
		return element;
	}

	public List<Treno> getTreni() {
		Collections.sort(treni, comparing(Treno::getAnno).thenComparing(Treno::getMese).thenComparing(Treno::getGiorno).reversed());
		return treni;
	}

	public List<Fermata> getFermate() {

		List<Fermata> element = new ArrayList<>(fermate.values());
		
		Collections.sort(element, comparing(Fermata::getOre).thenComparing(Fermata::getMinuti));
		return element;
	}
	
	public Map<String,Fermata> getFermateMap(){
		return fermate;
	}
	

	public int ritardoMassimo() {
		return -1;
	}

	public int ritardoMedio() {
		return -1;
	}

}

