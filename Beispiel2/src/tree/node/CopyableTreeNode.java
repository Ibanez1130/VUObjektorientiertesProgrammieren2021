package tree.node;

import rbs.copy.IDeepCopy;

public class CopyableTreeNode<NODETYPE extends IDeepCopy> extends GenericTreeNode<NODETYPE> {
	public CopyableTreeNode(String label, NODETYPE value) {
		super(label, value);
	}
	
	public CopyableTreeNode<NODETYPE> deepCopy() {
		if (!(super.nodeValue() instanceof IDeepCopy)) throw new ClassCastException();
		CopyableTreeNode<NODETYPE> t = new CopyableTreeNode<NODETYPE>(new String(super.getLabel()), (NODETYPE) ((IDeepCopy) super.nodeValue()).deepCopy());
		if (super.getLeftChild() != null) {
			t.setLeftChild(super.getLeftChild().deepCopy());
		}
		if (super.getRightChild() != null) {
			t.setRightChild(super.getRightChild().deepCopy());
		}
		return t;
	}
}
