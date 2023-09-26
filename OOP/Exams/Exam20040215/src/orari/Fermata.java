package orari;


public class Fermata {
  private String codiceStazione;
  private int ore;
  private int minuti;
  
  public Fermata(String codiceStazione, int ore, int minuti) {
  	this.codiceStazione = codiceStazione;
  	this.ore = ore;
  	this.minuti = minuti;
  }

  public String getStazione() {
    return codiceStazione;
  }

  public int getOre() {
    return ore;
  }

  public int getMinuti() {
    return minuti;
  }

}
