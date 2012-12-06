package br.ufpb.ed.treeAVL.logic;

public interface NodeTree<T> {
	
	void setElement(T element);
	T getElement();
	
	void setParent(NodeTree<T> parent);
	NodeTree<T> getParent();
	
	void setHeigth(int heigth);
	int getHeigth();

}
