package orari;

import java.util.ArrayList;
import java.util.List;

public class Treno {
	
	private Percorso percorso;
	private List<Passaggio> passaggio = new ArrayList<>();
	private int giorno;
	private int mese;
	private int anno;

	
	public Treno(Percorso percorso, int giorno, int mese, int anno) {

			this.percorso = percorso;
			this.giorno = giorno;
			this.mese = mese;
			this.anno = anno;
	}
  public Percorso getPercorso() {
    return percorso;
  }

  public int getGiorno() {
    return giorno;
  }

  public int getMese() {
    return mese;
  }

  public int getAnno() {
    return anno;
  }

  public Passaggio registraPassaggio(String codice, int ore, int minuti) 
  	throws StazioneNonValida {
	  
	  Passaggio elemento = null;
	  
	  try {
		  if(percorso.getFermateMap().containsKey(codice) == false) {
			  throw new StazioneNonValida();
		  	}
		  elemento = new Passaggio (this.percorso, codice, ore, minuti);
		  passaggio.add(elemento);
		  }
	  catch (StazioneNonValida erroreStazione){
	  		}
	  
    return elemento;
  }


public boolean arrivato() {
    return false;
  }

  public int ritardoMassimo() {
    return -1;
  }

  public int ritardoFinale() {
    return -1;
  }

}
