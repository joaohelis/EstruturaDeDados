package br.ufpb.ed.praticas.pratica05.questao02;

import java.util.List;

public class CidadeAleatoriaStrategy implements EscolhaCidadeStrategy {

	@Override
	public int getIndexNextCity(int[] controladorDeVisita, int[][] matrizDistanciaCidades, int currentCity, List<Integer> controladorDeVisitaList) {
		// Escolhe uma cidade dentre as cidades não visistadas
		Integer randomCity = controladorDeVisitaList.get((int)(Math.random() * controladorDeVisitaList.size()));		
		return randomCity;
	}

	@Override
	public String toString() {
		return "Cidade aleatória";
	}
	
}
