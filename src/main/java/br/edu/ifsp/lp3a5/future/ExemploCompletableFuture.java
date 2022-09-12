package br.edu.ifsp.lp3a5.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ExemploCompletableFuture {
	
	public double getValorSync() throws InterruptedException {
		return simularPegarValorRemoto();
	}

    public Future<Double> getValorAsync() {
        CompletableFuture<Double> valorFuturo = new CompletableFuture<>();
        
        new Thread(() -> {
            try {
                valorFuturo.complete(simularPegarValorRemoto());
            } catch (Exception e) {
            	valorFuturo.completeExceptionally(e);
            }
        }).start();
        return valorFuturo;
    }
    
    private double simularPegarValorRemoto() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return ThreadLocalRandom.current().nextDouble() * 100;
    }
    
    
    public static void main( String args[]) throws Exception {
    	exemploAssincrono();
    	exemploSincrono();
    }

        private static void exemploAssincrono() throws Exception {    	
    	long ini = System.currentTimeMillis();    
    	List<Future<Double>> listaFuture = new ArrayList<>();
    	
    	for (int i = 0; i < 365; i++) {
        	ExemploCompletableFuture exemplo1 = new ExemploCompletableFuture();
        	listaFuture.add(exemplo1.getValorAsync());
    	}
    	
    	for(Future<Double> future : listaFuture ) {
    		System.out.println( future.get() );	
    	}
    	
    	System.out.println( "Tempo de execução async: " + ( System.currentTimeMillis() - ini ) );
    }
    private static void exemploSincrono() throws Exception {
    	ExemploCompletableFuture exemplo1 = new ExemploCompletableFuture();
    	ExemploCompletableFuture exemplo2 = new ExemploCompletableFuture();
    	ExemploCompletableFuture exemplo3 = new ExemploCompletableFuture();
    	
    	long ini = System.currentTimeMillis();
    	System.out.println( exemplo1.getValorSync() );
    	System.out.println( exemplo2.getValorSync() );
    	System.out.println( exemplo3.getValorSync() );
    	
    	System.out.println( "Tempo de execução sync : " + ( System.currentTimeMillis() - ini ) );
    	
    }

}
