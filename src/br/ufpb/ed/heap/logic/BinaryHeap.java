package br.ufpb.ed.heap.logic;

public class BinaryHeap {
	
	protected int[] heap;	
	protected int size,
				increaseOrDecrease; // Representa o valor do incremento ou decremento do heap, pois o heap eh dinamico
	
	public BinaryHeap(int length){
		heap = new int[length];		
		size = 0;
		increaseOrDecrease = 0;
	}
	
	public BinaryHeap(){
		this(100);	
		this.increaseOrDecrease = 100;
	}
	
	protected static int parent(int i){
		return (int)((i-1)/2);		
	}
	
	protected static int childrenLeft(int i){
		return 2 * i + 1;
	}
	
	protected static int childrenRigth(int i){
		return 2 * i + 2;
	}
	
	public Iterator iterator(){
		return new HeapIterator(heap, size);
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
	
	public String toString(){
		String aux = "";
		Iterator it = iterator();
		if(it.hasNext()){
			while(it.hasNext())
				aux += it.next()+", ";
			aux = aux.substring(0, aux.length()-2);
		}
		return aux;
	}

}
