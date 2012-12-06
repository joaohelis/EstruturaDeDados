package br.ufpb.ed.praticas.pratica05.questao02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Pratica05 {
	
	public static void main(String[] args) {
		
		int[][] matrizDistancia;
		
		try {
			matrizDistancia = lerArquivo("distancias_177.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Deu pau no arquivo");
			return;
		}
		
		CaxeiroViajante caxeiroViajante;
		EscolhaCidadeStrategy estrategia = null;
		
		System.out.println("Matriz de distancia das cidades:\n");
		imprimirMatriz(matrizDistancia);
		
		while(true){
			int opcao;
			String msgErro = "Informe apenas dígitos!";
			
			try{
				System.out.println("\nInforme o indice da cidade Inicial do percurso do caxeiro: ");
				System.out.println("\nOpcoes de indices: \n");
				for(int i = 0; i < matrizDistancia.length; ++i)
					System.out.println("Opcao: "+i);
				int cidadeInicial = Integer.parseInt(new Scanner(System.in).next());
				if (cidadeInicial < 0 || cidadeInicial > matrizDistancia.length-1){
					msgErro = "Digite dígios entre o intervalo especificado";
					throw new Exception();
				}
				caxeiroViajante = new CaxeiroViajante(matrizDistancia, cidadeInicial);
				System.out.println("\nInforme uma estratégia para traçar rota do caxeiro");
				System.out.println("\n1 - Cidade mais próxima");
				System.out.println("2 - Cidade Aleatória");
				opcao = Integer.parseInt(new Scanner(System.in).next());
				if (opcao < 1 || opcao > 2){
					msgErro = "Digite dígios entre o intervalo especificado";
					throw new Exception();
				}
			}catch(Exception e){
				System.out.println("\n>>> "+msgErro+" <<<");
				continue;
			}
			
			switch(opcao){
			case 1:
				estrategia = new CidadeMaisProximaStrategy();
				break;
			case 2:
				estrategia = new CidadeAleatoriaStrategy();
				break;
			}
			break;
		}
		
		System.out.println(">>> CAXEIRO VIAJANTE <<<");
		System.out.println("\nCidade de partida: "+caxeiroViajante.getCidadeInicial());
		int[] rota = caxeiroViajante.tracarRota(estrategia);
		System.out.println("\nRota: ");
		imprimirVetor(rota);
		System.out.println("\nDistancia a percorrer: "+caxeiroViajante.getDistanciaPercurso());
	}
	
	private static void imprimirVetor(int[] v){
		for(int i: v)
			System.out.print("|"+i);
		System.out.print("|\n");
	}
	
	private static int[][] lerArquivo(String nomeArquivo) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(nomeArquivo));;
		int qtde = sc.nextInt();
		int[][] matrizDistancia = new int[qtde][qtde]; 
		for (int i = 0; i < qtde; ++i)
			for(int j = 0; j < qtde; ++j)
				matrizDistancia[i][j] = sc.nextInt();
		return matrizDistancia;
	}
	
	private static void imprimirMatriz(int[][] matriz){
		for (int i = 0; i < matriz.length; ++i){
			for(int j = 0; j < matriz.length; ++j)
				System.out.print(matriz[i][j]+" ");
			System.out.println();
		}
	}

}
