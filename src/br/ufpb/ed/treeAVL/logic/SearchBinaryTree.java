package br.ufpb.ed.treeAVL.logic;

public class SearchBinaryTree<T extends Integer> extends LinkedBinaryTree<T>{
	
	public void insert(T key){
		this.root = insert((NodeBinaryTree<T>)root, key, null);
		updateHeigthAndDeff((NodeBinaryTree<?>) root); 
		balance((NodeBinaryTree<T>) root);
		updateHeigthAndDeff((NodeBinaryTree<?>) root); // Linha nao necessaria - usada apenas para testes na interface grafica
		++size;
	}
	
	private NodeBinaryTree<T> insert(NodeBinaryTree<T> tree, T key, NodeBinaryTree<T> parent) {
		if (tree == null)
			tree = new NodeBinaryTree<T>(key, null, null, parent); 
		else			
			if(key < tree.getElement())	
				tree.setLeft(insert((NodeBinaryTree<T>) tree.getLeft(), key, tree));
			else
				tree.setRigth(insert((NodeBinaryTree<T>)tree.getRigth(), key, tree));		
		return tree; 
	}
	
	public void remove(T key)throws ElementNotFoundException{
		if(find((NodeBinaryTree<T>)root, key)== null)
			throw new ElementNotFoundException("Element not found!");
		root = remove((NodeBinaryTree<T>)root, key);
		updateHeigthAndDeff((NodeBinaryTree<?>) root); 
		balance((NodeBinaryTree<T>) root);
		updateHeigthAndDeff((NodeBinaryTree<?>) root); // Linha nao necessaria - usada apenas para testes na interface grafica
		--size;                                      
		
	}
		
	private NodeBinaryTree<T> remove(NodeBinaryTree<T> tree, T key) {
		NodeBinaryTree<T> temp = find(tree, key);
		if(temp != null)			
			if(!hasLeft(temp) && !hasRigth(temp)) // No sem filhos
				if (isRoot(temp))					
					tree = null; 
				else if (temp.getElement() < temp.getParent().getElement())
					temp.getParent().setLeft(null);
				else
					temp.getParent().setRigth(null);
			else if(hasLeft(temp) && hasRigth(temp)){ // No com dois filhos				
				NodeBinaryTree<T> aux = (NodeBinaryTree<T>) temp.getRigth();
				while(hasLeft(aux))
					aux = (NodeBinaryTree<T>)aux.getLeft();
				temp.setElement((T) aux.getElement());
				aux.setElement(key);
				temp.getRigth().setParent(null);				
				temp.setRigth(remove((NodeBinaryTree<T>) temp.getRigth(), key));
			}else{                                              // No com filho a esquerda ou a direita
				NodeBinaryTree<T> aux = (NodeBinaryTree<T>) ((hasLeft(temp))? temp.getLeft(): temp.getRigth());				
				aux.setParent(temp.getParent());
				if(isRoot(temp))
					tree = aux; 
				else
					if (temp.getElement() < temp.getParent().getElement())				
						temp.getParent().setLeft(aux);
					else
						temp.getParent().setRigth(aux);
			}			
		return tree;
	}

	private NodeBinaryTree<T> find(NodeBinaryTree<T> tree, T key) {
		if (tree != null && tree.getElement() != key)
			if (key < tree.getElement())
				return find((NodeBinaryTree<T>)tree.getLeft(), key);
			else
				return find((NodeBinaryTree<T>)tree.getRigth(), key);
		return tree;
	}
	
	public NodeBinaryTree<T> find(T key){
		return find((NodeBinaryTree<T>) root, key);
	}
	
