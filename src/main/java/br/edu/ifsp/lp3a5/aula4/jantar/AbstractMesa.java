package br.edu.ifsp.lp3a5.aula4.jantar;

import java.util.logging.Logger;

public abstract class AbstractMesa {
	
	final protected Logger log = Logger.getLogger("logging.handler");
	
	final protected int qtdFilosofos;
	final protected Garfo[] garfos;
	final protected int[] tentativas;
	final private IRoteiroJantar roteiro;

	public AbstractMesa(int qtdFilosofos, IRoteiroJantar roteiro) {
		this.qtdFilosofos = qtdFilosofos;
		this.roteiro = roteiro;
		
		this.garfos = new Garfo[this.qtdFilosofos];
		this.tentativas = new int[this.qtdFilosofos];
		
		for (int i = 0; i < this.qtdFilosofos; i++) {
			this.garfos[i] = new Garfo();
			this.tentativas[i] = 0;
		}
	}
	
	protected IRoteiroJantar getRoteiro() {
		return this.roteiro;
	}
	
	public int getQtdFilosofos() {
		return this.qtdFilosofos;
	}

	protected Garfo escolheGarfoEsquerdo(Filosofo filosofo) {
		return escolheGarfo( filosofo.getPosicaoMesa() );
	}

	protected Garfo escolheGarfoDireito(Filosofo filosofo) {
		return escolheGarfo( (filosofo.getPosicaoMesa() + 1) % this.qtdFilosofos );
	}
	
	protected Garfo escolheGarfo(final int idx) {
		return garfos[idx];
	}


	public String getMsgGarfos() {
		final StringBuffer sb = new StringBuffer( "Garfos = [ " );
		for (int i = 0; i < this.qtdFilosofos; i++) {
			
			if ( i != 0 ) {
				sb.append(" , ");
			}
			
			sb.append(garfos[i]);
		}
		sb.append("]");
		return sb.toString();
	}

	public String getMsgTentativas() {
		final StringBuffer sb = new StringBuffer( "Tentou comer = [ " );
		for (int i = 0; i < this.qtdFilosofos; i++) {
			if ( i != 0 ) {
				sb.append(" , ");
			}
			
			sb.append(tentativas[i]);
		}
		sb.append("]");
		
		return sb.toString();
	}

	abstract public void pegaGarfosGuloso(Filosofo filosofo) throws InterruptedException;
	abstract public boolean pegaGarfosComplacente(Filosofo filosofo) throws InterruptedException;
	abstract public void devolveGarfos(Filosofo filosofo);
}
