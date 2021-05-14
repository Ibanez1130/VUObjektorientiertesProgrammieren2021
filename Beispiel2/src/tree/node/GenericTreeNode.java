package tree.node;

import java.util.Collection;
import container.Container;
import util.searchable.ISearchFilter;

public class GenericTreeNode<NODETYPE> implements ITreeNode<NODETYPE> {
	private ITreeNode<NODETYPE> leftChild;
	private ITreeNode<NODETYPE> rightChild;
	private NODETYPE nodeValue;
	private String label;
	
	public GenericTreeNode(String label, NODETYPE value) {
		this.label = label;
		this.nodeValue = value;
	}
	
	public Collection<ITreeNode<NODETYPE>> getChildren() {
		// "children that are directly connected to the node" -> only left and right childs
		Collection<ITreeNode<NODETYPE>> c = new Container<ITreeNode<NODETYPE>>();
		if (this.getLeftChild() != null) {
			c.add(this.leftChild);
		}
		if (this.getRightChild() != null) {
			c.add(this.rightChild);
		}
		return c;
	}
	
	public ITreeNode<NODETYPE> getLeftChild() {
		return this.leftChild;
	}
	
	public void setLeftChild(ITreeNode<NODETYPE> leftChild) {
		this.leftChild = leftChild;
	}
	
	public ITreeNode<NODETYPE> getRightChild() {
		return this.rightChild;
	}
	
	public void setRightChild(ITreeNode<NODETYPE> rightChild) {
		this.rightChild = rightChild;
	}
	
	public boolean isLeaf() {
		return (this.getLeftChild() == null) && (this.getRightChild() == null);
	}
	
	public NODETYPE nodeValue() {
		return this.nodeValue;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public ITreeNode<NODETYPE> findNodeByValue(NODETYPE searchValue) {
		if (this.checkNodeByValue(searchValue)) {
			return this;
		} else if (!(this.isLeaf())) {
			if (this.getLeftChild() != null) {
				ITreeNode<NODETYPE> l = this.getLeftChild().findNodeByValue(searchValue);
				if (l != null) {
					return l;
				}
			}
			if (this.getRightChild() != null) {
				ITreeNode<NODETYPE> r = this.getRightChild().findNodeByValue(searchValue);
				if (r != null) {
					return r;
				}
			}
		} else {
			return null;
		}
		return null;
	}
	
	public ITreeNode<NODETYPE> findNodeByNode(ITreeNode<NODETYPE> searchNode) {
		if (this.equals(searchNode)) {
			return this;
		} else if (!(this.isLeaf())) {
			if (this.getLeftChild() != null) {
				ITreeNode<NODETYPE> l = this.getLeftChild().findNodeByNode(searchNode);
				if (l != null) {
					return l;
				}
			}
			if (this.getRightChild() != null) {
				ITreeNode<NODETYPE> r = this.getRightChild().findNodeByNode(searchNode);
				if (r != null) {
					return r;
				}
			}
		} else {
			return null;
		}
		return null;
	}
	
	public boolean checkNodeByValue(NODETYPE value) {
		if (!(this.nodeValue() != null && value != null)) return false;
		return this.nodeValue().equals(value);
	}
	
	public ITreeNode<NODETYPE> insertNode(ITreeNode<NODETYPE> treeNode) {
		if (!(this.isLeaf())) {
			if (this.getLabel().compareTo(treeNode.getLabel()) >= 0) {
				// label of tree node is smaller than this
				if (this.getLeftChild() != null) {
					this.getLeftChild().insertNode(treeNode);
				} else {
					this.leftChild = treeNode;
				}
			} else {
				// label of tree node is bigger than this
				if (this.getRightChild() != null) {
					this.getRightChild().insertNode(treeNode);
				} else {
					this.rightChild = treeNode;
				}
			}
		} else {
			this.leftChild = treeNode;
		}
		return treeNode;
	}
	
	public String generateConsoleView(String spacer, String preamble) {
		String s = preamble + ((this.isLeaf()) ? "- " : "+ ") + this.toString() + "\n";
		if (this.isLeaf()) {
			return s;
		} else {
			if (this.getLeftChild() != null) {
				s += this.getLeftChild().generateConsoleView(spacer, spacer + preamble);
			}
			if (this.getRightChild() != null) {
				s += this.getRightChild().generateConsoleView(spacer, spacer + preamble);
			}
		}
		return s;
	}
	
	public String toString() {
		return "GenericTreeNode [ nodeValue: " + this.nodeValue + ", label: " + this.label + " ]";
	}
	
	public GenericTreeNode<NODETYPE> deepCopy() {
		GenericTreeNode<NODETYPE> t = new GenericTreeNode<NODETYPE>(new String(this.getLabel()), this.nodeValue());
		if (this.getLeftChild() != null) {
			t.setLeftChild(this.getLeftChild().deepCopy());
		}
		if (this.getRightChild() != null) {
			t.setRightChild(this.getRightChild().deepCopy());
		}
		return t;
	}
	
	public Collection<ITreeNode<NODETYPE>> searchByFilter(ISearchFilter filter, Object compareObject) {
		Collection<ITreeNode<NODETYPE>> c = new Container<ITreeNode<NODETYPE>>();
		if (filter == null) return c;
		if (filter.searchFilterFunction(this, compareObject)) {
			c.add(this);
		} else if (this.nodeValue != null && filter.searchFilterFunction(this.nodeValue(), compareObject)) {
			c.add(this);
		}
		if (this.getLeftChild() != null) {
			c.addAll(this.getLeftChild().searchByFilter(filter, compareObject));
		}
		if (this.getRightChild() != null) {
			c.addAll(this.getRightChild().searchByFilter(filter, compareObject));
		}
		return c;
	}
}
