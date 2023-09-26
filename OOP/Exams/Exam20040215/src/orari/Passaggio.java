package orari;

import java.util.ArrayList;
import java.util.List;

public class Passaggio {

	private String codiceStazione;
	private Percorso percorso;
	private int ore;
	private int minuti;

  public Passaggio(Percorso percorso, String codiceStazione, int ore, int minuti) {
	  	this.percorso = percorso;
		this.codiceStazione = codiceStazione;
		this.ore = ore;
		this.minuti = minuti;
	}

public String getStazione() {
    return codiceStazione;
  }

  public int getOra() {
    return ore;
  }

  public int getMinuti() {
    return minuti;
  }

  public int ritardo() {
	 int current = 0;
	 int expected = 0;
	 int result = 0;
	 
	 List<Fermata> lista;
	 lista = percorso.getFermate();
	 Fermata fermataPrevista = null;
	 
	 for(Fermata tmp: lista) {
		 if(tmp.getStazione().equals(codiceStazione) == true){
			 fermataPrevista = tmp;
			 break;
		 }
	 }
	 
	 expected = (fermataPrevista.getOre()*60) +fermataPrevista.getMinuti();
	 
     current = (ore*60) + minuti;
	  
	 result = Math.abs(current - expected);
	
    return result;
  }

}
