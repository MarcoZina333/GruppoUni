package it.unibs.fp.tamabase;

import it.unibs.fp.mylib.*;

public class TamaMain {

	public static final String MSG_INPUT_CAREZZE = "Inserire numero carezze: ";
	public static final String MSG_INPUT_BISCOTTI = "Inserire numero biscotti: ";
	public static final String MSG_INPUT_SAZIETA = "Inserire la sazieta di ";
	public static final String MSG_INPUT_AFFETTO = "Inserire la soddisfazione di ";
	public static final String MSG_INPUT_NOME = "Che nome vuoi dare al tuo Tamagotchi? ";
	public static final String MSG_BENVENUTO = "Benvenuti nel gioco del Tamagotchi!\n";

	public static void main(String[] args) {
		System.out.println(MSG_BENVENUTO);
		
		Tamagotchi myTama = TamaUtility.leggiTama();
		int scelta = 0;
		String[] voci = new String[2];
		voci[0] = "Dai biscotti a " + myTama.getNome();
		voci[1] = "Dai carezze a " + myTama.getNome();
		MyMenu menu = new MyMenu ("Scegli l'opzione: ", voci);
		int numBiscotti = 0, numCarezze = 0;
		do {
			System.out.println(myTama.toString());
			scelta = menu.scegli();
			switch (scelta) {
			case 1: //dai biscotti
				numBiscotti = NumeriCasuali.estraiIntero(1, 20);
				System.out.println("Dai "+ numBiscotti + " biscotti a " + myTama.getNome());
				myTama.daiBiscotti(numBiscotti);
				break;
			case 2: //dai carezze
				numCarezze = NumeriCasuali.estraiIntero(1, 20);
				System.out.println("Dai "+ numCarezze + " carezze a " + myTama.getNome());
				myTama.daiCarezze(numCarezze);
				break;
			}
		}
		while (scelta != 0 && !myTama.isMorto());
		System.out.println(myTama.toString());
		
	}
	

	
}
