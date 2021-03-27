package rbs.flight;

import java.util.List;
import java.util.ArrayList;

public class MultiStopFlight extends Flight {
	private List<IFlight> flights = new ArrayList<IFlight>();
	
	public MultiStopFlight(String id, IFlight flight) {
		super(id);
		this.flights.add(flight.deepCopy());
	}
	
	public MultiStopFlight(String id, List<IFlight> flights) throws FlightsNotConnectedException {
		super(id);
		int i = 0;
		for (; i < flights.size() - 2; i++) {
			if (flights.get(i).getDestination() != flights.get(i + 1).getDeparture()) {
				throw new FlightsNotConnectedException(flights);
			} else {
				this.flights.add(flights.get(i).deepCopy());
			}
		}
		super.destination = flights.get(i + 1).getDestination();
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

	public void addFlight() throws FlightsNotConnectedException {
		
	}
	
}
