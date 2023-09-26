package it.polito.po.test;
import java.util.List;

import org.junit.Test;

import orari.Fermata;
import orari.Orari;
import orari.Percorso;
import junit.framework.TestCase;



public class TestR2_Fermate extends TestCase {

	@Test
	public void testAggiungiFermata() throws Exception {
		Orari orari = new Orari();
		Percorso p = orari.creaPercorso("IC2345","Intercity");

		String nomeStazione = "Torino Porta Nuova";
		int ore = 10;
		int minuti = 15;
		Fermata f = p.aggiungiFermata(nomeStazione,ore,minuti);

		assertEquals(nomeStazione,f.getStazione());
		assertEquals(ore,f.getOre());
		assertEquals(minuti,f.getMinuti());
	}

	@Test
	public void testFermate() throws Exception {
		Orari orari = new Orari();
		Percorso p = orari.creaPercorso("IC2345","Intercity");

		p.aggiungiFermata("Torino Porta Nuova",10,15);
		p.aggiungiFermata("Vercelli",11,05);
		p.aggiungiFermata("Novara",11,40);
		p.aggiungiFermata("Milano Centrale",12,30);

		List<Fermata> fermate = p.getFermate();
		assertEquals(4,fermate.size());		
	}

	@Test
	public void testFermateOrdinate() throws Exception {
		Orari orari = new Orari();
		Percorso p = orari.creaPercorso("IC2345","Intercity");

		Fermata f1 = p.aggiungiFermata("Torino Porta Nuova",10,15);
		Fermata f2 = p.aggiungiFermata("Vercelli",11,05);
		Fermata f3 = p.aggiungiFermata("Novara",11,40);
		Fermata f4 = p.aggiungiFermata("Milano Centrale",12,30);

		List<Fermata> fermate = p.getFermate();
		assertSame(f1,fermate.get(0));		
		assertSame(f2,fermate.get(1));		
		assertSame(f3,fermate.get(2));		
		assertSame(f4,fermate.get(3));		
	}


}
