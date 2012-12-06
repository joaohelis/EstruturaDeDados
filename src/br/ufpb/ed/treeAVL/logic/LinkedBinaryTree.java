package br.ufpb.ed.treeAVL.logic;

public abstract class LinkedBinaryTree<T> implements BinaryTree<T>{
	
	protected BTNode<T> root;
	protected int size;
	
	public LinkedBinaryTree(){
		this.root = null;
		this.size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return (root == null);
	}


	@Override
	public NodeTree<T> parent(NodeTree<T> v) throws InvalidPositionException,
			BoundaryViolationException {
		BTNode<T> aux = (BTNode<T>) checkPosition(v);
		NodeTree<T> parentPos = aux.getParent();
		if (parentPos == null)
			throw new BoundaryViolationException("No parent");
		return parentPos;
	}

	@Override
	public Iterable<NodeTree<T>> children(NodeTree<T> v)
			throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInternal(NodeTree<T> v) throws InvalidPositionException {
		checkPosition(v);
		return (hasRigth(v)|| hasLeft(v));
	}

	@Override
	public boolean isExternal(NodeTree<T> v) throws InvalidPositionException {
		checkPosition(v);
		return (!hasRigth(v) && !hasLeft(v));
	}

	@Override
	public boolean isRoot(NodeTree<T> v) throws InvalidPositionException {
		try {
			this.parent(v);
		} catch (BoundaryViolationException e) {
			return true;
		}
		return false;
	}

	@Override
	public NodeTree<T> left(NodeTree<T> v) throws InvalidPositionException,
			BoundaryViolationException {
		BTNode aux = checkPosition(v);
		if (!hasLeft(aux))
			throw new BoundaryViolationException("No left child");
		return aux.getLeft();
	}

	@Override
	public NodeTree<T> rigth(NodeTree<T> v) throws InvalidPositionException,
			BoundaryViolationException {
		BTNode aux = checkPosition(v);
		if (!hasRigth(aux))
			throw new BoundaryViolationException("No rigth child");
		return aux.getRigth();
	}

	
	public static boolean hasLeft(NodeTree<?> v) throws InvalidPositionException {
		BTNode<?> aux = checkPosition(v);
		return (aux.getLeft() != null);
	}
	
	public static boolean hasRigth(NodeTree<?> v) throws InvalidPositionException {
		BTNode<?> aux = checkPosition(v);
		return (aux.getRigth() != null);
	}
	
	protected static BTNode<?> checkPosition(NodeTree<?> v)
		throws InvalidPositionException{
		if (v == null || !(v instanceof BTNode))
			throw new InvalidPositionException("The position is invalid");
		return (BTNode<?>) v;
	}
	
	protected NodeBinaryTree<T> createNode(T element, BTNode<T> parent,
			BTNode<T> left, BTNode<T> rigth){
		return new NodeBinaryTree<T>(element, parent, left, rigth);
	}

}
