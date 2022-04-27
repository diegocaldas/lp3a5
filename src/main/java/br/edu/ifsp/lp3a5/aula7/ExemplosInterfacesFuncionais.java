package br.edu.ifsp.lp3a5.aula7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class ExemplosInterfacesFuncionais {
	
	public static void main(String args[]) {
		exemploFunction();
	}
	
	/**
	 * Supplier : fornecedor - não recebe nada e retorna um valor
	 * Consumer : consumidor - recebe um valor e não retorna nada
	 */
	private void exemploSupplierConsumer() {
		Stream
		.generate( () -> new Random().nextInt() ) // a função generate recebe um supplier
		//Supplier : interface funcional que contém um método get sem argumentos
		// Ou seja, não recebe input, mas devolve um output
		.limit(10) //limita a 10 valores
		.forEach( (e) -> System.out.println(e) ); // a função forEach recebe um consumer
		//Consumer : interface funcional que contém um método accept com um parâmetro de entrada e void na saída
		//Consumer : oposto do supplier, não retorna nada, mas recebe um valor com o qual faz alguma ação.
	}
	
	/**
	 * BiConsumer, interface funcional que contém um método accept com dois valores de etrada e nenhum de saída.
	 * ou seja, recebe dois valores, realiza uma operação com eles, mas não retorna nada.
	 * O BiConsumer pode ser testado mais facilmente com um Map
	 */
	private static void exemploBiConsumer() {
		
		final HashMap< String, String> nomeSobrenome = new HashMap<>();
		nomeSobrenome.put("Diego", "Chaves");
		nomeSobrenome.put("João", "Ninguém");
		
        final BiConsumer<String, String> merge = (nome, sobrenome) ->
        {
        	System.out.print("\n" + nome + " " + sobrenome);
        };
        
        //forEach do Map aceita um BiConsumer que vai receber a chave e o valor como parâmetros
        nomeSobrenome.forEach(merge);
        
        System.out.println("\n\nExemplo com andThen");
        //Exemplo
        nomeSobrenome.forEach(merge.andThen(( a, b) -> System.out.print( " - " + (a + " " + b).length() ) ) );
	}
	
	/**
	 * Predicate é uma interface funcional que possui um método test que recebe 
	 * um valor e retorna um boolean
	 * 
	 * Pode ser visto em ação na função filter de uma List
	 */
	private static void exemploPredicate() {
		final List<String> frutas = Arrays.asList("ameixa", "jaca", "abacate", "caqui", "banana", "abacaxi");
		
		frutas.stream().filter(( fruta ) -> fruta.startsWith("a") ).forEach(System.out::println);
		
	}
	
	/**
	 * Function é uma interface funcional que contém um método apply
	 * que recebe um valor, aplica a função e devolve um resultado 
	 */
	private static void exemploFunction() {
		final List<String> frutas = Arrays.asList("ameixa", "jaca", "abacate", "caqui", "banana", "abacaxi");
		
		frutas.stream().map((fruta) -> fruta + "s" ).forEach(System.out::println);
		
		//BiFunction é uma function com dois valores de entrada
		//UnaryOperator é uma uma function que obrigatoriamente recebe e devolve um valor do mesmo tipo
	}


}
