package br.edu.ifsp.lp3a5.aula8;

import java.util.Optional;
import java.util.stream.IntStream;

public class ExemplosOptions {
	
	public static void main(String args[]) {
		testeOptionalInt();
	}
	
	private static void testeOptionalInt() {
		
		final Integer numero = null;
		final Integer numeroNull = 1;
		Optional<Integer> opt = Optional.ofNullable(numeroNull);
		
		System.out.println(opt.orElse(numero) );
		
		opt.ifPresent(System.out::println);


	}

}