package it.polito.po.test;

import orari.Orari;
import orari.Percorso;
import orari.PercorsoNonValido;
import orari.StazioneNonValida;
import orari.Treno;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class TestR5_Statistiche {

	private Orari orari;
	private Percorso p;
	private Treno t;
	
	@Before
	public void setUp() throws PercorsoNonValido, StazioneNonValida {
		orari = new Orari();
		p = orari.creaPercorso("IC2345","Intercity");
		p.aggiungiFermata("Torino Porta Nuova",10,15);
		p.aggiungiFermata("Vercelli",11,05);
		p.aggiungiFermata("Novara",11,40);
		p.aggiungiFermata("Milano Centrale",12,30);
		t = orari.nuovoTreno("IC2345",10,11,2004);		
		t.registraPassaggio("Torino Porta Nuova",10,20);
		t.registraPassaggio("Vercelli",11,15);
		t.registraPassaggio("Novara",11,46);
	}
	@Test
	public void testTrenoArrivato() throws Exception {
		
		assertFalse(t.arrivato());
		
		t.registraPassaggio("Milano Centrale",12,33);

		assertTrue(t.arrivato());
		
	}

	@Test
	public void testStatTreno() throws Exception {

		t.registraPassaggio("Milano Centrale",12,33);
		
		assertEquals(10,t.ritardoMassimo());
		assertEquals(3,t.ritardoFinale());
	}

	@Test
	public void testStatPercorso() throws Exception {

		t.registraPassaggio("Milano Centrale",12,33);
		
		Treno t2 = orari.nuovoTreno("IC2345",10,11,2004);		
		t2.registraPassaggio("Torino Porta Nuova",10,21);
		t2.registraPassaggio("Vercelli",11,8);
		t2.registraPassaggio("Novara",11,49);
		t2.registraPassaggio("Milano Centrale",12,35);
		
		assertEquals(5,p.ritardoMassimo());
		assertEquals(4,p.ritardoMedio());
	}

	@Test
	public void testClassificaRitardi() throws StazioneNonValida, PercorsoNonValido {
		t.registraPassaggio("Milano Centrale",12,33);
		
		Treno t2 = orari.nuovoTreno("IC2345",10,11,2004);		
		t2.registraPassaggio("Torino Porta Nuova",10,21);
		t2.registraPassaggio("Vercelli",11,8);
		t2.registraPassaggio("Novara",11,49);
		t2.registraPassaggio("Milano Centrale",12,35);
		
		p = orari.creaPercorso("IR431","Interregionale");
		p.aggiungiFermata("Torino Porta Nuova",6,45);
		p.aggiungiFermata("Villanova",7,10);
		p.aggiungiFermata("Asti",7,25);
		p.aggiungiFermata("Alessandria",7,55);
		p.aggiungiFermata("Arquata",8,10);
		p.aggiungiFermata("Genova Principe",8,30);

		Treno t3 = orari.nuovoTreno("IR431",7,11,2004);
		t3.registraPassaggio("Torino Porta Nuova",6,46);
		t3.registraPassaggio("Villanova",7,11);
		t3.registraPassaggio("Asti",7,26);
		t3.registraPassaggio("Alessandria",7,59);
		t3.registraPassaggio("Arquata",8,20);
		t3.registraPassaggio("Genova Principe",8,45);
		// ritardo: 15

		Treno t4 = orari.nuovoTreno("IR431",8,11,2004);
		t4.registraPassaggio("Torino Porta Nuova",6,45);
		t4.registraPassaggio("Villanova",7,11);
		t4.registraPassaggio("Asti",7,27);
		t4.registraPassaggio("Alessandria",8,1);
		t4.registraPassaggio("Arquata",8,15);
		t4.registraPassaggio("Genova Principe",8,39);
		// ritardo: 9
		
		List<String> classifica = orari.classificaRitardi();
		
		assertNotNull(classifica);
		assertEquals(2,classifica.size());
		assertEquals("IR431: 12",classifica.get(0));
	}
}
