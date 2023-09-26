package it.polito.po.test;

import orari.Orari;
import orari.Passaggio;
import orari.Percorso;
import orari.PercorsoNonValido;
import orari.StazioneNonValida;
import orari.Treno;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;



public class TestR4_Passaggi {
	Orari orari;
	Treno t;
	@Before
	public void setUp() throws PercorsoNonValido {
		orari = new Orari();
		Percorso p = orari.creaPercorso("IC2345","Intercity");
	    p.aggiungiFermata("Torino Porta Nuova",10,15);
		p.aggiungiFermata("Vercelli",11,05);
		p.aggiungiFermata("Novara",11,40);
		p.aggiungiFermata("Milano Centrale",12,30);
		t = orari.nuovoTreno("IC2345",10,11,2004);
	}
	
	@Test
	public void testRegistraPassaggio() throws Exception {

		Passaggio pass = t.registraPassaggio("Torino Porta Nuova",10,20);

		assertEquals("Torino Porta Nuova",pass.getStazione());
		assertEquals(10,pass.getOra());
		assertEquals(20,pass.getMinuti());		
	}

	@Test(expected=StazioneNonValida.class)
	public void testStazioneNonValida() throws Exception {
		t.registraPassaggio("Porta Susa",10,20);
	}

	@Test
	public void testRitardo() throws Exception {
		Passaggio pass = t.registraPassaggio("Torino Porta Nuova",10,20);

		assertEquals(5,pass.ritardo());
	}
}
