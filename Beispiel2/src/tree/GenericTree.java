package tree;

import tree.node.ITreeNode;
import java.util.Collection;
import util.searchable.ISearchFilter;
import container.Container;

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
		if (this.root == null) return null;
		return this.root.findNodeByValue(searchValue);
	}
	
	public ITreeNode<TREETYPE> findNode(ITreeNode<TREETYPE> searchNode) {
		if (this.root == null) return null;
		return this.root.findNodeByNode(searchNode);
	}
	
	public ITreeNode<TREETYPE> insertNode(ITreeNode<TREETYPE> treeNode) {
		if (this.root == null) {
			this.root = treeNode;
			return treeNode;
		}
		return this.root.insertNode(treeNode);
	}
	
	public String generateConsoleView(String spacer) {
		if (root == null) {
			return "GenericTree [ ]";
		}
		return "GenericTree [\n" + this.root.generateConsoleView(spacer, "  ") + "]";
	}
	
	public Collection<ITreeNode<TREETYPE>> searchByFilter(ISearchFilter filter, Object compareObject) {
		Collection<ITreeNode<TREETYPE>> c = new Container<ITreeNode<TREETYPE>>();
		if (this.root == null) return c;
		try {
			c.addAll(this.root.searchByFilter(filter, compareObject));
		} catch (NullPointerException ex) {
			System.out.println("A NullPointerException has been thrown while adding an element to the Container.");
		}
		return c;
	}
	
	public ITree<TREETYPE> deepCopy() {
		if (this.root == null) return new GenericTree<TREETYPE>();
		return new GenericTree<TREETYPE>(this.root.deepCopy());
	}
}
