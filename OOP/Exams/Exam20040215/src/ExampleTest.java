import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import orari.Fermata;
import orari.Orari;
import orari.Passaggio;
import orari.Percorso;
import orari.PercorsoNonValido;
import orari.StazioneNonValida;
import orari.Treno;

public class ExampleTest {

		@Test
		public void testApp() throws PercorsoNonValido, StazioneNonValida {
			
			// R1
			Orari orari = new Orari();
			Percorso p = orari.creaPercorso("IC2345","Intercity");
			assertNotNull("Missing percorso");
			assertFalse(p.isStraordinario());
			
			// R2
			p.aggiungiFermata("Torino Porta Nuova",10,15);
			p.aggiungiFermata("Vercelli",11,05);
			p.aggiungiFermata("Novara",11,40);
			p.aggiungiFermata("Milano Centrale",12,30);
	
			List<Fermata> fermate = p.getFermate();
			assertNotNull(fermate);
			assertEquals(4,fermate.size());
			assertEquals("Milano Centrale",fermate.get(3).getStazione());
			
			// R3
			Treno t = orari.nuovoTreno("IC2345",10,11,2004);
			assertNotNull(t);
			assertSame(p,t.getPercorso());
			
			// R4
			t.registraPassaggio("Torino Porta Nuova",10,20);
			t.registraPassaggio("Vercelli",11,15);
			Passaggio pass = t.registraPassaggio("Novara",11,46);
			assertEquals("Novara",pass.getStazione());
			assertEquals(6,pass.ritardo());
			
			// R5
			assertFalse(t.arrivato());
			t.registraPassaggio("Milano Centrale",12,33);
			assertTrue(t.arrivato());
			
			Treno t2 = orari.nuovoTreno("IC2345",10,11,2004);		
			t2.registraPassaggio("Torino Porta Nuova",10,21);
			t2.registraPassaggio("Vercelli",11,8);
			t2.registraPassaggio("Novara",11,49);
			t2.registraPassaggio("Milano Centrale",12,35);
			
			assertEquals(5,p.ritardoMassimo());

			
			Percorso pr = orari.creaPercorso("IR431","Interregionale");
			pr.aggiungiFermata("Torino Porta Nuova",6,45);
			pr.aggiungiFermata("Villanova",7,10);
			pr.aggiungiFermata("Asti",7,25);
			pr.aggiungiFermata("Alessandria",7,55);
			pr.aggiungiFermata("Arquata",8,10);
			pr.aggiungiFermata("Genova Principe",8,30);

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

			assertEquals(15,pr.ritardoMassimo());
			assertEquals(12,pr.ritardoMedio());

			List<String> classifica = orari.classificaRitardi();
			
			assertNotNull(classifica);
			assertEquals(2,classifica.size());
			assertEquals("IR431: 12",classifica.get(0));

		}
}
