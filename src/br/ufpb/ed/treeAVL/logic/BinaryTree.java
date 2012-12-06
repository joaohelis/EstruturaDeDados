package br.ufpb.ed.treeAVL.logic;

public interface BinaryTree<T> extends Tree<T> {
	
	NodeTree<T> left(NodeTree<T> v) 
			throws InvalidPositionException, BoundaryViolationException; 
	
	public NodeTree<T> rigth(NodeTree<T> v) 
			throws InvalidPositionException, BoundaryViolationException;
}
