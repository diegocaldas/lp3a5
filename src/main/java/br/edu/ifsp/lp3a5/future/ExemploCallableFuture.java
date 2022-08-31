package br.edu.ifsp.lp3a5.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ExemploCallableFuture {
	
	public static void main(String args[]) throws InterruptedException, ExecutionException {
		final FutureTask randomNumberFuture = new FutureTask(new ExemploCallable());
		
		Thread t = new Thread(randomNumberFuture);
		t.start();
		fazQualquerCoisaPorAlgumTempo();
		System.out.println( randomNumberFuture.get() );
	}
	
	public static void fazQualquerCoisaPorAlgumTempo() throws InterruptedException {
		System.out.println("vou começar a fazer qualquer coisa...");
		for(int i = 0; i < 10; i++) {
			Thread.sleep(500);
		}
		System.out.println("parei de fazer qualquer coisa...");		
	}

}
