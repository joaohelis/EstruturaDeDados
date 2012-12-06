package br.ufpb.ed.treeAVL.logic;

public interface BTNode<T> extends NodeTree<T>{
	
	void setLeft(BTNode<T> left);
	BTNode<T> getLeft();
	
	void setRigth(BTNode<T> rigth);
	BTNode<T> getRigth();

	int getDiff();
	void setDiff(int diff);

}
