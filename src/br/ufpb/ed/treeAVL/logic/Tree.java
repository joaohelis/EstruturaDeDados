package br.ufpb.ed.treeAVL.logic;

public interface Tree<T> {
	
	int size();
	
	boolean isEmpty();
	
	Iterator<NodeTree<T>> iterator();
	
	NodeTree<T> parent(NodeTree<T> v)
			throws InvalidPositionException, BoundaryViolationException;
	
	Iterable<NodeTree<T>> children(NodeTree<T> v)
		throws InvalidPositionException;
	
	boolean isInternal(NodeTree<T> v)
			throws InvalidPositionException;
	
	boolean isExternal(NodeTree<T> v)
			throws InvalidPositionException;
	
	boolean isRoot(NodeTree<T> v)
			throws InvalidPositionException;	

}
