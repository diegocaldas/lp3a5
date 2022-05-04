package br.edu.ifsp.lp3a5.aula8;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Scanner;

/**
 * Alguns exemplos de como tratar o erro de divisão por zero no Java
 * seguindo diferentes propostas
 * 
 * @author diego
 *
 */
public class ExemploDivisaoPorZero {
	
	public static void main(String args[]) {
		
		boolean continua = true;
		
		try ( final Scanner scanner = new Scanner(System.in) ) {
			do {
				System.out.println("\n----------------------------------------");
				System.out.println("Deseja fazer uma divisão [S | N]?");
				
				continua = scanner.next().toUpperCase().equals("S");
				
				if ( continua ) {
					System.out.println("Digite o numerador : ");
					final int numerador = scanner.nextInt();
					
					System.out.println("Digite o denominador : ");
					final int denominador = scanner.nextInt();
					
					//Renomear do exemplo 0 até o exemplo11
					exemplo0(numerador, denominador);
					
				}
				
			} while( continua ) ;
		} finally {
			System.out.println("Programa finalizado.");
		}
		

		
	}
	
	/**
	 * tratar antes de chamar o método não para o programa.
	 * 
	 * em métodos private essa é uma solução aceitável.
	 * 
	 * Porém em métodos public é algo problemático, pois parte do princípio que o programador 
	 * que usará o método conhece a regra do negócio, além disso, esse tratamento deverá 
	 * estar replicado em todas as chamadas do método, o que vai contra o DRY (dont repeat yourself)
	 */
	private static void exemplo0(int numerador, int denominador) {
		if (denominador == 0) {
			System.err.println("Denominador não pode ser zero.");
		}
		
		final double resultado = divisaoSemTratamento( numerador , denominador );
		System.out.printf("\nO resultado da divisão é %.2f" , resultado );
	}

	/**
	 * esse método gera uma RuntimeException, e ao não tratá-la isso vai parar o programa
	 */
	private static void exemplo1(int numerador, int denominador) {
		final double resultado = divisaoSemTratamento( numerador , denominador );
		System.out.printf("\nO resultado da divisão é %.2f" , resultado );
	}
	
	/**
	 * esse método trata a exception, evitando que o programa pare.
	 * Isso no entanto é um mau exemplo, pois a não divisão por zero é uma regra
	 * bem conhecida da divisão de números inteiros, portanto, não é uma 
	 * excepcionalidade, mas um erro do programador que não tratou a entrada do 
	 * usuário
	 */
	private static void exemplo2(int numerador, int denominador) {
		try {
			final double resultado = divisaoSemTratamento( numerador , denominador );
			System.out.printf("\nO resultado da divisão é %.2f" , resultado );			
		} catch (ArithmeticException e) {
			System.out.println("A divisão falhou, pois o denominador precisa ser um número positivo.");
		}

	}
	
	/** 
	 * Usa o método que retorna null quando o usuário digita 0 no denominador
	 * porém esquece de tratar o null, ocasionando um NullPointerException.
	 * A exception não tem qualquer relação com o dado digitado errado,
	 * não ficando claro o que gerou o erro.
	 *  
	 */
	private static void exemplo3(int numerador, int denominador) {

		final double resultado = divisaoUsandoNullComoIndicadorDeErro(numerador, denominador);
		System.out.printf("\nO resultado da divisão é %.2f" , resultado );	
	}
	
	/** 
	 * Usa o método que retorna null quando o usuário digita 0 no denominador e trata o null.
	 * É melhor do que o método sem tratamento.
	 *  
	 */
	private static void exemplo4(int numerador, int denominador) {

		final Double resultado = divisaoUsandoNullComoIndicadorDeErro(numerador, denominador);
		if ( resultado != null ) {
			System.out.printf("\nO resultado da divisão é %.2f" , resultado );			
		} else {
			System.err.println("Denominador não pode ser 0. Digite um número válido.\n\n");
		}
	
	}
	
	/** 
	 * Usa o método que lança IllegalArgumentException quando o usuário digita 0 no denominador.
	 * IllegalArgumentException é uma RuntimeException, o usuário pode esquecer de tratar, 
	 * parando o programa
	 *  
	 */
	private static void exemplo5(int numerador, int denominador) {

		final double resultado = divisaoLancandoIllegalArgumentException(numerador, denominador);
		System.out.printf("\nO resultado da divisão é %.2f" , resultado );			
	}
	
