package rbs.flight;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class RoundTripFlight extends Flight {
	private List<IFlight> flights = new ArrayList<IFlight>();
	
	public RoundTripFlight(String id, List<IFlight> flights) {
		super(id);
		if ((flights.size() == 2) && (flights.get(0).getDeparture() == flights.get(1).getDestination())) {
			flights
				.stream()
				.forEach(f -> this.flights.add(f.deepCopy()));
		}
		if (flights.size() > 0) {
			super.departure = flights.get(0).getDeparture();
			super.destination = flights.get(0).getDestination();
		}
	}
	
	public float getPrice() {
		return super.getPrice();
	}
	
	public List<IFlight> getFlights() {
		return this.flights;
	}
	
	public boolean equals(Object obj) {
		if (!(obj instanceof RoundTripFlight)) return false;
		RoundTripFlight r = (RoundTripFlight) obj;
		return (this.toString() == r.toString());
	}
	
	public String toString() {
		String flightsString = this.flights
				.stream()
				.map(f -> {
					return "Flight [ id = " + f.getFlightId() + ", departure = " + f.getDeparture() + ", destination = " + f.getDestination() + ", price = " + f.getPrice() + " ]";
				})
				.collect(Collectors.joining(", "));
		return "RoundTripFlight  [ id = " + super.getFlightId() + ", flights = [ " + flightsString + " ] ]";
	}
	
	public RoundTripFlight deepCopy() {
		return new RoundTripFlight(this.getFlightId(), this.getFlights());
	}

}
