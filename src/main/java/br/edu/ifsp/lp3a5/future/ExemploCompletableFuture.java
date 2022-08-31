package br.edu.ifsp.lp3a5.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ExemploCompletableFuture {
	
	public double getValorEmAlgumaAPIQualquer() throws InterruptedException {
		return simulaPegarValorRemoto();
	}
	
    public Future<Double> getValorAsync() {
        CompletableFuture<Double> valorFuturo = new CompletableFuture<>();
        new Thread(() -> {
            try {
                valorFuturo.complete(simulaPegarValorRemoto());
            } catch (Exception e) {
            	valorFuturo.completeExceptionally(e);
            }
        }).start();
        return valorFuturo;
    }
    
    private double simulaPegarValorRemoto() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);;
        return ThreadLocalRandom.current().nextDouble() * 100;
    }

}
