package aircontrol.airline;

import rbs.flight.IFlight;

public interface IAirlineListener {
	public void flightAdded(IFlight flight);
	public void flightRemoved(IFlight flight);
	public void notifyChange(IFlight flight);
}
