package br.ufpb.ed.treeAVL.logic;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeIterator<T> implements Iterator<T> {
	
	private int position;
	private List<BTNode<T>> linkedBinaryTree;
	
	public BinaryTreeIterator(BTNode<T> root){		
		this.linkedBinaryTree = new ArrayList<BTNode<T>>();
		this.position = -1;		
		inOrder(root, linkedBinaryTree);
	}

	private void inOrder(BTNode<T> v, List<BTNode<T>> l){
//		String aux = (v == null)? "null": v.getElement()+"";
//		System.out.println(aux);
		if (v != null){
			if (LinkedBinaryTree.hasLeft(v))
				inOrder(v.getLeft(), l);
			l.add(v);
			if (LinkedBinaryTree.hasRigth(v))
				inOrder(v.getRigth(), l);
		}
	}
	
	@Override
	public T next() {
		if (!hasNext())
			throw new RuntimeException("No next exist");
		++position;
		return (T) linkedBinaryTree.get(position);
	}

	@Override
	public boolean hasNext() {
		return (position < this.linkedBinaryTree.size()-1 && this.linkedBinaryTree.size() != 0);
	}

}

//package br.ufpb.ed.treeAVL.logic;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BinaryTreeIterator<T extends BTNode<T>> implements Iterator<T> {
//	
//	private int position;
//	private List<T> linkedBinaryTree;
//	
//	public BinaryTreeIterator(T tree){		
//		this.linkedBinaryTree = new ArrayList<T>();
//		this.position = -1;		
//		inOrder(tree, linkedBinaryTree);
//	}
//	
//	private void inOrder(T v, List<T> l){
////		String aux = (v == null)? "null": v.getElement()+"";
////		System.out.println(aux);
//		if (v != null){
//			if (LinkedBinaryTree.hasLeft(v))
//				inOrder((T) v.getLeft(), l);
//			l.add(v);
//			if (LinkedBinaryTree.hasRigth((NodeTree<?>) v))
//				inOrder((T)v.getRigth(), l);
//		}
//	}
//	
//	@Override
//	public T next() {
//		if (!hasNext())
//			throw new RuntimeException("No next exist");
//		++position;
//		return (T) linkedBinaryTree.get(position);
//	}
//
//	@Override
//	public boolean hasNext() {
//		return (position < this.linkedBinaryTree.size()-1 && this.linkedBinaryTree.size() != 0);
//	}
//
//}
