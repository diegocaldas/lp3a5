package br.edu.ifsp.lp3a5.aula4.jantar;

public class RoteiroGuloso implements IRoteiroJantar {
	
	public void jantarPensante(Filosofo filosofo) throws InterruptedException {
		filosofo.pensa();
		filosofo.getMesa().pegaGarfosGuloso(filosofo);
		filosofo.come();
		filosofo.devolveGarfos();
	}

}
