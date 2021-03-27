package rbs.flight;

public class OneWayFlight extends Flight {
	
	public OneWayFlight (String id, String departure, String destination) {
		super(id, departure, destination);
	}
	
	public OneWayFlight (String id, String departure, String destination, float price) {
		super(id, departure, destination, price);
	}
	
	public boolean equals(Object obj) {
		if (!(obj instanceof OneWayFlight)) return false;
		OneWayFlight o = (OneWayFlight) obj;
		return ((this.getFlightId() == o.getFlightId()) && (this.getDeparture() == o.getDeparture()) && (this.getDestination() == o.getDestination()));
	}
	
	public OneWayFlight deepCopy() {
		return new OneWayFlight(this.getFlightId(), this.getDeparture(), this.getDestination(), this.getPrice());
	}
}
