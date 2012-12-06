package br.ufpb.ed.praticas.pratica04.divideAndConquer;

import br.ufpb.ed.sortingAlgorithms.SortingAlgorithms;
import br.ufpb.ed.util.Util;

public class Main {
	
	public static void main(String[] args) {
		
		int[] v = new int[5];
		int minValue = 0,
			maxValue = 20;
		
		for(int i = 0; i < v.length; ++i)
			v[i] = Util.randint(0, 20);
		
		int chaveBusca = v[(int)(Math.random() * v.length)];
		
		System.out.println("V = "+new Main().toStringVector(v, maxValue)+" Length = "+v.length);
		System.out.println("\nBuscando a chave: "+chaveBusca+" no vetor V");
		System.out.println("Indice correspondente a chave: "+AlgorithimsDivideAndConquer.find(v, chaveBusca));
		System.out.println("O menor valor do vetor V é: "+AlgorithimsDivideAndConquer.minValue(v));
		System.out.println();
		System.out.println("Vetor desordenado: "+new Main().toStringVector(v, maxValue));
		System.out.println("Vetor ordenado   : "+new Main().toStringVector(SortingAlgorithms.quickSort(v),maxValue));
		
		
	}
	
	private String toStringVector(int[] v, int maxValue){
		String aux = "";
		for(int i = 0; i < v.length; ++i)
			aux += "|"+autoCompletar(v[i], Integer.toString(maxValue).length());
		aux += "|";
		return aux;
	}
	
	private String autoCompletar(int num, int qtdeMaxDigitos){
		String numAux = Integer.toString(num);
		while(numAux.length() < qtdeMaxDigitos)
			numAux = "0"+numAux;
		return numAux;
	}

}
