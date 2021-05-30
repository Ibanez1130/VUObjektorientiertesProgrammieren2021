package aircontrol.airline;

import java.util.Collection;
import rbs.flight.IFlight;

public interface IAirline {
	public boolean addFlight(IFlight flight);
	public boolean addFlights(Collection<IFlight> flights);
	public boolean removeFlight(IFlight flight);
	public boolean updateFlight(IFlight flight);
}
