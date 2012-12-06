
package br.ufpb.ed.LinkedList;

import br.ufpb.ed.util.Iterator;

public class LinkedList<T extends Float>{
	
	private Node<T> head;
	private int size;
	
	public LinkedList(){
		this.head = null;
		this.size = 0;
	}
	
	public void addSorting(T n){
		Node<T> e = head;
		if(isEmpty() || n <= e.getValue())
			this.head = new Node<T>(n, head);
		else{
			while(e.getProx() != null && e.getProx().getValue() < n)
				e = e.getProx();
			e.setProx(new Node<T>(n, e.getProx()));
		}
		++size;
	}
	
	public void addPosition(T e, int index){
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		if(this.head == null || index == 0)
			this.head = new Node<T>(e, head);
		else{
			Node<T> aux = this.getNode(index -1); 
			aux.setProx(new Node<T>(e, aux.getProx()));
		}
		++size;
	}
	
	public void addBeginning(T e){
		this.addPosition(e, 0);
	}
	
	public void addEnd(T e){
		this.addPosition(e, size);
	}
	
	public T remove(int index){
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		Node<T> aux = (index == 0)? this.head: getNode(index-1);
		T removeAux = ((index == 0)? aux.getValue(): aux.getProx().getValue());
		if(index == 0)
			this.head = this.head.getProx();
		else if(index == size -1)
			aux.setProx(null);
		else
			aux.setProx(aux.getProx().getProx());
		--this.size;
		return removeAux;
	}
	
	public T removeBeginning(){
		return this.remove(0);
	}
	
	public T removeEnd(){
		return this.remove(size-1);
	}
	
	private Node<T> getNode(int index){
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		Node<T> aux = head;
		for(int i = 0; i < index; i++)
			aux = aux.getProx();
		return aux;
	}
	
	public T get(int index){
		return getNode(index).getValue();
	}
	
	public boolean conteins(int e){
		Node<T> aux = this.head;
		while(aux.getProx() != null){
			if (aux.getValue() == e)
				return true;
			aux = aux.getProx();
		}
		return false;	
	}
	
	public int size(){
		return this.size;
	}
	
	public boolean isEmpty(){
		return (this.size == 0)?true: false;
	}
	
	
	public Iterator<T> iterator(){
		return new LinkedListIterator<T>(head);
	}
	
	public String toString(){
		String aux = "";
		Iterator<T> it = iterator();
		while(it.hasNext())
			aux += it.next();
		if(!isEmpty())
			aux = aux.substring(0, size-1);
		return aux;
	}
	
}
