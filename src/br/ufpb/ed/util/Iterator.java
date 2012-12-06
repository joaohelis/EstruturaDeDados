package br.ufpb.ed.util;

import br.ufpb.ed.LinkedList.Node;

public interface Iterator<T> {
	
	boolean hasNext();
	Node<T> next();

}
