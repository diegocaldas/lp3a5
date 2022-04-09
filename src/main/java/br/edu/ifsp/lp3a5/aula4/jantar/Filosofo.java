package br.edu.ifsp.lp3a5.aula4.jantar;

import java.util.Random;
import java.util.logging.Logger;

import br.edu.ifsp.lp3a5.utils.IRunnableStoppable;

public class Filosofo implements IRunnableStoppable {
	
	final Logger log = Logger.getLogger(Filosofo.class.getName());
	
	
	final public static int TEMPO_ESPERA_MAXIMO = 500;
	/**
	 * Coloca como volatile para evitar que uma variável armazenada em cache
	 * deixe a Thread em loop infinito
	 */
	private volatile boolean sair = false;
	
	private static Random randomGenerator = new Random();
	
	final private int numFilosofo;
	final private String idFilosofo;
	final private AbstractMesa mesa;

	
	int qtdComida = 0;

	public Filosofo(int numFilosofo, AbstractMesa mesa) {
		this.numFilosofo = numFilosofo;
		this.idFilosofo = "Filosofo_" + numFilosofo;
		this.mesa = mesa;
	}

	private static int getTempoEspera() {
		return randomGenerator.nextInt( TEMPO_ESPERA_MAXIMO );
	}
	
	public String getIdFilosofo() {
		return this.idFilosofo;
	}
	
	public int getPosicaoMesa() {
		return this.numFilosofo;
	}
	
	public int getQtdComida() {
		return this.qtdComida;
	}

	public void aguarda() throws InterruptedException {
		Thread.sleep( getTempoEspera() );
	}
	
	public void run() {
		try {
			while (!sair) {//caso entre em deadlock essa checagem se torna inútil
				mesa.getRoteiro().jantarPensante(this);
			}
		} catch (InterruptedException e) {
			log.severe("Thread " + idFilosofo + " terminou com o erro " + e.getMessage());
		}
	}
	
	public void sair() {
		this.sair = true;
	}

	/**
	 * Devolve um garfo à mesa.
	 */
	public void devolveGarfos() {
		mesa.devolveGarfos(this);
		log.info(idFilosofo + " está devolvendo os garfos..");
	}

	public void pensa() throws InterruptedException {
		log.info(idFilosofo + " está pensando..");
		this.aguarda();
	}

	public void come() throws InterruptedException {
		log.info(idFilosofo + " está comendo pela " + (++qtdComida) + "ª vez.");
		this.aguarda();;
	}
	
	public AbstractMesa getMesa() {
		return this.mesa;
	}

}
