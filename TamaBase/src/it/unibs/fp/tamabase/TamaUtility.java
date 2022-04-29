package it.unibs.fp.tamabase;

import it.unibs.fp.myutil.*;

public class TamaUtility {
	
	 
	
	/**
	 * Fa inserire all'utente nome del Tamagotchi e fa scegliere se iniziallizzarlo con sazieta e soddisfazione personalizzati, casuali o di default (50/50)
	 * @return istanza di Tamagotchi inserita dall'utente
	 */
	public static Tamagotchi leggiTama( ) {
		final String MSG_DEFAULT = "Valori di default (50, 50)";
		final String TITOLO_MENU = "Scegli impostazione iniziale di sazieta e soddisfazione";
		final String MSG_VAL_RANDOM = "Inserisci valori random";
		final String MSG_VAL_PERSONALIZZATI = "Inserisci valori personalizzati";
		final int MIN_RANDOM = 10;
		final int MAX_RANDOM = 90;
		String nome = InputDati.leggiStringaNonVuota(TamaMain.MSG_INPUT_NOME);
		int affetto = 0;
		int sazieta = 0;
		String[] voci = {MSG_VAL_PERSONALIZZATI, MSG_VAL_RANDOM};
		MyMenu menu = new MyMenu(TITOLO_MENU, voci, MSG_DEFAULT);
		switch (menu.scegli()) {
			case 0: //Si inizializza il Tamagotchi con valori di default
				return new Tamagotchi(nome);
			case 1: //Si inizializza il tamagotchi con sazieta e soddisfazione personalizzate
				sazieta = InputDati.leggiIntero(TamaMain.MSG_INPUT_SAZIETA + nome + ": ", Tamagotchi.SAZIETA_MIN, Tamagotchi.SAZIETA_MAX);
				affetto = InputDati.leggiIntero(TamaMain.MSG_INPUT_AFFETTO + nome + ": ", Tamagotchi.SODDISFAZIONE_MIN, Tamagotchi.SODDISFAZIONE_MAX);
				break;
			case 2: //Si inizializza il tamagotchi con soddisfazione e sazieta casuali
				affetto = NumeriCasuali.estraiIntero(MIN_RANDOM, MAX_RANDOM);
				sazieta = NumeriCasuali.estraiIntero(MIN_RANDOM, MAX_RANDOM);
		}
		return new Tamagotchi(nome, sazieta, affetto);
	}
	
	/**
	 * Funzione utile per essere sicuri di avere un double in un range prestabilito.
	 * @param valore
	 * @param min
	 * @param max
	 * @return il valore stesso se  nel range [min, max], min se il valore è minore di min, max se il valore è maggiore di max
	 */
	public static double doubleInRange(double valore, double min, double max) {
		if (valore >= max)
			return max;
		if (valore <= min)
			return min;
		return valore;
	}
	/**
	 * Controlla se valore è nel range tra min e max
	 * @param valore
	 * @param min
	 * @param max
	 * @return true se valore sta nel range tra min e max, false altrimenti
	 */
	public static boolean isInRange(double valore, double min, double max) {
		return valore >= min && valore <= max;
	}
}
