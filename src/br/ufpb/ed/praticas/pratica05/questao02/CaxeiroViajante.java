package br.ufpb.ed.praticas.pratica05.questao02;

import java.util.ArrayList;
import java.util.List;

public class CaxeiroViajante {
	
	private int[][]  matrizDistanciaCidades;
	private int cidadeInicial;
	private int distancia;
	
	public CaxeiroViajante(int[][] matrizDistanciaCidades){
		this(matrizDistanciaCidades, 0);
	}
	
	public CaxeiroViajante(int[][] matrizDistanciaCidades, int cidadeIncial){
		this.cidadeInicial = cidadeIncial;
		this.matrizDistanciaCidades = matrizDistanciaCidades;
		this.distancia = 0;
	}
	
	public int[] tracarRota(EscolhaCidadeStrategy estrategia){
		int qtdeCidades = matrizDistanciaCidades.length; // Armazena a quantidade de cidades a percorrer
		int[] controladorDeVisita = new int[qtdeCidades]; // Controla se a cidade foi visitada ou não
		List<Integer> controladorDeVisitaList = new ArrayList<Integer>(qtdeCidades);
		for(int i = 0; i < qtdeCidades; ++i)
			if(i != this.cidadeInicial)
				controladorDeVisitaList.add(i);
		int[] rota = new int[matrizDistanciaCidades.length + 1]; // indica a ordem das visitas

		int currentCity = this.cidadeInicial;
		int indiceOrdemVisita = 0; // Controla o indice das ordens de visita

		controladorDeVisita[currentCity] = 1; 
		rota[indiceOrdemVisita++] = currentCity;
		
		while(indiceOrdemVisita < qtdeCidades){
			Integer indexNextCity = estrategia.getIndexNextCity(controladorDeVisita, matrizDistanciaCidades, currentCity, controladorDeVisitaList);
			rota[indiceOrdemVisita++] = indexNextCity;
			// Adiciona o número um no indice correpondente a cidade escolhida, desta forma informando que esta já foi percorrida
			controladorDeVisita[indexNextCity] = 1;
			// Retira a cidade selecionada da lista, desta forma informando que já foi visitada
			controladorDeVisitaList.remove(indexNextCity);
			currentCity = indexNextCity;
		}
		
		rota[indiceOrdemVisita++] = this.cidadeInicial; 
		this.distancia = calcularDistancia(rota, matrizDistanciaCidades);
		return rota;
	}
	
	private int calcularDistancia(int[] ordemVisita, int[][] matriz){
		int distancia = 0;
		for(int i = 0; i < ordemVisita.length -1; ++i)
			distancia += matriz[ordemVisita[i]][ordemVisita[i+1]];
		return distancia;
	}
	
	public int getCidadeInicial(){
		return this.cidadeInicial;
	}
	
	public int getDistanciaPercurso(){
		return distancia;
	}

}
