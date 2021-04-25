package tree;

import tree.node.ITreeNode;
import tree.node.FlightTreeNode;
import rbs.flight.IFlight;

public class FlightTree extends GenericTree<IFlight> {
	public FlightTree(ITreeNode<IFlight> root) {
		super(root);
	}
	
	public FlightTree(IFlight flight) {
		super(new FlightTreeNode(flight));
	}
	
	public FlightTree() {
		super();
	}

}
