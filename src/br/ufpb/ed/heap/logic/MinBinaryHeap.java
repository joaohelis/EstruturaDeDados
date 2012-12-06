package br.ufpb.ed.heap.logic;

import br.ufpb.ed.util.Util;

public class MinBinaryHeap extends BinaryHeap{
	
	public MinBinaryHeap(int length){
		super(length);
	}
	
	public MinBinaryHeap(){
		super();
	}
	
	private static void minHeapFy(int[] v, int i, int size){
		int l = childrenLeft(i),
		    r = childrenRigth(i);
		int menor = (l < size && v[l] < v[i])? l: i;
		if (r < size && v[r] < v[menor]) 
			menor = r;
		if (menor != i){
			Util.swap(v, i, menor);
			minHeapFy(v, menor, size);
		}	
	}
	
	public static int[] heapSort(int[] v, int size){
		v = v.clone();
		buildMaxHeap(v, size);		
		for(int i = size -1; i >= 0; --i){
			Util.swap(v, 0, i);			
			minHeapFy(v, 0, --size);
		}
		return v;
	}
	
	private static void buildMaxHeap(int[] A, int size){
		for(int i = (int)((size-2)/2); i >= 0; --i)
			minHeapFy(A, i, size);
	}
	
	// Algoritimos para fila de prioridade
	
	public int heapMinimum(){
		return heap[0];
	}
	
	public int heapExtractMin(){
		if(isEmpty())
			throw new HeapEmptyException("The heap is empty!");		
		int min = heap[0];
		heap[0] = heap[size -1];		
		minHeapFy(heap, 0, --size);
		if (increaseOrDecrease != 0 && size % increaseOrDecrease == 0)
			heap = Util.increaseDecreaseVector(heap, this.increaseOrDecrease, Util.actionVector.decrease);
		return min;
	}
	
	public void heapDecreaseKey(int i, int key) throws HeapException{
		if(key >= heap[i])
			throw new HeapException("the key is less or equals than the previous value");
		heap[i] = key;		
		while(i > 0 && heap[parent(i)] > heap[i]){
			Util.swap(heap, i, parent(i));
			i = parent(i);
		}
	}
	
	public void minHeapInsert(int key){
		if(increaseOrDecrease != 0 && size +1 > heap.length)
			heap = Util.increaseDecreaseVector(heap, this.increaseOrDecrease, Util.actionVector.increase);
		else if(increaseOrDecrease == 0 && size +1 > heap.length)
			throw new LotedHeapException();
		heap[size] = Integer.MAX_VALUE;
		try {
			heapDecreaseKey(size, key);
			++size;
		} catch (HeapException e) {
			e.printStackTrace();
		}
	}


}