	public String toString(){
		String aux = "";
		Iterator<NodeTree<T>> it = iterator();
		while(it.hasNext()){
			BTNode<T> temp = (BTNode<T>)it.next();
			String left = null, rigth = null, parent = null;
            if(temp != null){
	            left = (temp.getLeft() == null)? "null" : Integer.toString(temp.getLeft().getElement());
	            rigth = (temp.getRigth() == null)? "null" : Integer.toString(temp.getRigth().getElement());
	            parent = (temp.getParent() == null)? "null" : Integer.toString(temp.getParent().getElement());
            }            
            aux += " BTNode [Element: "+temp.getElement() +
            	   ", Left: "+left +
			       ", Rigth: "+rigth +
			       ", Parent: "+ parent +
			       ", Diff: " + temp.getDiff() +
			       ", heigth: " + temp.getHeigth() +
			       "]\n";
		}
		return aux;
	}
	
	public Iterator<NodeTree<T>> iterator() {		
		return new BinaryTreeIterator(root);
	}
	
	public static int updateHeigthAndDeff(NodeBinaryTree<?> v) {
		if (v == null)
			return -1;
		int hRigth = updateHeigthAndDeff((NodeBinaryTree<?>) v.getRigth()),
			hLeft = updateHeigthAndDeff((NodeBinaryTree<?>) v.getLeft());
		v.setDiff(hRigth - hLeft);
		int h = Math.max(hRigth ,hLeft) + 1;
		v.setHeigth(h);
		return h;  
	}
	
//	Rota��o � Direita
//	Seja Y o filho � esquerda de X
//	Torne X o filho � direita de Y
//	Torne o filho � direita de Y o filho � esquerda de X.
	
	private void rotationRigth(NodeBinaryTree<T> x){
		NodeBinaryTree<T> y = (NodeBinaryTree<T>) x.getLeft(),
			      childrenRigthY = (NodeBinaryTree<T>) y.getRigth();
		if (isRoot(x))root = y;
		else if (((NodeBinaryTree<T>)x.getParent()).getLeft() == x)
			((NodeBinaryTree<T>)x.getParent()).setLeft(y);
		else
			((NodeBinaryTree<T>)x.getParent()).setRigth(y);	
		y.setParent(x.getParent());
		x.setParent(y);
		y.setRigth(x);
		if (childrenRigthY != null)childrenRigthY.setParent(x);
		x.setLeft(childrenRigthY);		
	}

//	Rota��o � Esquerda
//	Seja Y o filho � direita de X
//	Torne X filho � esquerda de Y
//	Torne o filho � esquerda de Y o filho � direita de X.
	
	private  void rotationLeft(NodeBinaryTree<T> x){					
		NodeBinaryTree<T> y = (NodeBinaryTree<T>) x.getRigth(),
				      childrenLeftY = (NodeBinaryTree<T>) y.getLeft();
		if (isRoot(x))root = y;//.setLeft(y);
		else if (((NodeBinaryTree<T>)x.getParent()).getLeft() == x)
			((NodeBinaryTree<T>)x.getParent()).setLeft(y);
		else
			((NodeBinaryTree<T>)x.getParent()).setRigth(y);						
		y.setParent(x.getParent());
		x.setParent(y);
		y.setLeft(x);
		if (childrenLeftY != null)childrenLeftY.setParent(x);
		x.setRigth(childrenLeftY);		
	}
	
	private void doubleRotationLeft(NodeBinaryTree<T> x){
		rotationRigth((NodeBinaryTree<T>) x.getRigth());
		rotationLeft(x);		
	}
	
	private void doubleRotationRigth(NodeBinaryTree<T> x){
		rotationLeft((NodeBinaryTree<T>) x.getLeft());
		rotationRigth(x);		
	}
	
	private void balance(NodeBinaryTree<T> x){
		if(x != null){			
			balance((NodeBinaryTree<T>) x.getLeft());
			balance((NodeBinaryTree<T>) x.getRigth());
			switch(x.getDiff()){
			case 2:				
				if (x.getRigth().getDiff() >= 0)
					rotationLeft(x);									
				else
					doubleRotationLeft(x);
				break;
			case -2:
				if(x.getLeft().getDiff() <= 0)
					rotationRigth(x);
				else
					doubleRotationRigth(x);
				break;
			} 
		}
	}
	
}
