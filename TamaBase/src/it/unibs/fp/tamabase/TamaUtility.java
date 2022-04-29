package it.unibs.fp.tamabase;

import it.unibs.fp.mylib.*;

public class TamaUtility {
	public static Tamagotchi leggiTama( ) {
		String nome = InputDati.leggiStringaNonVuota(TamaMain.MSG_INPUT_NOME);
		int affetto = 0;
		int sazieta = 0;
		String[] voci = {"Inserisci valori personalizzati", "Inserisci valori random"};
		MyMenu menu = new MyMenu("Scegli impostazione iniziale di sazieta e soddisfazione", voci, "Valori di default (50, 50)");
		switch (menu.scegli()) {
			case 0:
				return new Tamagotchi(nome);
			case 1:
				sazieta = InputDati.leggiIntero(TamaMain.MSG_INPUT_SAZIETA + nome + ": ", Tamagotchi.SAZIETA_MIN, Tamagotchi.SAZIETA_MAX);
				affetto = InputDati.leggiIntero(TamaMain.MSG_INPUT_AFFETTO + nome + ": ", Tamagotchi.SODDISFAZIONE_MIN, Tamagotchi.SODDISFAZIONE_MAX);
				break;
			case 2:
				affetto = NumeriCasuali.estraiIntero(10, 90);
				sazieta = NumeriCasuali.estraiIntero(10, 90);
		}
		return new Tamagotchi(nome, sazieta, affetto);
	}
	
	public static double doubleInRange(double valore, double min, double max) {
		if (valore >= max)
			return max;
		if (valore <= min)
			return min;
		return valore;
	}
	
	public static boolean isInRange(double valore, double min, double max) {
		return valore >= min && valore <= max;
	}
}
