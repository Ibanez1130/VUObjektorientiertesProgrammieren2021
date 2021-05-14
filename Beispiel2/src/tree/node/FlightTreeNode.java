package tree.node;

import rbs.flight.IFlight;
import rbs.flight.DualFlight;

public class FlightTreeNode extends GenericTreeNode<IFlight> {
	public FlightTreeNode(String label, IFlight value) {
		super(label, value);
		this.initialize(value);
	}
	
	private FlightTreeNode(DualFlight value) {
		super(value.getFlightId(), value);
	}
	
	public FlightTreeNode(IFlight value) {
		super(value.getFlightId(), value);
		this.initialize(value);
	}
	
	private void initialize(IFlight item) {
		if (item instanceof DualFlight) {
			((DualFlight) item).getFlights()
				.stream()
				.forEach(f -> this.insertNode(new FlightTreeNode(f.getFlightId(), f)));
		}
	}
	
	public ITreeNode<IFlight> insertNode(ITreeNode<IFlight> treeNode) {
		if(this.nodeValue().getFlightId().compareTo(treeNode.nodeValue().getFlightId()) > 1) {
			if(this.getLeftChild() != null) {
				return this.getLeftChild().insertNode(treeNode);
			}
			else {
				this.setLeftChild(treeNode);
			}
		}
		else {
			if(this.getRightChild() != null) {
				return this.getRightChild().insertNode(treeNode);
			}
			else {
				this.setRightChild(treeNode);
			}
		}
		return treeNode;
	}
	
	public FlightTreeNode deepCopy() {
		FlightTreeNode t = new FlightTreeNode(super.getLabel(), (IFlight) super.nodeValue().deepCopy());
		if (super.getLeftChild() != null) {
			t.setLeftChild(super.getLeftChild().deepCopy());
		}
		if (super.getRightChild() != null) {
			t.setRightChild(super.getRightChild().deepCopy());
		}
		return t;
	}
}
