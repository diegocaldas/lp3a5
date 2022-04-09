package br.edu.ifsp.lp3a5.aula4.jantar;

public class Garfo {
	public final static boolean DISPONIVEL = true;
	public final static boolean INDISPONIVEL = false;
	
	private boolean disponibilidade = DISPONIVEL;
	
	Filosofo filosofo = null;

	public boolean pegar(Filosofo filosofo) {
		if ( this.isDisponivel() ) {
			this.disponibilidade = INDISPONIVEL;
			this.filosofo = filosofo;
			return true;
		} else {
			return false;
		}
	}
	
	public void devolver() {
		this.disponibilidade = DISPONIVEL;
		this.filosofo = null;
	}
	
	public boolean isDisponivel() {
		return this.disponibilidade;
	}
	
	public String toString() {
		return isDisponivel() ? "LIVRE" : "ESTÁ COM " + filosofo.getIdFilosofo();
	}
}
