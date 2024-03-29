package br.ufpb.ed.heap.view;

import br.ufpb.ed.heap.logic.HeapException;
import br.ufpb.ed.heap.logic.Iterator;
import br.ufpb.ed.heap.logic.MaxBinaryHeap;
import br.ufpb.ed.heap.logic.MinBinaryHeap;
import br.ufpb.ed.util.Util;

public class MainTestMinBinaryHeap {
	
	public static void main(String[] args) {
		
		MinBinaryHeap bh = new MinBinaryHeap();
		
		int aux[] = new int[10];
		
		for (int i = 0; i < aux.length; ++i)
			bh.minHeapInsert((int)(Math.random() * 100));			
		
		System.out.println("HEAP:");
		System.out.println(bh);
		
		int[] aux2 = new int[bh.size()];
		Iterator it = bh.iterator();
		int i = 0;
		while(it.hasNext())
			aux2[i++] = it.next();
		
		System.out.println("\nHeap ordenado, utilizando o m�todo heapSort:");
		System.out.println(Util.toStringArray(MaxBinaryHeap.heapSort(aux2, aux2.length)));
		
		
		System.out.println("\nRetirando o elemento de menor priridade da fila");		
		bh.heapExtractMin();		
		System.out.println(bh);
				
		
		System.out.println("\nDecrementando a prioridade do elemento da posi��o 4 para 0");
		try {
			bh.heapDecreaseKey(4, 0);
		} catch (HeapException e) {
			System.out.println("Erro!");
		}
		System.out.println(bh);
		
		System.out.println("\nInserindo o elemento 50 na heap");
		bh.minHeapInsert(50);
		System.out.println(bh);
		
		System.out.println("\nInserindo o elemento 80 na heap");
		bh.minHeapInsert(80);
		System.out.println(bh);
		
	}


}
