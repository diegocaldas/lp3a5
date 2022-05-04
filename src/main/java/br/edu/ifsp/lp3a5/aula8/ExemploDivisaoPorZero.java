package br.edu.ifsp.lp3a5.aula8;

import java.util.InputMismatchException;
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
		
		try ( final Scanner scanner = new Scanner(System.in) ) {
			
			while ( desejaContinuar(scanner) ) {
				
				int numerador = 0;
				int denominador = 0;
				int exemplo = 0;
				boolean continueLoop = true;
				do { //baseado no exemplo 11.3 do Deitel & Deitel 10ª ed.
					try {
							System.out.print("Por favor, digite um numerador inteiro : ");
							numerador = scanner.nextInt();
							System.out.print("Por favor, digite um denominador inteiro : ");
							denominador = scanner.nextInt();
							
							System.out.print("Por favor, escolha um código de exemplo entre 0 e 11 : ");
							exemplo = scanner.nextInt();
							continueLoop = false; // entrada bem-sucedida; fim do loop
					} catch (InputMismatchException e) {
						/*
						 * Esse é um exemplo de algo que até um tempo atrás era muito estimulado em Java,
						 * o uso de Exception para tratar algo não tão excepcional, uma entrada errada do usuário.
						 * 
						 * Em outras linguagens, onde não há um mecanismo de exceções tão "musculoso" quanto em Java,
						 * há um desencorajamento para este tipo de uso de Exception, pois as Exceptions são muito
						 * mais custosas e são guardadas para situações nas quais seja necessário rastrear a pilha
						 * de execução ou obter mais informações de contexto onde o erro ocorreu.
						 * 
						 * Mesmo em Java, atualmente há uma crescente crítica a utilização das Exceptions para lidar
						 * com fluxos alternativos mais corriqueiros. 
						 */
						System.err.printf("%nException: %s%n", e);
						scanner.nextLine(); // descarta entrada para o usuário tentar de novo
						System.out.printf("Você precisa digitar inteiros. Por favor, tente outra vez. %n%n");
					}
				
				} while (continueLoop);

				
				switch( exemplo ) {
					case 0 : exemplo0(numerador, denominador); break;
					case 1 : exemplo1(numerador, denominador); break;
					case 2 : exemplo2(numerador, denominador); break;
					case 3 : exemplo3(numerador, denominador); break;
					case 4 : exemplo4(numerador, denominador); break;
					case 5 : exemplo5(numerador, denominador); break;
					case 6 : exemplo6(numerador, denominador); break;
					case 7 : exemplo7(numerador, denominador); break;
					case 8 : exemplo8(numerador, denominador); break;
					case 9 : exemplo9(numerador, denominador); break;
					case 10 : exemplo10(numerador, denominador); break;
					case 11 : exemplo11(numerador, denominador); break;
					default : System.out.println("Exemplo " + exemplo + " não existe. Digite um valor entre 0 e 11");
				}

				
			}
		} finally {
			System.out.println("Programa finalizado.");
		}
	}
	
	private static boolean desejaContinuar(Scanner scanner) {
		imprimeLinha();
		System.out.println("Deseja fazer uma divisão [S | N]?");
		return scanner.next().toUpperCase().equals("S");
	}
	
	private static void imprimeLinha() {
		System.out.println("\n----------------------------------------");
	}

	private static void exemplo0(int numerador, int denominador) {
		
		imprimeLinha();
		System.out.println("Exemplo 0");
		imprimeLinha();
		System.out.println("Nesse exemplo o denominador é tratado com um if 0 antes da chamada à função.");
		System.out.println("O erro é tratado e não há risco de um fim anormal.");
		System.out.println("Embora seja uma solução muito utilizada, seu uso é mais indicado em métodos private.");
		System.out.println("Em método public pode ser algo problemático.");
		System.out.println("Primeiramente, por quebrar o encapsulamento, jogando o tratamento de uma regra de negócio para um objeto externo.");
		System.out.println("Além disso, tal tratamento precisará ser replicado em todas as chamadas ao método, algo que vai contra o DRY (d'ont repeat yourself).");
		imprimeLinha();

		if (denominador == 0) {
			System.out.println("ERRO : Denominador não pode ser zero.");
			return;
		}
		
		final double resultado = divisaoSemTratamento( numerador , denominador );
		System.out.printf("\nAo dividir %d por %d o resultado da divisão foi %.2f" , numerador, denominador, resultado );
	}

	private static void exemplo1(int numerador, int denominador) {
		imprimeLinha();
		System.out.println("Exemplo 1");
		imprimeLinha();
		System.out.println("Nesse exemplo não há qualquer tratamento ao denominador 0.");
		System.out.println("Com isso a divisão será efetuada e poderá gerar uma exceção que caso não seja tratada pode finalizar a execução do programa.");
		imprimeLinha();
		
		final double resultado = divisaoSemTratamento( numerador , denominador );
		System.out.printf("\nAo dividir %d por %d o resultado da divisão foi %.2f" , numerador, denominador, resultado );
	}
	
	private static void exemplo2(int numerador, int denominador) {
		imprimeLinha();
		System.out.println("Exemplo 2");
		imprimeLinha();
		System.out.println("Nesse exemplo o método não trata o erro, sendo gerada uma ArithmeticException que é capturada e tratada pelo método que fez a chamada.");
		System.out.println("Tal qual o Exemplo 0, não é tão problemático em métodos private, porém em métodos public quebra o encapsulamento e gera código redundante.");
		System.out.println("No entanto, mesmo em métodos private esse é um tipo de exceção questionável.");
		System.out.println("A não divisão por 0 é uma regra bem conhecida da divisão de números inteiros, logo, deixar que a exceção ocorra é um erro de programação.");
		System.out.println("Nesse caso o melhor é corrigir o método do que contornar o erro com tratamento de exceções.");
		imprimeLinha();
		
		try {
			final double resultado = divisaoSemTratamento( numerador , denominador );
			System.out.printf("\nAo dividir %d por %d o resultado da divisão foi %.2f" , numerador, denominador, resultado );			
		} catch (ArithmeticException e) {
			System.out.println("A divisão falhou, pois o denominador precisa ser um número positivo.");
		}

	}
	
	private static void exemplo3(int numerador, int denominador) {
		imprimeLinha();
		System.out.println("Exemplo 3");
		imprimeLinha();
		System.out.println("Esse exemplo difere dos anteriores pois utiliza um método que trata a entrada do usuário, devolvendo null caso o denominador seja 0.");
		System.out.println("O null é tratado e é exibido um aviso de denominador 0.");
		System.out.println("Retornar null na ausência de um estado válido foi muito utilizado até o Java 7.");
		System.out.println("null, nesses casos, funciona como uma espécie de código de erro. Seu uso e significado deve ser indicado na documentação dos métodos public.");
		imprimeLinha();
		
		final Double resultado = divisaoUsandoNullComoIndicadorDeErro(numerador, denominador);
		if ( resultado != null ) {
			System.out.printf("\nAo dividir %d por %d o resultado da divisão foi %.2f" , numerador, denominador, resultado );			
		} else {
			System.out.println("ERRO : Denominador não pode ser 0. Digite um número válido.\n");
		}
	
	}

	private static void exemplo4(int numerador, int denominador) {
		imprimeLinha();
		System.out.println("Exemplo 4");
		imprimeLinha();
		System.out.println("Usa o método com retorno null, porém, não trata o null.");
		System.out.println("Esse é o grande problema de retornar null ou códigos de erros.");
		System.out.println("Mesmo bem documentado, isso não implica que quem vá invocar o método faça o tratamento.");
		System.out.println("Como pode ser visto nesse exemplo o não tratamento do null pode gerar um grande problema.");
		System.out.println("Quando o programa tenta usar o valor null é lançada uma exceção de ponteiro nulo sem relação com o denominador zero."); 
		System.out.println("Assim, não há clareza do porquê da exceção lançada, o que mascara o problema, além disso o programa pode terminar de forma inesperada.");
		imprimeLinha();

		final double resultado = divisaoUsandoNullComoIndicadorDeErro(numerador, denominador);
		System.out.printf("\nAo dividir %d por %d o resultado da divisão foi %.2f" , numerador, denominador, resultado );	
	}
	
	private static void exemplo5(int numerador, int denominador) {
		imprimeLinha();
		System.out.println("Exemplo 5");
		imprimeLinha();
		System.out.println("Usa um método que lança IllegalArgumentException quando recebe denominador 0");
		System.out.println("IllegalArgumentException descende de RuntimeException, um tipo de exceção que não precisa de verificação");
		System.out.println("Quem vai utilizar fica responsável por ler a documentação e tratar a exceção adequadamente.");
		imprimeLinha();

		try {
			final double resultado = divisaoLancandoIllegalArgumentException(numerador, denominador);
			System.out.printf("\nAo dividir %d por %d o resultado da divisão foi %.2f" , numerador, denominador, resultado );			
		} catch(IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private static void exemplo6(int numerador, int denominador) {
		imprimeLinha();
		System.out.println("Exemplo 6");
		imprimeLinha();
		System.out.println("É usado o método que lança IllegalArgumentException já visto no exemplo 5.");
		System.out.println("Porém, o usuário não trata a exceção e ela causa o término do programa.");
		System.out.println("Exceções não verificadas não geram obrigação de declaração e o usuário as vezes esquece de tratar.");
		System.out.println("Apesar de ser um um problema, o erro é mais fácil de identificar do que o null pointer do exemplo 4.");
		imprimeLinha();
		
		final double resultado = divisaoLancandoIllegalArgumentException(numerador, denominador);
		System.out.printf("\nAo dividir %d por %d o resultado da divisão foi %.2f" , numerador, denominador, resultado );			
	}

	private static void exemplo7(int numerador, int denominador) {
		imprimeLinha();
		System.out.println("Exemplo 7");
		imprimeLinha();
		System.out.println("Usa um método que lança uma exceção customizada quando recebe denominador 0");
		System.out.println("É uma exceção que exige verificação (checked exception) e está na assinatura do método.");
		System.out.println("Isso obriga o programador a tratar a exceção."); 
		System.out.println("Se a mensagem estiver bem explícita, não é necessário saber a regra do negócio, basta retornar a mensagem");
		System.out.println("O único ponto discutível nessa estratégia é o alto custo de usar exception para tratar um fluxo alternativo para um argumento errado.");
		System.out.println("Apesar do uso de exceções ser uma cultura da plataforma Java, um uso generalizado pode levar a problemas de desempenho.");
		System.out.println("O custo precisa ser avaliado de acordo com o contexto.");
		System.out.println("Em uma operação ocasional como esta do exemplo não chega a ser um problema.");
		System.out.println("Mas ao processar milhões de registros e com a possibilidade do erro ser uma situação comum e não uma excepcionalidade a situação é outra.");
		System.out.println("Nesse caso específico tampouco a pilha de execução é uma informação relevante, apenas o aviso do denominador 0 parece ser suficiente.");
		imprimeLinha();

		try {
			final double resultado = divisaoLancandoCheckedException(numerador, denominador);
			System.out.printf("\nAo dividir %d por %d o resultado da divisão foi %.2f" , numerador, denominador, resultado );			
		} catch(DivisaoPorZeroException e) {
			e.printStackTrace();
		}
	}
	
	private static void exemplo8(int numerador, int denominador) {
		
		imprimeLinha();
		System.out.println("Exemplo 8");
		imprimeLinha();
		System.out.println("Aqui é usada a exceção verificável também usada no exemplo 7.");
		System.out.println("Porém, apesar de abrir o bloco catch com o erro, o usuário não faz nenhum tratamento.");
		System.out.println("Ou seja, um programador relapso pode engolir e silenciar a exceção, dificultando descobrir onde o erro está ocorrendo.");
		imprimeLinha();
		
		try {
			final double resultado = divisaoLancandoCheckedException(numerador, denominador);
			System.out.printf("\nAo dividir %d por %d o resultado da divisão foi %.2f" , numerador, denominador, resultado );			
		} catch(DivisaoPorZeroException e) {
			//não faz nada.
			//Quem faz isso ganha um pedaço de carvão do Papai Noel no Natal.
		}
	}

	private static void exemplo9(int numerador, int denominador) {
		imprimeLinha();
		System.out.println("Exemplo 9");
		imprimeLinha();
		System.out.println("O método utilizado trata a exceção retornando um Optional empty quando o denominador é zero.");
		System.out.println("A partir do Java 8 a linguagem incorporou elementos do paradigma funcional entre elas o Optional.");
		System.out.println("Em algumas situações pode ser vantajoso, pois retornar um Option não é tão custoso quanto gerar uma exceção.");
		System.out.println("Porém, em milhões de execuções também pode impactar o desempenho, pois cria uma chamada extra para se acessar o valor.");
		System.out.println("A maior vantagem do empty é a redução do risco de um ponteiro de exceção nula.");
		System.out.println("Porém possui problemas similares ao código de erro ou ao uso do null.");
		System.out.println("Na prática funciona como um código de erro em forma de mônada.");
		System.out.println("O usuário precisará saber o significado do empty caso seja necessário notificar o usuário.");
		System.out.println("Ou seja, a documentação do método precisa ser bem feita, caso seja usado em método public.");
		imprimeLinha();
		
		OptionalDouble resultado = divisaoRetornandoOptionalDouble(numerador, denominador);
		
		//No Java 9
		//resultado.ifPresentOrElse( r -> System.out.printf("\nO resultado da divisão é %.2f" , r ));
		
		//No Java 8
		if ( resultado.isPresent() ) {
			System.out.printf("\nAo dividir %d por %d o resultado da divisão foi %.2f" , numerador, denominador, resultado.getAsDouble() );	
		} else {
			System.out.println("Denominador não pode ser 0.");
		}
		
	}

	private static void exemplo10(int numerador, int denominador) {
		imprimeLinha();
		System.out.println("Exemplo 10");
		imprimeLinha();
		System.out.println("O optional também pode ser mal utilizado, resultado em erro parecido com o null pointer.");
		System.out.println("O método get do Optional vai tentar pegar o valor, mesmo que ele seja empty, por isso seu uso não é aconselhado.");
		imprimeLinha();
		
		OptionalDouble resultado = divisaoRetornandoOptionalDouble(numerador, denominador);
		System.out.printf("\nAo dividir %d por %d o resultado da divisão foi %.2f" , numerador, denominador, resultado.getAsDouble() );	
	}
	
	/**
	 * Esboço de solução para situações nas quais o uso da exception possa ser muito custoso,
	 * ou caso o programador tenha antipatia por exceptions.
	 * 
	 * No Java não há a opção de múltiplos retornos, mas pode-se criar uma classe de retorno
	 * que contenha tanto o valor quanto a mensagem de erro. 
	 */
	private static void exemplo11(int numerador, int denominador) {
		imprimeLinha();
		System.out.println("Exemplo 11");
		imprimeLinha();
		System.out.println("Retorna um objeto com o valor e uma mensagem de erro caso o valor esteja vazio.");
		System.out.println("Outras linguagens oferecem a possibilidade de múltiplos retornos.");
		System.out.println("Assim, pode ser retornado um valor e também uma mensagem explicativa para caso o valor esteja vazio.");
		System.out.println("O Java não possui esse tipo de retorno, mas pode ser criado um objeto de retorno que encapsule o valor e a mensagem.");
		System.out.println("Esse tipo de estratégia esboçada aqui melhora o encapsulamento e reduz a repetição de código.");
		System.out.println("Mas em situações de muita carga de dados pode ter o mesmo problema de eficiência do Optional apontado no exemplo 9.");
		imprimeLinha();
		
		RetornoDivisao retorno = divisaoRetornandoObjetoDeResultado(numerador, denominador);
		retorno.resultado.ifPresent( r -> System.out.printf("\nAo dividir %d por %d o resultado da divisão foi %.2f" , numerador, denominador, r ) );
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