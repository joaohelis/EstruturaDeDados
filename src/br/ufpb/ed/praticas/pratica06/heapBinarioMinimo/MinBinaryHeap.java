package br.ufpb.ed.praticas.pratica06.heapBinarioMinimo;

import br.ufpb.ed.heap.logic.LotedHeapException;
import br.ufpb.ed.praticas.pratica06.Vertice;
import br.ufpb.ed.util.Util;
import br.ufpb.ed.util.Util.actionVector;

public class MinBinaryHeap extends BinaryHeap{
	
	public MinBinaryHeap(int length){
		super(length);
	}
	
	public MinBinaryHeap(){
		super();
	}
	
	public MinBinaryHeap(Vertice[] v){
		super(v);
		buildMinHeap(this.heap, size);
	}
	
	public int find(Vertice v){
		for(int i = 0; i < this.size; ++i)
			if (this.heap[i].equals(v))
				return i;
		return -1;
	}
	
	private static void minHeapFy(Vertice[] v, int i, int size){
		int l = childrenLeft(i),
		    r = childrenRigth(i);
		int menor = (l < size && v[l].getChave() < v[i].getChave())? l: i;
		if (r < size && v[r].getChave() < v[menor].getChave()) 
			menor = r;
		if (menor != i){
			Vertice aux = v[i];
			v[i] = v[menor];
			v[menor] = aux;			
			minHeapFy(v, menor, size);
		}	
	}
	
	public static Vertice[] heapSort(Vertice[] v, int size){
		v = v.clone();
		buildMinHeap(v, size);		
		for(int i = size -1; i >= 0; --i){
			Vertice aux = v[0];
			v[0] = v[i];
			v[i] = aux;						
			minHeapFy(v, 0, --size);
		}
		return v;
	}
	
	public void buildMinHeap(){
		buildMinHeap(this.heap, size);
	}
	
	private static void buildMinHeap(Vertice[] A, int size){
		for(int i = (int)((size-2)/2); i >= 0; --i)
			minHeapFy(A, i, size);
	}
	
	// Algoritimos para fila de prioridade
	
	public Vertice heapMinimum(){
		return heap[0];
	}
	
	public Vertice heapExtractMin(){
		if(isEmpty())
			throw new HeapEmptyException("The heap is empty!");		
		Vertice min = heap[0];
		heap[0] = heap[size -1];		
		minHeapFy(heap, 0, --size);
		if (increaseOrDecrease != 0 && size % increaseOrDecrease == 0)
			heap = increaseDecreaseVector(heap, this.increaseOrDecrease, Util.actionVector.decrease);
		return min;
	}
	
	public void heapDecreaseKey(int i, int key) throws HeapException{
		if(key > heap[i].getChave())		
			throw new HeapException("the key is more (maior) than the previous value");	
		heap[i].setChave(key);		
		while(i > 0 && heap[parent(i)].getChave() > heap[i].getChave()){
			Vertice aux = this.heap[i];
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
		heap[size].setChave(Integer.MAX_VALUE);
		try {
			heapDecreaseKey(size, key);
			++size;
		} catch (HeapException e) {
			e.printStackTrace();
		}
	}
	
	public static Vertice[] increaseDecreaseVector(Vertice[] vector, int qtde, actionVector action){
		int length = vector.length;
		if(action == actionVector.decrease){
			length -= qtde;
			qtde = -qtde;
		}	
		Vertice[] aux = new Vertice[vector.length + qtde];
		for(int i = 0; i < length; ++i)
			aux[i] = vector[i];
		return aux;
	}
}
