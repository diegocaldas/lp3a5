package br.edu.ifsp.lp3a5.aula7;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ExemplosStream {
	
	public static void main(String args[]) {
		exemploReduce();
	}
	
	
	private static void criandoStreams() {
		//A interface collection possui o método stream e parallelStream
		final List<String> lista = Arrays.asList("amor", "bola", "casa", "dado");
		
		//Criação de stream a partir de uma lista
		lista.stream().forEach(System.out::println);
		
		
		System.out.println("\n\nExemplo com parallelStream");
		//O parallelStream é para programação paralela, situações em que não é necessário que o fluxo de processamento seja linear
		lista.parallelStream().forEach(System.out::println);
		
		
		//Também é possível criar streams a partir de arrays
		String[] nomes = new String[] {"Ana", "Beatriz", "Clara", "Diana", "Eliane"};
		System.out.println("\n\nStream a partir de array usando a classe java.util.Arrays");
		Arrays.stream(nomes).forEach(System.out::println);
		
		
		//É possível criar um Stream também com a própria classe Stream usando o método of
		System.out.println("\n\nAo usar o método Stream.of podem ser passados Object");
		Stream.of("casa", 1, "teste").forEach(System.out::println);
		
		//Para números é possível gerar um intervalo 
		System.out.println("\n\nCriando um stream com o método IntStream.range");
		IntStream.range(10, 20).forEach(System.out::println); 
		//caso deseje incluir o último número pode usar o rangeClosed
		
		//Outra forma de criar um stream é o método iterate.
		// Inicia-se com uma semente e uma função que define como ela será transformada
		System.out.println("\n\nCriando um stream com o método Stream.iterate");
		Stream.iterate(true, valor -> !valor).limit(5).forEach(System.out::println); 
		
		//Para listar arquivos em um diretório pode-se utilizar o Files.list
		System.out.println("\n\nCriando um stream com Files.list");
		try {
			Files.list(Paths.get("/tmp")).forEach(System.out::println);
			
			//Se o objetivo for listar o conteúdo dentro de arquivos texto use Files.lines
			System.out.println("\n\nCriando um stream com Files.lines");
			final String nmArq = "/tmp/testeStream";
		    final List<String> linhas = Arrays.asList("Um título Bacana", "Um subtítulo para encher linguiça", "Um texto em latim para marcar o espaço", "Anímula, Vágula, Blândula!!!");
		    Path file = Paths.get(nmArq);
		    Files.write(file, linhas, StandardCharsets.UTF_8);
		    
			Files.lines(file).forEach(System.out::println);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	private static void exemploMap() {
		//Mapeia os números inteiros nos pares
		IntStream.rangeClosed(1, 10).map(n -> n*2).peek(null).forEach(System.out::println); 
	}
	
	private static void exemploCollect() {
		
	        Map<Integer,List<String>> mapColetado = 
        	Stream.of("Ana", "Beatriz", "Clara", "Diana", "Eliane")
                	.collect(Collectors.groupingBy(nome -> nome.length() ) );
                
        	System.out.println(mapColetado);
		
		//A chave do map eh do tipo que é retornado no lambda do groupingBy
		Map<Boolean,List<String>> mapColetado2 = 
        	Stream.of("Ana", "Beatriz", "Clara", "Diana", "Eliane")
                	.collect(Collectors.groupingBy(nome -> nome.length() > 5 ) );
                
        	System.out.println(mapColetado2);
		
		
	}
	
	
	private static void exemploReduce() {
		
		//EXEMPLOS COM BinaryOperator
		final OptionalInt resultadoSoma = IntStream.rangeClosed(1, 10).reduce( (v1, v2) -> v1+v2  );
		System.out.println(resultadoSoma.getAsInt());
		
		
		final Optional<String> resultadoConcatenacao =
				Stream.of("Ana", "Beatriz", "Clara", "Diana", "Eliane")
				.reduce( (v1, v2) -> v1.concat(v2) );
		System.out.println(resultadoConcatenacao.get());
		
		
		//Exemplos com valor de identidade e BinaryOperator
		final String resultadoConcatenacao2 =
				Stream.of("Ana", "Beatriz", "Clara", "Diana", "Eliane", "")
				.reduce("", (v1, v2) -> v1.concat(v2) );
		System.out.println(resultadoConcatenacao2);
		
		try {
			final Optional<String>  resultadoConcatenacao3 =
					Arrays.stream(new String[] {})
					.reduce((v1, v2) -> v1.concat(v2) );
			System.out.println(resultadoConcatenacao3.get());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println();
		final String  resultadoConcatenacao4 =
				Arrays.stream(new String[] {})
				.reduce("",(v1, v2) -> v1.concat(v2) );
		System.out.println("RESULTADO COM IDENTIDADE =[" + resultadoConcatenacao4 + "]");

	}
}
