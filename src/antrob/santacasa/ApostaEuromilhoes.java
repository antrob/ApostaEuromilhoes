package antrob.santacasa;

import java.util.Arrays;
import java.util.Random;

/*
 	Class names are written in UpperCamelCase
 	Class names are typically nouns or noun phrases
 	
 	Item 15: Minimize the accessibility of classes and members
 	Item 56: Write doc comments for all exposed API elements
 */


/**
 * A class <code>ApostaEuromilhoes</code> representa uma Aposta Simples do Euromilhões.
 * Uma Aposta é constituída por
 * <ul>
 * 	<li>5 números inteiros diferentes entre 1 e 50</li>
 * 	<li>2 estrelas (números inteiros diferentes entre 1 e 12)</li>   
 * </ul> 
 * <br>Exemplo de utilização:
 * <pre>
 * {@code 
 *   ApostaEuromilhoes aposta = new ApostaEuromilhoes( new int[] {12,17, 24, 35, 47}, new int[]{ 2, 5} );
 * 
 *   ApostaEuromilhoes chave = new ApostaEuromilhoes(new int[] {6, 12, 19, 24, 38}, new int[] {3,5});
 *		
 *   Acertos resultado = aposta.getAcertos(chave);				
 * }
 * </pre>
 * 
 * @author antrob
 * @version 1.0
 */
public class ApostaEuromilhoes {

	// Uma aposta do Euromilhoes é composta por 5 numeros inteiros e 2 estrelas
	private int[] numeros;
	private int[] estrelas;
	
	
	// Gera uma aposta aleatória
	/**
	 * Cria um objecto <code>ApostaEuromilhoes</code> com números e estrelas gerados aleatóriamente
	 * 
	 */
	public ApostaEuromilhoes() {
		
		Random rnd = new Random();
		int numero;
		int i;
	
		numeros = new int[5];
		estrelas = new int[2];
		
		// Gera 5 numeros aleatórios diferentes entre 1 e 50
		numeros[0] = rnd.nextInt(50) + 1;
		i=1;
		while ( i < 5)
		{
			numero = rnd.nextInt(50) + 1;
			if( ! contem(numeros, numero))
			{
				numeros[i] = numero;
				i++;
			}	
		}
		
		// Gera 2 numeros aleatórios diferentes entre 1 e 12
		estrelas[0] = rnd.nextInt(12) + 1;
		i=1;
		while ( i < 2)
		{
			numero = rnd.nextInt(12) + 1;
			if( ! contem(estrelas, numero))
			{
				estrelas[i] = numero;
				i++;
			}	
		}
		
		Arrays.sort(numeros);
		Arrays.sort(estrelas);
	}
	

	/**
	 * Cria um objecto <code>ApostaEuromilhoes</code> a partir dos números e estrelas passados nos parâmetros.
	 * 
	 * @param numeros array com 5 números inteiros (diferentes e entre 1 e 50)
	 * @param estrelas array com 2 números inteiros (diferentes e entre 1 e 12)
	 * @throws IllegalArgumentException se os números ou as estrelas não respeitarem as condições
	 */
	public ApostaEuromilhoes(int[] numeros, int[] estrelas) {
		
		// Item 49 Check parameters for validity
		
		// Tem de ser 5 numeros
		if( numeros.length != 5)
			throw new IllegalArgumentException("Têm de ser 5 números");
		
		// Os numeros tem de estar no intervalo de 1 a 50
		for( int numero: numeros)
		{
			if( numero<0 || numero>50)
				throw new IllegalArgumentException("Numeros têm de estar entre 1 e 50");
		}
		
		// Nao podem haver numeros iguais
		for ( int i=0; i < 4; i++ )
		{
			for( int j=i+1; j<5; j++)
			{
				if(numeros[i] == numeros[j])
					throw new IllegalArgumentException("Os números têm de ser diferentes");
			}
		}
		
		// Tem de ser 2 estrelas
		if( estrelas.length != 2)
			throw new IllegalArgumentException("Têm de ser 2 estrelas");
		
		// As estrelas tem de estar no intervalo de 1 a 12
		for( int estrela: estrelas)
		{
			if( estrela<0 || estrela>12)
				throw new IllegalArgumentException("Estrelas têm de estar entre 1 e 12");
		}
		
		// Nao podem haver estrelas iguais
		if(estrelas[0] == estrelas[1])
			throw new IllegalArgumentException("As estrelas têm de ser diferentes");

		this.numeros = numeros;
		this.estrelas = estrelas;
		
		Arrays.sort(this.numeros);
		Arrays.sort(this.estrelas);
	}


	/*
	    Method names are written in lowerCamelCase.
		Method names are typically verbs or verb phrases
	 */
	/**
	 * Método de acesso (leitura) aos números da aposta
	 * 
	 * @return array com os números da aposta
	 */
	public int[] getNumeros() {
		// Item 50 Make defensive copies when needed
		int[] numerosCopia = new int[numeros.length];
		
		for( int i=0; i<numeros.length; i++ )
		{
			numerosCopia[i] = numeros[i];
		}
			
		return numerosCopia;
	}


	/**
	 * Método de acesso (leitura) às estrelas da aposta
	 * 
	 * @return array com as estrelas aposta
	 */
	public int[] getEstrelas() {
		// Item 50 Make defensive copies when needed
		
		int[] estrelasCopia = new int[estrelas.length];
		
		for( int i=0; i<estrelas.length; i++ )
		{
			estrelasCopia[i] = estrelas[i];
		}
		
		return estrelasCopia;
	}
	
	/**
	 * Calcula a quantidade de números e estrelas que a aposta acertou 
	 * 
	 * @param Objecto da classe <code>ApostaEuromilhoes</code> com a chave do Euromilhões
	 * @return Objecto da classe  <code>Acertos</code>
	 *  contendo o número de números e estrelas certas
	 */
	public Acertos getAcertos( ApostaEuromilhoes chave)
	{
		int numerosAcertados;
		int estrelasAcertadas;
		
		numerosAcertados = 0;
		estrelasAcertadas = 0;
		
		for(int numero: numeros)
		{
			if( contem(chave.numeros, numero) )
					numerosAcertados++;
		}
		
		for(int estrela: estrelas)
		{
			if( contem(chave.getEstrelas(), estrela) )
					estrelasAcertadas++;
		}
		
		
		return new Acertos(numerosAcertados, estrelasAcertadas);
	}
	
	
	// Item 12: Always override toString
	/** 
	 * Devolve uma representação em string da aposta.
	 * No formato [n1,n2,n3,n4,n5]-[e1,e2], onde ni são os numeros e ei são as estrelas
	 * 
	 * @return Uma string com a representação de uma aposta 
	 */
	@Override public String toString() {
		return Arrays.toString(numeros) + "-" + Arrays.toString(estrelas);
	}	
				
	
	static private boolean contem(int[] numeros, int numero )
	{
		for( int n: numeros)
		{
			if( n == numero) return true;
		}
		
		return false;
	}

}
