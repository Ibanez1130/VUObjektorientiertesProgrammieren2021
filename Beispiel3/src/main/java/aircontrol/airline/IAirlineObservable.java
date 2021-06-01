package aircontrol.airline;

import rbs.flight.IFlight;
import aircontrol.filter.INotificationFilter;

public interface IAirlineObservable extends IObservable<IFlight> {
	public void addNotificationFilter(IAirlineListener listener, INotificationFilter<IFlight> filter);
}
