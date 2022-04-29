package it.unibs.fp.tamabase;

public class Tamagotchi {
	private static final double DENOMINATORE_BISCOTTI = 400.0;
	private static final double MODIFICATORE_BISCOTTI = 1.3;
	private static final int SODDISFAZIONE_DEFAULT = 50;
	private static final int SAZIETA_DEFAULT = 50;
	public static final double FATTORE_BISCOTTI = 4;
	public static final double FATTORE_CAREZZE = 2;
	public static final int SAZIETA_MIN = 0;
	public static final int SAZIETA_SOGLIA_INF = 30;
	public static final int SAZIETA_SOGLIA_SUP = 90;
	public static final int SAZIETA_MAX = 100;
	public static final int SODDISFAZIONE_MIN = 0;
	public static final int SODDISFAZIONE_SOGLIA_INFELICE = 30;
	public static final int SODDISFAZIONE_SOGLIA_FELICE = 90;
	public static final int SODDISFAZIONE_MAX = 100;
	public static final String MSG_MORTO = "\nSono morto X(\n";
	public static final String MSG_INFELICE = "\nSono infelice :(\n";
	private static final String MSG_FELICE = "\nSono felice! :)\n";
	private String nome;
	private double sazieta;
	private double soddisfazione;
	/**
	 * moltiplicatore utilizzato ogni volta che si da un biscotto al Tamagotchi
	 */
	private double incrementoBiscotto = 1.6;
	
	/**
	 * Costruttore di Tamagotchi 
	 * @param nome del Tamagotchi
	 * @param sazieta se fuori dal range consentito, viene assegnato il valore di default (SAZIETA_DEFAULT)
	 * @param soddisfazione se fuori dal range consentito, viene assegnato il valore di default (SODDISFAZIONE_DEFAULT)
	 */
	public Tamagotchi(String nome, double sazieta, double soddisfazione){
		this.nome = nome;
		if (!TamaUtility.isInRange(soddisfazione, SODDISFAZIONE_MIN, SODDISFAZIONE_MAX))
			this.soddisfazione = SODDISFAZIONE_DEFAULT;
		else this.soddisfazione = soddisfazione;
		
		if (!TamaUtility.isInRange(sazieta, SAZIETA_MIN, SAZIETA_MAX))
			this.sazieta = SAZIETA_DEFAULT;
		else this.sazieta = sazieta;
		aggiornaFattoreBiscotti();
	}

