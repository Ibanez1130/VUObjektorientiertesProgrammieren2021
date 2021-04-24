package tree.node;

import util.FlightCategory;

public class CategoryTreeNode<NODETYPE, CATEGORY> extends GenericTreeNode<NODETYPE> {
	private CATEGORY category;
	public CategoryTreeNode(CATEGORY category) {
		// not 100% sure if this is how the author intended it to be implemented
		super((category instanceof FlightCategory) ? ((FlightCategory) category).getLabel() : category.toString(), null);
		this.category = category;
	}
	
	public CATEGORY getCategory() {
		return this.category;
	}
	
	public NODETYPE nodeValue() {
		return null;
	}
	
	public String getLabel() {
		return super.getLabel();
	}
	
	public CategoryTreeNode<NODETYPE, CATEGORY> deepCopy() {
		CategoryTreeNode<NODETYPE, CATEGORY> t = new CategoryTreeNode<NODETYPE, CATEGORY>(this.getCategory());
		if (super.getLeftChild() != null) {
			t.setLeftChild(super.getLeftChild().deepCopy());
		}
		if (super.getRightChild() != null) {
			t.setRightChild(super.getRightChild().deepCopy());
		}
		return t;
	}
}
