package br.ufpb.ed.praticas.pratica06.heapBinarioMinimo;

import br.ufpb.ed.praticas.pratica06.Vertice;

public class HeapIterator implements Iterator {
	
	private Vertice[] heap;
	
	private int size,
				ponteiro = -1;
	
	public HeapIterator(Vertice[] heap, int size){
		this.heap = heap;
		this.size = size;
	}

	@Override
	public Vertice next() {
		if(!hasNext())
			throw new IteratorException("No has Next");
		return heap[++ponteiro];
	}

	@Override
	public boolean hasNext() { 
		return (ponteiro < size-1 && size > 0);
	}

}
