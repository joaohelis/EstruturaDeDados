package br.ufpb.ed.LinkedList;

import br.ufpb.ed.heap.logic.IteratorException;
import br.ufpb.ed.util.Iterator;

public class LinkedListIterator<E> implements Iterator<E>{

	private Node<E> head = null;
	
	public LinkedListIterator(Node<E> node){
		this.head = node;
	}
	
	public Node<E> next() {
		if(!hasNext())
			throw new IteratorException("No has next");
		Node<E> aux = head;
		head = head.getProx();
		return aux;
	}

	public boolean hasNext() {
		return (head != null);
	}

}
