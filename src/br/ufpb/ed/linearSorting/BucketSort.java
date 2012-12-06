package br.ufpb.ed.linearSorting;

import br.ufpb.ed.LinkedList.LinkedList;
import br.ufpb.ed.util.Iterator;

public class BucketSort {
	
	public static float[] bucketSort(float[] v){
		if(!validateInputMethodBucketSort(v))
			throw new  BucketSortException("input invalid");
		float[] b = new float[v.length];
		LinkedList<Float>[] aux = new LinkedList[10];
		for(int i = 0; i < v.length; ++i){
			if(aux[(int)(v[i] * 10)] == null)
				aux[(int)(v[i] * 10)] = new LinkedList<Float>();
			aux[(int)(v[i] * 10)].addSorting(v[i]);
		}
		
		int cont = 0;
		for(int i = 0; i < aux.length; ++i)
			if (aux[i] != null){
				Iterator<Float> it = aux[i].iterator();
				while(it.hasNext())
					b[cont++] = it.next().getValue();
			}
		return b;
	}
	
	private static boolean validateInputMethodBucketSort(float[] v){
		for(float i: v)
			if (i < 0 || i >= 1)
				return false;
		return true;
	}
	
	public static void main(String[] args) {
		
		float[] aux = {0.5f, 0.55f, 0.10f, 0.22f, 0.01f, 0.67f};
		
		for(float i: aux)
			System.out.print(i + ", ");
		System.out.println();
				
		float[] aux2 = bucketSort(aux);
		
		for(float i: aux2)
			System.out.print(i + ", ");
		System.out.println();
		
	}

}
