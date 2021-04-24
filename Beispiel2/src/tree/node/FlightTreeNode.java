package tree.node;

import rbs.flight.IFlight;
import rbs.flight.DualFlight;

public class FlightTreeNode extends GenericTreeNode<IFlight> {
	public FlightTreeNode(String label, IFlight value) {
		super(label, value);
		this.initialize(value);
	}
	
	private FlightTreeNode(DualFlight value) {
		super((value != null) ? value.getFlightId() : "", value);
	}
	
	public FlightTreeNode(IFlight value) {
		this((value != null) ? value.getFlightId() : "", value);
	}
	
	private void initialize(IFlight item) {
		if (item instanceof DualFlight) {
			((DualFlight) item).getFlights()
				.stream()
				.forEach(f -> this.insertNode(new FlightTreeNode(f.getFlightId(), f)));
		}
	}
	
	public ITreeNode<IFlight> insertNode(ITreeNode<IFlight> treeNode) {
		// since we write the flightId into the label, this doesn't change anything in the implementation
		return super.insertNode(treeNode);
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
