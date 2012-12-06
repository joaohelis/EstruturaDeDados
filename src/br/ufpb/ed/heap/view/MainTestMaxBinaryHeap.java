package br.ufpb.ed.heap.view;

import br.ufpb.ed.heap.logic.MaxBinaryHeap;
import br.ufpb.ed.heap.logic.HeapException;
import br.ufpb.ed.heap.logic.Iterator;
import br.ufpb.ed.util.Util;

public class MainTestMaxBinaryHeap {
	
	public static void main(String[] args) {
		
		MaxBinaryHeap bh = new MaxBinaryHeap();
		
		int aux[] = new int[10];
		
		for (int i = 0; i < aux.length; ++i)
			bh.maxHeapInsert((int)(Math.random() * 100));			
		
		System.out.println("HEAP:");
		System.out.println(bh);
		
		int[] aux2 = new int[bh.size()];
		Iterator it = bh.iterator();
		int i = 0;
		while(it.hasNext())
			aux2[i++] = it.next();
		
		System.out.println("\nHeap ordenado, utilizando o método heapSort:");
		System.out.println(Util.toStringArray(MaxBinaryHeap.heapSort(aux2, aux2.length)));
		
		
		System.out.println("\nRetirando o elemento de maior priridade da fila");		
		bh.heapExtractMax();		
		System.out.println(bh);
				
		
		System.out.println("\nIncrementando a prioridade do elemento da posição 4 para 100");
		try {
			bh.heapIncreaseKey(4, 100);
		} catch (HeapException e) {
			System.out.println("Erro!");
		}
		System.out.println(bh);
		
		System.out.println("\nInserindo o elemento 50 na heap");
		bh.maxHeapInsert(50);
		System.out.println(bh);
		
		System.out.println("\nInserindo o elemento 80 na heap");
		bh.maxHeapInsert(80);
		System.out.println(bh);
		
	}

}
