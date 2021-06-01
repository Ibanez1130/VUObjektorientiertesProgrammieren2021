package aircontrol.terminal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import rbs.flight.IFlight;
import aircontrol.airline.IAirlineListener;

public class Terminal implements ITerminal, IAirlineListener {
	private long id;
	private Collection<IFlight> flights = new ArrayList<>();
	private Collection<String> logentries = new ArrayList<>();

	public Terminal(long id) {
		this.id = id;
	}

	public void flightAdded(IFlight flight) {
		if (this.flights.add(flight)) {
			this.logentries.add("A flight has been added. flight: " + flight.toString());
		}
	}

	public void flightRemoved(IFlight flight) {
		if (this.flights.remove(flight)) {
			this.logentries.add("A flight has been removed. flight: " + flight.toString());
		}
	}
	
	public void notifyChange(IFlight flight) {
		for (IFlight f : this.flights) {
			if (f.equals(flight)) {
				f.updatePrice(flight.getPrice());
				this.logentries.add("A flight has been updated. flight: " + f.toString());
			}
		}
	}
	
	public void outputLogEntries() {
		int i = 0;
		System.out.println("--- Output log entries for terminal with id " + this.id + " ---\n");
		for (String l : this.logentries) {
			i++;
			System.out.println(i + ". " + l + "\n");
		}
	}
	
	public String toString() {
		String flights = this.flights.stream().map(f -> f.toString()).collect(Collectors.joining(", "));
		String logs = this.logentries.stream().collect(Collectors.joining(", "));
		return "Terminal [ id = " + this.id + ", flights = [ " + flights + " ], logentries = [ " + logs + " ] ]";
	}
}