	/**
	 * Costruttore di Tamagotchi con solamente il nome e che inizializza sazieta e soddisfazione ai valori di default 
	 * @param nome
	 */
	public Tamagotchi(String nome) {
		this.nome = nome;
		this.sazieta = SAZIETA_DEFAULT;
		this.soddisfazione = SODDISFAZIONE_DEFAULT;
		aggiornaFattoreBiscotti();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSazieta() {
		return sazieta;
	}

	/**
	 * Funzione per aggiornare incrementoBiscotti in base alla sazieta attuale, piu è sazio, meno si sazia
	 */
	public void aggiornaFattoreBiscotti() {
		incrementoBiscotto = MODIFICATORE_BISCOTTI - sazieta / DENOMINATORE_BISCOTTI;
	}
	
	public void setSazieta(double sazieta) {
		this.sazieta = TamaUtility.doubleInRange(sazieta, SAZIETA_MIN, SAZIETA_MAX);
		aggiornaFattoreBiscotti();
	}

	public double getSoddisfazione() {
		return soddisfazione;
	}
	
	/**
	 * Setter per entrambi gli attributi di sazieta e soddisfazione
	 * @param sazieta
	 * @param soddisfazione
	 */
	public void setSazietaESoddisfazione(double sazieta, double soddisfazione) {
		this.sazieta = TamaUtility.doubleInRange(sazieta, SAZIETA_MIN, SAZIETA_MAX);
		aggiornaFattoreBiscotti();
		this.soddisfazione = TamaUtility.doubleInRange(soddisfazione, SODDISFAZIONE_MIN, SODDISFAZIONE_MAX);
	}

	public void setSoddisfazione(double soddisfazione) {
		this.soddisfazione = TamaUtility.doubleInRange(soddisfazione, SODDISFAZIONE_MIN, SODDISFAZIONE_MAX);
	}
	
	/**
	 * Metodo contenente l'algoritmo che aumenta la sazieta e diminuisce la soddisfazione in base ai biscotti dati.
	 * L'incremento alla sazieta varia in base alla sazieta attuale del Tamagotchi
	 * @param numBiscotti numero di biscotti che si vuol dare al Tamagotchi
	 */
	public void riceviBiscotti(int numBiscotti) {
		for (int i = 0; i < numBiscotti; i++) {
			aggiornaFattoreBiscotti();
			this.sazieta = Math.min(SAZIETA_MAX, sazieta * incrementoBiscotto);
		}
		this.soddisfazione = Math.max(SODDISFAZIONE_MIN, soddisfazione - (double)numBiscotti/FATTORE_BISCOTTI);
	}
	
	public double getIncrementoBiscotto() {
		aggiornaFattoreBiscotti();
		return incrementoBiscotto;
	}
	
	/**
	 * Metodo contenente l'algoritmo che diminuisce la sazieta e aumenta la soddisfazione in base alle carezze date.
	 * @param numBiscotti numero di biscotti che si vuol dare al Tamagotchi
	 */
	public void riceviCarezze(int numCarezze) {
		this.soddisfazione = Math.min(SODDISFAZIONE_MAX, soddisfazione + numCarezze);
		this.sazieta = Math.max(SAZIETA_MIN, sazieta - numCarezze/FATTORE_CAREZZE);
		aggiornaFattoreBiscotti();
	}
	
	/**
	 * 
	 * @return true se la sazieta del Tamagotchi sta in un range prestabilito (SAZIETA_SOGLIA_INF, SAZIETA_SOGLIA_SUP) e se ha almeno una soddisfazione minima; false altrimenti
	 */
	public boolean sonoFelice() {
		return (TamaUtility.isInRange(sazieta, SAZIETA_SOGLIA_INF, SAZIETA_SOGLIA_SUP) && this.soddisfazione > SODDISFAZIONE_SOGLIA_FELICE);
	}
	/**
	 * 
	 * @return true se la sazieta del Tamagotchi non sta in un range prestabilito (SAZIETA_SOGLIA_INF, SAZIETA_SOGLIA_SUP) o la soddisfazione sta sotto una certa soglia; false altrimenti
	 */
	public boolean sonoTriste() {
		return (this.sazieta < SAZIETA_SOGLIA_INF || this.sazieta > SAZIETA_SOGLIA_SUP || this.soddisfazione < SODDISFAZIONE_SOGLIA_INFELICE);
	}
	
	/**
	 * 
	 * @return true se la sazieta ha raggiunto la soglia minima o massima o la soddisfazione ha raggiunto la soglia massima
	 */
	public boolean sonoMorto() {
		return (this.sazieta == SAZIETA_MIN || this.sazieta == SAZIETA_MAX || this.soddisfazione == SODDISFAZIONE_MIN);
	}
	
	/**
	 * 
	 * @return il double che indica la media aritmetica tra sazieta e benessere e che indica il grado generale di benessere del Tamagotchi
	 */
	public double getBenessere() {
		return (sazieta + soddisfazione)/2;
	}
	
	/**
	 * @return la String contenente tutte le informazioni riguardo lo stato attuale del Tamagotchi
	 */
	public String toString() {
		StringBuffer descrizione = new StringBuffer();
		descrizione.append("\nNome: " + nome);
		descrizione.append(String.format("\nSazieta: %.2f", sazieta));
		descrizione.append(String.format("\nSoddisfazione: %.2f", soddisfazione));
		descrizione.append(String.format("\nGrado di benessere: %.2f", getBenessere()));
		if (this.sonoMorto())
			descrizione.append(MSG_MORTO);
		else if (this.sonoFelice())
			descrizione.append(MSG_FELICE);
		else if (this.sonoTriste())
				descrizione.append(MSG_INFELICE);
		return descrizione.toString();
	}
	
	
	
}
