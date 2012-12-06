package br.ufpb.ed.linearSorting;

import br.ufpb.ed.util.Util;

public class CountingSort{
	
	public static int[] countSort(int[] a){
		int minValue = Integer.MAX_VALUE, maxValue = Integer.MIN_VALUE;
		for(int i: a){
			minValue = Math.min(minValue, i);
			maxValue = Math.max(maxValue, i);
		}
		return countSort(a, minValue, maxValue);
	}
	
	public static int[] countSort(int[] a, int minValue, int maxValue){
		
		int k = maxValue - minValue;
		
		int[] b = new int[a.length]; // Vetor de saida.
		
		int[] count = new int[k+1]; // Vetor para contagem das vezes em que a chave x aparece no vetor
		
		for (int i = 0; i < a.length; ++i) // Incrementando o contador
			count[a[i] - minValue] += 1;
		
		for (int i = 1; i < count.length; ++i) // Acumulando o contador
			count[i] += count[i-1];	
		
		for(int i = a.length-1; i >= 0; --i)             // Colocando o elemento do vetor de entrada em um vetor de saida, na posição
			b[(count[a[i] - minValue]--)-1] = a[i];      // em que todos os seus anteriores são menores ou iguais a ele		
		return b;
	}
	
	public static int[] countSort(int[] a, int k){
		int[] b = new int[a.length]; // Vetor de saida.
		
		int[] count = new int[k+1]; // Vetor para contagem das vezes em que a chave xi aparece no vetor
		
		for (int i = 0; i < a.length; ++i) // Incrementando o contador
			count[a[i]] += 1;              // O de contagem é incrementado na posição da chave i no vetor a ( vetor de entrada )
		
		for (int i = 1; i < count.length; ++i) // Acumulando o contador
			count[i] += count[i-1];	           
		
		for(int i = a.length -1; i >= 0; --i) // Colocando o elemento do vetor de entrada em um vetor auxiliar na posição
			b[(count[a[i]]--)-1] = a[i];      // em que todos os seus anteriores são menores ou iguais a ele		
		return b;
	}
	
	public static void main(String[] args) {
		
		int[] a = new int[10000000];
		
		for(int i = 0; i < a.length; ++i)
			a[i] = Util.randint(1000, 10000);
		
		long t1 = System.currentTimeMillis();
		int[] aux = countSort(a, 1000, 10000);
		long t2 = System.currentTimeMillis();
		
		System.out.println("\nTempo de execução dos valores com intervalo definido intervalo: "+ ((t2 - t1)));
		
		long t1_2 = System.currentTimeMillis();
		int[] aux_2 = countSort(a, 10000);
		long t2_2 = System.currentTimeMillis();
		
		System.out.println("\nTempo de execução dos valores sem intervalo definido: "+ ((t2_2 - t1_2)));
		
//		for (int i = 0; i < aux.length; ++i)
//			System.out.print(aux[i] +((i != aux.length -1)?" ,": ""));
		
//		System.out.println("Array desordenado: "+Util.toStringArray(a));
//		System.out.println("Array ordenado   : "+Util.toStringArray(countSort(aux)));
		
	}
	
}
