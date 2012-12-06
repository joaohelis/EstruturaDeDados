package br.ufpb.ed.heap.logic;

import br.ufpb.ed.util.Util;

public class MaxBinaryHeap extends BinaryHeap{
	
	public MaxBinaryHeap(int length){
		super(length);
	}
	
	public MaxBinaryHeap(){
		super();
	}
	
	private static void maxHeapFy(int[] v, int i, int size){
		int l = childrenLeft(i),
		    r = childrenRigth(i);
		int maior = (l < size && v[l] > v[i])? l: i;
		if (r < size && v[r] > v[maior]) 
			maior = r;
		if (maior != i){
			Util.swap(v, i, maior);
			maxHeapFy(v, maior, size);
		}	
	}
	
	public static int[] heapSort(int[] v, int size){
		v = v.clone();
		buildMaxHeap(v, size);		
		for(int i = size -1; i >= 0; --i){
			Util.swap(v, 0, i);			
			maxHeapFy(v, 0, --size);
		}
		return v;
	}
	
	private static void buildMaxHeap(int[] A, int size){
		for(int i = (int)((size-2)/2); i >= 0; --i)
			maxHeapFy(A, i, size);
	}
	
	
	// Algoritimos para fila de prioridade
	
	public int heapMaximun(){
		return heap[0];
	}
	
	public int heapExtractMax(){
		if(isEmpty())
			throw new HeapEmptyException("The heap is empty!");		
		int max = heap[0];
		heap[0] = heap[size -1];		
		maxHeapFy(heap, 0, --size);
		if (increaseOrDecrease != 0 && size % increaseOrDecrease == 0)
			heap = Util.increaseDecreaseVector(heap, this.increaseOrDecrease, Util.actionVector.decrease);
		return max;
	}
	
	public void heapIncreaseKey(int i, int key) throws HeapException{
		if(key <= heap[i])
			throw new HeapException("the key is smaller or equals than the previous value");
		heap[i] = key;		
		while(i > 0 && heap[parent(i)] < heap[i]){
			Util.swap(heap, i, parent(i));
			i = parent(i);
		}
	}
	
	public void maxHeapInsert(int key){
		if(increaseOrDecrease != 0 && size +1 > heap.length)
			heap = Util.increaseDecreaseVector(heap, this.increaseOrDecrease, Util.actionVector.increase);
		else if(increaseOrDecrease == 0 && size +1 > heap.length)
			throw new LotedHeapException();
		heap[size] = Integer.MIN_VALUE;
		try {
			heapIncreaseKey(size, key);
			++size;
		} catch (HeapException e) {
			e.printStackTrace();
		}
	}
		
}
