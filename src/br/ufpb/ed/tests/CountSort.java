package br.ufpb.ed.tests;

import br.ufpb.ed.util.Util;

public class CountSort {

	    public static int[] ordenar(int[] a, int k){
	        
//	    	O valor k, diz respeito ao maior valor contido no vetor
//	    	EX: se k for iqual a 10, entao teremos que ter os indices
//	    	do vetor de 0 a 10.
//	    	Ao inicializar-mos um vetor de tamanho k, no caso do exemplo acima 10
//	    	então seus indices vao estar entre 0 e 9, no entanto queremos que os
//	    	indices estejam entre 0 e 10, logo devemos inicializar o vetor com
//	    	k + 1 elementos.
	    	
	        //int[] c = new int[k]; // 
	    	
	    	int[] c = new int[k + 1]; 
	    	
	        int[] b = new int[a.length];
	        
//	        for(int i = 0; i < k; i++){ Esse for não é necessário, pq os indices de um vetor de inteiros em java
//	            c[i] = 0;               por default sao inicializados em 0
//	        }
	        
	        //for(int j = 1; j < a.length; j++){ devemos iniciar o i com 0
		    for(int j = 0; j < a.length; j++){
	        	c[a[j]] = c[a[j]] + 1;
	        }
	        
	        //for(int i = 1; i < k - 1; i++){
		    for(int i = 1; i < c.length; i++){
	            c[i] = c[i] + c[i-1];
	        }
	        
	        for(int j = a.length - 1; j >= 0; j--){
	            //b[c[a[j]]] = a[j];
	        	b[c[a[j]]-1] = a[j];
	            c[a[j]] = c[a[j]] - 1;
	        }
	        
	        return b;
	}

	
	public static void main(String[] args) {
		
		int[] t = {2,5,3,0,2,3,0,3};
        int[] vetor = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9, 3, 2};
        int[] ordenado = {9,8,7,6,5,4,3,2,1};
        
        System.out.println(Util.toStringArray(ordenar(t, 5)));
        System.out.println(Util.toStringArray(ordenar(vetor,9)));
		
	}

}
