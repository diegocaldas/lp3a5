package br.edu.ifsp.lp3a5.future;

import java.util.Random;
import java.util.concurrent.Callable;

public class ExemploCallable implements Callable {

	@Override
	public Object call() throws Exception {
		final int numeroAleatorio = (new Random()).nextInt(10); 
		Thread.sleep( numeroAleatorio * 1000);
		return numeroAleatorio;
	}
	
	
}
