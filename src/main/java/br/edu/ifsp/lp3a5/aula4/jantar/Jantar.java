package br.edu.ifsp.lp3a5.aula4.jantar;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

import br.edu.ifsp.lp3a5.utils.ICicloDeVida;

/**
 * Coloca os filósofos em suas devidas posições e dá início ao jantar.
 * 
 * @author diego
 *
 */
public class Jantar implements ICicloDeVida{
	
	//usa logging.handler para poder printar no painel
	final private Logger log = Logger.getLogger("logging.handler");
	
	private Instant instanteInicial;
	private Instant instanteFinal;
	
	final private int qtdFilosofos;
	final private Filosofo[] filosofos;
	
	final private AbstractMesa mesa;
	
	public Jantar( AbstractMesa mesa ) {
		this.mesa = mesa;
		this.qtdFilosofos = mesa.getQtdFilosofos();
		this.filosofos = new Filosofo[ this.qtdFilosofos ];
	}
	
	/**
	 * Inicia um novo jantar com novos filósfos cada vez que é executado.
	 */
	public void inicia() {
		log.info("Comecou a execucao.");
		instanteInicial = Instant.now();
		for (int posicaoFilosofo = 0; posicaoFilosofo < qtdFilosofos; posicaoFilosofo++) {
			
			//libera a cadeira caso ainda tenha um filósofo do jantar anterior
			if (filosofos[posicaoFilosofo] != null) {
				filosofos[posicaoFilosofo].sair();
			}
			
			filosofos[posicaoFilosofo] = new Filosofo(posicaoFilosofo, mesa);
			new Thread(filosofos[posicaoFilosofo]).start();
		}			
	}
	
	/**
	 * Acaba o jantar.
	 */
	public void acaba() {
		for (int posicaoFilosofo = 0; posicaoFilosofo < qtdFilosofos; posicaoFilosofo++) {
			filosofos[posicaoFilosofo].sair();
		}
		
		imprimeQtdComida();
		
		instanteFinal = Instant.now();
		
		final Duration total = Duration.between(instanteInicial, instanteFinal);
		log.info("Tempo de execucao " + total);
	}
	
	/**
	 * Faz uma lista de quantas vezes cada filósofo comeu.
	 */
	public void imprimeQtdComida() {
		final StringBuffer sb = new StringBuffer( "Conseguiu comer = [ " );
		
		for (int i = 0; i < filosofos.length; i++) {
			if ( i!=0 ) {
				sb.append(" , ");
			}
			sb.append(filosofos[i].getQtdComida());
		}
		
		sb.append("]");
		log.info( sb.toString() );
	}
}
