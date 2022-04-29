package it.unibs.fp.tamabase;

import static org.junit.Assert.*;

import org.junit.Test;

public class TamaTest {
	
	public static final double EPSILON = 0.001;
	
	@Test
	public void dieForZeroHunger()  {
		final Tamagotchi tama = new Tamagotchi("Pippo", 0, 50);
		assertTrue(tama.isMorto());
	}
	

	@Test
	public void testDiedGorged() {
		final Tamagotchi tama = new Tamagotchi("Kira");
		tama.setSazietaESoddisfazione(100, 50);
		assertTrue("Tama sazieta 100 -> morto ingozzato", tama.isMorto());
	}
	
	@Test
	public void testSatietyIncreased() {
		final Tamagotchi tama = new Tamagotchi("Kira", 50, 50);
		final double expectedSatiety = Math.min(tama.getSazieta() * tama.getIncrementoBiscotto(), Tamagotchi.SAZIETA_MAX);
		final double expectedAffectivity = Math.max(0, tama.getSoddisfazione() - 1/Tamagotchi.FATTORE_BISCOTTI);
		tama.daiBiscotti(1);
		assertEquals(expectedSatiety, tama.getSazieta(), EPSILON);
		assertEquals(expectedAffectivity, tama.getSoddisfazione(), EPSILON);
	}
	
	@Test
	public void testAffectivityIncreased() {
		final Tamagotchi tama = new Tamagotchi("Kira", 50, 50);
		final int NUMERO_CAREZZE = 1;
		final double expectedAffectivity = Math.min(tama.getSoddisfazione() + NUMERO_CAREZZE, Tamagotchi.SODDISFAZIONE_MAX);
		final double expectedSatiety = Math.max(0, tama.getSazieta() - NUMERO_CAREZZE/Tamagotchi.FATTORE_CAREZZE);
		tama.daiCarezze(NUMERO_CAREZZE);
		assertEquals(expectedSatiety, tama.getSazieta(), EPSILON);
		assertEquals(expectedAffectivity, tama.getSoddisfazione(), EPSILON);
	}
	
	@Test
	public void testSatietyExceeded() {
		final Tamagotchi tama = new Tamagotchi("Kira", 99, 50);
		tama.daiBiscotti(1);
		assertTrue(tama.isMorto());
	}
	
	
	@Test
	public void testInitWithNegativeArgument()
	{
		Tamagotchi tama = new Tamagotchi("Kira", 50, -1);
		double expectedAffectivity = 50;
		assertEquals(expectedAffectivity, tama.getSoddisfazione(), EPSILON);
	}
	
	@Test
	public void testSetNegativeSatietyAndAffectivity() {
		final Tamagotchi tama = new Tamagotchi("Kira");
		tama.setSazietaESoddisfazione(-50, 50);
		assertEquals(tama.getSazieta(), 0, EPSILON);
		assertTrue(tama.isMorto());
	}
	
	@Test
	public void testSetSatietyAndNegativeAffectivity() {
		final Tamagotchi tama = new Tamagotchi("Kira");
		tama.setSazietaESoddisfazione(50, -50);
		assertEquals(tama.getSoddisfazione(), 0, EPSILON);
		assertTrue(tama.isMorto());
	}
	
	@Test
	public void testIsSad() {
		final Tamagotchi tama = new Tamagotchi("Kira", 29, 2);
		assertTrue(tama.isTriste());
	}
	
	@Test
	public void testIsSadTooFood() {
		final Tamagotchi tama = new Tamagotchi("Kira", 91, 50);
		assertTrue(tama.isTriste());
	}
	
}
