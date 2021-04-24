package tree;

import tree.node.ITreeNode;

public class GenericTree<TREETYPE> implements ITree<TREETYPE> {
	protected ITreeNode<TREETYPE> root;

	public GenericTree() {
		this.root = null;
	}
	
	public GenericTree(ITreeNode<TREETYPE> root) {
		this.root = root;
	}
	
	public void setRoot(ITreeNode<TREETYPE> root) {
		this.root = root;
	}
	
	public ITreeNode<TREETYPE> getRoot() {
		return this.root;
	}
	
	public ITreeNode<TREETYPE> findNode(TREETYPE searchValue) {
		
	}
}
