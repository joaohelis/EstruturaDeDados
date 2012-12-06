package br.ufpb.ed.sortingAlgorithms;

import br.ufpb.ed.util.Util;


public class SortingAlgorithms {
	
	public static void bubbleSort(int[] array){
		for(int i = 0; i < array.length -1; ++i)
			for(int j = i+1; j < array.length; ++j)
				if(array[i] > array[j])
					Util.swap(array, i, j);				
	}
	
	public static void selectionSort(int[] array){
		for(int i = 0; i < array.length -1; ++i){	
			int min = i;
			for(int j = i+1; j < array.length; ++j)
				if(array[min] > array[j] )
					min = j;
			Util.swap(array, i, min);
		}		
	}
	
	public static int[] quickSort(int[] v){
		v = v.clone();
		return quickSort(v, 0, v.length-1);
	}
	
	
	private static int[] quickSort(int[] v, int init, int end){
		if (init < end){
			int p = partition(v, init, end);
			quickSort(v, init, p-1);
			quickSort(v, p+1, end);
		}
		return v;
	}
	
//	private static int partition(int[] v, int init, int end){
//		int pivot = v[init],
//			a = init + 1,
//			b = end;
//		while(true){
//			while(a <= end && v[a] <= pivot)
//				++a;
//			while (b >= init && v[b] > pivot)
//				--b;
//			if (a <= b)
//				Util.swap(v, a, b);
//			else{
//				Util.swap(v, init, b);
//				return b;
//			}
//		}
//	}
	
	
	private static int partition(int[] v, int init, int end){ 
		int indexPivot = Util.randint(init, end);
		Util.swap(v, init, indexPivot);
		int pivot = v[init],
			a = init + 1,
			b = end;
		while(true){
			while(a <= end && v[a] <= pivot)
				++a;
			while (b >= init && v[b] >pivot)
				--b;
			if (a <= b)
				Util.swap(v, a, b);
			else{
				Util.swap(v, init, b);
				return b;
			}
		}
	}
	

	
	
//	public static void mergeSort(int[] v){
//	mergeSort(0, v.length, v);
//}
//
//private static void mergeSort(int ini, int post, int[] s){
//	if(ini < post-1){
//		int mid = (ini + post)/2;
//		mergeSort(ini, mid, s);
//		mergeSort(mid, post, s);
//		s = merge(ini, mid, post, s);
//	}				
//}
//
//private static int[] merge(int ini, int mid, int post, int[] s){
//	int[] aux = new int[post - ini];
//	int i = ini,
//	    j = mid,
//	    k = 0;
//	while(i < mid && j < post)
//		if(s[i] <= s[j])
//			aux[k++] = s[i++];
//		else
//			aux[k++] = s[j++];
//	while(i < mid)
//		aux[k++] = s[i++];
//	while(j < post)
//		aux[k++] = s[j++];
//	for (int index = ini; index < post; ++index)
//		s[index] = aux[index - ini];
//	return aux;
//}
	
	
}