	/** 
	 * Usa o método que lança IllegalArgumentException quando o usuário digita 0 no denominador.
	 * O programador deve exibir a mensagem da exceção.  
	 */
	private static void exemplo6(int numerador, int denominador) {

		try {
			final double resultado = divisaoLancandoIllegalArgumentException(numerador, denominador);
			System.out.printf("\nO resultado da divisão é %.2f" , resultado );			
		} catch(IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
			
	}

	
	/** 
	 * Usa o método que lança CheckedException quando o usuário digita 0 no denominador.
	 * 
	 * Isso obriga o usuário a tratar a exceção. Ele não precisa saber a regra do negócio,
	 * para isso a mensagem deve ser bem explícita.
	 * 
	 * O ponto a ser levado em consideração é o alto custo do lançamento da exception.
	 * 
	 * Nem sempre a exception compensa, neste caso específico a pilha de execução não agrega
	 * informação relevante e não há garantias que o usuário, ou o programa que está gerando 
	 * a entrada, não ficará entrando com o número 0 seguidas vezes no denominador.
	 * 
	 * Por conta desse tipo de situação há um crescente número de pessoas que tem evitado 
	 * o uso generalizado de exceptions em Java.
	 * 
	 */
	private static void exemplo7(int numerador, int denominador) {

		try {
			final double resultado = divisaoLancandoCheckedException(numerador, denominador);
			System.out.printf("\nO resultado da divisão é %.2f" , resultado );			
		} catch(DivisaoPorZeroException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * Usa o método que lança uma checked exception quando o usuário digita 0 no denominador.
	 * O mau programador pode engolir a exception.
	 * 
	 * Esse tipo de situação pode ser identificada em validadores de código, por isso é melhor
	 * do que quando é utilizado código de erro.
	 */
	private static void exemplo8(int numerador, int denominador) {

		try {
			final double resultado = divisaoLancandoCheckedException(numerador, denominador);
			System.out.printf("\nO resultado da divisão é %.2f" , resultado );			
		} catch(DivisaoPorZeroException e) {
			//não faz nada.
		}
	}
	
	/**
	 * O Optional não é tão custoso quando a exception
	 * porém volta a uma situação parecida a do código de erro.
	 * obriga que o usuário saiba que quando o valor não estiver presente
	 * o denominador foi zero.
	 * 
	 * É basicamente um código de erro em forma de mônada.
	 * 
	 * Ainda assim, melhor do que um null, pois evita o null pointer.
	 * 
	 * Porém, se o usuário não souber o significado do código, a informação se perde.
	 * Ou seja, a documentação do método precisa ser bem feita e se possível gerar log
	 * quando tiver criar o empty.
	 */
	private static void exemplo9(int numerador, int denominador) {
		OptionalDouble resultado = divisaoRetornandoOptionalDouble(numerador, denominador);
		
		//No Java 9
		//resultado.ifPresentOrElse( r -> System.out.printf("\nO resultado da divisão é %.2f" , r ));
		
		//No Java 8
		if ( resultado.isPresent() ) {
			System.out.printf("\nO resultado da divisão é %.2f" , resultado.getAsDouble() );	
		} else {
			System.err.println("Denominador não pode ser 0.");
		}
		
	}
	
	/**
	 * O Optional também pode ser mal utilizado e gerar uma exception que para o programa.
	 */
	private static void exemplo10(int numerador, int denominador) {
		OptionalDouble resultado = divisaoRetornandoOptionalDouble(numerador, denominador);
		System.out.printf("\nO resultado da divisão é %.2f" , resultado.getAsDouble() );	
	}
	
	/**
	 * Esboço de solução para situações nas quais o uso da exception possa ser muito custoso,
	 * ou caso o programador tenha antipatia por exceptions.
	 * 
	 * No Java não há a opção de múltiplos retornos, mas pode-se criar uma classe de retorno
	 * que contenha tanto o valor quanto a mensagem de erro. 
	 */
	private static void exemplo11(int numerador, int denominador) {
		RetornoDivisao retorno = divisaoRetornandoObjetoDeResultado(numerador, denominador);
		retorno.resultado.ifPresent( r -> System.out.printf("\nO resultado da divisão é %.2f" , r ) );
		retorno.mensagem.ifPresent( m -> System.out.println( m ) );
	}
	
	
	/**
	 * O método simplesmente divide um valor pelo outro.
	 * 
	 * Se o denominador for zero é lançada uma ArithmeticException.
	 * 
	 * @param numerador
	 * @param denominador
	 * @return
	 */
	private static double divisaoSemTratamento(int numerador, int denominador) {
		return numerador / denominador;
	}
	
	/**
	 * O método verifica se o denominador é diferente de zero antes de prosseguir
	 * 
	 * @param numerador
	 * @param denominador
	 * @return
	 */
	private static Double divisaoUsandoNullComoIndicadorDeErro(int numerador, int denominador) {
		if ( denominador == 0 ) {
			return null;
		}
		
		return (double) (numerador / denominador);
	}
	
	private static double divisaoLancandoIllegalArgumentException(int numerador, int denominador) {
		if ( denominador == 0 ) {
			throw new IllegalArgumentException("Denominador não pode ser 0");
		}
		
		return (double) (numerador / denominador);
	}
	
	private static double divisaoLancandoCheckedException(int numerador, int denominador) throws DivisaoPorZeroException {
		if ( denominador == 0 ) {
			throw new DivisaoPorZeroException("Denominador não pode ser 0");
		}
		
		return (double) (numerador / denominador);		
	}
	
	private static OptionalDouble divisaoRetornandoOptionalDouble(int numerador, int denominador) {
		if (denominador == 0) {
			return OptionalDouble.empty();
		} else {
			return OptionalDouble.of(numerador/denominador);
		}
	}
	
	private static RetornoDivisao divisaoRetornandoObjetoDeResultado(int numerador, int denominador) {
		if (denominador == 0) {
			return new RetornoDivisao("Denominador não pode ser 0.");
		} else {
			return new RetornoDivisao( numerador / denominador );
		}
	}

}

class DivisaoPorZeroException extends Exception { 
	/**
	 * 
	 */
	private static final long serialVersionUID = -3254144233731847721L;

	public DivisaoPorZeroException(String msg) {
		super(msg);
	}
}

class RetornoDivisao {
	OptionalDouble resultado;
	Optional<String> mensagem;
	
	public RetornoDivisao(double resultado) {
		this.resultado = OptionalDouble.of(resultado);
		this.mensagem = Optional.empty();
	}
	
	public RetornoDivisao(String mensagem) {
		this.resultado = OptionalDouble.empty();
		this.mensagem = Optional.of(mensagem);
	}
}