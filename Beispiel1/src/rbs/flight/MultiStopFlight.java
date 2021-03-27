package rbs.flight;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class MultiStopFlight extends Flight {
	private List<IFlight> flights = new ArrayList<IFlight>();
	
	public MultiStopFlight(String id, IFlight flight) {
		super(id);
		this.flights.add(flight.deepCopy());
	}
	
	public MultiStopFlight(String id, List<IFlight> flights) throws FlightsNotConnectedException {
		super(id);
		if (flights.size() > 0) {
			IFlight prev = null;
			for (IFlight f : flights) {
				if ((prev != null) && (!(prev.getDestination().equals(f.getDeparture())))) {
					throw new FlightsNotConnectedException(flights);
				}
				this.flights.add(f.deepCopy());
				prev = f;
			}
			super.departure = flights.get(0).getDeparture();
			super.destination = prev.getDestination();
		}
	}
	
	public float getPrice() {
		float sum = 0.0f;
		for (IFlight f : this.flights) {
			sum += f.getPrice();
		}
		return sum;
	}
	
	public List<IFlight> getFlights() {
		return this.flights;
	}

	public void addFlight(IFlight flight) throws FlightsNotConnectedException {
		if (super.destination.equals(flight.getDeparture())) {
			super.destination = flight.getDestination();
		} else {
			throw new FlightsNotConnectedException(this.flights);
		}
	}
	
	public boolean equals(Object obj) {
		if (!(obj instanceof MultiStopFlight)) return false;
		MultiStopFlight r = (MultiStopFlight) obj;
		return this.toString().equals(r.toString());
	}
	
	public String toString() {
		String flightsString = this.flights
				.stream()
				.map(f -> {
					return "Flight [ id = " + f.getFlightId() + ", departure = " + f.getDeparture() + ", destination = " + f.getDestination() + ", price = " + f.getPrice() + " ]";
				})
				.collect(Collectors.joining(", "));
		return "MultiStopFlight [ id = " + super.getFlightId() + ", flights = [ " + flightsString + " ] ]";
	}
	
	public MultiStopFlight deepCopy() {
		try {
			return new MultiStopFlight(this.getFlightId(), this.getFlights());
		} catch (FlightsNotConnectedException e) {
			return null;
		}
	}
	
}
