package aircontrol.airline;

import rbs.flight.IFlight;
import aircontrol.filter.INotificationFilter;

public interface IAirlineObservable {
	public void addNotificationFilter(IAirlineListener listener, INotificationFilter<IFlight> filter);
}
