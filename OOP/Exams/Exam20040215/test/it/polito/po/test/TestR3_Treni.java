package it.polito.po.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.List;
import org.junit.Test;
import orari.Orari;
import orari.Percorso;
import orari.PercorsoNonValido;
import orari.Treno;



public class TestR3_Treni {

	@Test
	public void testNuovoTreno() throws Exception {
		Orari orari = new Orari();
		Percorso p = orari.creaPercorso("IC2345","Intercity");
		p.aggiungiFermata("Torino Porta Nuova",10,15);
		p.aggiungiFermata("Vercelli",11,05);
		p.aggiungiFermata("Novara",11,40);
		p.aggiungiFermata("Milano Centrale",12,30);
		
		Treno t = orari.nuovoTreno("IC2345",10,11,2004);
		assertSame(p,t.getPercorso());
		assertEquals(10,t.getGiorno());
		assertEquals(11,t.getMese());
		assertEquals(2004,t.getAnno());
	}

	@Test(expected=PercorsoNonValido.class)
	public void testPercorsoNonValido() throws PercorsoNonValido {
		Orari orari = new Orari();
		Percorso p = orari.creaPercorso("IC2345","Intercity");
		p.aggiungiFermata("Torino Porta Nuova",10,15);
		p.aggiungiFermata("Vercelli",11,05);
		p.aggiungiFermata("Novara",11,40);
		p.aggiungiFermata("Milano Centrale",12,30);
		
		/*Treno t =*/ orari.nuovoTreno("AA2345",10,11,2004);
	}

	@Test
	public void testTreni() throws Exception {
		Orari orari = new Orari();
		Percorso p = orari.creaPercorso("IC2345","Intercity");
		p.aggiungiFermata("Torino Porta Nuova",10,15);
		p.aggiungiFermata("Vercelli",11,05);
		p.aggiungiFermata("Novara",11,40);
		p.aggiungiFermata("Milano Centrale",12,30);
		Treno t1 = orari.nuovoTreno("IC2345",10,11,2004);
		Treno t2 = orari.nuovoTreno("IC2345",11,11,2004);

		List<Treno> treni = p.getTreni();
		
		assertSame(t2,treni.get(0));
		assertSame(t1,treni.get(1));
	}
}
