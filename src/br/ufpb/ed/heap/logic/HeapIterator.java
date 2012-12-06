package br.ufpb.ed.heap.logic;

public class HeapIterator implements Iterator {
	
	private int[] heap;
	
	private int size,
				ponteiro = -1;
	
	public HeapIterator(int[] heap, int size){
		this.heap = heap;
		this.size = size;
	}

	@Override
	public int next() {
		if(!hasNext())
			throw new IteratorException("No has Next");
		return heap[++ponteiro];
	}

	@Override
	public boolean hasNext() { 
		return (ponteiro < size-1 && size > 0);
	}

}
