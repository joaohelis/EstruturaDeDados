package br.ufpb.ed.linearSorting;

import br.ufpb.ed.util.Util;

public class RadixSort {
	
	public static int[] radixSort(int[] a, int d){
		a = a.clone();
		for(int i = d-1; i >= 0 ; --i)
			a = countSort(a, 9, i, d);			
		return a;
	}
	
	public static int[] radixSort(int[] a){
		a = a.clone();
		int d = lengthDigitMax(a);		
		for(int i = d-1; i >= 0 ; --i)
			a = countSort(a, 9, i, d);			
		return a;
	}
	
	private static int lengthDigitMax(int[] a){
		int lengthMaxDigit = Integer.MIN_VALUE;
		for(int i: a)
			lengthMaxDigit = Math.max(Integer.toString(i).length(), lengthMaxDigit);
		return lengthMaxDigit;
	}
	
	private static String formatString(int num, int d){
		String aux = Integer.toString(num);	
		while(aux.length() < d)
			aux = "0" + aux;
		return aux;
	}
	
	private static int[] countSort(int[] a, int k, int pos, int d){
		int[] b = new int[a.length]; // Vetor de saida.
		
		int[] count = new int[k+1]; // Vetor para contagem das vezes em que a chave x aparece no vetor
		
		for (int i = 0; i < a.length; ++i) // Incrementando o contador
			count[Integer.parseInt(Character.toString(formatString(a[i],d).charAt(pos)))] += 1;
		
		for (int i = 1; i < count.length; ++i) // Acumulando o contador
			count[i] += count[i-1];	
		
		for(int i = a.length-1; i >= 0; --i)// Colocando o elemento do vetor de entrada em um vetor auxiliar na posição
			b[(count[Integer.parseInt(Character.toString(formatString(a[i],d).charAt(pos)))]--)-1] = a[i];      // em que todos os seus anteriores são menores ou iguais a ele		
		return b;
	}
	
	public static void main(String[] args) {
		int[] a = {13, 50, 34, 21, 12, 100, 1500, 1,156};
		System.out.println(Util.toStringArray(radixSort(a)));
	}

}
