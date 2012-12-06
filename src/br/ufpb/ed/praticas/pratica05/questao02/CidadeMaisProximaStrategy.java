package br.ufpb.ed.praticas.pratica05.questao02;

import java.util.List;

public class CidadeMaisProximaStrategy implements EscolhaCidadeStrategy {

	@Override
	public int getIndexNextCity(int[] controladorDeVisita,
			int[][] matrizDistanciaCidades, int currentCity, List<Integer> controladorDeVisitaList) {
		int indiceMenorDistancia =  -1; 
		int menorDistancia = Integer.MAX_VALUE;
		// Dado uma cidade procura a menor distancia entre esta cidade e todas as outras
		for(int j = 0; j < matrizDistanciaCidades[currentCity].length; ++j){
			if (controladorDeVisita[j] == 0  && matrizDistanciaCidades[currentCity][j] > 0 && matrizDistanciaCidades[currentCity][j] < menorDistancia){
				menorDistancia = matrizDistanciaCidades[currentCity][j];
				indiceMenorDistancia = j;
			}
		}
		return indiceMenorDistancia;
	}
	
	@Override
	public String toString() {
		return "Cidade mais próxima";
	}
	

}
