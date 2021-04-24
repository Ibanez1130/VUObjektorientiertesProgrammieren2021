package tree.node;

import rbs.copy.IDeepCopy;
import util.searchable.ISearchableByFilter;
import java.util.Collection;

public interface ITreeNode<NODETYPE> extends IDeepCopy, ISearchableByFilter<ITreeNode<NODETYPE>> {
	public boolean checkNodeByValue(NODETYPE value);
	public ITreeNode<NODETYPE> deepCopy();
	public ITreeNode<NODETYPE> findNodeByNode(ITreeNode<NODETYPE> searchNode);
	public ITreeNode<NODETYPE> findNodeByValue(NODETYPE searchValue);
	public String generateConsoleView(String spacer, String preamble);
	public Collection<ITreeNode<NODETYPE>> getChildren();
	public String getLabel();
	public ITreeNode<NODETYPE> getLeftChild();
	public ITreeNode<NODETYPE> getRightChild();
	public ITreeNode<NODETYPE> insertNode(ITreeNode<NODETYPE> treeNode);
	public boolean isLeaf();
	public NODETYPE nodeValue();
	public void setLeftChild(ITreeNode<NODETYPE> leftChild);
	public void setRightChild(ITreeNode<NODETYPE> rightChild);
}
