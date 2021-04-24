package tree;

import rbs.copy.IDeepCopy;
import util.searchable.ISearchableByFilter;
import tree.node.ITreeNode;

public interface ITree<TREETYPE> extends IDeepCopy, ISearchableByFilter<ITreeNode<TREETYPE>> {
	public ITree<TREETYPE> deepCopy();
	public ITreeNode<TREETYPE> findNode(ITreeNode<TREETYPE> searchNode);
	public ITreeNode<TREETYPE> findNode(TREETYPE searchValue);
	public String generateConsoleView(String spacer);
	public ITreeNode<TREETYPE> getRoot();
	public ITreeNode<TREETYPE> insertNode(ITreeNode<TREETYPE> treeNode);
	public void setRoot(ITreeNode<TREETYPE> root);
}
