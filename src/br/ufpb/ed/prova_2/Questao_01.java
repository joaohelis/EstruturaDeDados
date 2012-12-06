package br.ufpb.ed.prova_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Questao_01 {
	
	public static void main(String[] args) {
		
		int[][] matrizDistancia = null;
		
		try {
			matrizDistancia = lerArquivo("distancias.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Deu pau no arquivo!");
			return;
		}
		
		List<Integer> rota = tracarRota(matrizDistancia, 0, 7);
		
		System.out.println("Rota a percorrer: \n");
		imprimirRota(rota);
		
		System.out.println("\nDistancia do percurso: "+ccalcularDistancia((ArrayList<Integer>) rota, matrizDistancia));
	}
	
	public static List<Integer> tracarRota(int[][] matrizDistanciaCidades, int cidadeInicial, int cidadeFinal){

		List<Integer>[] listaAdjacencia = new List[matrizDistanciaCidades.length];
		
		for(int i = 0; i < matrizDistanciaCidades.length; ++i)
			for(int j = 0; j < matrizDistanciaCidades[i].length; ++j)
				if (matrizDistanciaCidades[i][j] > 0){
					if (listaAdjacencia[i] == null)
						listaAdjacencia[i] = new LinkedList<Integer>();
					listaAdjacencia[i].add(j);
				}
		
		int[] controladorDeVisita = new int[matrizDistanciaCidades.length]; // Controla se a cidade foi visitada ou não
		List<Integer> rota = new ArrayList<Integer>();
		int currentCity = cidadeInicial;
		
		do{
			rota.add(currentCity);
			controladorDeVisita[currentCity] = 1;
			int indiceMenorDistancia =  -1; 
			int menorDistancia = Integer.MAX_VALUE;
			for(int cidadeVizinha: listaAdjacencia[currentCity])
				if (controladorDeVisita[cidadeVizinha] == 0 && matrizDistanciaCidades[currentCity][cidadeVizinha] < menorDistancia){
					menorDistancia = matrizDistanciaCidades[currentCity][cidadeVizinha];
					indiceMenorDistancia = cidadeVizinha;
				}
			if (indiceMenorDistancia == -1) throw new RuntimeException("Impossivel achar percurso");
			currentCity = indiceMenorDistancia;
		}while(currentCity != cidadeFinal);
		rota.add(currentCity);
		return rota;
	}
	
	private static int ccalcularDistancia(ArrayList<Integer> rota, int[][] matriz){ // Se for um linkedlist o método é ineficiente
		int distancia = 0;
		for(int i = 0; i < rota.size() -1; ++i)
			distancia += matriz[rota.get(i)][rota.get(i+1)];
		return distancia;
	}
	
	private static void imprimirRota(List<Integer> rota){
		for(int i: rota)
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
	
}
