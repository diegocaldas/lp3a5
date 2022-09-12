package br.edu.ifsp.lp3a5.future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ExemploCallable implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		Random r = new Random();
		int segundosDeEspera = r.nextInt(5);
		TimeUnit.SECONDS.sleep(segundosDeEspera);
		System.out.println("Nome da thread " + Thread.currentThread());
		return segundosDeEspera;
	}
	
	private static void irFazerOutraCoisaDaVida() throws InterruptedException {
		System.out.println("fazendo outra coisa da vida");
		TimeUnit.SECONDS.sleep(2);
	}
	
	public static void main(String args[]) throws Exception {
		long ini = System.currentTimeMillis();
		
		ExecutorService poolExec = Executors.newFixedThreadPool(10);
		
		List< Future<Integer> > futures = new ArrayList<>();
		
		for ( int i = 0; i < 10; i++ ) {
			Callable<Integer> ec = new ExemploCallable();
			futures.add( poolExec.submit( ec ) );			
		}
		
		irFazerOutraCoisaDaVida();
		
		for ( Future<Integer> future : futures ) {
			System.out.println("O retorno foi " + future.get(1, TimeUnit.SECONDS) );			
		}
		
		System.out.printf("\ntempo de execução %d ms\n", System.currentTimeMillis() - ini);
		
		poolExec.shutdown();

	}
	
	private static void exemploFutureTask() throws Exception {
		
		FutureTask<Integer> exemploTask 
					= new FutureTask<>( new ExemploCallable());
		
		Thread t1 = new Thread(exemploTask);
		t1.start();
		
		//irFazerOutraCoisaDaVida();

		System.out.println( exemploTask.get( 1, TimeUnit.MILLISECONDS ) );
	}
	
	private static void exemploExecutorSingle() throws Exception {

		ExemploCallable ec = new ExemploCallable();
		
		ExecutorService singleExecutor 
					= Executors.newSingleThreadExecutor();
		Future<Integer> futureResult = singleExecutor.submit(ec);
		
		irFazerOutraCoisaDaVida();
		
		System.out.println( futureResult.get() );
		
		singleExecutor.shutdown();
	}
	
	private static void exemploExecutorPool() throws Exception {

		ExecutorService executorPool 
					= Executors.newFixedThreadPool(10);
		
		List<Future<Integer>> listaDeFutures
					= new ArrayList<>();
		
		for ( int i = 0; i < 10; i++) {
			Callable<Integer> exemploCallable 
					= new ExemploCallable();
			Future<Integer> futureResult 
					= executorPool.submit(exemploCallable);
			
			listaDeFutures.add(futureResult);
		}

		irFazerOutraCoisaDaVida();
		
		for (Future<Integer> future : listaDeFutures ) {
			System.out.println( future.get() );			
		}

		
		executorPool.shutdown();
	}

}
