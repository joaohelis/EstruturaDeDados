package br.ufpb.ed.treeAVL.logic;

public class NodeBinaryTree<E> implements BTNode<E>{
	
	private E element;
	private BTNode<E> left, rigth, parent;
	private int heigth, diff;
	
	public NodeBinaryTree(E element, BTNode<E> left, BTNode<E> rigth,
			BTNode<E> parent) {
		this.element = element;
		this.left = left;
		this.rigth = rigth;
		this.parent = parent;
	}

	public E getElement() {
		return element;
	}
	
	public void setElement(E element) {
		this.element = element;
	}
	
	public BTNode<E> getLeft() {
		return left;
	}
	
	public void setLeft(BTNode<E> left) {
		this.left = left;
	}
	
	public BTNode<E> getRigth() {
		return rigth;
	}
	
	public void setRigth(BTNode<E> rigth) {
		this.rigth = rigth;
	}
	
	public BTNode<E> getParent() {
		return parent;
	}
	
	public void setParent(NodeTree<E> parent) {
		this.parent = (BTNode<E>) parent;
	}
	
	public int getDiff() {
		return diff;
	}

	public void setDiff(int diff) {
		this.diff = diff;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	@Override
	public String toString() {
		String left = (this.left != null)? this.left.getElement().toString() :null;
		String rigth = (this.rigth != null)? this.rigth.getElement().toString() :null;
		String parent = (this.parent != null)? this.parent.getElement().toString() :null;
		return "BTNode [element=" + element + ", left=" + left + ", rigth="
				+ rigth + ", parent=" + parent + ", diff=" + diff + ", heigth="
				+ heigth + "]";
	}
}

