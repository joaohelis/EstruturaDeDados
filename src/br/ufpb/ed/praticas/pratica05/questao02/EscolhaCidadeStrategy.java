package br.ufpb.ed.praticas.pratica05.questao02;

import java.util.List;

public interface EscolhaCidadeStrategy {
	
	int getIndexNextCity(int[] controladorDeVisita, int[][] matrizDistanciaCidades, int currentCity, List<Integer> controladorDeVisitaList);
	
}
