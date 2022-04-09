package br.edu.ifsp.lp3a5.aula4.jantar;

import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.edu.ifsp.lp3a5.aula4.jantar.solucao1.Mesa1;
import br.edu.ifsp.lp3a5.aula4.jantar.solucao2.Mesa2;
import br.edu.ifsp.lp3a5.aula4.jantar.solucao3.Mesa3;
import br.edu.ifsp.lp3a5.utils.ICicloDeVida;
import br.edu.ifsp.lp3a5.utils.JanelaStartStop;
import br.edu.ifsp.lp3a5.utils.LogPanelHandler;

public class Main {
	
	//usa logging.handler para poder printar no painel
	final static Logger log = Logger.getLogger("logging.handler");
	

	public static void createAndShowGui(JPanel panel) {
		final JFrame frame = new JFrame("Start Stop");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void initialize(AbstractMesa mesa) {
		final ICicloDeVida jantar = new Jantar( mesa );
		final JanelaStartStop panel = new JanelaStartStop(jantar);
		final LogPanelHandler handler = LogPanelHandler.getInstance(panel);
		log.addHandler(handler);
		SwingUtilities.invokeLater(() -> createAndShowGui(panel));

	}
	
	public static void testeDeadlock() {
		initialize(new Mesa1(5));
		log.info("TESTE DEADLOCK");
		log.info("Neste exemplo o filósofo fica com um garfo na mão à "
				+ "espera que o segundo garfo libere e ele possa comer. "
				+ "Depois de um tempo gera uma situação de deadlock quando "
				+ "todos os filósofos ficam aguardando, sem comer ou "
				+ "liberar o talher para os demais.");
	}
	
	public static void testeStarved() {
		initialize(new Mesa2(5));
		log.info("TESTE STARVED");
		log.info("Neste exemplo o filósofo volta a pensar quando não consegue "
				+ "obter os dois garfos. Não há garantias que na próxima "
				+ "rodada ele conseguirá os dois garfos. Com isso existe a "
				+ "probabilidade de que uns comam bastante e outros fiquem "
				+ "famintos.");
	}
	
	public static void testeOK() {
		initialize(new Mesa3(5));
		log.info("TESTE NORMAL");
		log.info("Neste exemplo o filósofo agarra o primeiro garfo e não o solta "
				+ "até conseguir pegar o segundo e comer, porém, a sincronização "
				+ "dos métodos que pegam e devolvem os garfos evitam o problema"
				+ "de deadlock, pois quando um filósofo pega o primeiro garfo,"
				+ "os demais precisam esperar para conseguirem pegar um primeiro"
				+ "garfo também.");
	}

	public static void main(String[] args) {

		testeDeadlock();
		//testeStarved();
		//testeOK();
	}
}
