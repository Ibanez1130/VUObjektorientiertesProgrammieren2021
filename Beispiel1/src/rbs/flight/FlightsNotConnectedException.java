package rbs.flight;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FlightsNotConnectedException extends Exception {
	private List<IFlight> flights = new ArrayList<IFlight>();
	public static final long serialVersionUID = 01;

	public FlightsNotConnectedException(List<IFlight> flights) {
		flights
			.stream()
			.forEach(f -> this.flights.add(f.deepCopy()));
	}
	
	public String getMessage() {
		String flightsString = this.flights
					.stream()
					.map(f -> f.getFlightId())
					.collect(Collectors.joining(", "));
		return "Die folgenden Flüge sind nicht miteinander verbunden: " + flightsString;
	}

}
