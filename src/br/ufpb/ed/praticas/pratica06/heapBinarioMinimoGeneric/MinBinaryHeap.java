package br.ufpb.ed.praticas.pratica06.heapBinarioMinimoGeneric;

import br.ufpb.ed.heap.logic.LotedHeapException;
import br.ufpb.ed.praticas.pratica06.Vertice;
import br.ufpb.ed.util.Util;
import br.ufpb.ed.util.Util.actionVector;

public class MinBinaryHeap<T extends HeapElement> extends BinaryHeap<T>{
	
	public MinBinaryHeap(int length){
		super(length);
	}
	
	public MinBinaryHeap(){
		super();
	}
	
	public MinBinaryHeap(T[] v){
		super(v);
		buildMinHeap(this.heap, size);
	}
	
	public int find(T v){
		for(int i = 0; i < this.size; ++i)
			if (this.heap[i].equals(v))
				return i;
		return -1;
	}
	
	private void minHeapFy(HeapElement[] v, int i, int size){
		int l = childrenLeft(i),
		    r = childrenRigth(i);
		int menor = (l < size && v[l].compareTo(v[i]) < 0)? l: i;
		if (r < size && v[r].compareTo(v[menor]) < 0) 
			menor = r;
		if (menor != i){
			HeapElement aux = v[i];
			v[i] = v[menor];
			v[menor] = aux;			
			minHeapFy(v, menor, size);
		}	
	}
	
	public T[] heapSort(T[] v, int size){
		v = v.clone();
		buildMinHeap(v, size);		
		for(int i = size -1; i >= 0; --i){
			T aux = v[0];
			v[0] = v[i];
			v[i] = aux;						
			minHeapFy(v, 0, --size);
		}
		return v;
	}
	
	private void buildMinHeap(HeapElement[] A, int size){
		for(int i = (int)((size-2)/2); i >= 0; --i)
			minHeapFy(A, i, size);
	}
	
	// Algoritimos para fila de prioridade
	
	public HeapElement heapMinimum(){
		if (isEmpty())
			throw new HeapEmptyException("Empty Heap");
		return heap[0];
	}
	
	public T heapExtractMin(){
		if(isEmpty())
			throw new HeapEmptyException("The heap is empty!");		
		T min = (T)heap[0];
		heap[0] = heap[size -1];		
		minHeapFy(heap, 0, --size);
		if (increaseOrDecrease != 0 && size % increaseOrDecrease == 0)
			heap = increaseDecreaseVector(heap, this.increaseOrDecrease, Util.actionVector.decrease);
		return min;
	}
	
	public void heapDecreaseKey(int i, Integer key) throws HeapException{
		if(heap[i].getComparator() < key)		
			throw new HeapException("the key is more (maior) than the previous value");	
		heap[i].setComparator(key);		
		while(i > 0 && heap[parent(i)].compareTo(heap[i]) > 0){
			HeapElement aux = this.heap[i];
			heap[i] = heap[parent(i)];
			heap[parent(i)] = aux; 
			i = parent(i);
		}
	}
	
	public void minHeapInsert(int key){
		if(increaseOrDecrease != 0 && size +1 > heap.length)
			heap = increaseDecreaseVector(heap, this.increaseOrDecrease, Util.actionVector.increase);
		else if(increaseOrDecrease == 0 && size +1 > heap.length)
			throw new LotedHeapException();
		heap[size].setComparator(Integer.MAX_VALUE);
		try {
			heapDecreaseKey(size, key);
			++size;
		} catch (HeapException e) {
			e.printStackTrace();
		}
	}
	
	private HeapElement[] increaseDecreaseVector(HeapElement[] vector, int qtde, actionVector action){
		int length = vector.length;
		if(action == actionVector.decrease){
			length -= qtde;
			qtde = -qtde;
		}	
		HeapElement[] aux = new HeapElement[vector.length + qtde];
		for(int i = 0; i < length; ++i)
			aux[i] = vector[i];
		return aux;
	}
}
