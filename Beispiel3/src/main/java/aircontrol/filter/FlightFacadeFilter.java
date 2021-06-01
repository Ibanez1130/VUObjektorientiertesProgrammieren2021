package aircontrol.filter;

import rbs.flight.IFlight;

public class FlightFacadeFilter implements INotificationFilter<IFlight> {
	private DepartureNotificationFilter departureFilter;
	private DestinationNotificationFilter destinationFilter;
	private PriceNotificationFilter priceFilter;
	
	public FlightFacadeFilter(String departure, String destination, double priceLimit) {
		this.departureFilter = new DepartureNotificationFilter(departure);
		this.destinationFilter = new DestinationNotificationFilter(destination);
		this.priceFilter = new PriceNotificationFilter(priceLimit);
	}
	
	public boolean check(IFlight compareObject) {
		return this.departureFilter.check(compareObject) && this.destinationFilter.check(compareObject) && this.priceFilter.check(compareObject);
	}
}
